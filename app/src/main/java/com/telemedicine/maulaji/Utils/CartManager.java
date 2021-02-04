package com.telemedicine.maulaji.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.telemedicine.maulaji.model.CartItemsModel;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    List<CartItemsModel> cartList = new ArrayList<>();
    Context context;
    SessionManager sessionManager;
    private static CartManager cartManager;

    public CartManager with(Activity activity) {
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

    public void addItem(CartItemsModel data) {

        String cartItemString = "";
        if (sessionManager.get_Cart() != null && sessionManager.get_Cart().length() > 0) {
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>) gson.fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {
            }.getType());
            cartList.add(data);
            //  cartList.add(new CartItemsModel("test",""+123,1));

            sessionManager.set_Cart(gson.toJson(cartList));
        } else {
            cartList.add(data);
            Gson gson = new Gson();
            sessionManager.set_Cart(gson.toJson(cartList));
        }

    }

    public void UpdateItem(CartItemsModel data, int quantity, float Unitprice, String id) {

        String cartItemString = "";
        if (sessionManager.get_Cart() != null && sessionManager.get_Cart().length() > 0) {
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>) gson.fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {
            }.getType());

            for (int l = 0; l < cartList.size(); l++) {
                //Log.d("tag","cartList "+cartList.get(l));
                if (cartList.get(l).getId().equals(id)) {
                    /*   cartList.set(l,data);*/
                    data.setQuantity(quantity);
                    data.setUnitprice(Unitprice);
                    cartList.set(l, data);
                    sessionManager.set_Cart(gson.toJson(cartList));
                    Log.d("tag","list "+cartList.get(l).getUnitprice()+" qun "+cartList.get(l).getQuantity());
                    break;
                }

            }
            // cartList.rep(data);
            //  cartList.add(new CartItemsModel("test",""+123,1));


        } /*else {
            cartList.add(data);
            Gson gson = new Gson();
            sessionManager.set_Cart(gson.toJson(cartList));
        }*/

    }

    public void RemoveItem(CartItemsModel data, String id) {

        String cartItemString = "";
        if (sessionManager.get_Cart() != null && sessionManager.get_Cart().length() > 0) {
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>) gson.fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {
            }.getType());
            for (int q = 0; q < cartList.size(); q++) {
                if (cartList.get(q).getId().equals(id)) {
                    Log.d("tag", "equals " + cartList.get(q).getId());
                    try {
                        cartList.remove(q);
                        Log.d("tag", "Remove " + id);
                    } catch (Exception e) {
                        Log.d("tag", "Exception " + e.toString());
                    }
                    sessionManager.set_Cart(gson.toJson(cartList));
                }
            }

        }

    }

    public List<CartItemsModel> getCart() {

        String cartItemString = "";
        if (sessionManager.get_Cart() != null && sessionManager.get_Cart().length() > 0) {
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>) gson.fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {
            }.getType());

            //  Toast.makeText(context, sessionManager.get_Cart(), Toast.LENGTH_SHORT).show();

            // sessionManager.set_Cart(cartList.toString());
        } else {

            Gson gson = new Gson();
            sessionManager.set_Cart(gson.toJson(cartList));
        }

        return cartList;

    }


    public float getTotal() {
        float total = 0;

        String cartItemString = "";
        if (sessionManager.get_Cart() != null && sessionManager.get_Cart().length() > 0) {
            cartItemString = sessionManager.get_Cart();
            Gson gson = new Gson();
            cartList = (ArrayList<CartItemsModel>) gson.fromJson(cartItemString, new TypeToken<ArrayList<CartItemsModel>>() {
            }.getType());
            for (int i = 0; i < cartList.size(); i++) {
                total = total + cartList.get(i).getUnitprice();
            }
            //  Toast.makeText(context, sessionManager.get_Cart(), Toast.LENGTH_SHORT).show();

            // sessionManager.set_Cart(cartList.toString());
        } /*else {

            Gson gson = new Gson();
            sessionManager.set_Cart(gson.toJson(cartList));
        }*/

        return total;

    }

}
