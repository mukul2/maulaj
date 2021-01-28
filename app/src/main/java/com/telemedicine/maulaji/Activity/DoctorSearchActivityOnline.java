package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.adapter.DoctorsSearchAdapterOnline;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.OnlineDoctorsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.DataStore.TOKEN;

public class DoctorSearchActivityOnline extends BaseActivity {
    @BindView(R.id.ed_key)
    EditText ed_key;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context = this;
    List<OnlineDoctorsModel> data = new ArrayList<>();
    DoctorsSearchAdapterOnline mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search_online);
        ButterKnife.bind(this);
        progressbar.setVisibility(View.GONE);

        initAdapter();
        ed_key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                data.clear();

                String name = charSequence.toString();
                progressbar.setVisibility(View.VISIBLE);
                Api.getInstance().searchOnlineDoctorsName(TOKEN, name, new ApiListener.DownloadOnlineDocListener() {
                    @Override
                    public void onOnlineDocSearchSuccess(List<OnlineDoctorsModel> list) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();

                        data.addAll(list);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onOnlineDocSearchFailed(String msg) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initAdapter() {

        mAdapter = new DoctorsSearchAdapterOnline(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(staggeredGridLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        // recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);

    }


    public void back(View view) {
        onBackPressed();
    }
}