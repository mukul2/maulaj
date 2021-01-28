package com.telemedicine.maulaji.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.GeneralListener;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.adapter.SkillAdapterDoctor;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.StatusMessage;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;
import static com.telemedicine.maulaji.Fragments.HomeFragmentDrTwo.SKILLS;


public class SpecialSkillFragment extends Fragment {
    View v;
    Context context;
    @BindView(R.id.upload)
    ExtendedFloatingActionButton upload;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


    public static SpecialSkillFragment newInstance() {
        SpecialSkillFragment fragment = new SpecialSkillFragment();
        return fragment;
    }

    public SpecialSkillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.skill_fragment, container, false);
        context = v.getContext();
        ButterKnife.bind(this, v);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_skill_info_dialog);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                CardView cardDone = (CardView) dialog.findViewById(R.id.cardDone);
                TextView ed_body = (TextView) dialog.findViewById(R.id.ed_body);
                cardDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String body = ed_body.getText().toString().trim();
                        if (body.length() > 0) {
                            MyProgressBar.with(getContext()).show();
                            Api.getInstance().postSkillInfo(TOKEN, USER_ID, body, new ApiListener.PostSkillInfoListener() {
                                @Override
                                public void onPostSkillInfoSuccess(StatusMessage status) {
                                    if (status.getStatus() == true) {
                                        MyProgressBar.dismiss();
                                        dialog.dismiss();
                                        Toast.makeText(getContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
                                        GeneralListener.needRefresh.doRefresh(0);

                                    } else {
                                        Toast.makeText(getContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onPostSkillInfoFailed(String msg) {
                                    MyProgressBar.dismiss();

                                    dialog.dismiss();

                                }

                            });
                        }

                    }

                });


            }
        });
        initRecyclerView();


        return v;
    }

    private void initRecyclerView() {
        SkillAdapterDoctor mAdapter = new SkillAdapterDoctor(SKILLS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);

    }


}
