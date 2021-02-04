package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.LiveResponce.MessageEvent;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.CartManager;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.model.CartItemsModel;

import org.greenrobot.eventbus.EventBus;

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
                CustomDialog();
           /*     CartManager.getInstance().with(ProductBodyDetailActivity.this).addItem(new CartItemsModel(NOW_SHOWING_PRODUCT.getName(),
                        NOW_SHOWING_PRODUCT.getImg(),
                        ""+NOW_SHOWING_PRODUCT.getId(),
                        1,Float.parseFloat(NOW_SHOWING_PRODUCT.getPrice().replaceAll("[^\\d.]", "")),Float.parseFloat(NOW_SHOWING_PRODUCT.getPrice().replaceAll("[^\\d.]", ""))));
            */}
        });


    }
 public void CustomDialog(){
     final Dialog dialog = new Dialog(context);
     // Include dialog.xml file
     dialog.setContentView(R.layout.dialog_add_cart);
     // Set dialog title
     dialog.setTitle("Add To Cart");


     // set values for custom dialog components - text, image and button
     TextView tv_name = (TextView) dialog.findViewById(R.id.tv_name);
     TextView tv_price = (TextView) dialog.findViewById(R.id.tv_price);
     TextView tv_quantity = (TextView) dialog.findViewById(R.id.tv_quantity);
     TextView tvUnitTotal = (TextView) dialog.findViewById(R.id.tvUnitTotal);
     TextView tv_Posetive = (TextView) dialog.findViewById(R.id.tv_Posetive);
     TextView tvNegative = (TextView) dialog.findViewById(R.id.tvNegative);
     Button btnCancle = (Button) dialog.findViewById(R.id.btnCancle);
     Button btnADDTOcart = (Button) dialog.findViewById(R.id.btnADDTOcart);
     ImageView image = (ImageView) dialog.findViewById(R.id.img);
     dialog.show();
     Glide.with(context).load(PHOTO_BASE_PHARMACY+NOW_SHOWING_PRODUCT.getImg()+".jpg").into(image);
     // tv_name.setText(NOW_SHOWING_PRODUCT.getName());
     tv_name.setText(NOW_SHOWING_PRODUCT.getName());
     tv_price.setText("MRP "+NOW_SHOWING_PRODUCT.getPrice());
     try{
         tvNegative.setOnClickListener((View view) -> {
             if (Integer.parseInt(tv_quantity.getText().toString()) >= 1) {
                 int i = Integer.parseInt(tv_quantity.getText().toString()) - 1;
                 float ut = i * Integer.parseInt(tv_price.getText().toString());

                 tv_quantity.setText(String.valueOf(i));
                 tvUnitTotal.setText("MRP. " + ut);
             }


         });
     }
     catch (Exception e){
         Log.d("tag",e.toString());
     }

     try{
         tv_Posetive.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int i = Integer.parseInt(tv_quantity.getText().toString()) + 1;
                 float ut = i * Integer.parseInt(tv_price.getText().toString());

                 tv_quantity.setText(String.valueOf(i));
                 tvUnitTotal.setText("MRP. " + ut);
             }
         });
     }
     catch (Exception e){
         Log.d("tag",e.toString());
     }




     // if decline button is clicked, close the custom dialog
     btnCancle.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             // Close dialog
             dialog.dismiss();
         }
     });
     btnADDTOcart.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             CartManager.getInstance().with(ProductBodyDetailActivity.this).addItem(new CartItemsModel(NOW_SHOWING_PRODUCT.getName(),
                     NOW_SHOWING_PRODUCT.getImg(),
                     ""+NOW_SHOWING_PRODUCT.getId(),
                     Integer.parseInt(tv_quantity.getText().toString()),Float.parseFloat(NOW_SHOWING_PRODUCT.getPrice().replaceAll("[^\\d.]", "")),Float.parseFloat(tvUnitTotal.getText().toString())));
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