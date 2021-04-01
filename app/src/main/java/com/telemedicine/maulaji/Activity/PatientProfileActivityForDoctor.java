package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.telemedicine.maulaji.Fragments.AppintmentInfoFragment;
import com.telemedicine.maulaji.Fragments.CallGpNowMyAppFragment;
import com.telemedicine.maulaji.Fragments.DiseasesHistoryFragment;
import com.telemedicine.maulaji.Fragments.DocumentFragment;
import com.telemedicine.maulaji.Fragments.HomeVisitAppointment;
import com.telemedicine.maulaji.Fragments.LabReportsFragment;
import com.telemedicine.maulaji.Fragments.PrescriptionsFragment;
import com.telemedicine.maulaji.Fragments.ScheduledAppointmentfragment;
import com.telemedicine.maulaji.Fragments.UrgentConsultationFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;


public class PatientProfileActivityForDoctor extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @BindView(R.id.tv_name)
    TextView tv_name;
    Context context = this ;
    SessionManager sessionManager ;
    String targetuserID;
    String partner_id;
    String partner_name;
    String partner_photo;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_for_doctor);
        ButterKnife.bind(this);
        //PrescriptionsFragment
        //DiseasesHistoryFragment
        sessionManager = new SessionManager(this);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
             partner_id =(String) b.get("partner_id");
            //Toast.makeText(context, "got patient id "+partner_id, Toast.LENGTH_SHORT).show();
             partner_name =(String) b.get("partner_name");
             partner_photo =(String) b.get("partner_name");
            type =(String) b.get("type");
            tv_name.setText(partner_name);

        }
        viewPager = (ViewPager) findViewById(R.id.vpPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        int selectedColor = Color.WHITE;
        int normal = Color.WHITE;
        tabLayout.setTabTextColors(normal, selectedColor);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        tabLayout.setupWithViewPager(viewPager);


        targetuserID = partner_id;
        String targetuserName = partner_name;
        String targetuserImage =partner_photo;

        ImageView cardVcall = (ImageView) findViewById(R.id.cardVcall);
        ImageView cardAcall = (ImageView) findViewById(R.id.cardAcall);
        ImageView cardChat = (ImageView) findViewById(R.id.cardChat);
        String doctor_id = sessionManager.getUserId();
        String room =targetuserID+"-"+doctor_id;
        cardVcall.setOnClickListener((View view)->{
            MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                @Override
                public void onDialogClicked(boolean result) {
                    if(result){
                        Api.getInstance().appNotification("p"+targetuserID,"incomming_call",room,"Incoming Call","New Incomming call from "+sessionManager.getUserName(),PHOTO_BASE+sessionManager.get_userPhoto());

                        JitsiMeetConferenceOptions options
                                = new JitsiMeetConferenceOptions.Builder()
                                .setRoom(room)
                                .setAudioOnly(false)
                                .setVideoMuted(false)
                                .setSubject("Consultation")
                                .setFeatureFlag("invite.enabled", false)
                                .build();
                        JitsiMeetActivity.launch(context, options);


                    }

                }
            },"Do you want to start Video Call?");
        });
        cardAcall.setOnClickListener((View view)->{
            MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                @Override
                public void onDialogClicked(boolean result) {
                    if(result){
                        Api.getInstance().appNotification("p"+targetuserID,"incomming_call",room,"Incoming Call","New Incomming call from "+sessionManager.getUserName(),PHOTO_BASE+sessionManager.get_userPhoto());

                        JitsiMeetConferenceOptions options
                                = new JitsiMeetConferenceOptions.Builder()
                                .setRoom(room)
                                .setAudioOnly(true)
                                .setVideoMuted(true)
                                .setSubject("Consultation")
                                .setFeatureFlag("invite.enabled", false)
                                .build();
                        JitsiMeetActivity.launch(context, options);


                    }

                }
            },"Do you want to start Audio Call?");
        });
        cardChat.setOnClickListener((View view)->{
            MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                @Override
                public void onDialogClicked(boolean result) {
                    if(result){
                        Intent intent = new Intent(context, ChatActivityCommon.class);
                        intent.putExtra("partner_id",targetuserID);
                        intent.putExtra("partner_name",targetuserName);
                        intent.putExtra("partner_photo",targetuserImage);
                        startActivity(intent);



                        // Api.getInstance().appNotification("p"+patient_id,"incomming_call",room,"Incoming Call","New Incomming call from "+sessionManager.getUserName());

                    }

                }
            },"Do you want to start Chat?");

        });
        setupViewPager(viewPager);

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
                .setFeatureFlag("invite.enabled", false)
                // When using JaaS, set the obtained JWT here
                //.setToken("MyJWT")
                .setWelcomePageEnabled(false)
                .build();
        JitsiMeet.setDefaultConferenceOptions(defaultOptions);
    }

    private void setupViewPager(ViewPager viewPager) {
       // Toast.makeText(this, "XXX "+targetuserID, Toast.LENGTH_SHORT).show();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( AppintmentInfoFragment.newInstance(type), "Appointment Info");

       if(!targetuserID.equals("0")){
           adapter.addFragment( PrescriptionsFragment.newInstance(targetuserID), "Prescriptions");
           adapter.addFragment(new LabReportsFragment(targetuserID), "Lab Reports");
           adapter.addFragment(new DocumentFragment(targetuserID), "Documents");
       }




        viewPager.setAdapter(adapter);
    }

    public void back(View view) {
        onBackPressed();
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
}