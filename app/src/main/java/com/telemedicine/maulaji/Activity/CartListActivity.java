package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.telemedicine.maulaji.Fragments.CheckoutFragment;
import com.telemedicine.maulaji.Fragments.UploadPrescriptionBottomSheet;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.CartManager;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.Utils.Utillites;
import com.telemedicine.maulaji.model.CartItemsModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PRODUCT;

public class CartListActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.cartTotalPrice)
    TextView cartTotalPrice;
    @BindView(R.id.cardCheckout)
    CardView cardCheckout;
    engineGridViews engineGridViews;
    Context context = this;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
       // Utillites.thisContext(context);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);

        engineGridViews = new engineGridViews();
        List<CartItemsModel> list = CartManager.getInstance().with(CartListActivity.this).getCart();
        float total = 0 ;
        if(list.size()>0){
            for (int i = 0 ;i <list.size();i++){
                total = total+list.get(i).getPrice();
            }
            cartTotalPrice.setText("Total : "+total+" RS");

        }else {
            cartTotalPrice.setText("Total : "+00+" RS");
        }

        if (list.size() > 0) {
            cardCheckout.setVisibility(View.VISIBLE);
            cardCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //CheckoutFragment
                    Gson gson= new Gson();
                    CheckoutFragment addPhotoBottomDialogFragment = CheckoutFragment.newInstance(gson.toJson(list));
                    addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                            "add_photo_dialog_fragment");
                }
            });
        } else {
            cardCheckout.setVisibility(View.GONE);
        }
        // Toast.makeText(this, "items are in cart "+list.size(), Toast.LENGTH_SHORT).show();
        com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
            @Override
            public void onSelected(int pos, int optional) {
                // NOW_SHOWING_PRODUCT = response.get(pos);
                //   startActivity(new Intent(context, ProductBodyDetailActivity.class));

            }


        };
        engineGridViews.showCartList(list, recycler, context, R.layout.cart_item, listener);

    }


    public void back(View view) {
        onBackPressed();
    }
}