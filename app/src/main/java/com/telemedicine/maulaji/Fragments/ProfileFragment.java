package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.telemedicine.maulaji.Activity.BillsActivity;
import com.telemedicine.maulaji.Activity.MedicalHistoryActivityPatient;
import com.telemedicine.maulaji.Activity.NotificationActivity;
import com.telemedicine.maulaji.Activity.PatientDiseaseSumActivity;
import com.telemedicine.maulaji.Activity.PatientPersonalInfoActivity;
import com.telemedicine.maulaji.Activity.PatientPrescriptionRecheckActivity;
import com.telemedicine.maulaji.Activity.PatientProfileInfoActivity;
import com.telemedicine.maulaji.Activity.PrescriptionActivityPatient;
import com.telemedicine.maulaji.Activity.TestRecomendationListActivity;
import com.telemedicine.maulaji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProfileFragment extends Fragment {
    View v;
    Context context;
    @BindView(R.id.linearPersonal)
    LinearLayout linearPersonal;
    @BindView(R.id.linearTest)
    LinearLayout linearTest;
//    @BindView(R.id.linearLabForm)
//    LinearLayout linearLabForm;

    @BindView(R.id.linearLF)
    LinearLayout linearLF;
    @BindView(R.id.linearPrescription)
    LinearLayout linearPrescription;
    @BindView(R.id.linearPrescriptionReview)
    LinearLayout linearPrescriptionReview;
    @BindView(R.id.linearNotification)
    LinearLayout linearNotification;
    @BindView(R.id.linearDocuments)
    LinearLayout linearDocuments;
    @BindView(R.id.linearMedicalHistory)
    LinearLayout linearMedicalHistory;



    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.profile_fragment, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        linearPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PatientPersonalInfoActivity.class));
            }
        });


        linearNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PatientProfileInfoActivity.class);
                i.putExtra("pos",0);

                startActivity(i);
              //  startActivity(new Intent(context, PatientProfileInfoActivity.class));
            }
        });
        linearLF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PatientProfileInfoActivity.class);
                i.putExtra("pos",1);

                startActivity(i);
              //  startActivity(new Intent(context, PatientProfileInfoActivity.class));
            }
        });


        linearPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PatientProfileInfoActivity.class));
            }
        });

        linearMedicalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(context, MedicalHistoryActivityPatient.class));
            }
        });




        return v;
    }
    @OnClick(R.id.linearDocuments)
    public  void BillActivity(){
        //open document pager

        Intent i = new Intent(context, PatientProfileInfoActivity.class);
        i.putExtra("pos",2);

        startActivity(i);

    }

    //





}
