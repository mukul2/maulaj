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
import com.telemedicine.maulaji.LiveResponce.MessageEvent;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.CartManager;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.Utils.Utillites;
import com.telemedicine.maulaji.model.CartItemsModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PRODUCT;

public class CartListActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.cardCheckout)
    CardView cardCheckout; @BindView(R.id.tvTotal)
    TextView tvTotal;
    engineGridViews engineGridViews;
    Context context = this;
    SessionManager sessionManager;
   public static float Total;
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        Utillites.thisContext(context);
        Toast.makeText(context, " this is " + context, Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);

        engineGridViews = new engineGridViews();
        Total=CartManager.getInstance().with(CartListActivity.this).getTotal();
        tvTotal.setText(String.valueOf(Total));
        List<CartItemsModel> list = CartManager.getInstance().with(CartListActivity.this).getCart();
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent ) {
        if (messageEvent.isSuccess()){
            tvTotal.setText(String.valueOf(Total));
        }

    }
      //  engineGridViews.showCartList(CartManager.getInstance().with(CartListActivity.this).getCart(), recycler, context, R.layout.cart_item);};
}