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
import com.telemedicine.maulaji.adapter.MySubscriptionsAdapterPatient;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.SubscriptionsModel;
import com.telemedicine.maulaji.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;


public class MySubscriptionsFragment extends Fragment implements  ApiListener.SubscriptionListDownlaodListener{
    View v;
    Context context;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;


    public static MySubscriptionsFragment newInstance() {
        MySubscriptionsFragment fragment = new MySubscriptionsFragment();
        return fragment;
    }

    public MySubscriptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_subscriptions, container, false);
        context=v.getContext();

        ButterKnife.bind(this,v);

        Api.getInstance().get_subscription_list(TOKEN,"patient",USER_ID,this);



        return v;
    }


    @Override
    public void onSubscriptionListDownlaodSuccess(List<SubscriptionsModel> data) {
        Toast.makeText(context, "size "+data.size(), Toast.LENGTH_SHORT).show();

        MySubscriptionsAdapterPatient mAdapter = new MySubscriptionsAdapterPatient(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                recyclerView.VERTICAL,false);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onSubscriptionListDownlaodFailed(String msg) {

    }
}
