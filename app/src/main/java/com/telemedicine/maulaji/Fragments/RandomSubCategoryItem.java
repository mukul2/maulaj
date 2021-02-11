package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.telemedicine.maulaji.Activity.ProductBodyDetailActivity;
import com.telemedicine.maulaji.Activity.ProductListByCategoryActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.MedicineModel4;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomSubCategoryItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomSubCategoryItem extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    View view ;
    Context context ;
    String key;
    engineGridViews engineGridViews;
    public RandomSubCategoryItem(String k) {
        // Required empty public constructor
        this.key = k;
    }

    public static RandomSubCategoryItem newInstance(String key) {
        RandomSubCategoryItem fragment = new RandomSubCategoryItem(key);

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
        view = inflater.inflate(R.layout.fragment_random_sub_category_item, container, false);
        ButterKnife.bind(this,view);
        context = view.getContext();
        engineGridViews = new engineGridViews();
        HashMap<String,String>hashMap= new HashMap<>();
        hashMap.put("key",key);
        Api.getInstance().all_medicines(hashMap, new ApiListener.MedDownloadListener() {
            @Override
            public void onMedDownloadSuccess(List<MedicineModel4> response) {
                //Toast.makeText(context, "med size " + response.size(), Toast.LENGTH_SHORT).show();
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos, int optional) {
                        NOW_SHOWING_PRODUCT = response.get(pos);
                        startActivity(new Intent(context, ProductBodyDetailActivity.class));

                    }


                };
                engineGridViews.showShopMedicineList(response, recycler, context, R.layout.shod_medicine_item, listener);

            }

            @Override
            public void onMedDownloadFailed(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        });

        return  view;
    }
}