package com.telemedicine.maulaji.Activity;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.telemedicine.maulaji.Fragments.DepartmentsListFragment;
import com.telemedicine.maulaji.Fragments.HospitalListFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.BasicInfoModel;
import com.telemedicine.maulaji.model.SpecialistNameCount;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartmentsActivity extends AppCompatActivity implements ApiListener.basicInfoDownloadListener {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    public static List<String> HOSPITALS = new ArrayList<>();
    public static List<SpecialistNameCount> SPECIALIST = new ArrayList<>();
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        ButterKnife.bind(this);


        Api.getInstance().downloadBasicInfo(this);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DepartmentsListFragment(), "Departments");
        adapter.addFragment(new HospitalListFragment(), "Hospitals");
        viewPager.setAdapter(adapter);
        int selectedColor=context.getResources().getColor(R.color.black);
        int normal=context.getResources().getColor(R.color.textText);
        tabs.setTabTextColors(normal,selectedColor);

    }

    @Override
    public void onBasicInfoDownloadSuccess(BasicInfoModel data) {
        if (data != null) {
            HOSPITALS = data.getHospitalList();
            SPECIALIST = data.getSpacialist();
            setupViewPager(viewpager);
            tabs.setupWithViewPager(viewpager);
        } else {
            onBackPressed();
            Toast.makeText(this, "Something is not right.Try again later", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBasicInfoDownloadFailed(String msg) {
        onBackPressed();
        Toast.makeText(this, "Something is not right.Try again later", Toast.LENGTH_SHORT).show();


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

    public void Back(View view) {
        onBackPressed();
    }
}
