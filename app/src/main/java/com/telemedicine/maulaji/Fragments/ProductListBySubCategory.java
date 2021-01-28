package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telemedicine.maulaji.R;

public class ProductListBySubCategory extends Fragment {

    View view;
    Context context;
    String k ;


    public ProductListBySubCategory(String key) {
        this.k = key;

    }


    public static ProductListBySubCategory newInstance(String key) {
        ProductListBySubCategory fragment = new ProductListBySubCategory(key);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_list_by_sub_category, container, false);
        context = view.getContext();
        return view;
    }
}