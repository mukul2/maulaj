package com.telemedicine.maulaji.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.telemedicine.maulaji.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragmentNewDr extends Fragment {


    public BlogFragmentNewDr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_fragment_new_dr, container, false);
    }

}
