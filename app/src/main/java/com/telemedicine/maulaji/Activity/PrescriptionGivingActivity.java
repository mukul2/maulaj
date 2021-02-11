package com.telemedicine.maulaji.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.AccountPicker;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.adapter.PrescriptionGivingAdapterDoctor;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.MedicineModel;
import com.telemedicine.maulaji.model.MedicineModel2;
import com.telemedicine.maulaji.model.MedicineModel3;
import com.telemedicine.maulaji.model.MedicineModel4;
import com.telemedicine.maulaji.model.MedicineVaritiModel;
import com.telemedicine.maulaji.model.PrescriptionMedicineModel;
import com.telemedicine.maulaji.model.StatusMessage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PATIENT_ID;
import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;
import static com.telemedicine.maulaji.Data.Data.REQUEST_TO_PRESCRIBE;
import static com.telemedicine.maulaji.Data.Data.SESSION_MANAGER;
import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;
import static com.telemedicine.maulaji.Data.DataStore.selectedSearchAppointmentModel;

public class PrescriptionGivingActivity extends AppCompatActivity implements ApiListener.DownloadMedicinesListInfoListener,
        ApiListener.PrescriptionAddListener, ApiListener.ReplyPrescriptionRequestListener, ApiListener.MedDownloadListener {
    List<MedicineModel4> ALL_MEDICINE = new ArrayList<>();
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.ed_diseases)
    EditText ed_diseases;
    @BindView(R.id.ed_note)
    EditText ed_note;
    @BindView(R.id.ed_advise)
    EditText ed_advise;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_problem)
    TextView tv_problem;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context = this;
    List<PrescriptionMedicineModel> medicineList = new ArrayList<>();
    int selected_med = 0;
    PrescriptionGivingAdapterDoctor mAdapter;
    Spinner spinnerMedicineName;
    Spinner spinnerVarities;
    int morning = 0;
    int noon = 0;
    int night = 0;
    String selectedDurationType = null;
    String selectedDuration = null;
    int MORNING_DOSE_COUNT = 0;
    int NOON_DOSE_COUNT = 0;
    int NIGHT_DOSE_COUNT = 0;
    int DURATION_COUNT = 0;

    int BEFORE_MEAL = 0;
    int AFTER_MEAL = 1;

    int MORNIG_STATUS = 0;
    int NOON_STATUS = 0;
    int NIGHT_STATUS = 0;
    String SELECTED_DURATION = "";
    String TYPE;
    String medVarities = "";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_giving);
        sessionManager = new SessionManager(this);

        //setUpStatusbar();
        ButterKnife.bind(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

//        if (bundle != null) {
//            String type = bundle.getString("type");
//            if (type.equals("chambePrescription")) {
//                TYPE = "chambePrescription";
//                //selectedSearchAppointmentModel
//                //   Glide.with(context).load(PHOTO_BASE + selectedSearchAppointmentModel.getPatientInfo().getPhoto()).into(image);
//                //  tv_name.setText(selectedSearchAppointmentModel.getPatientInfo().getName());
//                //  tv_problem.setText(selectedSearchAppointmentModel.getProblems());
//
//            } else if (type.equals("prescriptionRequest")) {
//                TYPE = "prescriptionRequest";
//
//                requestPrescibe();
//            }
//        } else {
//            Toast.makeText(context, "Unknown type", Toast.LENGTH_LONG).show();
//
//
//        }
        HashMap<String,String>hashMap = new HashMap<String, String>();
        Api.getInstance().all_medicines(hashMap, new ApiListener.MedDownloadListener() {
            @Override
            public void onMedDownloadSuccess(List<MedicineModel4> response) {
                Toast.makeText(context, "size " + response.size(), Toast.LENGTH_SHORT).show();
                ALL_MEDICINE = response;
                initMedicineRecycler();
            }

            @Override
            public void onMedDownloadFailed(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void requestPrescibe() {
        Glide.with(context).load(PHOTO_BASE + REQUEST_TO_PRESCRIBE.getPatientInfo().getPhoto()).into(image);
        tv_name.setText(REQUEST_TO_PRESCRIBE.getPatientInfo().getName());
        tv_problem.setText(REQUEST_TO_PRESCRIBE.getProblem());
    }

    private void initMedicineRecycler() {
        mAdapter = new PrescriptionGivingAdapterDoctor(medicineList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    public void setUpStatusbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    public void back(View view) {
        onBackPressed();
    }

    //
    public void AddMedicine(View view) {

        SELECTED_DURATION = "";
        BEFORE_MEAL = 0;
        AFTER_MEAL = 1;

        MORNIG_STATUS = 0;
        NOON_STATUS = 0;
        NIGHT_STATUS = 0;


        MORNING_DOSE_COUNT = 0;
        NOON_DOSE_COUNT = 0;
        NIGHT_DOSE_COUNT = 0;
        DURATION_COUNT = 0;


        morning = noon = night = 0;
        selectedDurationType = null;
        selectedDuration = null;

        final Dialog dialog = new Dialog(PrescriptionGivingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.add_medicine_dialog);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        CheckBox checkBox_1 = (CheckBox) dialog.findViewById(R.id.checkbox_1);
        CheckBox checkbox_2 = (CheckBox) dialog.findViewById(R.id.checkbox_2);
        CheckBox checkbox_3 = (CheckBox) dialog.findViewById(R.id.checkbox_3);

//morning start
        TextView tv_morning_minus = (TextView) dialog.findViewById(R.id.tv_morning_minus);
        TextView tv_morning_plus = (TextView) dialog.findViewById(R.id.tv_morning_plus);
        TextView tv_morning_dose = (TextView) dialog.findViewById(R.id.tv_morning_dose);
        tv_morning_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox_1.setChecked(true);
                MORNING_DOSE_COUNT++;
                tv_morning_dose.setText("" + MORNING_DOSE_COUNT);

            }
        });
        tv_morning_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MORNING_DOSE_COUNT != 0) {
                    MORNING_DOSE_COUNT--;
                    tv_morning_dose.setText("" + MORNING_DOSE_COUNT);
                    if (MORNING_DOSE_COUNT == 0) {
                        checkBox_1.setChecked(false);

                    }
                } else {
                    checkBox_1.setChecked(false);

                }

            }
        });

        //morning ends


