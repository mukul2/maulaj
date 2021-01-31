package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

public class ProductSearchActivity extends AppCompatActivity {
    @BindView(R.id.ed_key)
    EditText ed_key ;
    @BindView(R.id.tv_count)
    TextView tv_count ;
    @BindView(R.id.recycler)
    RecyclerView recycler ;
    Context context = this ;
    engineGridViews engineGridViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        ButterKnife.bind(this);
        engineGridViews = new engineGridViews();
        //search_medicine
        ed_key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = s.toString();
                if(key.length()>0){
                    tv_count.setVisibility(View.VISIBLE);
                    tv_count.setText("Please wait");
                    recycler.setVisibility(View.VISIBLE);
                    Api.getInstance().search_medicine(key, new ApiListener.MedDownloadListener() {
                        @Override
                        public void onMedDownloadSuccess(List<MedicineModel4> response) {
                            tv_count.setText(""+response.size()+" items found");

                          //  Toast.makeText(context, "found "+response.size(), Toast.LENGTH_SHORT).show();
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
                }else {
                    recycler.setVisibility(View.GONE);
                    tv_count.setVisibility(View.GONE);
                    tv_count.setText("");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}