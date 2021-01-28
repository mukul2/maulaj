package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.telemedicine.maulaji.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CallGpNowHomeFragment extends Fragment {
    View view;
    Context context ;
    @BindView(R.id.linearLookDoctor)
    LinearLayout linearLookDoctor;


    public CallGpNowHomeFragment() {

    }

    public static HomeFragmentAlternativeMedX newInstance() {
        HomeFragmentAlternativeMedX fragment = new HomeFragmentAlternativeMedX();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_call_gp_now_home, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        linearLookDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 SpecialistBottomSheet addPhotoBottomDialogFragment = SpecialistBottomSheet.newInstance();
                 addPhotoBottomDialogFragment.show(getChildFragmentManager(), "add_photo_dialog_fragment");

            }
        });
        return  view;
    }
}