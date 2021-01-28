package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.adapter.PendingAppointmentAdapterPatient;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.AppointmentModelNew;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;


public class ConfirmedAppointmentsFragment extends Fragment implements ApiListener.appoinetmentsDownloadListener {
    View v;
    Context context;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


    public static ConfirmedAppointmentsFragment newInstance() {
        ConfirmedAppointmentsFragment fragment = new ConfirmedAppointmentsFragment();
        return fragment;
    }

    public ConfirmedAppointmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.patient_pending, container, false);
        context=v.getContext();
        ButterKnife.bind(this,v);

        Api.getInstance().getAppointments( TOKEN,"patient",USER_ID,"1",this);



        return v;
    }

    @Override
    public void onAppointmentDownloadSuccess(List<AppointmentModelNew> status) {
        PendingAppointmentAdapterPatient mAdapter = new PendingAppointmentAdapterPatient(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }



}
