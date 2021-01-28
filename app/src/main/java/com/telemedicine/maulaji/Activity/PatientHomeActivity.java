package com.telemedicine.maulaji.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.telemedicine.maulaji.Data.sharedPhotoListener;
import com.telemedicine.maulaji.Fragments.CallGpNowMyAppFragment;
import com.telemedicine.maulaji.Fragments.ChatFragment;
import com.telemedicine.maulaji.Fragments.HomeFragmentAlternativeMedX;
import com.telemedicine.maulaji.Fragments.ProfileFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.CitySelectedListener;
import com.telemedicine.maulaji.Utils.CustomDrawerButton;
import com.telemedicine.maulaji.Utils.LocationPermisionGaveListener;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.Utils.UtilLocation;
import com.telemedicine.maulaji.Utils.doForMe;
import com.telemedicine.maulaji.Utils.locatiionRetrivedListener;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.BasicInfoModel;
import com.telemedicine.maulaji.model.Data;
import com.telemedicine.maulaji.model.SpecialistNameCount;
import com.telemedicine.maulaji.service.NotificationReceivedListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;
import static com.telemedicine.maulaji.Data.Data.SESSION_MANAGER;
import static com.telemedicine.maulaji.Data.Data.bottom_nav;
import static com.telemedicine.maulaji.Data.Data.itemView;
import static com.telemedicine.maulaji.Data.Data.menuView;
import static com.telemedicine.maulaji.Data.Data.notificationBadge;
import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;
import static com.telemedicine.maulaji.Data.DataStore.USER_TYPE;

public class PatientHomeActivity extends BaseActivity implements ApiListener.basicInfoDownloadListener {
    Context context = this;
    Resources resources;
    int primaryClr, another;

    String country="0";



    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;

    TextView tv_4;
    int anotherColorText = Color.GRAY;

    @BindView(R.id.bottom)
    BottomNavigationView bottom;
    @BindView(R.id.vpPager)
    ViewPager vpPager;
    // LinearLayout.LayoutParams enable = new LinearLayout.LayoutParams(30, 30);
    //  LinearLayout.LayoutParams disbale = new LinearLayout.LayoutParams(25, 25);
    public static List<String> HOSPITALS = new ArrayList<>();
    public static List<SpecialistNameCount> SPECIALIST = new ArrayList<>();
    SessionManager sesMan;