//noon start
        TextView tv_noon_minus = (TextView) dialog.findViewById(R.id.tv_noon_minus);
        TextView tv_noon_plus = (TextView) dialog.findViewById(R.id.tv_noon_plus);
        TextView tv_noon_dose = (TextView) dialog.findViewById(R.id.tv_noon_dose);
        tv_noon_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkbox_2.setChecked(true);
                NOON_DOSE_COUNT++;
                tv_noon_dose.setText("" + NOON_DOSE_COUNT);

            }
        });
        tv_noon_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NOON_DOSE_COUNT != 0) {
                    NOON_DOSE_COUNT--;
                    tv_noon_dose.setText("" + NOON_DOSE_COUNT);
                    if (NOON_DOSE_COUNT == 0) {
                        checkbox_2.setChecked(false);

                    }
                } else {
                    checkbox_2.setChecked(false);

                }

            }
        });

        //morning ends
        //noon start
        TextView tv_night_minus = (TextView) dialog.findViewById(R.id.tv_night_minus);
        TextView tv_night_dose = (TextView) dialog.findViewById(R.id.tv_night_dose);
        TextView tv_night_plus = (TextView) dialog.findViewById(R.id.tv_night_plus);
        tv_night_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkbox_3.setChecked(true);
                NIGHT_DOSE_COUNT++;
                tv_night_dose.setText("" + NIGHT_DOSE_COUNT);

            }
        });
        tv_night_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NIGHT_DOSE_COUNT != 0) {
                    NIGHT_DOSE_COUNT--;
                    tv_night_dose.setText("" + NIGHT_DOSE_COUNT);
                    if (NIGHT_DOSE_COUNT == 0) {
                        checkbox_3.setChecked(false);

                    }
                } else {
                    checkbox_3.setChecked(false);

                }

            }
        });

        //morning ends

        //duration startes
        TextView duration_plus = (TextView) dialog.findViewById(R.id.tv_duration_plus);
        TextView tv_duration_minus = (TextView) dialog.findViewById(R.id.tv_duration_minus);
        TextView tv_duration_value = (TextView) dialog.findViewById(R.id.tv_duration_value);


        duration_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DURATION_COUNT++;
                tv_duration_value.setText("" + DURATION_COUNT);

            }
        });
        tv_duration_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DURATION_COUNT > 0) {
                    DURATION_COUNT--;
                    tv_duration_value.setText("" + DURATION_COUNT);

                } else {

                }

            }
        });

        //duration ends


        RadioGroup RadioGroupNight = (RadioGroup) dialog.findViewById(R.id.RadioGroupNight);


        RadioGroupNight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.night_before: {
                        NIGHT_STATUS = 0;
                        Toast.makeText(context, "before", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case R.id.night_after: {
                        NIGHT_STATUS = 1;
                        Toast.makeText(context, "after", Toast.LENGTH_SHORT).show();


                        break;
                    }
                }


            }
        });


        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.getId() == R.id.checkbox_1) {
                    if (b == true) {
                        morning = 1;
                    } else {
                        morning = 0;
                    }
                }
                if (compoundButton.getId() == R.id.checkbox_2) {
                    if (b == true) {
                        noon = 1;
                    } else {
                        noon = 0;
                    }
                }
                if (compoundButton.getId() == R.id.checkbox_3) {
                    if (b == true) {
                        night = 1;
                    } else {
                        night = 0;
                    }
                }


            }
        };

        checkBox_1.setOnCheckedChangeListener(listener);
        checkbox_2.setOnCheckedChangeListener(listener);
        checkbox_3.setOnCheckedChangeListener(listener);

        List<String> medicines = new ArrayList<>();
        medicines.add("Select");
        for (int i = 0; i < ALL_MEDICINE.size(); i++) {
            medicines.add(ALL_MEDICINE.get(i).getName());
        }

        TextView tv_add = (TextView) dialog.findViewById(R.id.tv_add);

        medVarities = "";
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDuration = SELECTED_DURATION;
                if (selected_med > 0) {
                    if (morning == 1 || noon == 1 || night == 1) {
                        if (selectedDuration != null && (selectedDuration.length() > 0)) {
                            String dose = "" + MORNING_DOSE_COUNT + "-" + NOON_DOSE_COUNT + "-" + NIGHT_DOSE_COUNT;
                            medicineList.add(new PrescriptionMedicineModel(Integer.valueOf(ALL_MEDICINE.get(selected_med - 1).getId()), SELECTED_DURATION, ALL_MEDICINE.get(selected_med - 1).getName(), medVarities, "" + DURATION_COUNT, dose, NIGHT_STATUS));
                            mAdapter.notifyItemInserted(medicineList.size() - 1);
                            spinnerMedicineName.setSelection(0);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(PrescriptionGivingActivity.this, "Please add medicine dose duration", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PrescriptionGivingActivity.this, "Please add medicine dose", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(PrescriptionGivingActivity.this, "Please add medicine dose", Toast.LENGTH_SHORT).show();

                }
            }
        });

