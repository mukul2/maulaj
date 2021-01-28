package com.telemedicine.maulaji.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.Data.ListenerPatientsData;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.adapter.RecomendedTestAppointmentAdapterPatient;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.RecomentationModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationFragment extends Fragment implements ApiListener.patientNotificationDataDownloadListener{
    View v;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    RecomendedTestAppointmentAdapterPatient mAdapter;
    @BindView(R.id.tv_no_item)
    TextView tv_no_item;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this,v);
        ListenerPatientsData.setPatientNotificationDataDownloadListener(this);
        return  v;
    }


    @Override
    public void onNotificationDownloaded(List<RecomentationModel> status) {
//        mAdapter = new RecomendedTestAppointmentAdapterPatient(status);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recycler_view.setLayoutManager(mLayoutManager);
//        recycler_view.setItemAnimator(new DefaultItemAnimator());
//        recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//        recycler_view.setAdapter(mAdapter);
//        if (status.size()>0){
//            tv_no_item.setVisibility(View.GONE);
//        }else {
//            tv_no_item.setVisibility(View.VISIBLE);
//
//        }
    }
}
