package com.telemedicine.maulaji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.model.VideoCallHistoryModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;

/**
 * Created by mukul on 3/10/2019.
 */


public class CallLogHistoryAdapter extends RecyclerView.Adapter<CallLogHistoryAdapter.MyViewHolder> {
    List<VideoCallHistoryModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  tv_name, tv_duration, tv_date;
        ImageView circleImageView;
        RelativeLayout relative_container;
        CircleImageView profile;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_duration = (TextView) view.findViewById(R.id.tv_duration);
            profile = (CircleImageView) view.findViewById(R.id.profile);


        }
    }


    public CallLogHistoryAdapter(List<VideoCallHistoryModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.call_log_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final VideoCallHistoryModel movie = list.get(position);
        context = holder.tv_name.getContext();
        SessionManager sessionManager=new SessionManager(context);
        if (sessionManager.getUserType().equals("p")){
            holder.tv_name.setText(movie.getDrInfo().getName());
            Glide.with(context).load(PHOTO_BASE+movie.getDrInfo().getPhoto()).into(holder.profile);

        }else {
            holder.tv_name.setText(movie.getPatientInfo().getName());
            Glide.with(context).load(PHOTO_BASE+movie.getPatientInfo().getPhoto()).into(holder.profile);


        }
        Date currentDate = new Date(Long.parseLong( movie.getCallTime()));
        DateFormat df = new SimpleDateFormat("hh:mm:ss aa EEE dd MMM yy");

        holder.tv_date.setText(df.format(currentDate));
        holder.tv_duration.setText(movie.getDuration()+" seconds");


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}