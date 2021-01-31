package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.StackPageTransformer;
import com.telemedicine.maulaji.Fragments.DoctorsProfileFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.DoctorModelRaw;
import com.telemedicine.maulaji.model.HospitalModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DOC;

public class AllSpecialist extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;
    @BindView(R.id.tv_deptName)
    TextView tv_deptName;
    Context context = this ;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;
    Boolean firstTimeDone = false ;
    @BindView(R.id.spinnerHospital)
    Spinner spinnerHospital;
    @BindView(R.id.tv_list)
    TextView tv_list;
    @BindView(R.id.tv_profile)
    TextView tv_profile;
    FlippableStackView mFlippableStack;
    ColorFragmentAdapter mPageAdapter;

    List<Fragment> mViewPagerFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_specialist);
        ButterKnife.bind(this);
        engineGridViews = new engineGridViews();

        mFlippableStack = (FlippableStackView) findViewById(R.id.flippable_stack_view);


        engineGridViews = new engineGridViews();
        recycler_view.setVisibility(View.VISIBLE);
        mFlippableStack.setVisibility(View.GONE);
        tv_list.setTextColor(context.getColor(R.color.white));
        tv_list.setBackgroundColor(context.getColor(R.color.colorPrimary));

        tv_profile.setTextColor(context.getColor(R.color.colorPrimary));
        tv_profile.setBackgroundColor(context.getColor(R.color.white));

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String id =(String) b.get("dept");
            String name =(String) b.get("name");
            tv_deptName.setText(name);


            downloadSpecalist(id,null);
            Api.getInstance().hospital_list(new ApiListener.HospitalDownloadListener() {
                @Override
                public void onHospitalDownloadSuccess(List<HospitalModel> response) {
                    List<String> hospitals = new ArrayList<>();
                    hospitals.add("All Hospitals");
                    for(int i = 0 ; i < response.size() ; i++){
                        hospitals.add(response.get(i).getName());

                    }


                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.white_spinner, hospitals);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerHospital.setAdapter(dataAdapter);

                    //spinnerHospital.setSelection(locatedCountryIndex);
                    spinnerHospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            // dowblaodAndPopulate(response.get(i).getNicename());
                            if(!firstTimeDone){

                                firstTimeDone= true;
                            }else {
                                if(i==0){
                                    downloadSpecalist(id,null);
                                }else {
                                    downloadSpecalist(id,response.get(i-1).getId());
                                }

                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                @Override
                public void onHospitalDownloadFailed(String msg) {

                }
            });









        }



        //dept
    }

    public void downloadSpecalist(String deptid,String hospitalId){
        Api.getInstance().specialist_doctor_raw(deptid,hospitalId, new ApiListener.AllGPDownloadListener() {
            @Override
            public void onAllGPDownloadSuccess(List<DoctorModelRaw> response) {
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos,int op) {
                        Intent intent =  new Intent(context,DateAndTimeSlotActivity.class);
                        DoctorModelRaw data = response.get(pos);
                      // NOW_SHOWING_DYNAMIC = data;
                        NOW_SHOWING_DOC = data ;
                       // startActivity(intent);

                        DoctorsProfileFragment addPhotoBottomDialogFragment = DoctorsProfileFragment.newInstance(data,"typeTwo");
                        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "add_photo_dialog_fragment");

                    }
                };
                Toast.makeText(context, ""+response.size(), Toast.LENGTH_SHORT).show();








                if  (response.size()>0) {
                    createViewPagerFragments(response);
                    mPageAdapter = new ColorFragmentAdapter(getSupportFragmentManager(), mViewPagerFragments);
                    mFlippableStack. initStack(response.size()>3?4:response.size(),
                            StackPageTransformer.Orientation.VERTICAL,
                            0.95f,
                            0.7f,
                            0.5f,
                            StackPageTransformer.Gravity.CENTER);
                    mFlippableStack.setAdapter(mPageAdapter);


                    tv_list.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recycler_view.setVisibility(View.VISIBLE);
                            mFlippableStack.setVisibility(View.GONE);
                            tv_list.setTextColor(context.getColor(R.color.white));
                            tv_list.setBackgroundColor(context.getColor(R.color.colorPrimary));

                            tv_profile.setTextColor(context.getColor(R.color.colorPrimary));
                            tv_profile.setBackgroundColor(context.getColor(R.color.white));

                        }
                    });

                    tv_profile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recycler_view.setVisibility(View.GONE);
                            mFlippableStack.setVisibility(View.VISIBLE);
                            tv_list.setTextColor(context.getColor(R.color.colorPrimary));
                            tv_list.setBackgroundColor(context.getColor(R.color.white));

                            tv_profile.setTextColor(context.getColor(R.color.white));
                            tv_profile.setBackgroundColor(context.getColor(R.color.colorPrimary));
                        }
                    });
                    tv_no_data.setVisibility(View.GONE);
                    engineGridViews.showDrGridList(response,recycler_view,context,R.layout.dr_item_blk_grad,listener );
                }
                else {
                    tv_no_data.setVisibility(View.VISIBLE);
                    engineGridViews.showDrGridList(response,recycler_view,context,R.layout.dr_item_blk_grad,listener );

                }

            }

            @Override
            public void onAllGPDownloadFailed(String msg) {
                Toast.makeText(AllSpecialist.this, msg, Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void createViewPagerFragments(List<DoctorModelRaw> response) {
        mViewPagerFragments = new ArrayList<>();



        for (int i = 0; i < response.size(); i++) {
            DoctorModelRaw data =  response.get(i);
          //  mViewPagerFragments.add(DoctorsProfileFragment.newInstance(data));
        }
    }
    private class ColorFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ColorFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
    public void back(View view) {
        onBackPressed();
    }
}