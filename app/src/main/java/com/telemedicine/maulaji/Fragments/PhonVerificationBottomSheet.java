package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.telemedicine.maulaji.Activity.PhonVarificationActivity;
import com.telemedicine.maulaji.Activity.SignUpActivity;
import com.telemedicine.maulaji.Activity.SignupActivityPatient;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.StatusMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.TEMP_SAVE_GUEST_EMAIL;
import static com.telemedicine.maulaji.Data.Data.TEMP_SAVE_GUEST_NAME;
import static com.telemedicine.maulaji.Data.DataStore.VERIFICATION_PHONE_NUMBER;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhonVerificationBottomSheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhonVerificationBottomSheet extends BottomSheetDialogFragment {
    Context context;
    View view;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.tv_showInstruction)
    TextView tv_showInstruction;
    @BindView(R.id.cardSubmit)
    CardView cardSubmit;
    @BindView(R.id.cardVerifyOTP)
    CardView cardVerifyOTP;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth firebaseAuth;
    String mVerificationId;
    @BindView(R.id.linearOne)
    LinearLayout linearOne;
    @BindView(R.id.linearTwo)
    LinearLayout linearTwo;
    @BindView(R.id.ed_code)
    EditText ed_code;

    @BindView(R.id.spinner)
    Spinner spinner;
    String selectedCountryCode ;
    String signUpas ;
    String pre_sent_phone;
    public PhonVerificationBottomSheet(String u,String phone) {
        this.signUpas = u;
        pre_sent_phone = phone;

        // Required empty public constructor
    }

    public static PhonVerificationBottomSheet newInstance(String signUpas,String phone) {
        PhonVerificationBottomSheet fragment = new PhonVerificationBottomSheet(signUpas,phone);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_phon_verification_bottom_sheet, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        ed_phone.setText(pre_sent_phone);

        cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_phone.setError(null);
                String number =selectedCountryCode+ed_phone.getText().toString().trim();
                if (number.length() > 5) {
                    Api.getInstance().apiuniquephonenumber(number, new ApiListener.PhoneUniqueCheckListener() {
                        @Override
                        public void onPhoneUniqueCheckSuccess(String response) {
                            try{
                                if (response.equals("0")) {
                                    request_sms_varification(number);
                                } else {
                                    MyDialog.getInstance().with(getActivity())
                                            .message("Sorry, this phone number is allready used.Try login or use another number for signup")
                                            .autoBack(false)
                                            .autoDismiss(false)
                                            .showMsgOnly();
                                }
                            }catch (Exception e){
                                MyDialog.getInstance().with(getActivity())
                                        .message(e.getMessage())
                                        .autoBack(false)
                                        .autoDismiss(false)
                                        .showMsgOnly();
                            }

                        }

                        @Override
                        public void onPhoneUniqueCheckFailed(String msg) {
                            Toast.makeText(context, "Please input phone number", Toast.LENGTH_SHORT).show();

                        }
                    });

                }else{
                    Toast.makeText(context, number, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cardVerifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compare_pin();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();

        initSpinner();
        return view;
    }

    private void initSpinner() {
        List<String> coutryCode = new ArrayList<String>();
        coutryCode.add("+44");
        coutryCode.add("+92");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, coutryCode);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountryCode = coutryCode.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void compare_pin() {
        MyProgressBar.with(context);
        if (ed_code.getText().toString().trim().length() > 3) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, ed_code.getText().toString().trim());
            signInWithPhoneAuthCredential(credential);
        }
    }
    private void request_sms_varification(String phoneNumber) {
        //   progressDial.show();

        MyProgressBar.with(context);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                MyProgressBar.with(context);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                MyProgressBar.dismiss();
                ed_phone.setError(e.getLocalizedMessage());

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    Toast.makeText(context, "Invalid request", Toast.LENGTH_LONG).show();

                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    Toast.makeText(context, "The SMS quota for the project has been exceeded", Toast.LENGTH_LONG).show();

                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
               MyProgressBar.dismiss();
                mVerificationId = verificationId;
               // Toast.makeText(context, "A pin has been sent to " + phoneNumber + " Enter The Verification Code You Have Received Through SMS", Toast.LENGTH_SHORT).show();
                tv_showInstruction.setText("A pin has been sent to " + phoneNumber + " Enter The Verification Code You Have Received Through SMS");
                // ed_number.setEnabled(false);
                VERIFICATION_PHONE_NUMBER = phoneNumber;

                //  progressDial.dismiss();
                 linearOne.setVisibility(View.GONE);
                 linearTwo.setVisibility(View.VISIBLE);


            }
        };
        PhoneAuthProvider.getInstance().verifyPhoneNumber(


                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {

                    if (task.isSuccessful()) {
                        final FirebaseUser user = task.getResult().getUser();

                        // presentActivity();
                        MyProgressBar.dismiss();
                        Intent i = new Intent(context, SignupActivityPatient.class);
                        //   i.putExtra("code", credential.getSmsCode());
                          i.putExtra("number", ed_phone.getText().toString().trim());
                          i.putExtra("countrycode", selectedCountryCode);
                          i.putExtra("signUpas", signUpas);
                          i.putExtra("tempName", TEMP_SAVE_GUEST_NAME);
                          i.putExtra("tempEmail", TEMP_SAVE_GUEST_EMAIL);
                          if(pre_sent_phone!=null&&pre_sent_phone.length()>0){
                              i.putExtra("wasItGuest", "1");
                          }else{
                              i.putExtra("wasItGuest", "0");
                          }


                        // i.putExtra("fb_id", user.getUid());
                        // CachedData.CODE= credential.getSmsCode();
                        //  CachedData.NUMBER=ed_number.getText().toString().trim().replace("+","");
                        //  CachedData.fb_id=user.getUid();


                         startActivity(i);
                         getActivity().finish();

                        Toast.makeText(context, "Successfully verified", Toast.LENGTH_SHORT).show();

                    } else {
                        //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            //  MyUtils.getInstance().buildSnackMessage(snackView,"Invalid code !!!",LENGTH_LONG).show();
                            // Toasty.error(Phone_Varification_Activity.this,)
                            //  Toasty.error(PhonVarificationActivity.this,"Invalid code !!!",Toast.LENGTH_LONG,true).show();
                        }
                        // pDialog.hide();
                    }
                });
    }

}

