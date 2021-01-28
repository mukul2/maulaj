package com.telemedicine.maulaji.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telemedicine.maulaji.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiagnosticInvestigationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiagnosticInvestigationsFragment extends Fragment {


    public DiagnosticInvestigationsFragment() {
        // Required empty public constructor
    }


    public static DiagnosticInvestigationsFragment newInstance(String param1, String param2) {
        DiagnosticInvestigationsFragment fragment = new DiagnosticInvestigationsFragment();

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
        return inflater.inflate(R.layout.fragment_diagnostic_investigations, container, false);
    }
}