    @BindView(R.id.customDrawer)
    CustomDrawerButton customDrawerButton;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.profilePic)
    CircleImageView profilePic;

     LocationManager locationManager;
     String provider;

     boolean didGotLocation = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        ButterKnife.bind(this);


        sesMan = new SessionManager(this);
        tv_address.setText("Choose your city");

        TOKEN = sesMan.getToken();
        USER_ID = sesMan.getUserId();
        SESSION_MANAGER = sesMan;

        USER_TYPE = "p";
        getColorManagement();

        FirebaseMessaging.getInstance().subscribeToTopic("p"+USER_ID);

       // Toast.makeText(context,"p"+USER_ID , Toast.LENGTH_SHORT).show();
        //FirebaseMessaging.getInstance().subscribeToTopic(USER_TYPE);

        //Api.getInstance().downloadBasicInfo(this);
        setUpStatusbar();

        customDrawerButton.setDrawerLayout(drawer_layout);
        customDrawerButton.getDrawerLayout().addDrawerListener(customDrawerButton);
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
        Glide.with(PatientHomeActivity.this).load(SESSION_MANAGER.get_userPhoto()).into(profilePic);
       // Toast.makeText(context, PHOTO_BASE + SESSION_MANAGER.get_userPhoto(), Toast.LENGTH_SHORT).show();
        //Glide.with(PatientHomeActivity.this).load("https://appointmentbd.com/frontend/user/1561548940.jpg").into(profilePic);
        tv_name.setText(SESSION_MANAGER.getUserName());
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PatientPersonalInfoActivity.class));
            }
        });
        setupViewPager(vpPager);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    bottom.getMenu().getItem(0).setChecked(true);
                    bottom.getMenu().getItem(1).setChecked(false);
                    bottom.getMenu().getItem(2).setChecked(false);
                   bottom.getMenu().getItem(3).setChecked(false);
                    /*     bottom.getMenu().getItem(4).setChecked(false);

                 */

                } else if (position == 1) {

                    bottom.getMenu().getItem(0).setChecked(false);
                    bottom.getMenu().getItem(1).setChecked(true);
                    bottom.getMenu().getItem(2).setChecked(false);
                    bottom.getMenu().getItem(3).setChecked(false);
                    /*     bottom.getMenu().getItem(4).setChecked(false);

                */

                }


                else if (position == 2) {
                    bottom.getMenu().getItem(0).setChecked(false);
                    bottom.getMenu().getItem(1).setChecked(false);
                    bottom.getMenu().getItem(2).setChecked(true);
                     bottom.getMenu().getItem(3).setChecked(false);
                    /*     bottom.getMenu().getItem(4).setChecked(false);

                */

                }

                else if (position == 3) {
                    bottom.getMenu().getItem(0).setChecked(false);
                    bottom.getMenu().getItem(1).setChecked(false);
                    bottom.getMenu().getItem(2).setChecked(false);
                    bottom.getMenu().getItem(3).setChecked(true);
                 //   bottom.getMenu().getItem(4).setChecked(false);

                }
                /*
                else if (position == 4) {
                    bottom.getMenu().getItem(0).setChecked(false);
                    bottom.getMenu().getItem(1).setChecked(false);
                    bottom.getMenu().getItem(2).setChecked(false);
                    bottom.getMenu().getItem(3).setChecked(false);
                    bottom.getMenu().getItem(4).setChecked(true);

                }

                */
                bottom.getMenu().getItem(position).setChecked(true);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home: {

                        vpPager.setCurrentItem(0);

                        return true;
                    }
                    case R.id.notification: {

                        vpPager.setCurrentItem(1);
                        return true;
                    }
                    case R.id.chat: {
                        vpPager.setCurrentItem(2);
                        return true;
                    }
                    case R.id.profile: {
                        vpPager.setCurrentItem(3);
                        return true;
                    }



                   /*
                    case R.id.blog: {
                        vpPager.setCurrentItem(4);
                        return true;
                    }

                 */
                }
                return false;
            }
        });
        // addBadgeView();
        bottom_nav = bottom;
        menuView = (BottomNavigationMenuView) bottom_nav.getChildAt(0);
        itemView = (BottomNavigationItemView) menuView.getChildAt(1);
        notificationBadge = LayoutInflater.from(context).inflate(R.layout.noti_badge, menuView, false);
        NotificationReceivedListener.setMyPicUploadListener(new NotificationReceivedListener.NotiReceivedListener() {
            @Override
            public String onSucced(Data data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("mkln","call received opening intent");
                     //   startActivity(new Intent(context,IcommingCallJitsi.class));

                        Intent intent = new Intent(getApplicationContext(),FullScreenCallIncommingActivity.class);
                        intent.putExtra("img",data.getExtraData());
                        intent.putExtra("name",data.getTitle());
                        intent.putExtra("room",data.getRoom());

                        startActivity(intent);






                    }
                });
                return null;
            }
        });
        // Initialize default options for Jitsi Meet conferences.
        URL serverURL;
        try {
            // When using JaaS, replace "https://meet.jit.si" with the proper serverURL
            serverURL = new URL("https://simra.org");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid server URL!");
        }


        JitsiMeetConferenceOptions defaultOptions
                = new JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)

                // When using JaaS, set the obtained JWT here
                //.setToken("MyJWT")
                .setWelcomePageEnabled(false)
                .build();
        JitsiMeet.setDefaultConferenceOptions(defaultOptions);


        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
               // Toast.makeText(context, location.toString(), Toast.LENGTH_SHORT).show();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates

