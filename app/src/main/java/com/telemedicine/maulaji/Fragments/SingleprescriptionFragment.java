package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleprescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleprescriptionFragment extends Fragment {
    Map<String, Object> object ;
    View view;
    Context context ;



    public SingleprescriptionFragment(Map<String, Object> o) {
        // Required empty public constructor
        this.object = o;
    }


    public static SingleprescriptionFragment newInstance(Map<String, Object> o) {

        SingleprescriptionFragment fragment = new SingleprescriptionFragment(o);

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
        view = inflater.inflate(R.layout.fragment_singleprescription, container, false);
        context = view.getContext();


        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
        TextView tv_symptoms = (TextView)view.findViewById(R.id.tv_symptoms);
        TextView tv_advice = (TextView)view.findViewById(R.id.tv_advice);
        TextView tv_doctor = (TextView)view.findViewById(R.id.tv_doctor);
        RecyclerView recycler_viewMeds = (RecyclerView) view.findViewById(R.id.recycler_viewMeds);
       // Double d = Double.parseDouble(object.get("date").toString());
        // Long t = Long.getLong(data.get("date").toString());
        // java.util.Date time=new java.util.Date((long)Long.getLong(data.get("date").toString()));
       // java.util.Date time=new java.util.Date(d.longValue());
        tv_date.setText(object.get("date").toString());
        tv_doctor.setText(object.get("doctorname").toString());
        tv_symptoms.setText(object.get("symptom").toString().trim());
        tv_advice.setText(object.get("advice").toString());
        //tv_date.setText(data.get("date").toString());
        //Log.i("uni",data.get("date").toString());

        List medList =(List) object.get("medicine_list");
        //showMedicineList
        engineGridViews engineGridViews = new engineGridViews();
        engineGridViews. showMedicineList(medList,recycler_viewMeds,context,R.layout.medicine_row,null);



        return  view ;
    }
}