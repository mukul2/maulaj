package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.telemedicine.maulaji.Activity.AllHomeVisitDoctorsList;
import com.telemedicine.maulaji.Activity.AllSpecialist;
import com.telemedicine.maulaji.Activity.AllUrgentDoctorsActivity;
import com.telemedicine.maulaji.Activity.AmbulanceActivity;
import com.telemedicine.maulaji.Activity.ChatListActivity;
import com.telemedicine.maulaji.Activity.GPSearchActivity;
import com.telemedicine.maulaji.Activity.GuideLineActivity;
import com.telemedicine.maulaji.Activity.HomeCareRequestActivity;
import com.telemedicine.maulaji.Activity.OnlineDoctorsActivity;
import com.telemedicine.maulaji.Activity.ShopActivity;
import com.telemedicine.maulaji.Activity.SpecialistActivity;
import com.telemedicine.maulaji.Activity.SubscriptionActivityPatient;
import com.telemedicine.maulaji.Activity.UrgentCareRequestActivity;
import com.telemedicine.maulaji.Activity.VideoCallAppointmentList;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiClientRawApi;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.DepartmentModel2;
import com.telemedicine.maulaji.model.DepartmentModel3;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class HomeFragmentAlternativeMedX extends Fragment {
    View v;
    Context context;
    @BindView(R.id.cardChember)
    CardView cardChember;
    @BindView(R.id.cardOnline)
    CardView cardOnline;

    @BindView(R.id.cardChat)
    CardView cardChat;
    @BindView(R.id.cardGuide)
    CardView cardGuide;
    @BindView(R.id.cardVideoAppointment)
    CardView cardVideoAppointment;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_view_myServices)
    RecyclerView recycler_view_myServices;


    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.linearDeptChooseBody)
    LinearLayout linearDeptChooseBody;

    @BindView(R.id.cardOpenPharmacey)
    CardView cardOpenPharmacey;




    engineGridViews engineGridViews ;

    int selectedServicePos = 0;

    String selctedDepartmentId ;


    public static HomeFragmentAlternativeMedX newInstance() {
        HomeFragmentAlternativeMedX fragment = new HomeFragmentAlternativeMedX();
        return fragment;
    }

    public HomeFragmentAlternativeMedX() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment_alter, container, false);
        context = v.getContext();

        ButterKnife.bind(this, v);
        // Glide.with(this).load(R.drawable.mother_child_bg).apply(RequestOptions.bitmapTransform(new BlurTransformation(5,3))).into(imgBn);

        //shimmar.startShimmer();
        engineGridViews = new engineGridViews();
        initFirstRecycler();
        initSpinner();
        cardChember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SpecialistActivity.class));
                //  Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        cardOpenPharmacey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
                context.startActivity(new Intent(context, ShopActivity.class));
                //  Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        cardChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChatListActivity.class));
                //  Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });

        cardVideoAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, VideoCallAppointmentList.class));
                //  Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });

//        linearSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(selectedServicePos ==0){
//                    startActivity(new Intent(context,GPSearchActivity.class));
//                }
//                if(selectedServicePos ==1){
//                 Intent i =   new Intent(context, AllSpecialist.class);
//                 i.putExtra("dept",""+selctedDepartmentId);
//                    startActivity(i);
//                }
//                if(selectedServicePos ==2){
//                 Intent i =   new Intent(context, UrgentCareRequestActivity.class);
//                // i.putExtra("dept",""+selctedDepartmentId);
//                    startActivity(i);
//                }
//                if(selectedServicePos ==3){
//                 Intent i =   new Intent(context, HomeCareRequestActivity.class);
//                // i.putExtra("dept",""+selctedDepartmentId);
//                    startActivity(i);
//                }
//            }
//        });
        //TYPE_OF_ACTIVITY
        /*
        Api.getInstance().getDepList(TOKEN, new ApiListener.DeptDownloadListener() {
            @Override
            public void onDepartmentDownloadSuccess(List<DeptModel> list) {
                //Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();
                TYPE_OF_ACTIVITY="OnlineDoc";
                shimmar.stopShimmer();
                shimmar.setVisibility(View.GONE);
                DepartmentsAdapterGrid mAdapter = new DepartmentsAdapterGrid(list);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,2);
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);

            }

            @Override
            public void onDepartmentDownloadFailed(String msg) {

            }
        });

         */
        return v;
    }

    private void initSpinner() {
       Api.getInstance().get_dept_list(new ApiListener.DeptListDownload2Listener() {
           @Override
           public void onDeptListDownloadSuccess(List<DepartmentModel3> response) {
               Log.i("mkl",response.toString());
               List<String> allDat =new ArrayList<>();
               allDat.add("Choose");
               for (int i = 0 ; i<response.size();i++){
                   final DepartmentModel3 data = response.get(i);
                   allDat.add(data.getSpeciality().toString());



               }
               //spinner
               ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, allDat);

               dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

               spinner.setAdapter(dataAdapter);



               spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int ii, long l) {

                       if(ii>0) {
                           DepartmentModel3 data =  response.get(ii-1);
                           //selctedDepartmentId =""+ Integer.parseInt(data.get("id").toString());
                           selctedDepartmentId =""+data.getId();
                           Log.i("mkl", "onItemSelected: "+ selctedDepartmentId);
                           Intent i = new Intent(context, AllSpecialist.class);
                           i.putExtra("dept", "" + selctedDepartmentId);
                           i.putExtra("name", "" + data.getSpeciality());
                           startActivity(i);
                       }
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });

           }

           @Override
           public void onDeptListDownloadFailed(String msg) {

           }
       });