//        Dexter.withActivity(this)
//                .withPermissions(
//                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_NETWORK_STATE,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        // check if all permissions are granted
//                        if (report.areAllPermissionsGranted()) {
//                            // do you work now
//
////                            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
////                            // Define the criteria how to select the locatioin provider -> use
////                            // default
////                            Criteria criteria = new Criteria();
////                            provider = locationManager.getBestProvider(criteria, false);
////                            Location location = locationManager.getLastKnownLocation(provider);
////
////                            // Initialize the location fields
////                            if (location != null) {
////                                System.out.println("Provider " + provider + " has been selected.");
////                                onLocationChanged(location);
////                            } else {
////
////
////
////                                Toast.makeText(context, "Location not available",
////                                        Toast.LENGTH_SHORT).show();
////
////                            }
//                            //  Toast.makeText(activity, "im ok", Toast.LENGTH_SHORT).show();
//                        }
//
//                        // check for permanent denial of any permission
//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            // permission is denied permenantly, navigate user to app settings
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                })
//                .onSameThread()
//                .check();



     //   LocationPermisionGaveListener.setNeedRefresh();

      //  LocationPermisionGaveListener.listener.doRefresh();

        locatiionRetrivedListener.setMyLocationRetriver_(new locatiionRetrivedListener.myLocationRetriver() {
            @Override
            public Location onLocationretrived(Location location, String address, String cityName,String cou) {
                if (location != null) {
                   // CURRENT_LOCATION = location;
                  //  tv_currentLocation.setText(address);
                   // fromLocation = address;
                    tv_address.setText(address);
                    didGotLocation = true;
                    country = cou ;
                } else {
                  //  Toast.makeText(context, "Null location", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });


    }
    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
//        if(locationManager!=null) {
//          //  locationManager.requestLocationUpdates(provider, 400, 1, this);
//        }
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
//        if(locationManager!=null) {
//            locationManager.removeUpdates(this);
//        }
    }
//    @Override
//    public void onLocationChanged(Location location) {
//        int lat = (int) (location.getLatitude());
//        int lng = (int) (location.getLongitude());
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String c = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
//            Gson gson = new Gson();
//            Log.i("mkl", postalCode+"x "+c);
//           if(!didGotLocation){
//               tv_address.setText(address);
//               didGotLocation = true ;
//           }else {
//
//           }
//
//            country = c ;
//            Toast.makeText(context, c, Toast.LENGTH_SHORT).show();// Here 1 represent max location result to returned, by documents it recommended 1 to 5
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//    }

//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        Toast.makeText(this, "Enabled new provider " + provider,
//                Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        Toast.makeText(this, "Disabled provider " + provider,
//                Toast.LENGTH_SHORT).show();
//    }
    private void addBadgeView() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottom.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(1);

        View notificationBadge = LayoutInflater.from(this).inflate(R.layout.noti_badge, menuView, false);

        itemView.addView(notificationBadge);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
       // adapter.addFragment(new CallGpNowHomeFragment(), "TWO");
        adapter.addFragment(new HomeFragmentAlternativeMedX(), "TWO");
        adapter.addFragment(new CallGpNowMyAppFragment(), "TWO");
        adapter.addFragment(new ChatFragment(), "TWO");
      //  adapter.addFragment(new HomeFragmentAlternativeMedX(), "TWO");
      //  adapter.addFragment(new HomeFragmentAlternativeMedX(), "TWO");
      //  adapter.addFragment(new HomeFragmentAlternativeMedX(), "TWO");
       // adapter.addFragment(new NotificationFragmentPatient(), "TWO");
     //   adapter.addFragment(new MyVdoAppFragment(), "TWO");
       adapter.addFragment(new ProfileFragment(), "ONE");
       //adapter.addFragment(new ProfileFragment(), "ONE");
       // adapter.addFragment(new AppointmentsFragment(), "ONE");
        // adapter.addFragment(new NotificationFragmentPatient(), "TWO");

       // adapter.addFragment(new BlogFragmentPatient(), "ONE");

        viewPager.setAdapter(adapter);
    }

    public void click_urgent(View view) {
        customDrawerButton.changeState();

        startActivity(new Intent(this, UrgentCareRequestActivity.class));
    }

    public void chooseCity(View view) {
        //CityChooseActivity
        //country
        CitySelectedListener.setListener(new CitySelectedListener.CityCelectListener() {
            @Override
            public void onSelectedSucced(String link) {
                tv_address.setText(link);
               // Toast.makeText(context, "seleccted "+link, Toast.LENGTH_SHORT).show();

            }
        });
      Intent i =  new Intent(this, CityChooseActivity.class);
       i.putExtra("country",country);
      startActivity(i);


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Glide.with(PatientHomeActivity.this).load(PHOTO_BASE + SESSION_MANAGER.get_userPhoto()).into(profilePic);
        //Glide.with(PatientHomeActivity.this).load("https://appointmentbd.com/frontend/user/1561548940.jpg").into(profilePic);
        tv_name.setText(SESSION_MANAGER.getUserName());
    }


    private void setUpStatusbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onBasicInfoDownloadSuccess(BasicInfoModel data) {
        if (data != null) {
            HOSPITALS = data.getHospitalList();
            SPECIALIST = data.getSpacialist();

        } else {
            // onBackPressed();
            // Toast.makeText(this, "Something is not right.Try again later", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBasicInfoDownloadFailed(String msg) {
        //onBackPressed();
        //  Toast.makeText(this, "Something is not right.Try again later", Toast.LENGTH_SHORT).show();


    }


    private void getColorManagement() {
        resources = context.getResources();
        primaryClr = resources.getColor(R.color.colorPrimary);
        another = Color.GRAY;
    }



 /*   @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        switch (view.getId()) {
            case R.id.linerhomebutton:
                selectedFragment = HomeFragment.newInstance();
                break;
            case R.id.linerMedicineButton:
                selectedFragment = BlogFragmentPatient.newInstance();
                do_0_0_0_1();
                break;
            case R.id.linerAppointmentButton:
                selectedFragment = AppointmentsFragment.newInstance();
                do_0_0_1_0();
                break;
            case R.id.linerProfileButton:
                selectedFragment = ProfileFragment.newInstance();
                do_0_1_0_0();
                break;

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();

    }
    */

    public void logout(View view) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic("p"+USER_ID);
        sesMan.setLoggedIn(false);
        startActivity(new Intent(this, UserChooseActivity.class));
        finishAffinity();
    }
    public void DoctorSearchActivityOnline(View view) {

        startActivity(new Intent(this, DoctorSearchActivityOnline.class));
    }

    public void click_1(View view) {
        customDrawerButton.changeState();
        startActivity(new Intent(this, GPSearchActivity.class));
    }

    public void click_2(View view) {
        customDrawerButton.changeState();
        startActivity(new Intent(this, HomeCareRequestActivity.class));

    }

    public void click_3(View view) {
        customDrawerButton.changeState();
    }

    public void click_4(View view) {
        customDrawerButton.changeState();
        vpPager.setCurrentItem(2,true);
        //open chat fragment
        //startActivity(new Intent(this,ChatListActivity.class));

    }

    public void click_5(View view) {
        customDrawerButton.changeState();
        vpPager.setCurrentItem(3,true);
        //open profile fragment
        //startActivity(new Intent(this, AmbulanceActivity.class));

    }

    public void click_6(View view) {
        customDrawerButton.changeState();

        startActivity(new Intent(this, GuideLineActivity.class));

    }

    public void click_7(View view) {
        customDrawerButton.changeState();

    }

    public void click_video(View view) {
        customDrawerButton.changeState();
        vpPager.setCurrentItem(1,true);
        //open my appointments
        //startActivity(new Intent(this, VideoCallHistoryPatientActivity.class));
    }

    public void openSupport(View view) {
        //PublicAskByPatient

        startActivity(new Intent(this, PublicAskByPatient.class));
    }


}
