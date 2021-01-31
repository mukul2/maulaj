package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.telemedicine.maulaji.Activity.DateAndTimeSlotActivity;
import com.telemedicine.maulaji.Activity.HomeCareRequestActivity;
import com.telemedicine.maulaji.Activity.UrgentCareRequestActivity;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.DoctorModelRaw;

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
        ImageView img = (ImageView) view.findViewById(R.id.img);
        tv.setText(docData.getName().toString());
        tv_department.setText(docData.getDepartment().toString());
        tv_address.setText(docData.getAddress().toString());
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
                }else if (nowShowingType.equals("typeFour")) {
                    Intent intent = new Intent(context, HomeCareRequestActivity.class);

                    NOW_SHOWING_DOC = docData;
                    //   NOW_SHOWING_DYNAMIC = data__;
                    startActivity(intent);
                }


            }
        });

        return view;
    }
}