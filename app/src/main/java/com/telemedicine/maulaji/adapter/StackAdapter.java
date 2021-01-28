package com.telemedicine.maulaji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.StackItems;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.List;
import java.util.Map;

public class StackAdapter extends BaseAdapter {
 
    List arrayList;
    LayoutInflater inflater;
    ViewHolder holder = null;
    Context context ;
 
    public StackAdapter(Context con, List<StackItems> arrayList) {
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(con);
        this.context = con;
    }
 
    @Override
    public int getCount() {
        return arrayList.size();
    }
 
    @Override
    public Object getItem(int pos) {
        return arrayList.get(pos);
    }
 
    @Override
    public long getItemId(int pos) {
        return pos;
    }
 
    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.prescription_row, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Map<String, Object> data = (Map<String, Object>) arrayList.get(pos);

        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
        TextView tv_symptoms = (TextView) view.findViewById(R.id.tv_symptoms);
        TextView tv_advice = (TextView) view.findViewById(R.id.tv_advice);
        TextView tv_doctor = (TextView) view.findViewById(R.id.tv_doctor);
        RecyclerView recycler_viewMeds = (RecyclerView) view.findViewById(R.id.recycler_viewMeds);
        //holder.image.setBackgroundResource(arrayList.get(pos).getImage());

        tv_doctor.setText(data.get("doctorname").toString());
        tv_symptoms.setText(data.get("symptom").toString().trim());
        tv_advice.setText(data.get("advice").toString());
        //tv_date.setText(data.get("date").toString());
        //Log.i("uni",data.get("date").toString());

        List medList =(List) data.get("medicine_list");
        //showMedicineList
        engineGridViews engineGridViews = new engineGridViews();

        engineGridViews.showMedicineList(medList,recycler_viewMeds,context,R.layout.medicine_row,null);
 
        return view;
    }
 
    public class ViewHolder {
        ImageView image;
    }
 
}