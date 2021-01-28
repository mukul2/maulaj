package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.Activity.CallLogHistoryActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.adapter.CurrentlyOnlineDoctorAdapter;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.VideoCallModel;
import com.telemedicine.maulaji.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoCallFragmenttFragment extends Fragment implements  ApiListener.onlineDoctorListener {
    View v;
    Context context;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_no_item)
    TextView tv_no_item;
    @BindView(R.id.tv_callLog)
    TextView tv_callLog;



    public VideoCallFragmenttFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_video_call, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);
        Api.getInstance().downloadOnlineDoctors(this);
        tv_callLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CallLogHistoryActivity.class));
            }
        });


        return v;
    }


    @Override
    public void onOnlineDoctorSearchSuccess(List<VideoCallModel> list) {
        if (list.size()>0) {
            tv_no_item.setVisibility(View.GONE);
            CurrentlyOnlineDoctorAdapter mAdapter = new CurrentlyOnlineDoctorAdapter(list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));

            recycler_view.setAdapter(mAdapter);
        }else {
            tv_no_item.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onOnlineDoctorSearchFailed(String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }


}
