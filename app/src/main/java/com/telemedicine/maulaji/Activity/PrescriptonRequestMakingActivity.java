package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.StatusMessage;

import static com.telemedicine.maulaji.Data.DataStore.NOW_SHOWING_ONLINE_DOC;
import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;

public class PrescriptonRequestMakingActivity extends AppCompatActivity {
    Context context = this ;
    String  paymentInfo;
    String  paymentType;
    String  paypal_id;
    String amountPaid="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescripton_request_making);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            paymentInfo = (String) b.get("paymentInfo");
            paymentType = (String) b.get("paymentType");
            amountPaid = (String) b.get("amount");
            paypal_id = (String) b.get("paypal_id");


            EditText ed_problems = (EditText) findViewById(R.id.ed_problems);
            TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
            TextView tv_submit = (TextView) findViewById(R.id.tv_submit);
            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            tv_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String problem = ed_problems.getText().toString().trim();
                    if (problem.length() > 0) {
                        MyProgressBar.with(context);
                        Api.getInstance().addPrescriptionRequest(TOKEN, USER_ID, "" + NOW_SHOWING_ONLINE_DOC.getId(), problem, paymentType,paymentInfo,amountPaid,paypal_id,new ApiListener.PrescriptionRequestListener() {
                            @Override
                            public void onPrescriptionRequestSuccess(StatusMessage response) {
                                MyProgressBar.dismiss();
                                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(context,PatientHomeActivity.class));
                                finishAffinity();



                            }

                            @Override
                            public void onPrescriptionRequestFailed(String msg) {
                                MyProgressBar.dismiss();
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


                            }
                        });
                    }
                }
            });


        }else {
            onBackPressed();
        }


    }

    public void back(View view) {
        //onBackPressed();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
