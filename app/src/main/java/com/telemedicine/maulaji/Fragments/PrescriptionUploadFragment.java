package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telemedicine.maulaji.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PrescriptionUploadFragment extends Fragment {
    View view ;
    Context context ;
    @BindView(R.id.cardUpload)
    CardView cardUpload ;


    public PrescriptionUploadFragment() {
    }


    public static PrescriptionUploadFragment newInstance() {
        PrescriptionUploadFragment fragment = new PrescriptionUploadFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_prescription_upload, container, false);
        context = view .getContext();
        ButterKnife.bind(this,view);
        cardUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadPrescriptionBottomSheet addPhotoBottomDialogFragment = UploadPrescriptionBottomSheet.newInstance();
                addPhotoBottomDialogFragment.show(getChildFragmentManager(),
                        "add_photo_dialog_fragment");
            }
        });

        return  view ;
    }
}