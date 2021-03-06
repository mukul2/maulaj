package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.Activity.PatientProfileActivityForDoctor;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DYNAMIC;
import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PATIENT_ID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UrgentConsultationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeVisitAppointment extends Fragment {
    View view ;
    Context context ;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;
    SessionManager sessionManager ;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeVisitAppointment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UrgentConsultationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UrgentConsultationFragment newInstance(String param1, String param2) {
        UrgentConsultationFragment fragment = new UrgentConsultationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_visit_appointment, container, false);

        context = view.getContext();
        ButterKnife.bind(this,view);

        sessionManager = new SessionManager(context);
        engineGridViews = new engineGridViews();

        HashMap<String, String> request = new HashMap<String, String>();
        request.put("id", sessionManager.getUserId());
        request.put("userType", sessionManager.getUserType());

        Api.getInstance().home_visit_appointment_request_list(request, new ApiListener.HomeVisitListDownloadListener() {
            @Override
            public void onHomeVisitDownloadSuccess(List response) {
                Log.i("mklh",response.toString());
                Toast.makeText(context, ""+response.size(), Toast.LENGTH_SHORT).show();
                //patientname
                //time_slot
                //showScheduledAppList
                if(response.size()>0) {
                    com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                        @Override
                        public void onSelected(int pos,int optional) {
                            final Map<String, Object> data = (Map<String, Object>) response.get(pos);
                            String patient_id = data.get("patient_id").toString();
                            String patient_name = data.get("patient_name").toString();
                            String patient_photo =data.get("img_url")!=null? data.get("img_url").toString():"";
                            String doctor_id = sessionManager.getUserId();
                            String room =patient_id+"-"+doctor_id;

                            MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                                @Override
                                public void onDialogClicked(boolean result) {
                                    if(result){
                                        Intent intent = new Intent(context, PatientProfileActivityForDoctor.class);
                                        intent.putExtra("partner_id",patient_id);
                                        intent.putExtra("partner_name",patient_name);
                                        intent.putExtra("partner_photo",patient_photo);
                                        intent.putExtra("type","h");
                                        NOW_SHOWING_PATIENT_ID  = patient_id;
                                        NOW_SHOWING_DYNAMIC = (Map<String, Object>) response.get(pos);

                                        startActivity(intent);



                                        // Api.getInstance().appNotification("p"+patient_id,"incomming_call",room,"Incoming Call","New Incomming call from "+sessionManager.getUserName());

                                    }

                                }
                            },"Do you want to see the patient's profile?");

                        }
                    };

                    tv_no_data.setVisibility(View.GONE);
                   // tv_no_data.setText(response.toString());
                    recycler_view.setVisibility(View.VISIBLE);
                    if(sessionManager.getUserType().equals("d")) {
                        engineGridViews.showHomeAppList(response, recycler_view, context, R.layout.appointment_item_home_doctor, listener);
                    }else {
                        engineGridViews.showHomeAppListpatient(response, recycler_view, context, R.layout.appointment_item, listener);
                    }
                }else {
                    tv_no_data.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }

            }

            @Override
            public void onHomeVisitDownloadFailed(String msg) {
                Log.i("mkl",msg);
            }
        });

        return view;
    }
}