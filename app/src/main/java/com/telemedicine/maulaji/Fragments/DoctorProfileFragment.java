package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.DoctorDetailProfile;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorProfileFragment extends Fragment {
    SessionManager sessionManager ;
    Context context ;
    View view ;
    String doctor_id;

    EditText ed_name;
    EditText ed_phone;
    EditText ed_about_me;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DoctorProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorProfileFragment newInstance() {
        DoctorProfileFragment fragment = new DoctorProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doctor_profile, container, false);
        context = view.getContext();
        ed_name = (EditText) view.findViewById(R.id.ed_name);
        ed_phone = (EditText) view.findViewById(R.id.ed_phone);
        ed_about_me = (EditText) view.findViewById(R.id.ed_about_me);

        sessionManager = new SessionManager(context);
        doctor_id = sessionManager.getUserId();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("id", doctor_id);
       // Toast.makeText(context, request.toString(), Toast.LENGTH_SHORT).show();
        Api.getInstance().get_doctor_profile(request, new ApiListener.RawDocProfileDownloadListener() {
            @Override
            public void onDocDownloadSuccess(DoctorDetailProfile response) {
               initNameEditT(response.getName());
               initPhoneEditT(response.getPhone());
               initAboutMeEditT(response.getAboutMe());



            }

            private void initAboutMeEditT(String aboutMe) {
                ed_about_me.setText(aboutMe);
                ed_about_me.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String data = s.toString();
                        HashMap<String, String> request = new HashMap<String, String>();
                        request.put("id", doctor_id);
                        request.put("value",data);
                        request.put("field","phone");
                        Api.getInstance().updateDocProfile(request);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            private void initPhoneEditT(String phone) {
                ed_phone.setText(phone);
                ed_phone.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String data = s.toString();
                        HashMap<String, String> request = new HashMap<String, String>();
                        request.put("id", doctor_id);
                        request.put("value",data);
                        request.put("field","phone");
                        Api.getInstance().updateDocProfile(request);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            private void initNameEditT(String name) {
                ed_name.setText(name);
                ed_name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String data = s.toString();
                        HashMap<String, String> request = new HashMap<String, String>();
                        request.put("id", doctor_id);
                        request.put("value",data);
                        request.put("field","name");
                        Api.getInstance().updateDocProfile(request);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            @Override
            public void onADocDownloadFailed(String msg) {
                Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}