package com.telemedicine.maulaji.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFullScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        ButterKnife.bind(this);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get("link");
           // Glide.with(ImageFullScreenActivity.this).load(j).into(myZoomageView);

        }
    }
}
