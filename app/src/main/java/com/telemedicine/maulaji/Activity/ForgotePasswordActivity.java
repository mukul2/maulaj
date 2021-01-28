package com.telemedicine.maulaji.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.FetchProfileResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;

public class ForgotePasswordActivity extends AppCompatActivity {
    @BindView(R.id.ed_key)
    EditText ed_key;
    @BindView(R.id.linearSearchBody)
    LinearLayout linearSearchBody;
    @BindView(R.id.linearNoResult)
    LinearLayout linearNoResult;
    @BindView(R.id.linear_this_is_my_account)
    LinearLayout linear_this_is_my_account;

    @BindView(R.id.profile)
    ImageView profile;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_verification_text)
    TextView tv_verification_text;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgote_password);
        setUpStatusbar();
        ButterKnife.bind(this);
    }

    public void setUpStatusbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void search(View view) {
        String key = ed_key.getText().toString().trim();
        if (key.length() > 0) {
            MyProgressBar.with(context);
            Api.getInstance().fetchPeofile(key, new ApiListener.profileFetchListener() {
                @Override
                public void onprofileFetchSuccess(FetchProfileResponse list) {
                    MyProgressBar.dismiss();
                    if (list.getStatus() == true) {
                        linearNoResult.setVisibility(View.GONE);
                        linearSearchBody.setVisibility(View.GONE);
                        linear_this_is_my_account.setVisibility(View.VISIBLE);
                        Glide.with(context).load(PHOTO_BASE + list.getProfile().getImageUrl()).into(profile);
                        tv_name.setText(list.getProfile().getUsername());
                        tv_verification_text.setText("Send verification code to ******" + list.getProfile().getPhone().toString().substring((list.getProfile().getPhone().toString().length() - 7), (list.getProfile().getPhone().toString().length())));
                        tv_verification_text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ForgotePasswordActivity.this, ForgotePasswordPhoneVerificationActivity.class);
                                intent.putExtra("number", list.getProfile().getPhone().toString());
                                startActivity(intent);

                            }
                        });
                        if (list.getProfile().equals("p")) {
                            tv_type.setText("Patient");
                        } else {
                            tv_type.setText("Doctor");

                        }
                    } else {
                        linearNoResult.setVisibility(View.VISIBLE);

                    }
                }

                @Override
                public void onprofileFetchFailed(String msg) {
                    MyProgressBar.dismiss();

                }
            });


        }
    }

    public void saearchagain(View view) {
        linearNoResult.setVisibility(View.GONE);
        linearSearchBody.setVisibility(View.VISIBLE);
    }
}
