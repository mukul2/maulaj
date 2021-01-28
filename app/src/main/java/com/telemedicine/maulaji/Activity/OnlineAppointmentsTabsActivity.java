package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.telemedicine.maulaji.Fragments.DrAppointListFragments;
import com.telemedicine.maulaji.R;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnlineAppointmentsTabsActivity extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Context context = this;

    List<Map<String,String>> datesData = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_appointments_tabs);
        ButterKnife.bind(this);

        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);

      //  int selectedColor = context.getResources().getColor(R.color.white);
       // int normal = context.getResources().getColor(R.color.textText);
       // tabs.setTabTextColors(normal, selectedColor);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Calendar calendar = Calendar.getInstance();


        for (int i = 0; i<10;i++){
            Map<String,String> data_=new HashMap<>();
            DateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String dateFormatted = formatter.format(calendar.getTime());
            data_.put("datebig",dateFormatted);
            data_.put("date",""+calendar.get(Calendar.YEAR)+"-"+formateString(1+calendar.get(Calendar.MONTH))+"-"+formateString(calendar.get(Calendar.DATE)));
            data_.put("day",""+calendar.get(Calendar.DAY_OF_WEEK));
            data_.put("isSelected","1");
            data_.put("dayIndex",String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)));
            datesData.add(data_);
            adapter.addFragment(new DrAppointListFragments(data_), dateFormatted);
            calendar.add(Calendar.DATE,1);
        }

        viewPager.setAdapter(adapter);
    }

    public  String formateString(int input){
        return input> 9? ""+input:( "0"+String.valueOf(input));
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}