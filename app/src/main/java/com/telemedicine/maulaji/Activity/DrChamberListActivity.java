package com.telemedicine.maulaji.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.adapter.ChambersListAdapterDr;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.DrChamberResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrChamberListActivity extends AppCompatActivity implements ApiListener.chamberListDownloadListener{
    SessionManager sessionManager;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    ChambersListAdapterDr mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_chamber_list);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        Api.getInstance().getMyChambersList(sessionManager.getUserId(),this);
    }

    @Override
    public void onChamberListDownloadSuccess(List<DrChamberResponse> list) {
//        mAdapter = new ChambersListAdapterDr(list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(DrChamberListActivity.this);
//        recycler_view.setLayoutManager(mLayoutManager);
//        recycler_view.setItemAnimator(new DefaultItemAnimator());
//        recycler_view.addItemDecoration(new DividerItemDecoration(DrChamberListActivity.this, LinearLayoutManager.VERTICAL));
//        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onChamberListDownloadFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    public void addNewChamber(View view) {
        startActivity(new Intent(this,AdddChamberActivity.class));
    }

    public void back(View view) {
        onBackPressed();
    }
}
