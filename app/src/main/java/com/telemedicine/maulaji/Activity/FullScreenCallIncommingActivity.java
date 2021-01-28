package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.telemedicine.maulaji.R;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullScreenCallIncommingActivity extends AppCompatActivity {
    @BindView(R.id.imgBig)
    ImageView imgBig;
    @BindView(R.id.cardReceive)
    CardView cardReceive;
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.imgSmall)
    CircleImageView imgSmall;
    Context context = this ;


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_screen_call_incomming);
        ButterKnife.bind(this);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String img =(String) b.get("img");
            String name =(String) b.get("name");
            String room =(String) b.get("room");
            Glide.with(context).load(img).apply(RequestOptions.bitmapTransform(new BlurTransformation(25,3))).into(imgBig);
            Glide.with(context).load(img).into(imgSmall);
            cardReceive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JitsiMeetConferenceOptions options
                            = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(room)

                            .build();
                    // Launch the new activity with the given options. The launch() method takes care
                    // of creating the required Intent and passing the options.
                       JitsiMeetActivity.launch(context, options);
                       finish();
                }
            });

            cardReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onBackPressed();
                }
            });

        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }







    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */

}