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

import com.telemedicine.maulaji.Activity.ProductListByCategoryActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ByConditionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ByConditionsFragment extends Fragment {
    View view;
    Context context;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    String k ;

    engineGridViews engineGridViews;

    public ByConditionsFragment() {


    }


    public static ByConditionsFragment newInstance() {
        ByConditionsFragment fragment = new ByConditionsFragment();

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
        view = inflater.inflate(R.layout.fragment_by_conditions, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        engineGridViews = new engineGridViews();
        Api.getInstance().all_medicinesCategory(new ApiListener.MedCatDownloadListener() {
            @Override
            public void onMedCatDownloadSuccess(List<String> response) {
              //  Toast.makeText(context, "" + response.size(), Toast.LENGTH_SHORT).show();
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos, int optional) {

                        Intent intent = new Intent(context, ProductListByCategoryActivity.class);

                        intent.putExtra("type", "category");
                        intent.putExtra("key", response.get(pos));
                        startActivity(intent);


                    }


                };
                engineGridViews.showShopCategoryList(response, recycler, context, R.layout.category_list_item, listener);


            }

            @Override
            public void onMedCatDownloadFailed(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();


            }
        });
        return  view;
    }
}