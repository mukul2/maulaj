package com.telemedicine.maulaji.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.StackPageTransformer;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.telemedicine.maulaji.Activity.DocumentAddAcitvityForPatient;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.telemedicine.maulaji.Data.Data.testList;
import static com.telemedicine.maulaji.Data.DataStore.TOKEN;
import static com.telemedicine.maulaji.Data.DataStore.USER_ID;


public class DocumentFragment extends Fragment {
    View view;
    Context context;
    FlippableStackView mFlippableStack;
    String targetuserID;
    ColorFragmentAdapter mPageAdapter;
    List<Fragment> mViewPagerFragments;
    @BindView(R.id.floatingAdddoc)
    ExtendedFloatingActionButton floatingAdddoc;
    SessionManager sessionManager ;

    public DocumentFragment(String uid) {
        this.targetuserID = uid;
    }


    public static DocumentFragment newInstance(String uid) {
        DocumentFragment fragment = new DocumentFragment(uid);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_document, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(context);

        if(sessionManager.getUserType().equals("p")){
            floatingAdddoc.setVisibility(View.VISIBLE);
        }else {
            floatingAdddoc.setVisibility(View.GONE);
        }




        floatingAdddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context,DocumentAddAcitvityForPatient.class));
               //askPermission();
            }
        });
        initPhotoAddedListener();
        GeneralListener.setNeedRefresh(new GeneralListener.youNeedRefresh() {
            @Override
            public void doRefresh(int value) {
                download();

            }
        });
        download();
        return view;
    }

    private void download() {
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("id", targetuserID);
        Api.getInstance().getDocumentsForPatient(request, new ApiListener.PatientDpcumenttListDownloadListener() {
            @Override
            public void onPatientDpcumentListDownloadSuccess(List response) {
                //  Toast.makeText(context, "lab rep"+response.size(), Toast.LENGTH_SHORT).show();

                if (response.size() >0) {

                    createViewPagerFragments(response);

                    mPageAdapter = new ColorFragmentAdapter(getChildFragmentManager(), mViewPagerFragments);
                    mFlippableStack = (FlippableStackView) view.findViewById(R.id.flippable_stack_view);
                    mFlippableStack.initStack(response.size(),
                            StackPageTransformer.Orientation.VERTICAL,
                            0.95f,
                            0.7f,
                            0.6f,
                            StackPageTransformer.Gravity.CENTER);
                    mFlippableStack.setAdapter(mPageAdapter);
                }else {
                    Toast.makeText(context, "No Document", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPatientDpcumentListDownloadFailed(String msg) {

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
                .start((Activity)context);
    }
    private void initPhotoAddedListener() {
        sharedPhotoListener.setpListenerUri(new sharedPhotoListener.PhotoPickedListenerUri() {
            @Override
            public void onPicSelected(Uri data) {
                Dialog dialog = doForMe.showDialog(context, R.layout.upload_doc_dialog);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
                if (data != null) {
                    Glide.with(context).load(data).into(imageView);
                }
                TextView tv_upload = (TextView) dialog.findViewById(R.id.tv_upload);
                EditText ed_title = (EditText) dialog.findViewById(R.id.ed_title);
                TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
                tv_upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (data != null) {
                            String title = ed_title.getText().toString().trim();
                            MyProgressBar.with(context);
                            File f = new File(data.getPath());
                            MultipartBody.Part photo = null;
                            RequestBody requestFile =
                                    RequestBody.create(MediaType.parse("multipart/form-data"), f);
                            photo = MultipartBody.Part.createFormData("photo", f.getName(), requestFile);
                            /*
                            Api.getInstance().doctorDocumentUpload(TOKEN, c_m_b(USER_ID), c_m_b(title), photo, new ApiListener.DocDocUploadListener() {
                                @Override
                                public void onDocDocUploadSuccess(StatusMessage data) {
                                    MyProgressBar.dismiss();
                                    if (data.getStatus() == true) {
                                        dialog.dismiss();
                                        Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
                                        GeneralListener.needRefresh.doRefresh(0);


                                    }
                                }

                                @Override
                                public void onDocDocUploadFailed(String msg) {
                                    MyProgressBar.dismiss();
                                    Toast.makeText(context, "Add failed.Try again later", Toast.LENGTH_SHORT).show();


                                }
                            });

                             */

                        }
                    }
                });


            }
        });
    }
    private void createViewPagerFragments(List response) {
        mViewPagerFragments = new ArrayList<>();


        for (int i = 0; i < response.size(); i++) {
            Map<String, Object> data = (Map<String, Object>) response.get(i);
            mViewPagerFragments.add(PatientDocumentSingleFragment.newInstance(data));
        }
    }

    private class ColorFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ColorFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}