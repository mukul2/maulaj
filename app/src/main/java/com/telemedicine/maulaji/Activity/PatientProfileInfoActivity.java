package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.telemedicine.maulaji.Fragments.AppintmentInfoFragment;
import com.telemedicine.maulaji.Fragments.CallGpNowMyAppFragment;
import com.telemedicine.maulaji.Fragments.DiagnosticInvestigationsFragment;
import com.telemedicine.maulaji.Fragments.DiseasesHistoryFragment;
import com.telemedicine.maulaji.Fragments.DocumentFragment;
import com.telemedicine.maulaji.Fragments.HomeVisitAppointment;
import com.telemedicine.maulaji.Fragments.LabReportsFragment;
import com.telemedicine.maulaji.Fragments.PrescriptionsFragment;
import com.telemedicine.maulaji.Fragments.ScheduledAppointmentfragment;
import com.telemedicine.maulaji.Fragments.UrgentConsultationFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyDialog;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PatientProfileInfoActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    Context context = this ;
    SessionManager sessionManager ;
    String targetuserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_info);
        ButterKnife.bind(this);
        //PrescriptionsFragment
        //DiseasesHistoryFragment
        sessionManager = new SessionManager(this);
        targetuserID = sessionManager.getUserId();

        viewPager = (ViewPager) findViewById(R.id.vpPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        int selectedColor = Color.WHITE;
        int normal = Color.WHITE;
        tabLayout.setTabTextColors(normal, selectedColor);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);

        ImageView cardVcall = (ImageView) findViewById(R.id.cardVcall);
        ImageView cardAcall = (ImageView) findViewById(R.id.cardAcall);
        ImageView cardChat = (ImageView) findViewById(R.id.cardChat);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            int j =(int) b.get("pos");
            viewPager.setCurrentItem(j);
        }





    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new PrescriptionsFragment(targetuserID), "Prescriptions");
        adapter.addFragment(new LabReportsFragment(targetuserID), "Lab Reports");
        adapter.addFragment(new DocumentFragment(targetuserID), "Documents");
       // adapter.addFragment(new DiagnosticInvestigationsFragment(targetuserID), "Diagnostic Investigations");



        viewPager.setAdapter(adapter);
    }

    public void back(View view) {
        onBackPressed();
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