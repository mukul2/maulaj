package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivityPatient extends AppCompatActivity {
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    Context context = this;
    boolean isEmailUnique = false;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.cardSubmit)
    CardView cardSubmit;
    @BindView(R.id.tvtitle)
    TextView tvtitle;
    String userType = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_patient);
        ButterKnife.bind(this);

        //number
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String number = (String) b.get("number");
            String signUpas = (String) b.get("signUpas");
            String countrycode = (String) b.get("countrycode");
            String wasItGuest = (String) b.get("wasItGuest");
            if(wasItGuest.equals("1")){
                String tempName = (String) b.get("tempName");
                String tempEmail = (String) b.get("tempEmail");
                ed_name.setText(tempName);
                ed_email.setText(tempEmail);
            }
            if (signUpas.equals("p")){
                tvtitle.setText("Signup as Patient");
                userType = "0";
            }else {
                userType = "1";
                tvtitle.setText("Signup as Doctor");
            }
            ed_phone.setText(number);

            ed_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String email = ed_email.getText().toString().trim();
                        if (email.length() > 0) {
                            Api.getInstance().apiuniqueemail(email, new ApiListener.PhoneUniqueCheckListener() {
                                @Override
                                public void onPhoneUniqueCheckSuccess(String response) {
                                    if (response.equals("0")) {
                                        progressBar.setVisibility(View.GONE);
                                        isEmailUnique = true;

                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        ed_email.setError("Email is allready used");
                                    }
                                }

                                @Override
                                public void onPhoneUniqueCheckFailed(String msg) {
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                                }
                            });
                        } else {
                            ed_email.setError(null);
                        }


                    }
                }
            });

            cardSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = ed_name.getText().toString().trim();
                    String password = ed_password.getText().toString().trim();
                    String email = ed_email.getText().toString().trim();
                    String phone = ed_phone.getText().toString().trim();
                    if(name.length()>0){
                        if(password.length()>0){
                            if(email.length()>0){
                                if(isEmailUnique){
                                    if(phone.length()>0){
                                        MyProgressBar.with(context);
                                        Api.getInstance().patient_register(name, phone, email, password, countrycode, userType, new ApiListener.SignupListener() {
                                            @Override
                                            public void onSignupSuccess(String response) {
                                                MyProgressBar.dismiss();
                                                if(response.equals("1")) {
                                                    MyDialog.getInstance().with(context)
                                                            .message("Signup is successfully done.Please check your inbox/spam and click the verify link to active your account")
                                                            .autoBack(false)
                                                            .autoDismiss(false)
                                                            .show();
                                                    onBackPressed();
                                                    onBackPressed();
                                                    onBackPressed();
                                                    onBackPressed();

                                                }else{
                                                    MyDialog.getInstance().with(context)
                                                            .message(response)
                                                            .autoBack(false)
                                                            .autoDismiss(false)
                                                            .show();
                                                }

                                            }

                                            @Override
                                            public void onSignupFailed(String msg) {
                                                MyProgressBar.dismiss();
                                                MyDialog.getInstance().with(context)
                                                        .message("Error occured.Please try another time")
                                                        .autoBack(false)
                                                        .autoDismiss(false)
                                                        .show();
                                            }
                                        });


                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public void back(View view) {
        onBackPressed();
    }
}