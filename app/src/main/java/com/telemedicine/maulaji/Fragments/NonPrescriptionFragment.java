package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.telemedicine.maulaji.Activity.PatientProfileActivityForDoctor;
import com.telemedicine.maulaji.Activity.ProductListByCategoryActivity;
import com.telemedicine.maulaji.Activity.ProductListSecondLevel;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DYNAMIC;
import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PATIENT_ID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NonPrescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NonPrescriptionFragment extends Fragment implements View.OnClickListener {

    View view;
    Context context;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.tv_three)
    TextView tv_three;
    @BindView(R.id.tv_four)
    TextView tv_four;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;

    Intent i;

    public NonPrescriptionFragment() {
        // Required empty public constructor
    }


    public static NonPrescriptionFragment newInstance() {
        NonPrescriptionFragment fragment = new NonPrescriptionFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_non_prescription, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);

        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        tv_four.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.tv_one) {
            i = new Intent(context, ProductListSecondLevel.class);
            i.putExtra("state", "0");
            i.putExtra("title", "Medicines");
        }


        if (v.getId() == R.id.tv_two) {
            i = new Intent(context, ProductListSecondLevel.class);
            i.putExtra("state", "1");
            i.putExtra("title", "Personal Care");
        }


        if (v.getId() == R.id.tv_three) {
            i = new Intent(context, ProductListSecondLevel.class);
            i.putExtra("state", "2");
            i.putExtra("title", "Wellbeing & Fitness");
        }


        if (v.getId() == R.id.tv_four) {
            i = new Intent(context, ProductListSecondLevel.class);
            i.putExtra("state", "3");
            i.putExtra("title", "Medical Devices");
        }




        startActivity(i);
    }
}