package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.telemedicine.maulaji.Fragments.SpecialistBottomSheet;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestActivity extends AppCompatActivity {

    Context context = this;





    @BindView(R.id.recycler_view_myServices)
    RecyclerView recycler_view_myServices;


    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.cardOpenPharmacey)
    CardView cardOpenPharmacey;




    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews ;

    int selectedServicePos = 0;

    String selctedDepartmentId ;
    SessionManager sessionManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        sessionManager.setLoggedIn(false);
        sessionManager.setuserId("0");

        //shimmar.startShimmer();
        engineGridViews = new engineGridViews();
        initFirstRecycler();
        //initSpinner();
        //  context.startActivity(new Intent(context, ShopActivity.class));
        cardOpenPharmacey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShopActivity.class));
            }
        });
    }
    private void initFirstRecycler() {
        List servicesType = new ArrayList();
       // servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 1);put("name", "GP"); }});
       // servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Specialist"); }});
        servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Urgent Consultation"); }});
        servicesType.add(new HashMap<String, Object>() {{ put("isSelected", 0);put("name", "Home Visit"); }});


        com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener serviceTypeSelectListener = new engineGridViews.TapSelectListener() {
            @Override
            public void onSelected(int pos,int op) {
                selectedServicePos = pos ;


                if(pos == 0){
                   // Intent i =   new Intent(context, UrgentCareRequestActivity.class);
                    Intent i =   new Intent(context, AllUrgentDoctorsActivity.class);
                    startActivity(i);
                }else  if(pos == 1){
                 //   Intent i =   new Intent(context, HomeCareRequestActivity.class);
                    Intent i =   new Intent(context, AllHomeVisitDoctorsList.class);
                    startActivity(i);
                }



            }
        };

        engineGridViews.showGuestGridList(servicesType,recycler_view_myServices,context,R.layout.dept_grid,serviceTypeSelectListener );


    }

    private void initSpinner() {
        Api.getInstance().department_list(new ApiListener.DeptListDownload() {
            @Override
            public void onDeptListDownloadSuccess(List response) {
                Log.i("mkl",response.toString());
                List<String> allDat =new ArrayList<>();
                allDat.add("Choose");
                for (int i = 0 ; i<response.size();i++){
                    final Map<String, Object> data = (Map<String, Object>) response.get(i);
                    allDat.add(data.get("speciality").toString());



                }
                //spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, allDat);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);



                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int ii, long l) {

                        if(ii>0) {
                            Map<String, Object> data = (Map<String, Object>) response.get(ii);
                            //selctedDepartmentId =""+ Integer.parseInt(data.get("id").toString());
                            selctedDepartmentId = "" + (2 + ii);
                            Intent i = new Intent(context, AllSpecialist.class);
                            i.putExtra("dept", "" + selctedDepartmentId);
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



    }

    public void back(View view) {
        onBackPressed();
    }
}