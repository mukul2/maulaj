package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.CartManager;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.model.CartItemsModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PRODUCT;
import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE_PHARMACY;

public class ProductBodyDetailActivity extends AppCompatActivity {
    @BindView(R.id.subcategory)
    TextView subcategory;
    @BindView(R.id.tv_description)
    TextView tv_description;
    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_name2)
    TextView tv_name2;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_cartStatus)
    TextView tv_cartStatus;
    Context context = this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_body_detail);
        ButterKnife.bind(this);
        Glide.with(context).load(PHOTO_BASE_PHARMACY+NOW_SHOWING_PRODUCT.getImg()+".jpg").into(img);
       // tv_name.setText(NOW_SHOWING_PRODUCT.getName());
        tv_name2.setText(NOW_SHOWING_PRODUCT.getName());
        tv_price.setText("MRP "+NOW_SHOWING_PRODUCT.getPrice());
        tv_cartStatus.setText("ADD TO CART");
        category.setText(NOW_SHOWING_PRODUCT.getCategory());
        subcategory.setText(NOW_SHOWING_PRODUCT.getSubcategory());
        tv_description.setText(NOW_SHOWING_PRODUCT.getDescription());

        tv_cartStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.getInstance().with(ProductBodyDetailActivity.this).addItem(new CartItemsModel(NOW_SHOWING_PRODUCT.getName(),NOW_SHOWING_PRODUCT.getImg(),""+NOW_SHOWING_PRODUCT.getId(),1,Float.parseFloat(NOW_SHOWING_PRODUCT.getPrice().replaceAll("[^\\d.]", ""))));
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NOW_SHOWING_PRODUCT = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NOW_SHOWING_PRODUCT = null;
    }
}