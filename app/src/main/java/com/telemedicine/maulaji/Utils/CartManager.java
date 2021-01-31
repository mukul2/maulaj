package com.telemedicine.maulaji.Utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.telemedicine.maulaji.model.CartItemsModel;

import java.util.ArrayList;
import java.util.List;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PRODUCT;

public  class CartManager {
    List<CartItemsModel> cartList = new ArrayList<>();
    Context context;
    SessionManager sessionManager ;
    private static CartManager cartManager;
    public  CartManager with(Activity activity) {
        this.context = activity;
        this.sessionManager = new SessionManager(activity);
        return this;
    }
    public static CartManager getInstance() {

        if (cartManager == null) {
            cartManager = new CartManager();
        }
        return cartManager;
    }
    public void addItem(CartItemsModel data){

        String cartItemString="";
        if( sessionManager.get_Cart()!=null&& sessionManager.get_Cart().length()>0){
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>)gson. fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {}.getType());
            cartList.add(data);
          //  cartList.add(new CartItemsModel("test",""+123,1));

            sessionManager.set_Cart(gson.toJson(cartList));
        }else{
            cartList.add(data);
            Gson gson = new Gson();
            sessionManager.set_Cart(gson.toJson(cartList));
        }

    }

    public List<CartItemsModel> getCart(){

        String cartItemString="";
        if( sessionManager.get_Cart()!=null&&sessionManager.get_Cart().length()>0){
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
           cartList = (ArrayList<CartItemsModel>)gson. fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {}.getType());

          //  Toast.makeText(context, sessionManager.get_Cart(), Toast.LENGTH_SHORT).show();

           // sessionManager.set_Cart(cartList.toString());
        }else{

            Gson gson = new Gson();
            sessionManager.set_Cart(gson.toJson(cartList));
        }

        return  cartList;

    }

}
