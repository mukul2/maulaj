package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.telemedicine.maulaji.Fragments.NonPrescriptionFragment;
import com.telemedicine.maulaji.Fragments.PrescriptionUploadFragment;
import com.telemedicine.maulaji.R;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

     TabLayout tabLayout;
     ViewPager viewPager;
     Context context = this ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        int selectedColor = context.getResources().getColor(R.color.white);
        int normal = context.getResources().getColor(R.color.white);
        tabLayout.setTabTextColors(normal, selectedColor);
        tabLayout.setSelectedTabIndicatorColor(context.getResources().getColor(R.color.white));
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( NonPrescriptionFragment.newInstance(), "Non Prescription");
        adapter.addFragment( PrescriptionUploadFragment.newInstance(), "Prescription");

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