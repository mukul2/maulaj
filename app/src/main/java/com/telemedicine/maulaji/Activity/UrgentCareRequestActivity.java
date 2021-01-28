package com.telemedicine.maulaji.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.google.gson.JsonElement;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DOC;

public class UrgentCareRequestActivity extends AppCompatActivity {
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_age)
    EditText ed_age;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_reason)
    EditText ed_reason;
    Context context = this ;
    SessionManager sessionManager;
    @BindView(R.id.spinnerUserType)
    Spinner spinnerUserType;
    @BindView(R.id.linearHideContent)
    LinearLayout linearHideContent;
    enum userType  {MYSELF, OTHER};
    String dateInString ;
    String appintmentFor;
    @BindView(R.id.ed_allergy)
    EditText ed_allergy;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    List ids = new ArrayList();
    userType selectedUserType = userType.MYSELF;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_care_request);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        engineGridViews = new engineGridViews();
        if(!sessionManager.getUserId().equals("0")){
            spinnerUserType.setVisibility(View.GONE);
            linearHideContent.setVisibility(View.VISIBLE);
            selectedUserType = userType.OTHER;
            ed_phone.setText(sessionManager.get_userMobile());
            ed_name.setText(sessionManager.getUserName());
            ed_email.setText(sessionManager.get_userEmail());
            spinnerUserType.setVisibility(View.VISIBLE);
            initSpinner();
        }else{
            selectedUserType = userType.OTHER;
            ed_phone.setText("");
            ed_name.setText("");
            ed_email.setText("");
            spinnerUserType.setVisibility(View.GONE);
        }


        ed_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    showDialog(999);
                }
            }
        });

       // symptomsList();

    }

    private void symptomsList() {
        HashMap<Integer, String> maps = new HashMap<Integer, String>();
        HashMap<Integer, String> mapsSub = new HashMap<Integer, String>();

        Api.getInstance().symptoms_list_get(new ApiListener.SymptomsDownloadListener() {
            @Override
            public void onSymptomsDownloadSuccess(List list) {
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos,int optional) {
                        final Map<String, Object> data = (Map<String, Object>) list.get(pos);
                        // Toast.makeText(context, ""+optional, Toast.LENGTH_SHORT).show();

                        //data.get("name").toString()
                        if (optional==1){
                            maps.put(Integer.parseInt(data.get("id").toString()),data.get("name").toString());
                        }else {
                            maps.remove(Integer.parseInt(data.get("id").toString()));
                        }
                        mapsSub.putAll(maps);
                        ids.clear();
                        Iterator it = mapsSub.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            System.out.println(pair.getKey() + " = " + pair.getValue());
                            ids.add(pair.getValue());
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                        Toast.makeText(context, ids.toString(), Toast.LENGTH_SHORT).show();

                    }
                };

                engineGridViews.showSymptomsListPatient(list, recycler_view, context, R.layout.checkbox_symptoms_items, listener);
            }

            @Override
            public void onSymptomsDownloadFailed(String msg) {

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getDefault());
            //  Toast.makeText(context, ""+calendar.getTime().getYear(), Toast.LENGTH_SHORT).show();
            return new DatePickerDialog(this,
                    myDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    dateInString = "" + arg1 + "-" + (arg2 + 1) + "-" + arg3;
                    Toast.makeText(context, dateInString, Toast.LENGTH_SHORT).show();
                    ed_age.setText(dateInString);

                }
            };
    private void initSpinner() {
        List<String> categories = new ArrayList<String>();
        categories.add("Appointment For Myself");
        categories.add("Appointment For Someone");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserType.setAdapter(dataAdapter);
        spinnerUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    linearHideContent.setVisibility(View.GONE);
                    selectedUserType = userType.MYSELF;
                    ed_phone.setText(sessionManager.get_userMobile());
                    ed_name.setText(sessionManager.getUserName());
                    ed_email.setText(sessionManager.get_userEmail());

                }else {
                    linearHideContent.setVisibility(View.VISIBLE);
                    selectedUserType = userType.OTHER;
                    ed_phone.setText("");
                    ed_name.setText("");
                    ed_email.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void back(View view) {
        onBackPressed();
    }

    public void submit(View view) {
        String phone = ed_phone.getText().toString().trim();
        String name = ed_name.getText().toString().trim();
        String age = ed_age.getText().toString().trim();
        String reason = ed_reason.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String allergy = ed_allergy.getText().toString().trim();

        if(selectedUserType== userType.MYSELF){
            name = sessionManager.getUserName().trim();
            appintmentFor = "0";
        }else {
            name = ed_name.getText().toString().trim();
            appintmentFor = "1";
        }

        if(selectedUserType== userType.MYSELF){
            name = sessionManager.getUserName().trim();
            appintmentFor = "0";

        }else {
            name = ed_name.getText().toString().trim();
            appintmentFor = "1";
            if(sessionManager.getUserId().equals("0")){
                appintmentFor = "2";
            }
        }

        String hospital = "477";

        if(selectedUserType== userType.MYSELF | name.length()>0){
            if(selectedUserType== userType.MYSELF |  age.length()>0){
                if(email.length()>0){
                    if(reason.length()>0) {
                        {


                        if (phone.length() > 0) {

                            MyProgressBar.with(context);
                            HashMap<String, String> request = new HashMap<String, String>();
                            request.put("patient_id", sessionManager.getUserId());
                            request.put("dr_id", NOW_SHOWING_DOC.getId());
                            request.put("problems", reason);
                            request.put("reason", reason);
                            request.put("phone", phone);
                            request.put("patient_name", name);
                            request.put("birth_date", age);
                            request.put("hospital_id", hospital);
                            request.put("status", "1");
                            request.put("email", email);
                            request.put("dateReq", "2020-12-20");
                            request.put("appointment_for", appintmentFor);
                            request.put("birth_date", dateInString);
                            request.put("allergy", allergy);
                            Api.getInstance().urgent_care_request_raw(request, new ApiListener.AppointmentInsertListener() {
                                @Override
                                public void onAppointmentInsertSuccess(JsonElement response) {
                                    Log.i("mkl", response.toString());
                                    MyProgressBar.dismiss();
                                    //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                                    if (sessionManager.getUserId().equals("0")) {
                                        startActivity(new Intent(context, GuestActivity.class));
                                    } else {
                                        startActivity(new Intent(context, PatientHomeActivity.class));

                                    }
                                    finishAffinity();
                                }

                                @Override
                                public void onAppointmentInsertFailed(String msg) {
                                    Log.i("mkl", msg);
                                    MyProgressBar.dismiss();
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                    }
                        }


                }
            }

        }
    }
}