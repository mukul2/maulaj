package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.Utils.SessionSaveListener;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.DataStore.VERIFICATION_PHONE_NUMBER;

public class OTPLoginActivity extends AppCompatActivity {
    String phone;
    String userType;
    Context context = this ;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth firebaseAuth;

    @BindView(R.id.tv_wait)
    TextView tv_wait;

    String mVerificationId;

    @BindView(R.id.ed_code_number)
    EditText ed_code_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_login);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
             phone =(String) b.get("phone");
             userType =(String) b.get("userType");
             MyProgressBar.with(context);



            request_sms_varification(phone);


        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {
                        Toast.makeText(context,"Successfull !!!" , Toast.LENGTH_SHORT).show();
                        final FirebaseUser user = task.getResult().getUser();

                        SessionSaveListener.listener.onSucced();

                        // presentActivity();
                        MyProgressBar.dismiss();
                        Intent i = new Intent(context, SplashActivity.class);
                        //   i.putExtra("code", credential.getSmsCode());
                        //  i.putExtra("number", ed_number.getText().toString().trim());
                        // i.putExtra("fb_id", user.getUid());
                        // CachedData.CODE= credential.getSmsCode();
                        //  CachedData.NUMBER=ed_number.getText().toString().trim().replace("+","");
                        //  CachedData.fb_id=user.getUid();


                        startActivity(i);
                        finish();

                        Toast.makeText(this, "Successfully verified", Toast.LENGTH_SHORT).show();

                    } else {
                        //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            //  MyUtils.getInstance().buildSnackMessage(snackView,"Invalid code !!!",LENGTH_LONG).show();
                            // Toasty.error(Phone_Varification_Activity.this,)
                            Toast.makeText(context,"Invalid code !!!" , Toast.LENGTH_SHORT).show();
                        }
                        // pDialog.hide();
                    }
                });
    }

    public void compare_pin(View view) {
        MyProgressBar.with(context);
        if (ed_code_number.getText().toString().trim().length() > 3) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, ed_code_number.getText().toString().trim());
            signInWithPhoneAuthCredential(credential);
        }
    }


    private void request_sms_varification(String pn) {
        //   progressDial.show();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

               // MyProgressBar.dismiss();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                MyProgressBar.dismiss();
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                tv_wait.setText("A pin has been sent to " + pn + " Enter The Verification Code You Have Received Through SMS");
                ed_code_number.setEnabled(false);
                VERIFICATION_PHONE_NUMBER = pn;





            }
        };
        PhoneAuthProvider.getInstance().verifyPhoneNumber(pn, 60, TimeUnit.SECONDS, OTPLoginActivity.this, mCallbacks);


    }


    public void resend(View view) {
    }
}