package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientDocumentSingleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientDocumentSingleFragment extends Fragment {
    View view ;
    Context context  ;
    @BindView(R.id.img)
    ImageView img ;

    Map<String, Object> data ;

    public PatientDocumentSingleFragment(Map<String, Object> d ) {
        // Required empty public constructor
        this.data = d ;
    }


    public static PatientDocumentSingleFragment newInstance(Map<String, Object> d) {
        PatientDocumentSingleFragment fragment = new PatientDocumentSingleFragment(d);

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
        view = inflater.inflate(R.layout.fragment_patient_document_single, container, false);
        context = view.getContext() ;
        ButterKnife.bind(this,view);
        String link = data.get("url").toString();
        if(link.contains("http")){
            if(link.contains(".png")|link.contains(".jpg")|link.contains(".jpeg")){
                Glide.with(context).load(link).into(img);
            }
        }else {
            if(link.contains(".png")|link.contains(".jpg")|link.contains(".jpeg")){
                Glide.with(context).load(PHOTO_BASE+link).into(img);
            }
        }
        return  view ;
    }
}