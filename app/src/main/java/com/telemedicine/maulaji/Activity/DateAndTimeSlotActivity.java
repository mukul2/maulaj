package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_DOC;

public class DateAndTimeSlotActivity extends AppCompatActivity {
    @BindView(R.id.calender)
    CalendarView calendarView;

    @BindView(R.id.tv_no_data)
    CardView tv_no_data;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    engineGridViews engineGridViews;
    Context context = this;
    enum SelectedDayType {past,present,future}
    SelectedDayType justSelected = SelectedDayType.present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time_slot);
        ButterKnife.bind(this);
        //free_slots_doctors_call_gp
        engineGridViews = new engineGridViews();
        initToday();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //Toast.makeText(DateAndTimeSlotActivity.this, ""+i+"/"+i1+"/"+i2, Toast.LENGTH_SHORT).show();



                String date = ""+i+"/"+(1+i1)+"/"+i2;
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.DATE,i2);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.YEAR,i);

                if(calendar.equals(Calendar.getInstance()) ) {
                    Log.i("mkl", "" + calendar.get(Calendar.DAY_OF_WEEK));
                    Log.i("mkl", "" + "" + calendar.getTimeInMillis() * 1000);
                    Log.i("mkl", NOW_SHOWING_DOC.getId());
                    //String id =String.valueOf((NOW_SHOWING_DYNAMIC.get("id")));
                    justSelected =  SelectedDayType.present;
                    tv_no_data.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                    loadAndShow(calendar);
                }else if( calendar.after(Calendar.getInstance())) {
                    Log.i("mkl", "" + calendar.get(Calendar.DAY_OF_WEEK));
                    Log.i("mkl", "" + "" + calendar.getTimeInMillis() * 1000);
                    Log.i("mkl", NOW_SHOWING_DOC.getId());
                    //String id =String.valueOf((NOW_SHOWING_DYNAMIC.get("id")));
                    justSelected =  SelectedDayType.future;
                    tv_no_data.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                    loadAndShow(calendar);
                }else {
                    justSelected =  SelectedDayType.present;
                    recycler_view.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                    Toast.makeText(DateAndTimeSlotActivity.this, "Sorry, Cannot select old days", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadAndShow(Calendar calendar) {
       // Toast.makeText(context, ""+NOW_SHOWING_DYNAMIC.get("id"), Toast.LENGTH_SHORT).show();
       // String id = new DecimalFormat("#").format(NOW_SHOWING_DYNAMIC.get("id"));


         String id = ""+NOW_SHOWING_DOC.getId();
         Api.getInstance().free_slots_doctors_call_gp("" + (calendar.get(Calendar.DAY_OF_WEEK)-1), "" + calendar.getTimeInMillis(),id, new ApiListener.AllSloatloadListener() {
            @Override
            public void onAllSloatDownloadSuccess(List response) {
                Log.i("mkl",response.toString());
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener serviceTypeSelectListener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos,int op) {
                        //selectedServicePos = pos ;
                        final Map<String, Object> data = (Map<String, Object>) response.get(pos);
                        Intent intent = new Intent(context,BookingAppointmentActivity.class);
                        intent.putExtra("dr_id",id);
                        intent.putExtra("s_time",data.get("s_time").toString());
                        intent.putExtra("e_time",data.get("e_time").toString());
                        intent.putExtra("time_slot",data.get("s_time").toString()+" To "+data.get("e_time").toString());
                        intent.putExtra("s_time_key",data.get("s_time_key").toString());
                        intent.putExtra("hospital_id",data.get("hospital_id").toString());
                        intent.putExtra("dateLong",String.valueOf(calendar.getTimeInMillis()));
                        intent.putExtra("date",""+calendar.get(Calendar.YEAR)+"-"+(1+calendar.get(Calendar.MONTH))+"-"+calendar.get(Calendar.DATE));
                        //startActivity(intent);

                        if(justSelected == SelectedDayType.present){

                            Calendar calendarReal = Calendar.getInstance();


                            Calendar calendarTodayOnlyDay= Calendar.getInstance();
                            calendarTodayOnlyDay.set(Calendar.HOUR_OF_DAY,0);
                            calendarTodayOnlyDay.set(Calendar.MINUTE,0);
                            calendarTodayOnlyDay.set(Calendar.SECOND,0);



                            //calendar.getTime().
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                            Date testDate = null;
                            try {
                                testDate = sdf.parse(data.get("s_time").toString().toLowerCase());
                              //  testDate = sdf.parse("22:51 pm");
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }

                            long sumedTime = calendarTodayOnlyDay.getTime().getTime()+testDate.getTime();

                            if(calendarReal.getTime().getTime()<sumedTime){
                                Log.i("mkl","passed "+calendarReal.getTime().getTime()+" vs  "+sumedTime);
                                Log.i("mkl","diff in sec  "+(-calendarReal.getTime().getTime()+sumedTime));
                                Log.i("mkl","diff in min  "+(-calendarReal.getTime().getTime()+sumedTime)/60000);
                                Toast.makeText(context, "passed "+calendarReal.getTime().getTime()+" vs  "+testDate.getTime(), Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }else {
                                Log.i("mkl","failed "+calendarReal.getTime().getTime()+" vs  "+sumedTime);
                                Log.i("mkl","diff in sec  "+(-calendarReal.getTime().getTime()+sumedTime));
                                Log.i("mkl","diff in min  "+(-calendarReal.getTime().getTime()+sumedTime)/60000);

                                Toast.makeText(context, "failed "+calendarReal.getTime().getTime()+" vs  "+testDate.getTime(), Toast.LENGTH_SHORT).show();
                            }


                       // Toast.makeText(context, data.get("s_time").toString().toLowerCase() + " vs "+newFormat, Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                if(response.size()>0) {
                    tv_no_data.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                    engineGridViews.showSlotList(response, recycler_view, context, R.layout.time_slot_item, serviceTypeSelectListener);
                }else {
                    tv_no_data.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAllSloatDownloadFailed(String msg) {
                //Toast.makeText(DateAndTimeSlotActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initToday() {
        Calendar calendar=Calendar.getInstance();
        loadAndShow(calendar);
    }

    public void back(View view) {
        onBackPressed();
    }
}