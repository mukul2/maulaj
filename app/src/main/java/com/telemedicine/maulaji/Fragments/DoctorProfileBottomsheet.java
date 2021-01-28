package com.telemedicine.maulaji.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.DoctorModelRaw;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorProfileBottomsheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorProfileBottomsheet extends BottomSheetDialogFragment {


    DoctorModelRaw doc ;
    public DoctorProfileBottomsheet() {
        // Required empty public constructor
    }


    public static DoctorProfileBottomsheet newInstance(DoctorModelRaw data  )  {
        DoctorProfileBottomsheet fragment = new DoctorProfileBottomsheet();

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
        return inflater.inflate(R.layout.fragment_doctor_profile_bottomsheet, container, false);
    }
}