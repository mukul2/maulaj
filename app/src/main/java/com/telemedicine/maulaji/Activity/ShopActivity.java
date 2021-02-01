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
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.telemedicine.maulaji.Data.sharedPhotoListener;
import com.telemedicine.maulaji.Fragments.NonPrescriptionFragment;
import com.telemedicine.maulaji.Fragments.PrescriptionUploadFragment;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.Utillites;
import com.theartofdev.edmodo.cropper.CropImage;

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
        //Toast.makeText(context, " this is " + context, Toast.LENGTH_SHORT).show();
Utillites.thisContext(context);
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

    public void back(View view) {
        onBackPressed();
    }

    public void openSearch(View view) {
        startActivity(new Intent(context,ProductSearchActivity.class));
    }

    public void openCart(View view) {
        startActivity(new Intent(context,CartListActivity.class));
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Toast.makeText(context, "One Image is picked", Toast.LENGTH_SHORT).show();
              //  resultUri = result.getUri();
              //  image.setImageURI(resultUri);
                sharedPhotoListener.pListenerUri.onPicSelected(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}