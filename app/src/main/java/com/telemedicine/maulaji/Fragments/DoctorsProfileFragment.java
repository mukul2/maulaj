package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonElement;
import com.telemedicine.maulaji.Activity.DateAndTimeSlotActivity;
import com.telemedicine.maulaji.Activity.HomeCareRequestActivity;
import com.telemedicine.maulaji.Activity.UrgentCareRequestActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.DoctorModelRaw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DOC;
import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorsProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorsProfileFragment extends BottomSheetDialogFragment {
    DoctorModelRaw docData;
    View view;
    Context context;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.cardBook)
    CardView cardBook;
    String nowShowingType = "";


    // TODO: Rename and change types of parameters


    public DoctorsProfileFragment(DoctorModelRaw objectMap, String type) {
        this.docData = objectMap;
        this.nowShowingType = type;
    }


    public static DoctorsProfileFragment newInstance(DoctorModelRaw objectMap, String type) {
        DoctorsProfileFragment fragment = new DoctorsProfileFragment(objectMap, type);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doctors_profile, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        Glide.with(context).load(PHOTO_BASE + docData.getImgUrl().toString()).into(img);
        TextView tv = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_department = (TextView) view.findViewById(R.id.tv_dept);
        TextView tv_address = (TextView) view.findViewById(R.id.tv_address);
        TextView tv_profile = (TextView) view.findViewById(R.id.tv_profile);
        TextView tv_about_me = (TextView) view.findViewById(R.id.tv_about_me);
        TextView tv_specialization = (TextView) view.findViewById(R.id.tv_specialization);
        TextView tv_education = (TextView) view.findViewById(R.id.tv_education);
        TextView tv_experience = (TextView) view.findViewById(R.id.tv_experience);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        tv.setText(docData.getName().toString());
        if (docData.getDepartment() != null) {
            tv_department.setText(docData.getDepartment().toString());
        } else {
            tv_department.setVisibility(View.GONE);
        }


        if (docData.getProfile() != null) {
            tv_profile.setText(docData.getProfile().toString());
        } else {
            tv_profile.setVisibility(View.GONE);
        }


        if (docData.getAbout_me() != null) {
            tv_about_me.setText(docData.getAbout_me().toString());
        } else {
            tv_about_me.setVisibility(View.GONE);
        }
        if (docData.getAddress() != null) {
           // tv_address.setText(docData.getAddress().toString());
        } else {
          //  tv_address.setVisibility(View.GONE);
        }

        if (docData.getSpecialization() != null) {
            tv_specialization.setText(docData.getSpecialization().toString());
        } else {
            tv_specialization.setVisibility(View.GONE);
        }

       // tv_education.setText(docData.getEducation().toString());
        // tv_experience.setText(docData.getExperience().toString());
        cardBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nowShowingType.equals("typeOne")) {
                    Intent intent = new Intent(context, DateAndTimeSlotActivity.class);

                    NOW_SHOWING_DOC = docData;
                    //   NOW_SHOWING_DYNAMIC = data__;
                    startActivity(intent);
                } else if (nowShowingType.equals("typeTwo")) {
                    Intent intent = new Intent(context, DateAndTimeSlotActivity.class);

                    NOW_SHOWING_DOC = docData;
                    //   NOW_SHOWING_DYNAMIC = data__;
                    startActivity(intent);
                } else if (nowShowingType.equals("typeThree")) {
                    Intent intent = new Intent(context, UrgentCareRequestActivity.class);

                    NOW_SHOWING_DOC = docData;
                    //   NOW_SHOWING_DYNAMIC = data__;
                    startActivity(intent);
                } else if (nowShowingType.equals("typeFour")) {
                    Intent intent = new Intent(context, HomeCareRequestActivity.class);

                    NOW_SHOWING_DOC = docData;
                    //   NOW_SHOWING_DYNAMIC = data__;
                    startActivity(intent);
                }


            }
        });

        if (docData.getEducation() != null) {
            try {
                // JSONObject object = new JSONObject(docData.getEducation().toString());
                JSONArray array = new JSONArray(docData.getEducation().toString());
                //college_institute
                String d = "";
                if (array.length() > 0) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        d = d + ob.getString("degree") + " , " + ob.getString("college_institute");
                        if (i + 1 == array.length()) {
                        } else {
                            d = d + "\n";
                        }

                    }
                    tv_education.setText(d);
                }
                // tv_education.setText(""+array.length());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else tv_education.setVisibility(View.GONE);
        String ex = "";
        if (docData.getExperience() != null){ try {
            ex = "";
            //exp_hospital_name
            //exp_from
            //exp_to
            //todate
            //designation
            // JSONObject object = new JSONObject(docData.getEducation().toString());
            JSONArray array = new JSONArray(docData.getExperience().toString());
            //college_institute

            if (array.length() > 0) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject ob = array.getJSONObject(i);
                    ex = ex + ob.getString("exp_hospital_name") + " , " + ob.getString("exp_from") + " , " + ob.getString("exp_to") + ob.getString("todate") + " , " + ob.getString("designation");
                    if (i + 1 == array.length()) {
                    } else {
                        ex = ex + "\n";
                    }

                }
                Log.i("mkl", ex);
                // Toast.makeText(context, ex, Toast.LENGTH_SHORT).show();
                tv_experience.setText(ex);
            } else {
                tv_experience.setText("No experience");
            }
            // tv_education.setText(""+array.length());
        } catch (JSONException e) {
            tv_experience.setVisibility(View.GONE);
            // Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }else tv_experience.setVisibility(View.GONE);

  if(docData.getAddress()!=null) {
      try {
          ex = "";
          //exp_hospital_name
          //exp_from
          //exp_to
          //todate
          //designation
          // JSONObject object = new JSONObject(docData.getEducation().toString());
          JSONArray array = new JSONArray(docData.getAddress().toString());
          //college_institute
          tv_address.setText("" + array.length());
          //  Toast.makeText(context, ""+array.length(), Toast.LENGTH_SHORT).show();

          if (array.length() > 0) {
              for (int i = 0; i < array.length(); i++) {

                  ex = ex + array.get(i).toString();
                  // ex = ex + ob.getString("exp_hospital_name") + " , " + ob.getString("exp_from") + " , " + ob.getString("exp_to") + ob.getString("todate") + " , " + ob.getString("designation");
                  if (i + 1 == array.length()) {
                  } else {
                      ex = ex + "\n";
                  }

              }
              // Toast.makeText(context, ex, Toast.LENGTH_SHORT).show();
              Log.i("mkl", ex);
              tv_address.setVisibility(View.VISIBLE);
              tv_address.setText(ex);
          } else {
              //  tv_address.setVisibility(View.GONE);
          }
          // tv_education.setText(""+array.length());
      } catch (JSONException e) {
          //  tv_address.setVisibility(View.GONE);
          Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
          e.printStackTrace();
      }
  }else {
      tv_address.setVisibility(View.GONE);
  }


        return view;
    }
}