//        ApiClientRawApi.getApiInterface().department_list(new ApiListener.DeptListDownload() {
//            @Override
//            public void onDeptListDownloadSuccess(List<DepartmentModel2> response) {
//                Log.i("mkl",response.toString());
//                List<String> allDat =new ArrayList<>();
//                allDat.add("Choose");
//                for (int i = 0 ; i<response.size();i++){
//                    final DepartmentModel2 data = response.get(i);
//                    allDat.add(data.getSpeciality().toString());
//
//
//
//                }
//                //spinner
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, allDat);
//
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                spinner.setAdapter(dataAdapter);
//
//
//
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int ii, long l) {
//
//                       if(ii>0) {
//                           DepartmentModel2 data =  response.get(ii-1);
//                           //selctedDepartmentId =""+ Integer.parseInt(data.get("id").toString());
//                           selctedDepartmentId =""+data.getId();
//                           Intent i = new Intent(context, AllSpecialist.class);
//                           i.putExtra("dept", "" + selctedDepartmentId);
//                           i.putExtra("name", "" + data.getSpeciality());
//                           startActivity(i);
//                       }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onDeptListDownloadFailed(String msg) {
//
//            }
//        });



    }


    private void initFirstRecycler() {
        List servicesType = new ArrayList();
        servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Doctor"); }});
        servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Specialist"); }});
        servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Urgent Consultation"); }});
        servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Home Visit"); }});


        com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener serviceTypeSelectListener = new engineGridViews.TapSelectListener() {
            @Override
            public void onSelected(int pos,int op) {
                selectedServicePos = pos ;


                if(pos == 0){
                    linearDeptChooseBody.setVisibility(View.GONE);
                    startActivity(new Intent(context, GPSearchActivity.class));
                }else  if(pos == 1){
                    linearDeptChooseBody.setVisibility(View.VISIBLE);
                }else  if(pos == 2){
                    linearDeptChooseBody.setVisibility(View.GONE);
                    Intent i =   new Intent(context, AllUrgentDoctorsActivity.class);
                   // Intent i =   new Intent(context, UrgentCareRequestActivity.class);
                    startActivity(i);
                }else  if(pos == 3){
                    linearDeptChooseBody.setVisibility(View.GONE);
                  //  Intent i =   new Intent(context, HomeCareRequestActivity.class);
                    Intent i =   new Intent(context, AllHomeVisitDoctorsList.class);
                    startActivity(i);
                }



            }
        };

        engineGridViews.showGridList(servicesType,recycler_view_myServices,context,R.layout.dept_grid,serviceTypeSelectListener );


    }
    //GuideLineActivity


    @OnClick(R.id.cardOnline)
    public void cardGuide() {
        startActivity(new Intent(context, OnlineDoctorsActivity.class));
    }

    @OnClick(R.id.cardGuide)
    public void openGuideline() {
        startActivity(new Intent(context, GuideLineActivity.class));
    }

    @OnClick(R.id.cardChat)
    public void openChatList() {

        //startActivity(new Intent(context, DrChatActivity.class));
    }

    @OnClick(R.id.cardSubscription)
    public void openSubscriptions() {
        startActivity(new Intent(context, SubscriptionActivityPatient.class));
    }

    @OnClick(R.id.cardAmbulance)
    public void openAmbulance() {
        startActivity(new Intent(context, AmbulanceActivity.class));
    }


}
