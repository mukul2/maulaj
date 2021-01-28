package com.telemedicine.maulaji.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.telemedicine.maulaji.Activity.HomeActivityDrActivity;
import com.telemedicine.maulaji.Activity.OTPLoginActivity;
import com.telemedicine.maulaji.Activity.PatientHomeActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.Utils.SessionSaveListener;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.DoctorLoginModel;
import com.telemedicine.maulaji.model.PatientLoginModel;
import com.telemedicine.maulaji.model.PatientprofileOtpModel;
import com.telemedicine.maulaji.model.UserInfo;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {
    View view;
    Context context;

    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ehckOtp)
    CheckBox ehckOtp;
    @BindView(R.id.relativePassBox)
    RelativeLayout relativePassBox;
    @BindView(R.id.linearLogin)
    LinearLayout linearLogin;
    @BindView(R.id.ed_password)
    EditText ed_password;
    String phone, password;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String DOCTOR = "d";
    String PATIENT = "p";
    String userType;

    enum loginType {OTP, PASSWORD}

    loginType prefferedLoginType = loginType.PASSWORD;

    //changes by 15:50 secondsdad
    ApiListener.LoginUserListener loginUserListenerP;
    ApiListener.DocLoginListener loginUserListenerD;
    // TODO: Rename and change types of parameters


    public LoginFragment(String u) {
        // Required empty public constructor

        this.userType = u;
    }


    public static LoginFragment newInstance(String userType) {
        LoginFragment fragment = new LoginFragment(userType);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(context);

        loginUserListenerP = new ApiListener.LoginUserListener() {
            @Override
            public void onUserLoginSuccess(PatientLoginModel response) {
                progressDialog.dismiss();
                Gson gson = new Gson();
                Log.i("mkl", gson.toJson(response));

                if (response.getStatus()) {
                    //Toast.makeText(context, "phone "+response.getUserInfo().getPhone().toString(), Toast.LENGTH_SHORT).show();
                    sessionManager.setuserId("" + response.getUserInfo().getPatientId());
                    sessionManager.setLoggedIn(true);
                    sessionManager.setuserName(response.getUserInfo().getUsername());
                    sessionManager.setuserType(userType);
                    sessionManager.setToken("Bearer " + response.getAccessToken());
                    sessionManager.set_userPhoto(response.getUserInfo().getImageUrl());
                    sessionManager.set_userMobile(response.getUserInfo().getPhone() == null ? "" : response.getUserInfo().getPhone().toString());
                    sessionManager.set_userEmail(response.getUserInfo().getEmail());
                    sessionManager.set_userdisplay(response.getUserInfo().getCompany() != null ? response.getUserInfo().getCompany().toString() : "");
                    sessionManager.set_userPass(ed_password.getText().toString().trim());
//            if (response.getUserInfo().g == 0) {
//                sessionManager.set_userStatus(false);
//                USER_ENABLED = false;
//            } else {
//                sessionManager.set_userStatus(true);
//                USER_ENABLED = true;
//            }

                    // Toast.makeText(this, status.getMessage(), Toast.LENGTH_LONG).show();

                    if (userType.equals(DOCTOR)) {
                        //  startActivity(new Intent(LoginActivity.this, HomeActivityDrActivity.class));
                        //  finishAffinity();
                    } else if (userType.equals(PATIENT)) {
                        startActivity(new Intent(context, PatientHomeActivity.class));
                        ((Activity) context).finishAffinity();

                    } else {
                        Toast.makeText(context, "Unknown usertype", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    MyDialog.getInstance().with(context)
                            .message("Wrong mobile or password")
                            .autoBack(false)
                            .autoDismiss(false)
                            .show();
                }
            }

            @Override
            public void onUserLoginFailed(String msg) {
                progressDialog.dismiss();
                MyDialog.getInstance().with(context)
                        .message(msg)
                        .autoBack(false)
                        .autoDismiss(false)
                        .show();
            }
        };
        loginUserListenerD = new ApiListener.DocLoginListener() {
            @Override
            public void onDocLoginSuccess(DoctorLoginModel response) {
                progressDialog.dismiss();
                Gson gson = new Gson();
                //Log.i("mkl",gson.toJson(response));


                //Toast.makeText(context, response.getString().get, Toast.LENGTH_SHORT).show();

                if (response.getStatus()) {
                    sessionManager.setuserId(response.getUserInfo().getIonUserId());
                    sessionManager.setLoggedIn(true);
                    sessionManager.setuserName(response.getUserInfo().getUsername());
                    sessionManager.setuserType(userType);
                    sessionManager.setToken("Bearer ");
                    sessionManager.set_userPhoto(response.getUserInfo().getImageUrl());
                    // sessionManager.set_userMobile(userObject.get("image_url")==null?"":response.getUserInfo().getPhone().toString());
                    sessionManager.set_userEmail(response.getUserInfo().getEmail());
                    // sessionManager.set_userdisplay(response.getUserInfo().getCompany()!=null?response.getUserInfo().getCompany().toString():"");
                    sessionManager.set_userPass(ed_password.getText().toString().trim());

                    sessionManager.set_drHospital(response.getUserInfo().getHospitalIonId());


//            if (response.getUserInfo().g == 0) {
//                sessionManager.set_userStatus(false);
//                USER_ENABLED = false;
//            } else {
//                sessionManager.set_userStatus(true);
//                USER_ENABLED = true;
//            }

                    // Toast.makeText(this, status.getMessage(), Toast.LENGTH_LONG).show();

                    if (userType.equals(DOCTOR)) {
                        startActivity(new Intent(context, HomeActivityDrActivity.class));
                        ((Activity) context).finishAffinity();
                    } else if (userType.equals(PATIENT)) {
                        startActivity(new Intent(context, PatientHomeActivity.class));
                        ((Activity) context).finishAffinity();

                    } else {
                        Toast.makeText(context, "Unknown usertype", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    MyDialog.getInstance().with(context)
                            .message("Wrong mobile or password")
                            .autoBack(false)
                            .autoDismiss(false)
                            .show();
                }
            }

            @Override
            public void onDocLoginFailed(String msg) {
                Log.i("mkl", msg);
                progressDialog.dismiss();
                MyDialog.getInstance().with(context)
                        .message(msg)
                        .autoBack(false)
                        .autoDismiss(false)
                        .show();
            }
        };
        linearLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = MyProgressBar.with(context);


                if(prefferedLoginType ==loginType.PASSWORD) {
                    HashMap<String, String> request = new HashMap<String, String>();
                    request.put("email", ed_phone.getText().toString().trim());
                    request.put("password", ed_password.getText().toString().trim());
                    if (userType.equals(PATIENT)) {
                        Api.getInstance().loginUser(request, loginUserListenerP);
                    } else {
                        Api.getInstance().loginDoctor(request, loginUserListenerD);

                    }
                }else{
                    HashMap<String, String> request = new HashMap<String, String>();
                    request.put("email", ed_phone.getText().toString().trim());
                    request.put("phone", ed_phone.getText().toString().trim());
                    if (userType.equals(PATIENT)) {
                        Api.getInstance().find_patient_id(request, new ApiListener.patientAccountFindListener() {
                            @Override
                            public void onpatientAccountFindSuccess(PatientprofileOtpModel response) {
                                Log.i("mkl",response.toString());
                                progressDialog.dismiss();
                                SessionSaveListener.setListener(new SessionSaveListener.SesssionSaveListener() {
                                    @Override
                                    public void onSucced() {

                                        sessionManager.setuserId("" + response.getId());
                                        sessionManager.setLoggedIn(true);
                                        sessionManager.setuserName(response.getName()+" saved my listener");
                                        sessionManager.setuserType(userType);
                                        sessionManager.setToken("Bearer " );
                                        sessionManager.set_userPhoto(response.getImgUrl());
                                        sessionManager.set_userMobile(response.getPhone() == null ? "" : response.getPhone().toString());
                                        sessionManager.set_userEmail(response.getEmail());
                                    }
                                });


                                Intent i = new Intent(context, OTPLoginActivity.class);
                                Toast.makeText(context,response.getPhone() , Toast.LENGTH_SHORT).show();
                                i.putExtra("phone",response.getPhone());
                                i.putExtra("userType",userType);
                                startActivity(i);
                            }

                            @Override
                            public void onpatientAccountFindFailed(String msg) {
                                Log.i("mkl",msg);
                                progressDialog.dismiss();
                            }
                        });
                    } else {
                        Api.getInstance().loginDoctor(request, loginUserListenerD);

                    }
                }

            }
        });
        ehckOtp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    relativePassBox.setAlpha(0.3f);
                    ed_password.setEnabled(false);
                    prefferedLoginType = loginType.OTP;
                } else {
                    relativePassBox.setAlpha(1.0f);
                    ed_password.setEnabled(true);
                    prefferedLoginType = loginType.PASSWORD;
                }
            }
        });

        return view;
    }
}