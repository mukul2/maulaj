package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialistBottomSheet extends BottomSheetDialogFragment {
    View view ;
    Context context;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;

    public static SpecialistBottomSheet newInstance() {
        return new SpecialistBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_specialist_bottom_sheet, container, false);
        context = view.getContext();
        engineGridViews = new engineGridViews();
        ButterKnife.bind(this,view);
        Api.getInstance().department_list(new ApiListener.DeptListDownload() {
            @Override
            public void onDeptListDownloadSuccess(List response) {
                Log.i("mkl",response.toString());
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos,int op) {

                    }
                };
                engineGridViews.showDeoptGridList(response,recycler_view,context,R.layout.departments_item_curved,listener );

            }

            @Override
            public void onDeptListDownloadFailed(String msg) {

            }
        });
        //departments_item_curved

        // get the views and attach the listener
        //department_list

        return view;

    }
}