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
import com.telemedicine.maulaji.model.MedHModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingAppointmentActivity extends AppCompatActivity {
    @BindView(R.id.spinnerUserType)
    Spinner spinnerUserType;
    @BindView(R.id.linearHideContent)
    LinearLayout linearHideContent;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_age)
    EditText ed_age;
    @BindView(R.id.ed_allergy)
    EditText ed_allergy;

    @BindView(R.id.ed_reason)
    EditText ed_reason;
    String dateInString;


    @BindView(R.id.ed_health_condition)
    EditText ed_health_condition;
    SessionManager sessionManager;
    String selctedDate;
    String selctedTime;
    String dateLong;
    Context context = this;
    Calendar myCalendar = Calendar.getInstance();

    String s_time_key;
    String hospital_id;
    String s_time;
    String e_time;
    String dr_id;
    String time_slot;
    List ids = new ArrayList();

    enum userType {MYSELF, OTHER}

    ;

    userType selectedUserType = userType.MYSELF;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initSpinner();
        engineGridViews = new engineGridViews();
        //NOW_SHOWING_DYNAMIC
        //Toast.makeText(this, NOW_SHOWING_DYNAMIC.get("name").toString(), Toast.LENGTH_SHORT).show();

        ed_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDialog(999);
                }
            }
        });

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (!sessionManager.getUserId().equals("0")) {
            // ed_name.setText(sessionManager.getUserName());
        }

        if (b != null) {
            s_time_key = (String) b.get("s_time_key");
            hospital_id = (String) b.get("hospital_id");
            s_time = (String) b.get("s_time");
            e_time = (String) b.get("e_time");
            dateLong = (String) b.get("dateLong");

            //Toast.makeText(context, "long time is "+dateLong, Toast.LENGTH_SHORT).show();
            Log.i("mkl",dateLong);
            selctedDate = (String) b.get("date");
            dr_id = (String) b.get("dr_id");
            time_slot = (String) b.get("time_slot");
        }



       // showSysptomsList();



    }

    private void showSysptomsList() {
        HashMap<Integer, String> maps = new HashMap<Integer, String>();
        HashMap<Integer, String> mapsSub = new HashMap<Integer, String>();
        Api.getInstance().symptoms_list_get(new ApiListener.SymptomsDownloadListener() {
            @Override
            public void onSymptomsDownloadSuccess(List<MedHModel> list) {
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos, int optional) {
                        MedHModel medHModel = list.get(pos);
                        // final Map<String, Object> data = (Map<String, Object>) list.get(pos);
                        // Toast.makeText(context, ""+optional, Toast.LENGTH_SHORT).show();

                        //data.get("name").toString()
                        if (optional == 1) {
                            maps.put(medHModel.getId(), medHModel.getName());
                        } else {
                            maps.remove(medHModel.getId());
                        }
                        mapsSub.putAll(maps);
                        ids.clear();
                        Iterator it = mapsSub.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
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
                if (i == 0) {
                    linearHideContent.setVisibility(View.GONE);
                    selectedUserType = userType.MYSELF;

                } else {
                    linearHideContent.setVisibility(View.VISIBLE);
                    selectedUserType = userType.OTHER;
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
        String appintmentFor = "";
        String phone = ed_phone.getText().toString().trim();
        String name = ed_name.getText().toString().trim();
        String age = ed_age.getText().toString().trim();
        String reason = ed_reason.getText().toString().trim();
        String allergy = ed_allergy.getText().toString().trim();
        String date_of_birth;

        String cond = ed_health_condition.getText().toString().trim();

        if (selectedUserType == userType.MYSELF) {
            name = sessionManager.getUserName().trim();
            appintmentFor = "0";
        } else {
            name = ed_name.getText().toString().trim();
            appintmentFor = "1";
        }


        if (selectedUserType == userType.MYSELF | name.length() > 0) {
            if (selectedUserType == userType.MYSELF | age.length() > 0) {

                if (reason.length() > 0) {


                    if (selectedUserType == userType.MYSELF | phone.length() > 0) {
                        if (cond.length() > 0) {
                            MyProgressBar.with(context);
                            HashMap<String, String> request = new HashMap<String, String>();
                            request.put("patient_id", sessionManager.getUserId());
                            request.put("dr_id", dr_id);
                            request.put("dr_id", dr_id);
                            request.put("problems", reason);
                            request.put("phone", phone);
                            request.put("name", name);
                            request.put("chamber_id", "0");
                            request.put("date_app", selctedDate);
                            request.put("status", "1");
                            request.put("type", "n");
                            request.put("s_time", s_time);
                            request.put("e_time", e_time);
                            request.put("age", age);
                            request.put("time_slot", time_slot);
                            request.put("gender", "Male");
                            request.put("reasonToVisit", reason);
                            request.put("condition", cond);
                            request.put("medications", "No Medication record");
                            request.put("weight", "00");
                            request.put("temparature", "No Temp record");
                            request.put("bloodPressure", "No Blood Pressure record");
                            request.put("fees", "00");
                            request.put("dateLong", dateLong);
                            request.put("s_time_key", s_time_key);
                            request.put("hospital_id", hospital_id);
                            request.put("symptoms", ids.toString());
                            request.put("appointment_for", appintmentFor);
                            request.put("date_of_birth", dateInString);
                            request.put("allergy", allergy);
                            Api.getInstance().appAppInfo(request, new ApiListener.AppointmentInsertListener() {
                                @Override
                                public void onAppointmentInsertSuccess(JsonElement response) {
                                    MyProgressBar.dismiss();
                                    Toast.makeText(BookingAppointmentActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(context, PatientHomeActivity.class));
                                    finishAffinity();
                                }

                                @Override
                                public void onAppointmentInsertFailed(String msg) {
                                    MyProgressBar.dismiss();
                                    Toast.makeText(BookingAppointmentActivity.this, msg, Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                    }


                }
            }

        }
    }
}