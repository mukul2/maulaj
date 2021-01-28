package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.StatusMessage;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;

public class DocumentAddAcitvityForPatient extends AppCompatActivity {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.ed_title)
    EditText ed_title;
    Context context = this ;
    Uri uri;
    SessionManager sessionManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_add_acitvity_for_patient);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
    }
    private void askPermission() {
        Dexter.withActivity(this)
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
                .start((Activity)context);
    }
    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 77) {
          //  ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
           // sharedPhotoListener.pListener.onPicSelected(returnValue);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri = result.getUri();

               // Toast.makeText(context, "image picked listened from activity", Toast.LENGTH_SHORT).show();

                Glide.with(context).load( result.getUri()).into(img);

               // img.setImageURI( result.getUri());


             //   sharedPhotoListener.pListenerUri.onPicSelected(result.getUri());

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    public void openGallary(View view) {
        askPermission();
    }

    public void submit(View view) {
        //addDocumentBypatient
        String title = ed_title.getText().toString().trim();
        String hospital  = "477";
        if (title.length()>0){
            if (uri != null) {

                MyProgressBar.with(context);
                File f = new File(uri.getPath());
                MultipartBody.Part photo = null;
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), f);
                photo = MultipartBody.Part.createFormData("fileToUpload", f.getName(), requestFile);
                Api.getInstance().addDocumentBypatient( c_m_b(sessionManager.getUserId()),c_m_b(hospital), c_m_b(title), photo, new ApiListener.PatientDocUPListener() {
                    @Override
                    public void onPatienttDocUPSuccess(StatusMessage data) {
                        MyProgressBar.dismiss();
                        if (data.getStatus() == true) {

                            Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                            GeneralListener.needRefresh.doRefresh(0);
                            onBackPressed();


                        }else {
                            Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onPatienttDocUPSuccessFailed(String msg) {
                        MyProgressBar.dismiss();
                        Toast.makeText(context, "Add failed.Try again later", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        }



    }
    private RequestBody c_m_b(String aThis) {
        return
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), aThis);
    }

}