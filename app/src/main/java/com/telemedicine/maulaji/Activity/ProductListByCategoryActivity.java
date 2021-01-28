package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.MedicineModel4;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PRODUCT;

public class ProductListByCategoryActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;
    Context context = this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_by_category);
        ButterKnife.bind(this);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        engineGridViews = new engineGridViews();
        if(b!=null)
        {
            String type =(String) b.get("type");
            String key =(String) b.get("key");
            tv_title.setText(key);

            if(type.equals("category")) {

                Api.getInstance().all_medicines(key, new ApiListener.MedDownloadListener() {
                    @Override
                    public void onMedDownloadSuccess(List<MedicineModel4> response) {
                        //Toast.makeText(ProductListByCategoryActivity.this, "med size " + response.size(), Toast.LENGTH_SHORT).show();
                        com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                            @Override
                            public void onSelected(int pos, int optional) {
                                NOW_SHOWING_PRODUCT = response.get(pos);
                                startActivity(new Intent(context,ProductBodyDetailActivity.class));


                            }


                        };
                        engineGridViews.showShopMedicineList(response, recycler, context, R.layout.shod_medicine_item, listener);

                    }

                    @Override
                    public void onMedDownloadFailed(String msg) {
                        Toast.makeText(ProductListByCategoryActivity.this, msg, Toast.LENGTH_SHORT).show();

                    }
                });
            }

            if(type.equals("brand")) {

                Api.getInstance().all_medicinesbyBrand(key, new ApiListener.MedDownloadListener() {
                    @Override
                    public void onMedDownloadSuccess(List<MedicineModel4> response) {
                        //Toast.makeText(ProductListByCategoryActivity.this, "med size " + response.size(), Toast.LENGTH_SHORT).show();
                        com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                            @Override
                            public void onSelected(int pos, int optional) {
                                NOW_SHOWING_PRODUCT = response.get(pos);
                                startActivity(new Intent(context,ProductBodyDetailActivity.class));

                            }


                        };
                        engineGridViews.showShopMedicineList(response, recycler, context, R.layout.shod_medicine_item, listener);

                    }

                    @Override
                    public void onMedDownloadFailed(String msg) {
                        Toast.makeText(ProductListByCategoryActivity.this, msg, Toast.LENGTH_SHORT).show();

                    }
                });
            }


        }
    }

    public void back(View view) {
        onBackPressed();
    }
}