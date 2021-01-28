package com.telemedicine.maulaji.Utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.telemedicine.maulaji.model.CartItemsModel;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    List<CartItemsModel> cartList = new ArrayList<>();
    Context context;
    SessionManager sessionManager ;
    public CartManager with(Context c){
        this.context = c;
        this.sessionManager = new SessionManager(c);
        return new CartManager();
    }

    public void addItem(CartItemsModel data){

        String cartItemString="";
        if( sessionManager.get_Cart()!=null){
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>)gson. fromJson(cartItemString,
                    new TypeToken<ArrayList<CartItemsModel>>() {
                    }.getType());
            cartList.add(data);
            sessionManager.set_Cart(cartList.toString());
        }else{
            cartList.add(data);
            sessionManager.set_Cart(cartList.toString());
        }

    }

    public void getCart(){

        String cartItemString="";
        if( sessionManager.get_Cart()!=null){
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>)gson. fromJson(cartItemString,
                    new TypeToken<ArrayList<CartItemsModel>>() {
                    }.getType());

            sessionManager.set_Cart(cartList.toString());
        }else{

            sessionManager.set_Cart(cartList.toString());
        }

    }

}
