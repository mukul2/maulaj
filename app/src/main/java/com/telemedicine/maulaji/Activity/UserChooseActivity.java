package com.telemedicine.maulaji.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.telemedicine.maulaji.Fragments.LoginFragment;
import com.telemedicine.maulaji.Fragments.PhonVerificationBottomSheet;
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
    LoginActivity.OpenOtherFragmentListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choose);
        ButterKnife.bind(this);
        i = new Intent(this,LoginActivity.class);
       // Glide.with(this).load(R.drawable.mother_child_bg).apply(RequestOptions.bitmapTransform(new BlurTransformation(15,3))).into(imgNurse);
        listener =   new LoginActivity.OpenOtherFragmentListener() {
            @Override
            public void open(String type) {
                openPhonVerificationFragmemnt(type);
            }
        };


    }

    public void openPhonVerificationFragmemnt(String t) {
        PhonVerificationBottomSheet frag = PhonVerificationBottomSheet.newInstance(t,"");
        frag.show(getSupportFragmentManager(), "add_photo_dialog_fragment");

    }
    public  void PatientLogin(View view){
        i.putExtra("loginUserType","p");
      //  startActivity(i);
        LoginFragment addPhotoBottomDialogFragment = LoginFragment.newInstance("p",listener);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "add_photo_dialog_fragment");

    }
    public  void DocLogin(View view){
        i.putExtra("loginUserType","d");
      //  startActivity(i);
        LoginFragment addPhotoBottomDialogFragment = LoginFragment.newInstance("d",listener);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "add_photo_dialog_fragment");

    }

    public void GuestLogin(View view) {


        startActivity(new Intent(this,GuestActivity.class));
    }
}