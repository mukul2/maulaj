package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.telemedicine.maulaji.Activity.PrescriptionGivingActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DYNAMIC;

public class AppintmentInfoFragment extends Fragment {
    View view ;
    Context context ;
    @BindView(R.id.tv_noMedicalRecord)
    TextView tv_noMedicalRecord;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_blood)
    TextView tv_blood;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_dob)
    TextView tv_dob;
    @BindView(R.id.tv_problem)
    TextView tv_problem;
    @BindView(R.id.symptomsRecycler)
    RecyclerView symptomsRecycler;
    @BindView(R.id.linearCallBody)
    RelativeLayout linearCallBody;
    @BindView(R.id.cardPrescrioption)
    CardView cardPrescrioption;
    String type ;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews ;
    String time_slot,reason,phone,birthdate,sex,address,bloodgroup;

    public AppintmentInfoFragment(String t) {
        this.type = t ;
    }


    public static AppintmentInfoFragment newInstance(String type) {
        AppintmentInfoFragment fragment = new AppintmentInfoFragment(type);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_appintment_info, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        engineGridViews = new engineGridViews();
        Log.i("23deb",NOW_SHOWING_DYNAMIC.toString());

        if(type.equals("u")){
            time_slot=NOW_SHOWING_DYNAMIC.get("created_at")!=null?NOW_SHOWING_DYNAMIC.get("created_at").toString():"";
            reason=NOW_SHOWING_DYNAMIC.get("reason")!=null?NOW_SHOWING_DYNAMIC.get("reason").toString():"No reason of appointment recorded";
            phone=NOW_SHOWING_DYNAMIC.get("phone").toString();
            birthdate=NOW_SHOWING_DYNAMIC.get("birthdate").toString();
            sex=NOW_SHOWING_DYNAMIC.get("sex").toString();
            address=NOW_SHOWING_DYNAMIC.get("address").toString();
            bloodgroup=NOW_SHOWING_DYNAMIC.get("bloodgroup").toString();
        }
        if(type.equals("s")){
            time_slot=NOW_SHOWING_DYNAMIC.get("time_slot")!=null?NOW_SHOWING_DYNAMIC.get("time_slot").toString():"";
            reason=NOW_SHOWING_DYNAMIC.get("reason")!=null?NOW_SHOWING_DYNAMIC.get("reason").toString():"No reason of appointment recorded";
            phone=NOW_SHOWING_DYNAMIC.get("phone").toString();
            birthdate=NOW_SHOWING_DYNAMIC.get("birthdate").toString();
            sex=NOW_SHOWING_DYNAMIC.get("sex").toString();
            address=NOW_SHOWING_DYNAMIC.get("address").toString();
            bloodgroup=NOW_SHOWING_DYNAMIC.get("bloodgroup").toString();
        }
        if(type.equals("h")){
            time_slot=NOW_SHOWING_DYNAMIC.get("date")!=null?NOW_SHOWING_DYNAMIC.get("date").toString():"";
            reason=NOW_SHOWING_DYNAMIC.get("reason")!=null?NOW_SHOWING_DYNAMIC.get("reason").toString():"No reason of appointment recorded";
            phone=NOW_SHOWING_DYNAMIC.get("phone").toString();
            birthdate=NOW_SHOWING_DYNAMIC.get("birthdate").toString();
            sex=NOW_SHOWING_DYNAMIC.get("sex").toString();
            address=NOW_SHOWING_DYNAMIC.get("address").toString();
            bloodgroup=NOW_SHOWING_DYNAMIC.get("bloodgroup").toString();
        }
        tv_time.setText(time_slot);
        tv_problem.setText(reason);
        tv_phone.setText(phone);
        tv_dob.setText(birthdate);
        tv_sex.setText(sex);
        tv_address.setText(address);
        tv_blood.setText(bloodgroup);

        linearCallBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                    @Override
                    public void onDialogClicked(boolean result) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + NOW_SHOWING_DYNAMIC.get("phone").toString()));
                        startActivity(intent);
                    }
                },"Do you want to dial  "+NOW_SHOWING_DYNAMIC.get("phone").toString()+" ?");
            }
        });
       if( NOW_SHOWING_DYNAMIC.get("vitals")!=null ){
           tv_noMedicalRecord.setVisibility(View.GONE);
            String symptoms = NOW_SHOWING_DYNAMIC.get("vitals").toString();
           // symptoms = symptoms.substring(1, symptoms.length() - 1);

            List<String> myList = new ArrayList<String>(Arrays.asList((symptoms).split(",")));
            com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                @Override
                public void onSelected(int pos, int optionalData) {

                }
            };
            engineGridViews.showSymptomsListDoc(myList, symptomsRecycler, context, R.layout.symptoms_show_dr_item, listener);
        }else{
           tv_noMedicalRecord.setVisibility(View.VISIBLE);
       }

        cardPrescrioption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PrescriptionGivingActivity.class);
                startActivity(intent);
            }
        });

        return  view ;
    }
}