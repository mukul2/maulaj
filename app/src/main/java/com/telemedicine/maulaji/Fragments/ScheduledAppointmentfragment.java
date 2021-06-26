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

import com.telemedicine.maulaji.Activity.ChatActivityCommon;
import com.telemedicine.maulaji.Activity.PatientProfileActivityForDoctor;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DYNAMIC;
import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_PATIENT_ID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduledAppointmentfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduledAppointmentfragment extends Fragment {
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

    public ScheduledAppointmentfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduledAppointmentfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduledAppointmentfragment newInstance(String param1, String param2) {
        ScheduledAppointmentfragment fragment = new ScheduledAppointmentfragment();
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
        view =  inflater.inflate(R.layout.fragment_scheduled_appointmentfragment, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);

        sessionManager = new SessionManager(context);
        engineGridViews = new engineGridViews();

        HashMap<String, String> request = new HashMap<String, String>();
        request.put("id", sessionManager.getUserId());
        request.put("userType", sessionManager.getUserType());

        Api.getInstance().scheduled_appointment_request_list(request, new ApiListener.ScheduledListDownloadListener() {
            @Override
            public void onScheduledListDownloadSuccess(List response) {
                Log.i("mkl",response.toString());
              //  Toast.makeText(context,  sessionManager.getUserType()+"   "+"scheduled app size "+response.size(), Toast.LENGTH_SHORT).show();
                //patientname
                //time_slot
                //showScheduledAppList
                if(response.size()>0) {


                    com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                        @Override
                        public void onSelected(int pos,int optional) {
                          //  Toast.makeText(context, "clcicked unified", Toast.LENGTH_SHORT).show();

                            if(sessionManager.getUserType().equals("d")) {
                                final Map<String, Object> data = (Map<String, Object>) response.get(pos);
                                String patient_id = data.get("patient").toString();
                                String patient_name = data.get("patientname").toString();
                                String patient_photo =data.get("img_url")!=null? data.get("img_url").toString():"";
                                String doctor_id = sessionManager.getUserId();
                                String room =patient_id+"-"+doctor_id;

                               // Toast.makeText(context, "caling "+"p"+patient_id, Toast.LENGTH_SHORT).show();
                                if(optional==1){



                                }

                                if(optional==2){



                                }

                                if(optional==3){



                                }
                                if(optional==4){

                                    //Toast.makeText(context, "clicke patient id "+patient_id, Toast.LENGTH_SHORT).show();
                                    MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                                        @Override
                                        public void onDialogClicked(boolean result) {
                                            if(result){
                                                Intent intent = new Intent(context, PatientProfileActivityForDoctor.class);
                                                intent.putExtra("partner_id",patient_id);
                                                intent.putExtra("partner_name",patient_name);
                                                intent.putExtra("partner_photo",patient_photo);
                                                intent.putExtra("type","s");
                                                NOW_SHOWING_PATIENT_ID  = patient_id;
                                                NOW_SHOWING_DYNAMIC = (Map<String, Object>) response.get(pos);


                                                startActivity(intent);



                                               // Api.getInstance().appNotification("p"+patient_id,"incomming_call",room,"Incoming Call","New Incomming call from "+sessionManager.getUserName());

                                            }

                                        }
                                    },"Do you want to see the patient's profile?");


                                }


                            }else {
                             //   Toast.makeText(context, response.get(pos).toString(), Toast.LENGTH_SHORT).show();
                                if(optional==5){


                                    final Map<String, Object> data = (Map<String, Object>) response.get(pos);

                                    String s_time = data.get("s_time").toString();



                                    String doctor_id = data.get("doctor_id").toString();
                                    String patient_photo =data.get("img_url")!=null? data.get("img_url").toString():"";
                                    String patient_id = sessionManager.getUserId();
                                    String room =patient_id+"-"+doctor_id;
                                   // Toast.makeText(context, room, Toast.LENGTH_SHORT).show();

                                    MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                                        @Override
                                        public void onDialogClicked(boolean result) {
                                            if(result){
                                            URL serverURL;
                                            try {
                                                // When using JaaS, replace "https://meet.jit.si" with the proper serverURL
                                                serverURL = new URL("https://simra.org");
                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                                throw new RuntimeException("Invalid server URL!");
                                            }
                                            JitsiMeetConferenceOptions defaultOptions
                                                    = new JitsiMeetConferenceOptions.Builder()
                                                    .setServerURL(serverURL)
                                                    .setFeatureFlag("invite.enabled", false)
                                                    // When using JaaS, set the obtained JWT here
                                                    //.setToken("MyJWT")
                                                    .setWelcomePageEnabled(false)
                                                    .build();
                                            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

                                            JitsiMeetConferenceOptions options
                                                    = new JitsiMeetConferenceOptions.Builder()
                                                    .setRoom(room)
                                                    .setAudioOnly(false)
                                                    .setFeatureFlag("invite.enabled", false)
                                                    .setVideoMuted(false)
                                                    .setSubject("Consultation")
                                                    .build();
                                            JitsiMeetActivity.launch(context, options);
                                        }}
                                    },"Do you want to Join this session?");





                                }
                                if(optional==4){


                                    final Map<String, Object> data = (Map<String, Object>) response.get(pos);
                                    String doctor_id = data.get("doctor_id").toString();
                                    String doctor_name = data.get("doctor_name").toString();
                                    String d_photo =data.get("img_url")!=null? data.get("img_url").toString():"";
                                    String patient_id = sessionManager.getUserId();
                                    String room =patient_id+"-"+doctor_id;
                                    // Toast.makeText(context, room, Toast.LENGTH_SHORT).show();

                                    MyDialog.getInstance().with(context).yesNoConfirmation(new MyDialog.confirmListener() {
                                        @Override
                                        public void onDialogClicked(boolean result) {
                                            if(result){
                                                Intent intent = new Intent(context, ChatActivityCommon.class);
                                                intent.putExtra("partner_id", doctor_id);
                                                intent.putExtra("partner_name",doctor_name );
                                                intent.putExtra("partner_photo",  d_photo);
                                                context.startActivity(intent);
                                            }}
                                    },"Do you want to Chat with your doctor?");





                                }
                            }


                        }


                    };

                    tv_no_data.setVisibility(View.GONE);
                    tv_no_data.setText(response.toString());
                    recycler_view.setVisibility(View.VISIBLE);
                    if(sessionManager.getUserType().equals("d")) {
                        engineGridViews.showScheduledAppList(response, recycler_view, context, R.layout.appointment_item, listener);

                    }else {
                        engineGridViews.showScheduledAppListPatient(response, recycler_view, context, R.layout.appointment_item_patient_with_control, listener);

                    }
                }else {
                    tv_no_data.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }

            }

            @Override
            public void onScheduledListDownloadFailed(String msg) {

             //   Toast.makeText(context, "Error while downloading scheduled appointments", Toast.LENGTH_SHORT).show();
                Log.i("mkl",msg);
            }
        });


        return  view;
    }
}