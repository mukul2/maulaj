package com.telemedicine.maulaji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.Data.DataStore;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.Day;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    Context context;
    List<Day> list = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day, tv_time;

        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_time = (TextView) view.findViewById(R.id.tv_time);


        }
    }


    public ScheduleAdapter() {


    }

    public ScheduleAdapter(List<Day> days) {
        this.list = days;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.days_single_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Day movie = list.get(position);

        holder.tv_day.setText(DataStore.convertToWeekDay(movie.getDay()));
        holder.tv_time.setText(movie.getTime());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}