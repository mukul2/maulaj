package com.telemedicine.maulaji.api;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.telemedicine.maulaji.model.AllCollectionWithdraModel;
import com.telemedicine.maulaji.model.AmbulanceModel;
import com.telemedicine.maulaji.model.AppointmentAddResponse;
import com.telemedicine.maulaji.model.AppointmentModel;
import com.telemedicine.maulaji.model.AppointmentModel2;
import com.telemedicine.maulaji.model.AppointmentModelNew;
import com.telemedicine.maulaji.model.BasicByDrResponse;
import com.telemedicine.maulaji.model.BasicInfoModel;
import com.telemedicine.maulaji.model.BillItem;
import com.telemedicine.maulaji.model.BillSummery;
import com.telemedicine.maulaji.model.BlogCategoryNameID;
import com.telemedicine.maulaji.model.BlogModel;
import com.telemedicine.maulaji.model.CallHistoryPatient;
import com.telemedicine.maulaji.model.CityModel;
import com.telemedicine.maulaji.model.CountryModel;
import com.telemedicine.maulaji.model.Data;
import com.telemedicine.maulaji.model.DepartmentModel;
import com.telemedicine.maulaji.model.DepartmentModel2;
import com.telemedicine.maulaji.model.DeptModel;
import com.telemedicine.maulaji.model.DiseasesModel;
import com.telemedicine.maulaji.model.DoctorLoginModel;
import com.telemedicine.maulaji.model.DoctorModel;
import com.telemedicine.maulaji.model.DoctorModelRaw;
import com.telemedicine.maulaji.model.DocumentModel;
import com.telemedicine.maulaji.model.DrChamberResponse;
import com.telemedicine.maulaji.model.DrEduChInfoModel;
import com.telemedicine.maulaji.model.DrOnlineServiceModel;
import com.telemedicine.maulaji.model.DrServiceModel;
import com.telemedicine.maulaji.model.EducationSkillModel;
import com.telemedicine.maulaji.model.FetchProfileResponse;
import com.telemedicine.maulaji.model.HospitalModel;
import com.telemedicine.maulaji.model.MedHModel;
import com.telemedicine.maulaji.model.MedicalHistoryModel;
import com.telemedicine.maulaji.model.MedicineModel;
import com.telemedicine.maulaji.model.MedicineModel2;
import com.telemedicine.maulaji.model.MedicineModel3;
import com.telemedicine.maulaji.model.MedicineModel4;
import com.telemedicine.maulaji.model.MedicineVaritiModel;
import com.telemedicine.maulaji.model.NotiModel;
import com.telemedicine.maulaji.model.NoticeModel;
import com.telemedicine.maulaji.model.OnlineDoctorModel;
import com.telemedicine.maulaji.model.OnlineDoctorsModel;
import com.telemedicine.maulaji.model.PatientLoginModel;
import com.telemedicine.maulaji.model.PatientprofileOtpModel;
import com.telemedicine.maulaji.model.PaymentMethodsModel;
import com.telemedicine.maulaji.model.PrescriptionModel;
import com.telemedicine.maulaji.model.PrescriptionRequestModel;
import com.telemedicine.maulaji.model.PrescriptionReviewModel;
import com.telemedicine.maulaji.model.ProfileUpdateResponse;
import com.telemedicine.maulaji.model.QueryModel;
import com.telemedicine.maulaji.model.RecomentationModel;
import com.telemedicine.maulaji.model.SearchDoctorModel;
import com.telemedicine.maulaji.model.ServiceNameInfo;
import com.telemedicine.maulaji.model.SignUpResponse;
import com.telemedicine.maulaji.model.StatusId;
import com.telemedicine.maulaji.model.StatusMessage;
import com.telemedicine.maulaji.model.StatusResponse;
import com.telemedicine.maulaji.model.SubscriptionViewResponse;
import com.telemedicine.maulaji.model.SubscriptionsModel;
import com.telemedicine.maulaji.model.TestList;
import com.telemedicine.maulaji.model.TestModel;
import com.telemedicine.maulaji.model.TestRecomendationModel;
import com.telemedicine.maulaji.model.TrackModel;
import com.telemedicine.maulaji.model.TreatmentHistoryModel;
import com.telemedicine.maulaji.model.UserInfo;
import com.telemedicine.maulaji.model.UserProfileResponse;
import com.telemedicine.maulaji.model.VideoAppointmentModel;
import com.telemedicine.maulaji.model.VideoCallHistoryModel;
import com.telemedicine.maulaji.model.VideoCallModel;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.telemedicine.maulaji.Data.Data.SELECTEDED_SLOT_DATE;
import static com.telemedicine.maulaji.Data.Data.SELECTEDED_SLOT_TIME;

/**
 *
 */
public class Api {
    // String fcmKey = "key=AAAA2oWVj0U:APA91bGsqOhHgOWsDUvEMnVSMVeWBy0b0roNJp6WZ7F-jGjsZzSaJtM8_vw5qkcjnRRN9EkkloHk1krFbaiCqKF3phstTFDU_-qxSJMudUS138huXGhugAyO0NXvSJT0y4Ldf9eIQUjQ";
    // String fcmKey = "key=AAAA0EpRwPY:APA91bHBbBup11jcpJ65yZKqUqkUK5IPDUN9O51ade_qcoFKZdqyUuiK07v3mFSUmrA2ZAEP1M0zV09a794SZPOlmvbvDAOHN5cNdKNst0aCMq4WJIKbhDMWPK0ks-obO7rUd_vgTGIn";
    //String fcmKey = "key=AAAACS8SI2g:APA91bEu30-Q5X5SiFaAq7H4ZTlkbG-8L3piQfZsY-6DoAC9gR5jjejeIIaHa264PY_j_QSiaN3KyY1ck0TD4XNK-BmIT6UCWt_LT4pHkzs-lwfbyisnZo1abSUj4UO9UyDu3kaGUOvN";
    // String fcmKey = "key=AAAA0EpRwPY:APA91bHBbBup11jcpJ65yZKqUqkUK5IPDUN9O51ade_qcoFKZdqyUuiK07v3mFSUmrA2ZAEP1M0zV09a794SZPOlmvbvDAOHN5cNdKNst0aCMq4WJIKbhDMWPK0ks-obO7rUd_vgTGIn";
    String fcmKey = "key=AAAAlgUaefM:APA91bFiD5Cn5g5S6dvM0DVi7tqsyZ1gNxLKt-dUhAZmYv29_MOLCVBlvCf-2ATFf9eRtmto3qA-afaWXFctwufxc9K-hjHPG2YmZk2_yUtR_ssqI1PxP7qaWJLO8AFr3Dpx79avHQju";

    private static Api dataManager = null;
    public static String ERROR_MSG = "Network Error.Try again";

    public static Api getInstance() {

        if (dataManager == null) {
            dataManager = new Api();
        }

        return dataManager;
    }

