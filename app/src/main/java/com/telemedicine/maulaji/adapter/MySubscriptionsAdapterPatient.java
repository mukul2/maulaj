package com.telemedicine.maulaji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.Activity.ChatActivityCommon;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.SubscriptionsModel;

import java.util.ArrayList;
import java.util.List;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;

/**
 * Created by mukul on 3/10/2019.
 */


public class MySubscriptionsAdapterPatient extends RecyclerView.Adapter<MySubscriptionsAdapterPatient.MyViewHolder> {
    List<SubscriptionsModel>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_dr_name, tv_duration, tv_expires;
        ImageView img_profile;
        RelativeLayout relative_container;
        LinearLayout linearNotVarified,linearVarified;


        public MyViewHolder(View view) {
            super(view);
            tv_dr_name = (TextView) view.findViewById(R.id.tv_dr_name);
            tv_duration = (TextView) view.findViewById(R.id.tv_duration);
            // tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_expires = (TextView) view.findViewById(R.id.tv_expires);
            linearNotVarified = (LinearLayout) view.findViewById(R.id.linearNotVarified);
            linearVarified = (LinearLayout) view.findViewById(R.id.linearVarified);

            img_profile = (ImageView) view.findViewById(R.id.img_profile);


        }
    }


    public MySubscriptionsAdapterPatient(List<SubscriptionsModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subscriptions_item_patients, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SubscriptionsModel movie = list.get(position);
        context = holder.tv_dr_name.getContext();
        holder.tv_dr_name.setText(movie.getDrInfo().getName());
        holder.tv_duration.setText(""+movie.getNumberOfMonths()+" Months");
        holder.tv_expires.setText("Expires : "+movie.getEnds());
        Glide.with(context).load(PHOTO_BASE+movie.getDrInfo().getPhoto()).into(holder.img_profile);
//        String time = "";
//        for (int i = 0; i < movie.getDays().size(); i++) {
//            time += movie.getDays().get(i).getDay() + "  " + movie.getDays().get(i).getTime() + "\n";
//        }
//        holder.tv_time.setText(time);
        if (movie.getStatus()==1){
            holder.linearNotVarified.setVisibility(View.GONE);
            holder.linearVarified.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatActivityCommon.class);
                    i.putExtra("partner_id", String.valueOf(movie.getDrId()));
                    i.putExtra("partner_name", movie.getDrInfo().getName());
                    i.putExtra("partner_photo", movie.getDrInfo().getPhoto());
                    context.startActivity(i);
                }
            });
        }else {
            holder.linearNotVarified.setVisibility(View.VISIBLE);
            holder.linearVarified.setVisibility(View.GONE);
        }



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}