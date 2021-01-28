package com.telemedicine.maulaji.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class UserChooseActivity extends BaseActivity {
    Intent i;
    @BindView(R.id.imgNurse)
    ImageView imgNurse;
    SessionManager sessionManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choose);
        ButterKnife.bind(this);
        i = new Intent(this,LoginActivity.class);
       // Glide.with(this).load(R.drawable.mother_child_bg).apply(RequestOptions.bitmapTransform(new BlurTransformation(15,3))).into(imgNurse);


    }

    public  void PatientLogin(View view){
        i.putExtra("loginUserType","p");
        startActivity(i);

    }
    public  void DocLogin(View view){
        i.putExtra("loginUserType","d");
        startActivity(i);

    }

    public void GuestLogin(View view) {


        startActivity(new Intent(this,GuestActivity.class));
    }
}