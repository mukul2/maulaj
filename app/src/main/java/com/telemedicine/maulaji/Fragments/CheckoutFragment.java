package com.telemedicine.maulaji.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.telemedicine.maulaji.Data.sharedPhotoListener;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.GeneralListener;
import com.telemedicine.maulaji.Utils.MyProgressBar;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.Utils.doForMe;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.StatusMessage;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UploadPrescriptionBottomSheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends BottomSheetDialogFragment {
    View view;
    Context context;
    @BindView(R.id.linerGuest)
    LinearLayout linerGuest;

    @BindView(R.id.ed_address)
    EditText ed_address;
    @BindView(R.id.ed_road)
    EditText ed_road;
    @BindView(R.id.ed_post_code)
    EditText ed_post_code;

    @BindView(R.id.cardSubmit)
    CardView cardSubmit;
    SessionManager sessionManager;

    MultipartBody.Part photo;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    String cartData ;
    public CheckoutFragment(String cart) {
        // Required empty public constructor
        this.cartData = cart ;
    }

    public static CheckoutFragment newInstance(String cart) {
        CheckoutFragment fragment = new CheckoutFragment(cart);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkout, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(context);
        if(sessionManager.getUserId().equals("0")){
            linerGuest.setVisibility(View.VISIBLE);
        }else{
            linerGuest.setVisibility(View.GONE);
        }


        cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProgressBar.with(context);
                String addredd = ed_address.getText().toString().trim();
                String post = ed_post_code.getText().toString().trim();
                String road = ed_road.getText().toString().trim();
                String name = ed_name.getText().toString().trim();
                String phone = ed_phone.getText().toString().trim();
                String uid = sessionManager.getUserId();
                String customphoto= "";
               if(uid.equals("0")){

                }else {
                     customphoto = sessionManager.get_userPhoto();
                }
                Api.getInstance().non_prescription_order(c_m_b(uid), c_m_b(customphoto), c_m_b(addredd), c_m_b(post), c_m_b(road), c_m_b(name),c_m_b(phone),cartData, new ApiListener.PrescriptionOrderPostListener() {
                    @Override
                    public void onPrescriptionOrderPostSuccess(StatusMessage data) {
                        Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                        MyProgressBar.dismiss();
                        getActivity().onBackPressed();
                    }

                    @Override
                    public void onPrescriptionOrderPosttFailed(String msg) {
                        //    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
                        MyProgressBar.dismiss();

                    }
                });
            }
        });
        return view;
    }






    private String c_m_b(String aThis) {
        return aThis;

    }
}