//spinnerVerities
        spinnerMedicineName = (Spinner) dialog.findViewById(R.id.spinnerMedicineName);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medicines);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedicineName.setAdapter(dataAdapter);
        spinnerMedicineName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selected_med = i;
                    //  Toast.makeText(PrescriptionGivingActivity.this, "" +ALL_MEDICINE.get(i-1).getId(), Toast.LENGTH_LONG).show();
                    String[] splitArray;
                    String title = ALL_MEDICINE.get(i - 1).getName();
                    EditText ed_varities = (EditText) dialog.findViewById(R.id.ed_varities);
                    try {
                         splitArray = title.split("\\s+");
                        ed_varities.setText(splitArray[splitArray.length-1]);
                    } catch (PatternSyntaxException ex) {
                        //
                    }


                    if (false) {

//
                        Api.getInstance().get_medicine_varities("" + ALL_MEDICINE.get(i - 1).getId(), new ApiListener.MedVaritiListDownloadListener() {
                            @Override
                            public void onMedVaritiListListDownloadSuccess(List<MedicineVaritiModel> responseV) {
                                Toast.makeText(PrescriptionGivingActivity.this, "Varities found " + responseV.size(), Toast.LENGTH_SHORT).show();

                                List<String> varities = new ArrayList<>();
                                varities.add("Choose");
                                for (int j = 0; j < responseV.size(); j++) {
                                    varities.add(responseV.get(j).getBody());
                                }


                              //  spinnerVarities = (Spinner) dialog.findViewById(R.id.spinnerVerities);
                                spinnerVarities = new Spinner(context);
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, varities);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerVarities.setAdapter(dataAdapter);
                                spinnerVarities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (i > 0) {
                                            medVarities = varities.get(i);
                                            // selected_med = i;
                                            // Toast.makeText(PrescriptionGivingActivity.this, "" + selected_med, Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });


                            }

                            @Override
                            public void onMedVaritiListListDownloadFailed(String msg) {
                                Toast.makeText(PrescriptionGivingActivity.this, "error while varities", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //duration
        RadioGroup durationRadio = (RadioGroup) dialog.findViewById(R.id.readioGrpDuration);
        durationRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioDay: {
                        SELECTED_DURATION = "days";
                        break;

                    }
                    case R.id.radioWeek: {
                        SELECTED_DURATION = "weeks";
                        break;


                    }
                    case R.id.radioMonth: {
                        SELECTED_DURATION = "months";
                        break;


                    }

                }
            }
        });


    }

    @Override
    public void onDownloadMedicinesListInfoSuccess(List<MedicineModel> status) {
        Toast.makeText(context, "downloaded med size " + status.size(), Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onDownloadMedicinesListFailed(String msg) {
        ALL_MEDICINE.clear();
        Toast.makeText(this, "med wonload error " + msg, Toast.LENGTH_SHORT).show();

    }

    public void submit(View view) {
        String diseases = ed_diseases.getText().toString().trim();
        String advice = ed_advise.getText().toString().trim();
        String note = ed_note.getText().toString().trim();
        String PRESCRIPTION_SERVICE_ID = "5";
        Gson gson = new Gson();
        Log.i("pres", gson.toJson(medicineList));
        String producedLine = "";
        String localBreak = "***";
        Toast.makeText(context, NOW_SHOWING_PATIENT_ID, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < medicineList.size(); i++) {
            producedLine += medicineList.get(i).getMedicineId() + localBreak + medicineList.get(i).getVarient() + localBreak + medicineList.get(i).getDose() + localBreak + medicineList.get(i).getDurationLength() + medicineList.get(i).getDurationType();
            if (i < medicineList.size() - 1) producedLine += "###";
        }
        Log.i("pres", producedLine);
        if (diseases.length() > 0 && advice.length() > 0 && note.length() > 0) {
            if (true) {
                MyProgressBar.with(context);
                HashMap<String, String> request = new HashMap<String, String>();
                request.put("patient", NOW_SHOWING_PATIENT_ID);
                request.put("doctor", sessionManager.getUserId());
                request.put("symptom", diseases);
                request.put("advice", advice);
                request.put("state", "");
                request.put("dd", "");
                request.put("medicine", producedLine);
                request.put("note", note);
                request.put("patientname", "");
                request.put("doctorname", sessionManager.getUserName());
                request.put("hospital_id", sessionManager.get_drHospital());
                Api.getInstance().push_prescription(request, new ApiListener.PrescriptionPushListener() {
                    @Override
                    public void onPrescriptionPushSuccess(StatusMessage response) {
                        MyProgressBar.dismiss();
                        Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();

                        MyDialog.getInstance().with(context).message(response.getMessage()).show();

                    }

                    @Override
                    public void onPrescriptionPushFailed(String msg) {
                        MyProgressBar.dismiss();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }
    }

    @Override
    public void onrescriptionAddSuccess(StatusMessage data) {
        MyProgressBar.dismiss();
        Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
        // onBackPressed();
    }

    @Override
    public void onrescriptionAddFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReplyPrescriptionRequestSuccess(StatusMessage data) {
        MyProgressBar.dismiss();
        Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onReplyPrescriptionRequestFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMedDownloadSuccess(List response) {
        ALL_MEDICINE.clear();
        Toast.makeText(context, "downloaded " + response.size(), Toast.LENGTH_SHORT).show();
        if (false) {//response != null
            ALL_MEDICINE.addAll(response);
            initMedicineRecycler();
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMedDownloadFailed(String msg) {
        Toast.makeText(context, "pres dn err" + msg, Toast.LENGTH_SHORT).show();

    }
}
