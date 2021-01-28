package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;
import com.telemedicine.maulaji.Fragments.DoctorsProfileFragment;
import com.telemedicine.maulaji.Fragments.PrescriptionsFragment;
import com.telemedicine.maulaji.Fragments.SingleprescriptionFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.CountryModel;
import com.telemedicine.maulaji.model.DoctorModelRaw;
import com.telemedicine.maulaji.model.HospitalModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DOC;

public class AllHomeVisitDoctorsList extends AppCompatActivity {
    engineGridViews engineGridViews;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerHospital)
    Spinner spinnerHospital;
    Context context = this ;
    FlippableStackView mFlippableStack;

    ColorFragmentAdapter mPageAdapter;

    List<Fragment> mViewPagerFragments;
    Boolean firstTimeDone = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_home_visit_doctors_list);
        ButterKnife.bind(this);
        engineGridViews = new engineGridViews();

        downloadDocs(null);
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
                                downloadDocs(null);
                            }else {
                                downloadDocs(response.get(i-1).getId());
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

    private void downloadDocs(String hospital) {
        Api.getInstance().get_home_visit_doctors(hospital,new ApiListener.RawDocDownloadListener() {
            @Override
            public void onAllDocDownloadSuccess(List<DoctorModelRaw> response) {
                Toast.makeText(context, ""+response.size(), Toast.LENGTH_SHORT).show();


                Gson gson= new Gson();
                Log.i("sw",gson.toJson(response));
                initRecycler(response);

/*
                createViewPagerFragments(response);

                mPageAdapter = new ColorFragmentAdapter(getSupportFragmentManager(), mViewPagerFragments);
                mFlippableStack = (FlippableStackView) findViewById(R.id.flippable_stack_view);
                mFlippableStack. initStack(response.size()>3?4:response.size(),
                        StackPageTransformer.Orientation.VERTICAL,
                        0.95f,
                        0.7f,
                        0.6f,
                        StackPageTransformer.Gravity.CENTER);
                mFlippableStack.setAdapter(mPageAdapter);

 */


            }

            @Override
            public void onAllDocDownloadFailed(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createViewPagerFragments(List<DoctorModelRaw> response) {
        mViewPagerFragments = new ArrayList<>();



        for (int i = 0; i < response.size(); i++) {
            DoctorModelRaw data =  response.get(i);
            mViewPagerFragments.add(DoctorsProfileFragment.newInstance(data));
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
    private void initRecycler(List<DoctorModelRaw> data) {
        com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
            @Override
            public void onSelected(int pos,int op) {
                Intent intent =  new Intent(context,HomeCareRequestActivity.class);
                // Map<String, Object> data__ = (Map<String, Object>) data.get(pos);
                // NOW_SHOWING_DYNAMIC = data__;
                //   NOW_SHOWING_DOC = data.get(pos);

                NOW_SHOWING_DOC =  data.get(pos);
                startActivity(intent);





            }
        };
        engineGridViews.showDrGridList(data,recyclerView,context,R.layout.dr_item_blk_grad,listener );
    }

    public void back(View view) {
        onBackPressed();
    }
}