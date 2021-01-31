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
public class UploadPrescriptionBottomSheet extends BottomSheetDialogFragment {
    View view;
    Context context;
    @BindView(R.id.linerGuest)
    LinearLayout linerGuest;
    @BindView(R.id.imgview)
    ImageView imgview;
    @BindView(R.id.ed_address)
    EditText ed_address;
    @BindView(R.id.ed_road)
    EditText ed_road;
    @BindView(R.id.ed_post_code)
    EditText ed_post_code;
    @BindView(R.id.ed_attach)
    TextView ed_attach;
    @BindView(R.id.cardSubmit)
    CardView cardSubmit;
    SessionManager sessionManager;

    MultipartBody.Part photo;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    public UploadPrescriptionBottomSheet() {
        // Required empty public constructor
    }

    public static UploadPrescriptionBottomSheet newInstance() {
        UploadPrescriptionBottomSheet fragment = new UploadPrescriptionBottomSheet();

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
        view = inflater.inflate(R.layout.fragment_upload_prescription_bottom_sheet, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(context);
        if(sessionManager.getUserId().equals("0")){
            linerGuest.setVisibility(View.VISIBLE);
        }else{
            linerGuest.setVisibility(View.GONE);
        }
        initPhotoAddedListener();
        ed_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();
            }
        });

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
                String customphoto = sessionManager.get_userPhoto();
                Api.getInstance().prescription_order_phaarmacey(c_m_b(uid), c_m_b(customphoto), c_m_b(addredd), c_m_b(post), c_m_b(road), c_m_b(name),c_m_b(phone),photo, new ApiListener.PrescriptionOrderPostListener() {
                    @Override
                    public void onPrescriptionOrderPostSuccess(StatusMessage data) {
                        //   Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void initPhotoAddedListener() {
        sharedPhotoListener.setpListenerUri(new sharedPhotoListener.PhotoPickedListenerUri() {
            @Override
            public void onPicSelected(Uri data) {


                imgview.setImageURI(data);

                File f = new File(data.getPath());
                photo = null;
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), f);
                photo = MultipartBody.Part.createFormData("fileToUpload", f.getName(), requestFile);


            }
        });
    }

    private void askPermission() {
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            openCamera();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    private void openCamera() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity());
    }

    public void PickImage(View view) {
        askPermission();

    }

    private RequestBody c_m_b(String aThis) {
        return
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), aThis);
    }
}
