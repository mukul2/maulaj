package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.telemedicine.maulaji.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class CallGpNowMyAppFragment extends Fragment {
    View v;
    Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    public static CallGpNowMyAppFragment newInstance() {
        CallGpNowMyAppFragment fragment = new CallGpNowMyAppFragment();
        return fragment;
    }

    public CallGpNowMyAppFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_call_gp_now_my_app, container, false);
        context = v.getContext();

        ButterKnife.bind(this, v);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        int selectedColor = Color.WHITE;
        int normal = Color.WHITE;
        tabLayout.setTabTextColors(normal, selectedColor);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);



        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ScheduledAppointmentfragment(), "Scheduled");
        adapter.addFragment(new UrgentConsultationFragment(), "Urgent");
        adapter.addFragment(new HomeVisitAppointment(), "Home Visit");


        viewPager.setAdapter(adapter);
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
