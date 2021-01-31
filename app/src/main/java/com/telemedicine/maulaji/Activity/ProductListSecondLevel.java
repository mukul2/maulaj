package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.telemedicine.maulaji.Fragments.ByBrandsFragments;
import com.telemedicine.maulaji.Fragments.ByConditionsFragment;
import com.telemedicine.maulaji.Fragments.NonPrescriptionFragment;
import com.telemedicine.maulaji.Fragments.PrescriptionUploadFragment;
import com.telemedicine.maulaji.Fragments.RandomSubCategoryItem;
import com.telemedicine.maulaji.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListSecondLevel extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Context context = this ;
    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_second_level);
        ButterKnife.bind(this);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String state = (String) b.get("state");
            String title = (String) b.get("title");
            tvtitle.setText(title);
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            if(state.equals("0")) setupViewPagerMedicines(viewPager);

            if(state.equals("1")) setupViewPagerPersonalCare(viewPager);
            if(state.equals("2")) setupViewPagerWelbeingAndFitness(viewPager);
            if(state.equals("3")) setupViewPagerWelbeingAndFitness(viewPager);
            if(state.equals("3")) setupViewPagerMedicalDevices(viewPager);

        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        int selectedColor = context.getResources().getColor(R.color.white);
        int normal = context.getResources().getColor(R.color.white);
        tabLayout.setTabTextColors(normal, selectedColor);
        tabLayout.setSelectedTabIndicatorColor(context.getResources().getColor(R.color.white));
    }

    private void setupViewPagerMedicines(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( ByConditionsFragment.newInstance(), "By Conditions");
        adapter.addFragment( ByBrandsFragments.newInstance(), "By Brands");

        viewPager.setAdapter(adapter);
    }
    private void setupViewPagerPersonalCare(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( RandomSubCategoryItem.newInstance("Baby & child health"), "Baby & child health");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Eye Care"), "Eye Care");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Foot Care"), "Foot Care");
        adapter.addFragment( RandomSubCategoryItem.newInstance("First aid"), "First aid");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Hair loss"), "Hair loss");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Hand Wash & Sanitizers"), "Hand Wash & Sanitizers");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Men's health"), "Men's health");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Men's toiletries"), "Men's toiletries");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Mouth, lips & oral cares"), "Mouth, lips & oral care");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Sexual wellbeing"), "Sexual wellbeing");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Skin care"), "Skin care");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Women's health"), "Women's health");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Women's toiletries"), "Women's toiletries");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Skin rejuvenation"), "Skin rejuvenation");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Covid"), "Covid");

        viewPager.setAdapter(adapter);
    }

    private void setupViewPagerWelbeingAndFitness(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( RandomSubCategoryItem.newInstance("Anti Aging"), "Anti Aging");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Baby & child vitamins"), "Baby & child vitamins");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Brain supplements"), "Brain supplements");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Immune health"), "Immune health");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Men's health supplements"), "Men's health supplements");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Multivitamins"), "Multivitamins");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Pregnancy supplements"), "Pregnancy supplements");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Skin, Nail & Hair Care"), "Skin, Nail & Hair Care");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Women's health supplements"), "Women's health supplements");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Bone & Jointment Care"), "Bone & Jointment Care");


        viewPager.setAdapter(adapter);
    }

    private void setupViewPagerMedicalDevices(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( RandomSubCategoryItem.newInstance("Blood Pressure Apparatus"), "Blood Pressure Apparatus");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Glucose Monitors"), "Glucose Monitors");
        adapter.addFragment( RandomSubCategoryItem.newInstance("Nebulizers"), "Nebulizers");


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