    public void appNotification(String targetuser, String type, String room, String title, String body, String extraData) {
        String recepent = "/topics/" + targetuser;


        Data data = new Data(type, room, title, body, extraData);
        NotiModel notificationModel = new NotiModel(recepent, data);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/fcm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.newmsg(fcmKey, notificationModel).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.i("mklnoti", response.body().toString());
                Gson gson = new Gson();
                Log.i("mklnoti", gson.toJson(data).toString());
                Log.i("mklnoti", recepent);
                if (response.body() != null) {
                    //  listener.onNotificationSentSuccess(response.body());
                } else {
                    // listener.onNotificationSentFailed("Api Error "+response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.i("mkl", t.getLocalizedMessage());
                // listener.onNotificationSentFailed("Api Error "+t.getLocalizedMessage());

                // MyDialog.getInstance().with(findLawyerActivity).message("error msg "+t.getMessage()).show();

            }
        });


    }

    public void loginUser(HashMap request, final ApiListener.LoginUserListener loginUserListener) {

        ApiClient.getApiInterface().login(request).enqueue(new Callback<PatientLoginModel>() {
            @Override
            public void onResponse(@NonNull Call<PatientLoginModel> call, @NonNull Response<PatientLoginModel> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    loginUserListener.onUserLoginSuccess(response.body());
                } else {
                    loginUserListener.onUserLoginFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PatientLoginModel> call, @NonNull Throwable t) {
                loginUserListener.onUserLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    public void push_prescription(HashMap request, final ApiListener.PrescriptionPushListener listener) {

        ApiClientRawApi.getApiInterface().push_prescription(request).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onPrescriptionPushSuccess(response.body());
                } else {
                    listener.onPrescriptionPushFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPrescriptionPushFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getCityList(String country, final ApiListener.CountryDownloadListener loginUserListener) {

        ApiClient.getApiInterface().getCityList(country).enqueue(new Callback<List<CityModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CityModel>> call, @NonNull Response<List<CityModel>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    loginUserListener.onCountryDownloadSuccess(response.body());
                } else {
                    loginUserListener.onCountryDownloadFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CityModel>> call, @NonNull Throwable t) {
                loginUserListener.onCountryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void country_list(final ApiListener.CountryCountryDownloadListener listener) {

        ApiClient.getApiInterface().country_list().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryModel>> call, @NonNull Response<List<CountryModel>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onCountryCountryDownloadSuccess(response.body());
                } else {
                    listener.onCountryCountryDownloadFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryModel>> call, @NonNull Throwable t) {
                listener.onCountryCountryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void hospital_list(final ApiListener.HospitalDownloadListener listener) {

        ApiClient.getApiInterface().hospital_list().enqueue(new Callback<List<HospitalModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<HospitalModel>> call, @NonNull Response<List<HospitalModel>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onHospitalDownloadSuccess(response.body());
                } else {
                    listener.onHospitalDownloadFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<HospitalModel>> call, @NonNull Throwable t) {
                listener.onHospitalDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void find_patient_id(HashMap request, final ApiListener.patientAccountFindListener listener) {

        ApiClientRawApi.getApiInterface().find_patient_id(request).enqueue(new Callback<PatientprofileOtpModel>() {
            @Override
            public void onResponse(@NonNull Call<PatientprofileOtpModel> call, @NonNull Response<PatientprofileOtpModel> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onpatientAccountFindSuccess(response.body());
                } else {
                    listener.onpatientAccountFindFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PatientprofileOtpModel> call, @NonNull Throwable t) {
                listener.onpatientAccountFindFailed(t.getLocalizedMessage());
            }
        });
    }

    public void loginDoctor(HashMap request, final ApiListener.DocLoginListener loginUserListener) {

        ApiClient.getApiInterface().loginDoctor(request).enqueue(new Callback<DoctorLoginModel>() {
            @Override
            public void onResponse(@NonNull Call<DoctorLoginModel> call, @NonNull Response<DoctorLoginModel> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    loginUserListener.onDocLoginSuccess(response.body());
                } else {
                    loginUserListener.onDocLoginFailed("Try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorLoginModel> call, @NonNull Throwable t) {
                loginUserListener.onDocLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    public void appAppInfo(HashMap request, final ApiListener.AppointmentInsertListener listener) {

        ApiClient.getApiInterface().appAppInfo(request).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                if (response != null) {
                    listener.onAppointmentInsertSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                listener.onAppointmentInsertFailed(t.getLocalizedMessage());
            }
        });
    }

    public void all_medicines(String key, final ApiListener.MedDownloadListener listener) {

        ApiClientRawApi.getApiInterface().all_medicines(key).enqueue(new Callback<List<MedicineModel4>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineModel4>> call, @NonNull Response<List<MedicineModel4>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedDownloadSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineModel4>> call, @NonNull Throwable t) {
                listener.onMedDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void search_medicine(String key, final ApiListener.MedDownloadListener listener) {

        ApiClientRawApi.getApiInterface().search_medicine(key).enqueue(new Callback<List<MedicineModel4>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineModel4>> call, @NonNull Response<List<MedicineModel4>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedDownloadSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineModel4>> call, @NonNull Throwable t) {
                listener.onMedDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void all_medicinesbyBrand(String key, final ApiListener.MedDownloadListener listener) {

        ApiClientRawApi.getApiInterface().all_medicinesbyBrand(key).enqueue(new Callback<List<MedicineModel4>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineModel4>> call, @NonNull Response<List<MedicineModel4>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedDownloadSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineModel4>> call, @NonNull Throwable t) {
                listener.onMedDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void all_medicinesCategory(final ApiListener.MedCatDownloadListener listener) {

        ApiClientRawApi.getApiInterface().all_medicinesCategory().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedCatDownloadSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                listener.onMedCatDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getBrands(final ApiListener.MedBrandDownloadListener listener) {

        ApiClientRawApi.getApiInterface().getBrands().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedBrandDownloadSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                listener.onMedBrandDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void scheduled_appointment_request_list(HashMap request, final ApiListener.ScheduledListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().scheduled_appointment_request_list(request).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onScheduledListDownloadSuccess(response.body());
                } else {
                    listener.onScheduledListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onScheduledListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_medicine_varities(String request, final ApiListener.MedVaritiListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().get_medicine_varities(request).enqueue(new Callback<List<MedicineVaritiModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineVaritiModel>> call, @NonNull Response<List<MedicineVaritiModel>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedVaritiListListDownloadSuccess(response.body());
                } else {
                    listener.onMedVaritiListListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineVaritiModel>> call, @NonNull Throwable t) {
                listener.onMedVaritiListListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_lab_reports(HashMap request, final ApiListener.LabReportListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().get_lab_reports(request).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onLabReportListDownloadSuccess(response.body());
                } else {
                    listener.onLabReportListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onLabReportListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDocumentsForPatient(HashMap request, final ApiListener.PatientDpcumenttListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().getDocumentsForPatient(request).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onPatientDpcumentListDownloadSuccess(response.body());
                } else {
                    listener.onPatientDpcumentListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onPatientDpcumentListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_prescriptions(HashMap request, final ApiListener.PrescriptionsListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().get_prescriptions(request).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onPrescriptionsListDownloadSuccess(response.body());
                } else {
                    listener.onPrescriptionsListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onPrescriptionsListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void urgent_appointment_list(HashMap request, final ApiListener.UrgentListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().urgent_appointment_list(request).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onUrgentListDownloadSuccess(response.body());
                } else {
                    listener.onUrgentListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onUrgentListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void home_visit_appointment_request_list(HashMap request, final ApiListener.HomeVisitListDownloadListener listener) {

        ApiClientRawApi.getApiInterface().home_visit_appointment_request_list(request).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onHomeVisitDownloadSuccess(response.body());
                } else {
                    listener.onHomeVisitDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onHomeVisitDownloadFailed(t.getLocalizedMessage());
            }
        });
    }


    public void urgent_care_request(HashMap request, final ApiListener.AppointmentInsertListener listener) {

        ApiClientHeroku.getApiInterface().urgent_care_request(request).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onAppointmentInsertSuccess(response.body());
                } else {
                    listener.onAppointmentInsertFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                listener.onAppointmentInsertFailed(t.getLocalizedMessage());
            }
        });
    }

    public void insert_home_care_req_raw(HashMap request, final ApiListener.AppointmentInsertListener listener) {

        ApiClientRawApi.getApiInterface().insert_home_care_req_raw(request).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onAppointmentInsertSuccess(response.body());
                } else {
                    listener.onAppointmentInsertFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                listener.onAppointmentInsertFailed(t.getLocalizedMessage());
            }
        });
    }

    public void urgent_care_request_raw(HashMap request, final ApiListener.AppointmentInsertListener listener) {

        ApiClientRawApi.getApiInterface().urgent_care_request_raw(request).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onAppointmentInsertSuccess(response.body());
                } else {
                    listener.onAppointmentInsertFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                listener.onAppointmentInsertFailed(t.getLocalizedMessage());
            }
        });
    }

    public void all_gp(final ApiListener.AllGPDownloadListener listener) {

        ApiClientHeroku.getApiInterface().all_gp().enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response != null && response.body() != null) {
                    listener.onAllGPDownloadSuccess(response.body());
                } else {
                    listener.onAllGPDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onAllGPDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void all_gp_raw(String hospital, final ApiListener.AllGPDownloadListener listener) {

        ApiClientRawApi.getApiInterface().all_gp_raw(hospital).enqueue(new Callback<List<DoctorModelRaw>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Response<List<DoctorModelRaw>> response) {
                if (response != null && response.body() != null) {
                    listener.onAllGPDownloadSuccess(response.body());
                } else {
                    listener.onAllGPDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Throwable t) {
                listener.onAllGPDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void urgent_doctors_list(String hospital, final ApiListener.RawDocDownloadListener listener) {

        ApiClientRawApi.getApiInterface().urgent_doctors_list(hospital).enqueue(new Callback<List<DoctorModelRaw>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Response<List<DoctorModelRaw>> response) {
                if (response != null && response.body() != null) {
                    listener.onAllDocDownloadSuccess(response.body());
                } else {
                    listener.onAllDocDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Throwable t) {
                listener.onAllDocDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_home_visit_doctors(String hospital, final ApiListener.RawDocDownloadListener listener) {

        ApiClientRawApi.getApiInterface().get_home_visit_doctors(hospital).enqueue(new Callback<List<DoctorModelRaw>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Response<List<DoctorModelRaw>> response) {
                if (response != null && response.body() != null) {
                    listener.onAllDocDownloadSuccess(response.body());
                } else {
                    listener.onAllDocDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Throwable t) {
                listener.onAllDocDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void specialist_doctor(String id, final ApiListener.AllGPDownloadListener listener) {

        ApiClientHeroku.getApiInterface().specialist_doctor(id).enqueue(new Callback<List<DoctorModelRaw>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Response<List<DoctorModelRaw>> response) {
                if (response != null && response.body() != null) {
                    listener.onAllGPDownloadSuccess(response.body());
                } else {
                    listener.onAllGPDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Throwable t) {
                listener.onAllGPDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void specialist_doctor_raw(String id, String hospitalId, final ApiListener.AllGPDownloadListener listener) {

        ApiClientRawApi.getApiInterface().specialist_doctor_raw(id, hospitalId).enqueue(new Callback<List<DoctorModelRaw>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Response<List<DoctorModelRaw>> response) {
                if (response != null && response.body() != null) {
                    listener.onAllGPDownloadSuccess(response.body());
                } else {
                    listener.onAllGPDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorModelRaw>> call, @NonNull Throwable t) {
                listener.onAllGPDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void free_slots_doctors_call_gp(String day, String date, String doctorID, final ApiListener.AllSloatloadListener listener) {

        ApiClient.getApiInterface().free_slots_doctors_call_gp(day, date, doctorID).enqueue(new Callback<List>() {
            @Override
            public void onResponse(@NonNull Call<List> call, @NonNull Response<List> response) {
                if (response != null && response.body() != null) {
                    listener.onAllSloatDownloadSuccess(response.body());
                } else {
                    listener.onAllSloatDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List> call, @NonNull Throwable t) {
                listener.onAllSloatDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void department_list(final ApiListener.DeptListDownload listener) {

        ApiClient.getApiInterface().department_list().enqueue(new Callback<List<DepartmentModel2>>() {
            @Override
            public void onResponse(@NonNull Call<List<DepartmentModel2>> call, @NonNull Response<List<DepartmentModel2>> response) {
                if (response != null && response.body() != null) {
                    listener.onDeptListDownloadSuccess(response.body());
                } else {
                    listener.onDeptListDownloadFailed("" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DepartmentModel2>> call, @NonNull Throwable t) {
                listener.onDeptListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postEducationInfo(String KEY, String dr_id, String title, String body, final ApiListener.PostEducationInfoListener listener) {

        ApiClient.getApiInterface().postEducationInfo(KEY, dr_id, title, body).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPostEducationInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPostEducationInfoFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_payment_methods_list(final ApiListener.PaymentMethodsDownloadListener listener) {

        ApiClient.getApiInterface().get_payment_methods_list().enqueue(new Callback<List<PaymentMethodsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PaymentMethodsModel>> call, @NonNull Response<List<PaymentMethodsModel>> response) {
                if (response != null) {
                    listener.onPaymentMethodsDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PaymentMethodsModel>> call, @NonNull Throwable t) {
                listener.onPaymentMethodsDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMedicinesList(String KEY, final ApiListener.DownloadMedicinesListInfoListener listener) {

        ApiClient.getApiInterface().getMedicine(KEY).enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineModel>> call, @NonNull Response<List<MedicineModel>> response) {
                if (response != null) {
                    listener.onDownloadMedicinesListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineModel>> call, @NonNull Throwable t) {
                listener.onDownloadMedicinesListFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAllTest(String KEY, final ApiListener.DownloadTestListInfoListener listener) {

        ApiClient.getApiInterface().getAlltestList(KEY).enqueue(new Callback<List<TestModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TestModel>> call, @NonNull Response<List<TestModel>> response) {
                if (response != null) {
                    listener.onDownloadTestListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TestModel>> call, @NonNull Throwable t) {
                listener.onDownloadTestListFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getOnLineDoctors(String KEY, String depID, final ApiListener.DownloadOnlineDocListener listener) {

        ApiClient.getApiInterface().searchOnlineDoctors(KEY, depID).enqueue(new Callback<List<OnlineDoctorsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<OnlineDoctorsModel>> call, @NonNull Response<List<OnlineDoctorsModel>> response) {
                if (response != null) {
                    listener.onOnlineDocSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<OnlineDoctorsModel>> call, @NonNull Throwable t) {
                listener.onOnlineDocSearchFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchOnlineDoctorsName(String KEY, String name, final ApiListener.DownloadOnlineDocListener listener) {

        ApiClient.getApiInterface().searchOnlineDoctorsName(KEY, name).enqueue(new Callback<List<OnlineDoctorsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<OnlineDoctorsModel>> call, @NonNull Response<List<OnlineDoctorsModel>> response) {
                if (response != null) {
                    listener.onOnlineDocSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<OnlineDoctorsModel>> call, @NonNull Throwable t) {
                listener.onOnlineDocSearchFailed(t.getLocalizedMessage());
            }
        });
    }

    public void blogsDownload(String KEY, String s, final ApiListener.BlogDownloadListener listener) {

        ApiClient.getApiInterface().getAllBlog(KEY, s).enqueue(new Callback<List<BlogModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<BlogModel>> call, @NonNull Response<List<BlogModel>> response) {
                if (response != null) {
                    listener.onBlogDownloaSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<BlogModel>> call, @NonNull Throwable t) {
                listener.onBlogDownloaSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void doctorDocumentUpload(String KEY, RequestBody id, RequestBody title, MultipartBody.Part photo, final ApiListener.DocDocUploadListener listener) {

        ApiClient.getApiInterface().doctorDocumentUpload(KEY, id, title, photo).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onDocDocUploadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onDocDocUploadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addPrescriptionPhoto(String KEY, RequestBody id, RequestBody title, MultipartBody.Part photo, final ApiListener.prescriptionUploadListener listener) {

        ApiClient.getApiInterface().addPrescriptionPhoto(KEY, id, title, photo).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPrescriptionUploadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPrescriptionUploadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void DiseasesDownload(String KEY, String user_ID, final ApiListener.DiseasesDownloadListener listener) {

        ApiClient.getApiInterface().getDiseasesRecord(KEY, user_ID).enqueue(new Callback<List<DiseasesModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DiseasesModel>> call, @NonNull Response<List<DiseasesModel>> response) {
                if (response != null) {
                    listener.onDiseasesDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DiseasesModel>> call, @NonNull Throwable t) {
                listener.onDiseasesDownloadSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addDiseases(String KEY, String user_ID, String disesaeName, String firstNotice, String status, final ApiListener.diseasesAddListener listener) {

        ApiClient.getApiInterface().addDiseases(KEY, user_ID, disesaeName, firstNotice, status).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onDiseasesAddSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onDiseasesAddSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDepList(String KEY, final ApiListener.DeptDownloadListener listener) {

        ApiClient.getApiInterface().getDepartments(KEY).enqueue(new Callback<List<DeptModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeptModel>> call, @NonNull Response<List<DeptModel>> response) {
                if (response != null) {
                    listener.onDepartmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DeptModel>> call, @NonNull Throwable t) {
                listener.onDepartmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchDoctors(String KEY, String name, String id, final ApiListener.DocSearchListener listener) {

        ApiClient.getApiInterface().searchDoctors(KEY, name, id).enqueue(new Callback<List<SearchDoctorModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SearchDoctorModel>> call, @NonNull Response<List<SearchDoctorModel>> response) {
                if (response != null) {
                    listener.onDocSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<SearchDoctorModel>> call, @NonNull Throwable t) {
                listener.onDocSearchFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addTestRec(String KEY, String appointmentID, String testList, final ApiListener.addTestRecListener listener) {

        ApiClient.getApiInterface().addTestRec(KEY, appointmentID, testList).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onAddTestRecSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onAddTestRecFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAllDocumentOFSingleDoc(String KEY, String id, final ApiListener.DoctorDocDownloadListener listener) {

        ApiClient.getApiInterface().getAllDocumentsBySingleDoctor(KEY, id).enqueue(new Callback<List<DocumentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DocumentModel>> call, @NonNull Response<List<DocumentModel>> response) {
                if (response != null) {
                    listener.onDoctorDocDownloadSuccess(response.body());


                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DocumentModel>> call, @NonNull Throwable t) {
                listener.onDoctorDocDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void checkMobileNumber(String phone, final ApiListener.NumberUniqueCheckListener listener) {

        ApiClient.getApiInterface().checkMobileNumber(phone).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onNumberUniqueCheckSuccess(response.body());


                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onNumberUniqueCheckFailed(t.getLocalizedMessage());
            }
        });
    }

    public void patientSignUp(RequestBody name, RequestBody department, RequestBody user_type, RequestBody password, RequestBody email, RequestBody phone, RequestBody designaiton, MultipartBody.Part photo, final ApiListener.PatientSignUPListener listener) {

        ApiClient.getApiInterface().signUpPatient(name, department, user_type, password, email, phone, designaiton, photo).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                if (response != null) {
                    listener.onPatientSignUPSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                listener.onPatientSignUPSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addDocumentBypatient(RequestBody id, RequestBody hospital_id, RequestBody title, MultipartBody.Part photo, final ApiListener.PatientDocUPListener listener) {

        ApiClientRawApi.getApiInterface().addDocumentBypatient(id, hospital_id, title, photo).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPatienttDocUPSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPatienttDocUPSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void updateProfile(String token, RequestBody id, RequestBody name, RequestBody designation_title, MultipartBody.Part photo, final ApiListener.PRofileUpdateListenerPatient listener) {

        ApiClient.getApiInterface().updatePRofile(token, id, name, designation_title, photo).enqueue(new Callback<ProfileUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileUpdateResponse> call, @NonNull Response<ProfileUpdateResponse> response) {
                if (response != null) {
                    listener.onPRofileUpdateSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<ProfileUpdateResponse> call, @NonNull Throwable t) {
                listener.onPRofileUpdateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postSkillInfo(String KEY, String dr_id, String body, final ApiListener.PostSkillInfoListener listener) {

        ApiClient.getApiInterface().postSkillInfo(KEY, dr_id, body).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPostSkillInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPostSkillInfoFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodRecomendedLits(String id, final ApiListener.TestDownloadListener listener) {

        ApiClient.getApiInterface().getTestList(id).enqueue(new Callback<List<TestList>>() {
            @Override
            public void onResponse(@NonNull Call<List<TestList>> call, @NonNull Response<List<TestList>> response) {
                if (response != null) {
                    listener.onTestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TestList>> call, @NonNull Throwable t) {
                listener.onTestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void doctorEduSkillDownload(String id, final ApiListener.doctorEduSkillDownloadListener listener) {

        ApiClient.getApiInterface().getMyEducationSkill(id).enqueue(new Callback<EducationSkillModel>() {
            @Override
            public void onResponse(@NonNull Call<EducationSkillModel> call, @NonNull Response<EducationSkillModel> response) {
                if (response != null) {
                    listener.ondoctorEduSkillDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<EducationSkillModel> call, @NonNull Throwable t) {
                listener.ondoctorEduSkillDownloadSuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodOnlineDoctorsLits(final ApiListener.OnlineDoctorsDownloadListener listener) {

        ApiClient.getApiInterface().getOnlineServiceDoctors().enqueue(new Callback<List<OnlineDoctorModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<OnlineDoctorModel>> call, @NonNull Response<List<OnlineDoctorModel>> response) {
                if (response != null) {
                    listener.onOnlineDoctorsDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<OnlineDoctorModel>> call, @NonNull Throwable t) {
                listener.onOnlineDoctorsDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodDrPending(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().dr_pending(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodDepartmentsList(final ApiListener.departmentsDownloadListener listener) {

        ApiClient.getApiInterface().getAllDepartments().enqueue(new Callback<List<DepartmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DepartmentModel>> call, @NonNull Response<List<DepartmentModel>> response) {
                if (response != null) {
                    listener.onDepartmentsListDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DepartmentModel>> call, @NonNull Throwable t) {
                listener.onDepartmentsListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodDrConfirmed(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().dr_confirmed(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void apiuniquephonenumber(String phone, final ApiListener.PhoneUniqueCheckListener listener) {

        ApiClientCodIgntrApi.getApiInterface().apiuniquephonenumber(phone).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response != null) {
                    listener.onPhoneUniqueCheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                listener.onPhoneUniqueCheckFailed(t.getLocalizedMessage());
            }
        });
    }

    public void patient_register(String name, String phone, String email, String password, String phonecode, String support_input, final ApiListener.SignupListener listener) {

        ApiClientCodIgntrApi.getApiInterface().patient_register(name, phone, email, password, phonecode, support_input).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response != null) {
                    listener.onSignupSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                listener.onSignupFailed(t.getLocalizedMessage());
            }
        });
    }

    public void apiuniqueemail(String phone, final ApiListener.PhoneUniqueCheckListener listener) {

        ApiClientCodIgntrApi.getApiInterface().apiuniqueemail(phone).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response != null) {
                    listener.onPhoneUniqueCheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                listener.onPhoneUniqueCheckFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodPaConfirmed(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().getPatientAllConfirmed(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_video_call_available_time(String token, String id, final ApiListener.AvailableInfoDownloadListener listener) {

        ApiClient.getApiInterface().get_video_call_available_time(token, id).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                if (response != null) {
                    listener.onAvailableInfoDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                listener.onAvailableInfoDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodPaPending(String id, final ApiListener.CommonappointmentDownloadListener listener) {

        ApiClient.getApiInterface().getPatientAllPending(id).enqueue(new Callback<List<AppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel>> call, @NonNull Response<List<AppointmentModel>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downlaodPaRecomendation(String id, final ApiListener.DrRecomentationDownloadListener listener) {

        ApiClient.getApiInterface().getpatientRecomentation(id).enqueue(new Callback<List<RecomentationModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<RecomentationModel>> call, @NonNull Response<List<RecomentationModel>> response) {
                if (response != null) {
                    listener.onRecomendationDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<RecomentationModel>> call, @NonNull Throwable t) {
                listener.onRecomendationFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadCallLog(String patient_id, final ApiListener.patientCallLogListener listener) {

        ApiClient.getApiInterface().getCallListBypatient(patient_id).enqueue(new Callback<List<CallHistoryPatient>>() {
            @Override
            public void onResponse(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Response<List<CallHistoryPatient>> response) {
                if (response != null) {
                    listener.onPatientCallLogSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Throwable t) {
                listener.onPatientCallLogFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadCallLogDoctor(String doctor_id, final ApiListener.doctorCallLogListener listener) {

        ApiClient.getApiInterface().getCallListByDoctor(doctor_id).enqueue(new Callback<List<CallHistoryPatient>>() {
            @Override
            public void onResponse(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Response<List<CallHistoryPatient>> response) {
                if (response != null) {
                    listener.onDoctorCallLogSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<CallHistoryPatient>> call, @NonNull Throwable t) {
                listener.onDoctorCallLogFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyMedicalistory(String id, final ApiListener.MedicalHistoryDownloadListener listener) {

        ApiClientRawApi.getApiInterface().getMyMedicalistory(id).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.onMedicalHistoryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                listener.onMedicalHistoryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadOnlineDoctors(final ApiListener.onlineDoctorListener listener) {

        ApiClient.getApiInterface().getOnlineDoctors().enqueue(new Callback<List<VideoCallModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<VideoCallModel>> call, @NonNull Response<List<VideoCallModel>> response) {
                if (response != null) {
                    listener.onOnlineDoctorSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<VideoCallModel>> call, @NonNull Throwable t) {
                listener.onOnlineDoctorSearchFailed(t.getLocalizedMessage());
            }
        });
    }

    public void symptoms_list_get(final ApiListener.SymptomsDownloadListener listener) {

        ApiClientRawApi.getApiInterface().symptoms_list_get().enqueue(new Callback<List<MedHModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedHModel>> call, @NonNull Response<List<MedHModel>> response) {
                if (response != null) {
                    listener.onSymptomsDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<MedHModel>> call, @NonNull Throwable t) {
                listener.onSymptomsDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void changeDrOnlineStatus(String drID, String isOnline, final ApiListener.doctorOnlineStatusChangeListener listener) {

        ApiClient.getApiInterface().changeOnlineStatus(drID, isOnline).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.ondoctorOnlineStatusChangeSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.ondoctorOnlineStatusChangeFailed(t.getLocalizedMessage());
            }
        });
    }

    public void CallLogPostStatus(String patient_id, String dr_id, String call_time, String duration, final ApiListener.PushCallLogListener listener) {

        ApiClient.getApiInterface().pushCallResponse(patient_id, dr_id, call_time, duration).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPushCallLogSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPushCallLogFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchDoctor(String dr_name, String hospital_name, String specialist, String city, String day, final ApiListener.doctorSearchListener doctorSearchListener) {
        ApiClient.getApiInterface().searchChamber(dr_name, hospital_name, specialist, city, day).enqueue(new Callback<List<DoctorModel>>() {
            @Override
            public void onResponse(Call<List<DoctorModel>> call, Response<List<DoctorModel>> response) {
                doctorSearchListener.onSearchSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<DoctorModel>> call, Throwable t) {
                doctorSearchListener.onSuccessFailed(t.getLocalizedMessage());

            }
        });

    }

    public void updatePassword(String phone,
                               String newPassword,
                               final ApiListener.updatePassword listener) {

        ApiClient.getApiInterface().updatePassword(phone, newPassword).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null && response.body() != null) {
                    listener.onUpdatePasswordSuccess(response.body());

                } else {
                    listener.onUpdatePasswordFailed(ERROR_MSG);
                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onUpdatePasswordFailed(t.getLocalizedMessage());
            }
        });

    }

    public void fetchPeofile(String dr_name, final ApiListener.profileFetchListener listener) {
        ApiClient.getApiInterface().fetchPeofile(dr_name).enqueue(new Callback<FetchProfileResponse>() {
            @Override
            public void onResponse(Call<FetchProfileResponse> call, Response<FetchProfileResponse> response) {
                listener.onprofileFetchSuccess(response.body());

            }

            @Override
            public void onFailure(Call<FetchProfileResponse> call, Throwable t) {
                listener.onprofileFetchFailed(t.getLocalizedMessage());

            }
        });

    }

    public void get_user_info(String id, final ApiListener.profileFetchListener listener) {
        ApiClient.getApiInterface().get_user_info(id).enqueue(new Callback<FetchProfileResponse>() {
            @Override
            public void onResponse(Call<FetchProfileResponse> call, Response<FetchProfileResponse> response) {
                listener.onprofileFetchSuccess(response.body());

            }

            @Override
            public void onFailure(Call<FetchProfileResponse> call, Throwable t) {
                listener.onprofileFetchFailed(t.getLocalizedMessage());

            }
        });

    }

    public void update_video_call_available_time(String token, String id, String time, final ApiListener.basicApiListener listener) {
        ApiClient.getApiInterface().update_video_call_available_time(token, id, time).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onBasicSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onBasicApiFailed(t.getLocalizedMessage());

            }
        });

    }

    public void BlogCategoryNameID(String token, final ApiListener.BlogCategoryDownloadListener doctorSearchListener) {
        ApiClient.getApiInterface().getBlogChamber(token).enqueue(new Callback<List<BlogCategoryNameID>>() {
            @Override
            public void onResponse(Call<List<BlogCategoryNameID>> call, Response<List<BlogCategoryNameID>> response) {
                doctorSearchListener.onBlogCategoryDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<BlogCategoryNameID>> call, Throwable t) {
                doctorSearchListener.onBlogCategoryDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void entryGeneralInfoDoctor(String dr_name, String email, String mobile, String password, String type, String last_degree, String currentHospital, final ApiListener.drBasicInfoPostListener listener) {
        ApiClient.getApiInterface().drGeneralEntry(dr_name, email, mobile, password, type, last_degree, currentHospital).enqueue(new Callback<StatusId>() {
            @Override
            public void onResponse(Call<StatusId> call, Response<StatusId> response) {
                listener.onBasicInfoPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusId> call, Throwable t) {
                listener.onBasicInfoPostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void setDrSchedule(String token, String id, String chamberName, String address, String visit_fee, String followUpfees, String days, final ApiListener.drSchedulePostListener listener) {
        ApiClient.getApiInterface().setDrSchedule(token, id, chamberName, address, visit_fee, followUpfees, days).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.ondrSchedulePostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.ondrSchedulePostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void downloadBasicInfo(final ApiListener.basicInfoDownloadListener listener) {
        ApiClient.getApiInterface().getBasicInfo().enqueue(new Callback<BasicInfoModel>() {
            @Override
            public void onResponse(Call<BasicInfoModel> call, Response<BasicInfoModel> response) {
                listener.onBasicInfoDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<BasicInfoModel> call, Throwable t) {
                listener.onBasicInfoDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void addAppointmentInfo(String token, String p_id, String dr_id, String problems, String phone, String name, String chamber_id, String date, String status, final ApiListener.AppointmentPOstListener listener) {
        ApiClient.getApiInterface().addAppointmentInfo(token, p_id, dr_id, problems, phone, name, chamber_id, date, status).enqueue(new Callback<AppointmentAddResponse>() {
            @Override
            public void onResponse(Call<AppointmentAddResponse> call, Response<AppointmentAddResponse> response) {
                listener.onAppointmentPOStSuccess(response.body());

            }

            @Override
            public void onFailure(Call<AppointmentAddResponse> call, Throwable t) {
                listener.onAppointmentPOStFailed(t.getLocalizedMessage());

            }
        });

    }

    public void get_vdo_appointment_slot(String token, String dr_id, String patient_id, String date, String day, final ApiListener.SlotSearchListener listener) {
        ApiClient.getApiInterface().get_vdo_appointment_slot(token, dr_id, patient_id, date, day).enqueue(new Callback<List<StatusMessage>>() {
            @Override
            public void onResponse(Call<List<StatusMessage>> call, Response<List<StatusMessage>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSlotSearchSuccess(response.body());
                } else {
                    listener.onSlotSearchFailed("API ERROR");

                }

            }

            @Override
            public void onFailure(Call<List<StatusMessage>> call, Throwable t) {
                listener.onSlotSearchFailed(t.getLocalizedMessage());

            }
        });

    }

    private RequestBody c_m_b(String aThis) {
        return
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), aThis);
    }

    public void addVideoAppointmentInfo(String token, RequestBody p_id, RequestBody dr_id, RequestBody payment_details, RequestBody payment_status, RequestBody amount, MultipartBody.Part image, final ApiListener.AppointmentPOstListener listener) {
        ApiClient.getApiInterface().addVideoAppointmentInfo(token, p_id, dr_id, payment_details, payment_status, amount, image, c_m_b(SELECTEDED_SLOT_DATE), c_m_b(SELECTEDED_SLOT_TIME)).enqueue(new Callback<AppointmentAddResponse>() {
            @Override
            public void onResponse(Call<AppointmentAddResponse> call, Response<AppointmentAddResponse> response) {
                listener.onAppointmentPOStSuccess(response.body());

            }

            @Override
            public void onFailure(Call<AppointmentAddResponse> call, Throwable t) {
                listener.onAppointmentPOStFailed(t.getLocalizedMessage());

            }
        });

    }

    public void prescription_order_phaarmacey(RequestBody id, RequestBody pic, RequestBody shippinaddress,RequestBody post,RequestBody road,RequestBody name,RequestBody phone, MultipartBody.Part image, final ApiListener.PrescriptionOrderPostListener listener) {
        ApiClientRawApi.getApiInterface().prescription_order_phaarmacey(id, pic, shippinaddress,road,post, name,phone,image).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onPrescriptionOrderPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onPrescriptionOrderPosttFailed(t.getLocalizedMessage());

            }
        });

    }

    public void non_prescription_order(String id, String pic, String shippinaddress,String post,String road,String name,String phone,String cart, final ApiListener.PrescriptionOrderPostListener listener) {
        ApiClientRawApi.getApiInterface().non_prescription_order(id, pic, shippinaddress,road,post, name,phone,cart).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onPrescriptionOrderPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onPrescriptionOrderPosttFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getMyChambersList(String id, final ApiListener.chamberListDownloadListener listener) {
        ApiClient.getApiInterface().getMyChambers(id).enqueue(new Callback<List<DrChamberResponse>>() {
            @Override
            public void onResponse(Call<List<DrChamberResponse>> call, Response<List<DrChamberResponse>> response) {
                listener.onChamberListDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<DrChamberResponse>> call, Throwable t) {
                listener.onChamberListDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getEduSKillChamber(String key, String id, final ApiListener.drChamberEduSkillDownloadListener listener) {
        ApiClient.getApiInterface().getSkillChamberEdu(key, id).enqueue(new Callback<DrEduChInfoModel>() {
            @Override
            public void onResponse(Call<DrEduChInfoModel> call, Response<DrEduChInfoModel> response) {
                listener.onChamberEduSkillDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<DrEduChInfoModel> call, Throwable t) {
                listener.onChamberEduSkillDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void checkMobile(String mobile, final ApiListener.CheckMobileListener checkMobileListener) {

        ApiClient.getApiInterface().checkMobile(mobile).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    checkMobileListener.onMobileCheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                checkMobileListener.onMobileCheckFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postAppointment(String patient_id, String chamber_id, String dr_id, String appointment_for, String phone, String problems, String date, final ApiListener.appoinetmentPOstListener listener) {

        ApiClient.getApiInterface().postAppointment(patient_id, chamber_id, dr_id, appointment_for, phone, problems, date).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onAppointmentPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onAppointmentPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postRecommendationTest(String appointment_id, String test_id, final ApiListener.recomendationTestPostListener listener) {

        ApiClient.getApiInterface().postRecommenTest(test_id, appointment_id).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onrecomendationTestPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onrecomendationTestPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postReview(String token, String p_id, String d_id, String old_presID, String comment, String payment_details, String payment_status, String amount, String paypal_id, final ApiListener.prescriptionPostListener listener) {

        ApiClient.getApiInterface().addRecheckReques(token, p_id, d_id, old_presID, comment, payment_details, payment_status, amount, paypal_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPrescriptionPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPrescriptionPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void add_payment_info_only(String token, RequestBody p_id, RequestBody d_id, RequestBody amount, RequestBody status, RequestBody reason, RequestBody transID, MultipartBody.Part attachbody, final ApiListener.basicApiListener listener) {

        ApiClient.getApiInterface().add_payment_info_only(token, p_id, d_id, amount, status, reason, transID, attachbody).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBasicSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBasicApiFailed(t.getLocalizedMessage());
            }
        });
    }

    public void add_payment_info_only_for_chamber_appoiontment(String token, RequestBody p_id, RequestBody d_id, RequestBody amount, RequestBody status, RequestBody reason, RequestBody transID, RequestBody table_id, MultipartBody.Part attachbody, final ApiListener.basicApiListener listener) {

        ApiClient.getApiInterface().add_payment_info_only_for_chamber_appoiontment(token, p_id, d_id, amount, status, reason, transID, table_id, attachbody).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBasicSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBasicApiFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_payment_list(String token, String id, String user_type, final ApiListener.PaymentListDownloadListener listener) {
        ApiClient.getApiInterface().get_payment_list(token, id, user_type).enqueue(new Callback<AllCollectionWithdraModel>() {
            @Override
            public void onResponse(@NonNull Call<AllCollectionWithdraModel> call, @NonNull Response<AllCollectionWithdraModel> response) {
                if (response != null) {
                    listener.onPaymentListDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AllCollectionWithdraModel> call, @NonNull Throwable t) {
                listener.onPaymentListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void add_withdrawal_request(String token, String dr_id, String amount, String bnk, final ApiListener.basicApiListener listener) {
        ApiClient.getApiInterface().add_withdrawal_request(token, dr_id, amount, bnk).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBasicSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBasicApiFailed(t.getLocalizedMessage());
            }
        });
    }

    public void add_subscription_info(String token, RequestBody p_id, RequestBody d_id, RequestBody payment_details, RequestBody number_of_months, RequestBody starts, RequestBody ends, RequestBody status, RequestBody amount, MultipartBody.Part attachbody, final ApiListener.basicApiListener listener) {

        ApiClient.getApiInterface().add_subscription_info(token, p_id, d_id, payment_details, number_of_months, starts, ends, status, amount, attachbody).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBasicSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBasicApiFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addChatRequest(String token, String p_id, String d_id, String payment_details, String status, String amount, final ApiListener.AppointmentPOstListener listener) {

        ApiClient.getApiInterface().addChatReques(token, p_id, d_id, payment_details, status, amount).enqueue(new Callback<AppointmentAddResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentAddResponse> call, @NonNull Response<AppointmentAddResponse> response) {
                if (response != null) {
                    listener.onAppointmentPOStSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AppointmentAddResponse> call, @NonNull Throwable t) {
                listener.onAppointmentPOStFailed(t.getLocalizedMessage());
            }
        });
    }

    public void check_subscriptions(String token, String p_id, String d_id, final ApiListener.SubscriptionViewListener listener) {

        ApiClient.getApiInterface().check_subscriptions(token, p_id, d_id).enqueue(new Callback<SubscriptionViewResponse>() {
            @Override
            public void onResponse(@NonNull Call<SubscriptionViewResponse> call, @NonNull Response<SubscriptionViewResponse> response) {
                if (response != null) {
                    listener.onSubscriptionViewSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<SubscriptionViewResponse> call, @NonNull Throwable t) {
                listener.onSubscriptionViewFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_subscription_list(String token, String user_type, String uid, final ApiListener.SubscriptionListDownlaodListener listener) {

        ApiClient.getApiInterface().get_subscription_list(token, user_type, uid).enqueue(new Callback<List<SubscriptionsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SubscriptionsModel>> call, @NonNull Response<List<SubscriptionsModel>> response) {
                if (response != null) {
                    listener.onSubscriptionListDownlaodSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<SubscriptionsModel>> call, @NonNull Throwable t) {
                listener.onSubscriptionListDownlaodFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_video_appointment_list(String token, String user_type, String uid, String date, final ApiListener.VideoCallReqListDownlaodListener listener) {

        ApiClient.getApiInterface().get_video_appointment_list(token, user_type, uid, date).enqueue(new Callback<List<VideoAppointmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<VideoAppointmentModel>> call, @NonNull Response<List<VideoAppointmentModel>> response) {
                if (response != null) {
                    listener.onVideoCallReqListDownlaodSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<VideoAppointmentModel>> call, @NonNull Throwable t) {
                listener.onVideoCallReqListDownlaodFailed(t.getLocalizedMessage());
            }
        });
    }

    public void drServiceAdd(String token, String dr_id, String service_id, String fees_per_unit, final ApiListener.DrAddServiceListener listener) {

        ApiClient.getApiInterface().add_Dr_service(token, dr_id, service_id, fees_per_unit).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onDrAddServiceSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onDrAddServiceFailed(t.getLocalizedMessage());
            }
        });
    }

    public void drServiceDelete(String token, String dr_id, String service_id, final ApiListener.DrdeleteServiceListener listener) {

        ApiClient.getApiInterface().delete_Dr_service(token, dr_id, service_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onDrDeleteServiceSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onDrDeleteServiceFailed(t.getLocalizedMessage());
            }
        });
    }

    public void change_video_appointment_status_done(String token, String appointment_id, final ApiListener.basicApiListener listener) {

        ApiClient.getApiInterface().change_video_appointment_status_done(token, appointment_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBasicSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBasicApiFailed(t.getLocalizedMessage());
            }
        });
    }

    public void drServiceFeesUpdate(String token, String dr_id, String service_id, String fees, final ApiListener.updateDrServiceListener listener) {

        ApiClient.getApiInterface().update_service_fees(token, dr_id, service_id, fees).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onDrUpdateServiceSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onDrUpdateServiceFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postCallRecord(String token, String p_id, String d_id, String call_time, String duration, String caller_id, String service_id, String dr_name, final ApiListener.VideoCallPostListener listener) {

        ApiClient.getApiInterface().addVideoCallSummery(token, p_id, d_id, call_time, duration, caller_id, service_id, dr_name).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onVideoCallPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onVideoCallPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyNotices(String token, String id, final ApiListener.NoticesDownloadListener listener) {

        ApiClient.getApiInterface().getMyNotices(token, id).enqueue(new Callback<List<NoticeModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<NoticeModel>> call, @NonNull Response<List<NoticeModel>> response) {
                if (response != null) {
                    listener.onNoticesDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<NoticeModel>> call, @NonNull Throwable t) {
                listener.onNoticesDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void update_notification_status(String token, String id, final ApiListener.NoticesStatusUpdateListener listener) {

        ApiClient.getApiInterface().update_notification_status(token, id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onNoticesStatusUpdateSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onNoticesStatusUpdateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyReviews(String token, String id, String type, final ApiListener.ReviewRequestDownloadListener listener) {

        ApiClient.getApiInterface().getMyRecheckRequests(token, id, type).enqueue(new Callback<List<PrescriptionReviewModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionReviewModel>> call, @NonNull Response<List<PrescriptionReviewModel>> response) {
                if (response != null) {
                    listener.onReviewRequestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionReviewModel>> call, @NonNull Throwable t) {
                listener.onReviewRequestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void singlePrescriptionDownload(String token, String id, final ApiListener.singlePrescriptionDownloadListener listener) {

        ApiClient.getApiInterface().getSinglePrescription(token, id).enqueue(new Callback<PrescriptionModel>() {
            @Override
            public void onResponse(@NonNull Call<PrescriptionModel> call, @NonNull Response<PrescriptionModel> response) {
                if (response != null) {
                    listener.onPrescriptionDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<PrescriptionModel> call, @NonNull Throwable t) {
                listener.onPrescriptionDownloaFailed(t.getLocalizedMessage());
            }
        });
    }

    public void blogPost(String token, String id, String body, final ApiListener.BlogPostListener listener) {

        ApiClient.getApiInterface().postBlog(token, id, body).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBlogPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBlogPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void blogPostWithPhoto(String token, RequestBody dr_id, RequestBody body, RequestBody blog_category, RequestBody title, RequestBody youtube_video, MultipartBody.Part attachbody, final ApiListener.BlogPostListener listener) {

        ApiClient.getApiInterface().postBlogWithPhoto(token, dr_id, body, blog_category, title, youtube_video, attachbody).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBlogPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBlogPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void track(String token, String track_id, String doctor_id, final ApiListener.TrackListener listener) {

        ApiClient.getApiInterface().TrackAppointment(token, track_id, doctor_id).enqueue(new Callback<List<TrackModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TrackModel>> call, @NonNull Response<List<TrackModel>> response) {
                if (response != null) {
                    listener.onTrackSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TrackModel>> call, @NonNull Throwable t) {
                listener.onTrackFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addPrescriptionRequest(String token, String patient_id, String dr_id, String problem, String payment_status, String payment_details, String amount, String paypalID, final ApiListener.PrescriptionRequestListener listener) {

        ApiClient.getApiInterface().addPrescriptionRequest(token, patient_id, dr_id, problem, payment_status, payment_details, amount, paypalID).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPrescriptionRequestSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPrescriptionRequestFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchAppointment(String id, String dr_id, String appointment_for, final ApiListener.appointmentSearchListener listener) {

        ApiClient.getApiInterface().searchAppointemntByDoctor(id, dr_id, appointment_for).enqueue(new Callback<List<AppointmentModel2>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModel2>> call, @NonNull Response<List<AppointmentModel2>> response) {
                if (response != null) {
                    listener.onAppointmentSearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModel2>> call, @NonNull Throwable t) {
                listener.onAppointmentSearchFailed(t.getLocalizedMessage());
            }
        });
    }


    public void getAppointmentsByDoctor(String dr_id, final ApiListener.appoinetmentsDownloadListener listener) {


    }

    public void getAppointments(String token, String type, String userID, String status, final ApiListener.appoinetmentsDownloadListener listener) {

        ApiClient.getApiInterface().getAppointmentsList(token, type, userID, status).enqueue(new Callback<List<AppointmentModelNew>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModelNew>> call, @NonNull Response<List<AppointmentModelNew>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModelNew>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyTestRecomendations(String token, String userID, final ApiListener.TestRecomDownloadListener listener) {

        ApiClient.getApiInterface().getMyTestRecomendation(token, userID).enqueue(new Callback<List<TestRecomendationModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TestRecomendationModel>> call, @NonNull Response<List<TestRecomendationModel>> response) {
                if (response != null) {
                    listener.onTestRecomDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TestRecomendationModel>> call, @NonNull Throwable t) {
                listener.onTestRecomDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyPrescriptionRequest(String token, String userID, String userType, final ApiListener.MyPrescriptionRequestDownloadListener listener) {

        ApiClient.getApiInterface().getmyPrescriptionRequest(token, userID, userType).enqueue(new Callback<List<PrescriptionRequestModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionRequestModel>> call, @NonNull Response<List<PrescriptionRequestModel>> response) {
                if (response != null) {
                    listener.onMyPrescriptionRequestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionRequestModel>> call, @NonNull Throwable t) {
                listener.onMyPrescriptionRequestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getPresCriptionsByPatient(String token, String pa_id, String userType, final ApiListener.PresCriptionDownloadListenerPatient listener) {

        ApiClient.getApiInterface().getMyPrescriptionsPatient(token, pa_id, userType).enqueue(new Callback<List<PrescriptionModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionModel>> call, @NonNull Response<List<PrescriptionModel>> response) {
                if (response != null) {
                    listener.onPrescriptionDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionModel>> call, @NonNull Throwable t) {
                listener.onPrescriptionDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAmbulanceList(String KEY, final ApiListener.DownloadAmbulanceListInfoListener listener) {

        ApiClient.getApiInterface().getAmbulanceList(KEY).enqueue(new Callback<List<AmbulanceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AmbulanceModel>> call, @NonNull Response<List<AmbulanceModel>> response) {
                if (response != null) {
                    listener.onDownloadAmbulanceListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AmbulanceModel>> call, @NonNull Throwable t) {
                listener.onDownloadAmbulanceListFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getVideoCallSummery(String token, String id, String userType, final ApiListener.VideoCallHistoryDownloadListenerPatient listener) {

        ApiClient.getApiInterface().getVideoCallSummery(token, id, userType).enqueue(new Callback<List<VideoCallHistoryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<VideoCallHistoryModel>> call, @NonNull Response<List<VideoCallHistoryModel>> response) {
                if (response != null) {
                    listener.onVideoCallHistoryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<VideoCallHistoryModel>> call, @NonNull Throwable t) {
                listener.onVideoCallHistoryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void yearlySingleUserBillList(String token, String user_id, String year, final ApiListener.UserBillDownloadListener listener) {

        ApiClient.getApiInterface().yearlySingleUserBillList(token, user_id, year).enqueue(new Callback<List<BillItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<BillItem>> call, @NonNull Response<List<BillItem>> response) {
                if (response != null) {
                    listener.onUserBillDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<BillItem>> call, @NonNull Throwable t) {
                listener.onUserBillDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void publicQueryPOst(String token, String message_body, String message_sender_id, String message_receiver_id, final ApiListener.publicQueryPostListenerPatient listener) {

        ApiClient.getApiInterface().addPublicQuery(token, message_body, message_sender_id, message_receiver_id, "0").enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPublicQueryPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPublicQueryPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyAllQuery(String token, String message_sender_id, String message_receiver_id, final ApiListener.publicQueryDownloadListenerPatient listener) {

        ApiClient.getApiInterface().getMyQueries(token, message_sender_id, message_receiver_id).enqueue(new Callback<List<QueryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<QueryModel>> call, @NonNull Response<List<QueryModel>> response) {
                if (response != null) {
                    listener.onPublicQueryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<QueryModel>> call, @NonNull Throwable t) {
                listener.onPublicQueryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getADoctorsServices(String token, String id, final ApiListener.DrServicesDownloadListener listener) {

        ApiClient.getApiInterface().getAllServiceByDr(token, id).enqueue(new Callback<List<DrOnlineServiceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DrOnlineServiceModel>> call, @NonNull Response<List<DrOnlineServiceModel>> response) {
                if (response != null) {
                    listener.onDrServicesDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DrOnlineServiceModel>> call, @NonNull Throwable t) {
                listener.onDrServicesDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void yearlySingleUserBillSummery(String token, String user_id, String year, final ApiListener.UserBillSummeryDownloadListener listener) {

        ApiClient.getApiInterface().yearlySingleUserBillSummery(token, user_id, year).enqueue(new Callback<BillSummery>() {
            @Override
            public void onResponse(@NonNull Call<BillSummery> call, @NonNull Response<BillSummery> response) {
                if (response != null) {
                    listener.onUserBillSummeryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<BillSummery> call, @NonNull Throwable t) {
                listener.onUserBillSummeryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAllServices(String token, final ApiListener.AllServiceDownloadListener listener) {

        ApiClient.getApiInterface().getAllOnlineServices(token).enqueue(new Callback<List<ServiceNameInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<ServiceNameInfo>> call, @NonNull Response<List<ServiceNameInfo>> response) {
                if (response != null) {
                    listener.onAllServiceDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<ServiceNameInfo>> call, @NonNull Throwable t) {
                listener.onAllServiceDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addPrecription(String token, String d_id, String pa_id, String medicins, String diseases, String appointmentID, String service_id, String dr_name, final ApiListener.PrescriptionAddListener listener) {

        ApiClient.getApiInterface().addPrescription(token, d_id, pa_id, medicins, diseases, appointmentID, service_id, dr_name).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onrescriptionAddSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onrescriptionAddFailed(t.getLocalizedMessage());
            }
        });
    }

    public void replyRecheck(String token, String dr_id, String patient_id, String medicine_info, String diseases_name, String recheck_id, String dr_comment, String need_of_prescription, String dr_name, String service_id, final ApiListener.PrescriptionAddListener listener) {

        ApiClient.getApiInterface().replyPrescriptionRecheck(token, dr_id, patient_id, medicine_info, diseases_name, recheck_id, dr_comment, need_of_prescription, dr_name, service_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onrescriptionAddSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onrescriptionAddFailed(t.getLocalizedMessage());
            }
        });
    }

    public void replyPrescriptionRequest(String token, String d_id, String pa_id, String medicins, String diseases, String reqID, String service_id, String dr_name, final ApiListener.ReplyPrescriptionRequestListener listener) {

        ApiClient.getApiInterface().replyPrescriptionRequest(token, d_id, pa_id, medicins, diseases, reqID, service_id, dr_name).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onReplyPrescriptionRequestSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onReplyPrescriptionRequestFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAppointmentsBypatient(String id, final ApiListener.appoinetmentsDownloadListener listener) {


    }

    public void changeStatus(String token, String id, String status, final ApiListener.appointmentStateChangeListener listener) {

        ApiClient.getApiInterface().changeAppointmentStatus(token, id, status).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onAppointmentChangeSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPppointmentChangeFailed(t.getLocalizedMessage());
            }
        });
    }

    public void patientTreatmentHistory(String patientid, final ApiListener.patientTreatmentHistoryListener listener) {

        ApiClient.getApiInterface().treatmentHistoryByPatient(patientid).enqueue(new Callback<List<TreatmentHistoryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TreatmentHistoryModel>> call, @NonNull Response<List<TreatmentHistoryModel>> response) {
                if (response != null) {
                    listener.onpatientTreatmentHistorySearchSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<TreatmentHistoryModel>> call, @NonNull Throwable t) {
                listener.onpatientTreatmentHistorySuccessFailed(t.getLocalizedMessage());
            }
        });
    }

    public void servePost(String appointment_id,
                          String dr_id,
                          String p_id,
                          String dr_name,
                          String p_name,
                          String comment,
                          String fees,
                          String chamber_id,
                          final ApiListener.servePostListener listener) {

        ApiClient.getApiInterface().postServeInfo(appointment_id, dr_id, p_id, dr_name, p_name, comment, fees, chamber_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onServePostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onServePostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getThisPfofile(String id, final ApiListener.profileDownloadListener listener) {

        ApiClient.getApiInterface().getThisProfile(id).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserProfileResponse> call, @NonNull Response<UserProfileResponse> response) {
                if (response != null) {
                    listener.onprofileDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<UserProfileResponse> call, @NonNull Throwable t) {
                listener.onprofileDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadDrServiceList(String id, final ApiListener.DrServiceDownloadListener listener) {

        ApiClient.getApiInterface().get_my_services_by_dr(id).enqueue(new Callback<List<DrServiceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DrServiceModel>> call, @NonNull Response<List<DrServiceModel>> response) {
                if (response != null) {
                    listener.onDrServiceDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DrServiceModel>> call, @NonNull Throwable t) {
                listener.onDrServiceDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postDrServices(String dr_id, String service_detail, final ApiListener.drServicePostListener listener) {

        ApiClient.getApiInterface().post_dr_service_list(dr_id, service_detail).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.ondrServicePostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.ondrServicePostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void updateDrInfo(String id, String hospital, String lastDegree, String name, final ApiListener.drprofileUpdateListener listener) {

        ApiClient.getApiInterface().updateDrBasicInfo(id, hospital, lastDegree, name).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.ondrprofileUpdateSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.ondrprofileUpdateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void setMedicalHistory(String id, String data, final ApiListener.medicalHistoryUpdateListener listener) {

        ApiClientRawApi.getApiInterface().setMedicalHistory(id, data).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.code() == 200 && response != null) {
                    listener.OnmedicalHistoryUpdateFailed(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                listener.OnmedicalHistoryUpdateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadTestNames(final ApiListener.testNamesDownloadListener listener) {
        ApiClient.getApiInterface().getTestNames().enqueue(new Callback<BasicByDrResponse>() {
            @Override
            public void onResponse(Call<BasicByDrResponse> call, Response<BasicByDrResponse> response) {
                listener.ontestNamesDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<BasicByDrResponse> call, Throwable t) {
                listener.ontestNamesDownloadFailed(t.getLocalizedMessage());

            }
        });

    }
}
