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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.telemedicine.maulaji.Fragments.PhonVerificationBottomSheet;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.google.gson.JsonElement;
import com.telemedicine.maulaji.model.MedHModel;
import com.telemedicine.maulaji.model.SympModel;
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
import static com.telemedicine.maulaji.Data.Data.TEMP_SAVE_GUEST_EMAIL;
import static com.telemedicine.maulaji.Data.Data.TEMP_SAVE_GUEST_NAME;
import static com.telemedicine.maulaji.Data.Data.TEMP_SAVE_GUEST_PHONE;

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

    @BindView(R.id.cardMedicalHistory)
    CardView cardMedicalHistory;
    @BindView(R.id.cardMedicalGuest)
    CardView cardMedicalGuest;
    @BindView(R.id.recycler2)
    RecyclerView recycler2;
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

        if (!sessionManager.getUserId().equals("0")) {

            //not guest
            // ed_name.setText(sessionManager.getUserName());
            showDiseasesHostory();
            cardMedicalHistory.setVisibility(View.VISIBLE);
            cardMedicalGuest.setVisibility(View.GONE);
        }else{
            //guest
            symptomsList();
            cardMedicalHistory.setVisibility(View.GONE);
            cardMedicalGuest.setVisibility(View.VISIBLE);

        }

    }
    private void showDiseasesHostory() {
        HashMap<Integer, String> maps = new HashMap<Integer, String>();
        HashMap<Integer, String> mapsSub = new HashMap<Integer, String>();
        Api.getInstance().symptoms_list_get(new ApiListener.SymptomsDownloadListener() {
            @Override
            public void onSymptomsDownloadSuccess(List<MedHModel> list) {
                Gson gson = new Gson();
             //   Log.i("sym", gson.toJson(list));
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos, int optional) {
                        Log.i("mkl",list.get(0).toString());
                        final MedHModel data = list.get(pos);
                        // Toast.makeText(context, ""+optional, Toast.LENGTH_SHORT).show();

                        //data.get("name").toString()
                        if (optional == 1) {
                            maps.put(data.getId(), data.getName());
                        } else {
                            if(!maps.isEmpty()) maps.remove(data.getId());
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


                        if(!ids.isEmpty()) {
                            //update from here
                            String dataToUpdate = ids.toString();
                            dataToUpdate = dataToUpdate.substring(1, dataToUpdate.length() -1);
                            Api.getInstance().setMedicalHistory(sessionManager.getUserId(), dataToUpdate, new ApiListener.medicalHistoryUpdateListener() {
                                @Override
                                public void OnmedicalHistoryUpdateSuccess(String list) {
                                    // Toast.makeText(MedicalHistoryActivityPatient.this, list, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void OnmedicalHistoryUpdateFailed(String msg) {
                                    // Toast.makeText(MedicalHistoryActivityPatient.this, msg, Toast.LENGTH_SHORT).show();

                                }
                            });
                            // Toast.makeText(context, dataToUpdate, Toast.LENGTH_SHORT).show();
                        }

                    }
                };


                Api.getInstance().getMyMedicalistory(sessionManager.getUserId(), new ApiListener.MedicalHistoryDownloadListener() {
                    @Override
                    public void onMedicalHistoryDownloadSuccess(List<String> res) {


                        for (int i = 0;i<list.size();i++){
                            for(int j = 0;j<res.size();j++){
                                if(list.get(i).getName().replaceAll(" ","").equals(res.get(j).replaceAll(" ",""))){
                                    list.get(i).setIsSelected(1);
                                    break;
                                }
                            }
                        }
                        for (int i = 0;i<list.size();i++){
                            if(list.get(i).getIsSelected()==1){
                                maps.put(list.get(i).getId(), list.get(i).getName().trim());
                            }else {
                                if(!maps.isEmpty()) maps.remove(list.get(i).getId());
                            }
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
                        // Toast.makeText(context, ids.toString(), Toast.LENGTH_SHORT).show();
                        engineGridViews.showSymptomsListPatient(list, recycler2, context, R.layout.checkbox_symptoms_items, listener);


                    }

                    @Override
                    public void onMedicalHistoryDownloadFailed(String msg) {
                    //    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onSymptomsDownloadFailed(String msg) {

            }
        });
    }
    private void symptomsList() {
        HashMap<Integer, String> maps = new HashMap<Integer, String>();
        HashMap<Integer, String> mapsSub = new HashMap<Integer, String>();

        Api.getInstance().symptoms_list_get(new ApiListener.SymptomsDownloadListener() {
            @Override
            public void onSymptomsDownloadSuccess(List<MedHModel> list) {
                Gson gson = new Gson();
                Log.i("sym", gson.toJson(list));
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos,int optional) {
                        final MedHModel data =  list.get(pos);
                        // Toast.makeText(context, ""+optional, Toast.LENGTH_SHORT).show();
                        Log.i("mm",""+ data);

                        //data.get("name").toString()
                       if (optional==1){
                            maps.put(Integer.parseInt(data.getId().toString()),data.getName().toString());
                        }else {
                            maps.remove(data.getId().toString());
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

                      //  Toast.makeText(context, ids.toString(), Toast.LENGTH_SHORT).show();

                    }
                };

                engineGridViews.showSymptomsListGuest(list, recycler_view, context, R.layout.checkbox_symptoms_items, listener);
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
                   // Toast.makeText(context, dateInString, Toast.LENGTH_SHORT).show();
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

       // Toast.makeText(context, "vitals "+ids.toString(), Toast.LENGTH_SHORT).show();
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
        TEMP_SAVE_GUEST_EMAIL = ed_email.getText().toString().trim();
        TEMP_SAVE_GUEST_NAME =  name;
        TEMP_SAVE_GUEST_PHONE = phone;
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
                            request.put("all_info", ids.toString());
                            TEMP_SAVE_GUEST_EMAIL = email;
                            Api.getInstance().urgent_care_request_raw(request, new ApiListener.AppointmentInsertListener() {
                                @Override
                                public void onAppointmentInsertSuccess(JsonElement response) {
                                    Log.i("mkl", response.toString());
                                    MyProgressBar.dismiss();
                                    Toast.makeText(context,"Appointment has  been booked successfully", Toast.LENGTH_SHORT).show();



                                    if (sessionManager.getUserId().equals("0")) {
                                        MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                                            @Override
                                            public void onDialogClicked(boolean result) {
                                                if (result==false){
                                                    startActivity(new Intent(context, GuestActivity.class));
                                                }else {

                                                    PhonVerificationBottomSheet frag = PhonVerificationBottomSheet.newInstance("p",phone);
                                                    frag.show(getSupportFragmentManager(), "add_photo_dialog_fragment");

                                                }

                                            }
                                        },"Do you want us to create an account for you?");


                                    } else {
                                        startActivity(new Intent(context, PatientHomeActivity.class));

                                    }
                                    MyDialog.getInstance().with(context)
                                            .message("Appointment has  been booked successfully")
                                            .autoBack(false)
                                            .autoDismiss(false)
                                            .showMsgOnly();
                                  //  finishAffinity();
                                }

                                @Override
                                public void onAppointmentInsertFailed(String msg) {
                                    Log.i("mkl", msg);
                                    MyProgressBar.dismiss();
                                   // Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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