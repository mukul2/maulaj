package com.telemedicine.maulaji.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telemedicine.maulaji.R;


public class PrescriptionUploadFragment extends Fragment {


    public PrescriptionUploadFragment() {
    }


    public static PrescriptionUploadFragment newInstance() {
        PrescriptionUploadFragment fragment = new PrescriptionUploadFragment();

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
        return inflater.inflate(R.layout.fragment_prescription_upload, container, false);
    }
}