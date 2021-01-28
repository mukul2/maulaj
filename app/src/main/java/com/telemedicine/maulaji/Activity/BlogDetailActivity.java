package com.telemedicine.maulaji.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.telemedicine.maulaji.R;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.NOW_SHOWING_BLOG;
import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;

public class BlogDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
//    @BindView(R.id.youtube_player_view)
//    YouTubePlayerView youtube_player_view;
    @BindView(R.id.tv_body)
    TextView tv_body;
    @BindView(R.id.coverImage)
    ImageView coverImage;
    Context context = this;
    @BindView(R.id.linearVdoBody)
    LinearLayout linearVdoBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
        ButterKnife.bind(this);
        tv_title.setText(NOW_SHOWING_BLOG.getTitle());
        tv_body.setText(NOW_SHOWING_BLOG.getBody());
        if (NOW_SHOWING_BLOG.getPhotoInfo().size() > 0) {

            Glide.with(context).load(PHOTO_BASE + NOW_SHOWING_BLOG.getPhotoInfo().get(0).getPhoto()).into(coverImage);
        }
        if (NOW_SHOWING_BLOG.getYoutube_video() != null && NOW_SHOWING_BLOG.getYoutube_video().length() > 0) {
            linearVdoBody.setVisibility(View.VISIBLE);
            coverImage.setVisibility(View.GONE);

        } else {
            linearVdoBody.setVisibility(View.GONE);
            coverImage.setVisibility(View.VISIBLE);


        }

    }

    public void Back(View view) {
        onBackPressed();
    }
}
