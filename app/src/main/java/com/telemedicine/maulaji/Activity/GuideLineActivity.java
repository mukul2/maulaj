package com.telemedicine.maulaji.Activity;

import android.os.Bundle;
import android.view.View;

import com.telemedicine.maulaji.R;

public class GuideLineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_line);
    }

    public void back(View view) {
        onBackPressed();
    }
}
