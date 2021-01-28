package com.telemedicine.maulaji.api;


import com.telemedicine.maulaji.model.AllCollectionWithdraModel;
import com.telemedicine.maulaji.model.AmbulanceModel;
import com.telemedicine.maulaji.model.AppointmentAddResponse;
import com.telemedicine.maulaji.model.AppointmentModel;
import com.telemedicine.maulaji.model.AppointmentModel2;
import com.telemedicine.maulaji.model.AppointmentModelNew;
import com.telemedicine.maulaji.model.AppointmentResponse;
import com.telemedicine.maulaji.model.BasicByDrResponse;
import com.telemedicine.maulaji.model.BasicInfoModel;
import com.telemedicine.maulaji.model.BillItem;
import com.telemedicine.maulaji.model.BillSummery;
import com.telemedicine.maulaji.model.BlogCategoryNameID;
import com.telemedicine.maulaji.model.BlogModel;
import com.telemedicine.maulaji.model.CallHistoryPatient;
import com.telemedicine.maulaji.model.CityModel;
import com.telemedicine.maulaji.model.CountryModel;
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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {


    @FormUrlEncoded
    @POST("yearlySingleUserBillList")
    Call<List<BillItem>> yearlySingleUserBillList(@Header("Authorization") String s, @Field("user_id") String user_id, @Field("year") String year);

    @GET("get_payment_methods_list")
    Call<List<PaymentMethodsModel>> get_payment_methods_list();

    @FormUrlEncoded
    @POST("yearlySingleUserBillSummery")
    Call<BillSummery> yearlySingleUserBillSummery(@Header("Authorization") String s, @Field("user_id") String user_id, @Field("year") String year);

    @FormUrlEncoded
    @POST("searchDoctor.php")
    Call<List<DoctorModel>> searchChamber(@Field("dr_name") String dr_name,
                                          @Field("hospital_name") String hospital_name,
                                          @Field("specialist") String specialist,
                                          @Field("city") String city,
                                          @Field("day") String day);

    @FormUrlEncoded
    @POST("changePasswood")
    Call<StatusMessage> updatePassword(@Field("phone") String phone,
                                       @Field("newPassword") String newPassword);

    @POST("send")
    Call<JsonElement> newmsg(@Header("Authorization") String s, @Body NotiModel model);

    @FormUrlEncoded
    @POST("fetchuserByEmailorPhone")
    Call<FetchProfileResponse> fetchPeofile(@Field("key") String key);

    @FormUrlEncoded
    @POST("get_user_info")
    Call<FetchProfileResponse> get_user_info(@Field("id") String key);

    @FormUrlEncoded
    @POST("update_video_call_available_time")
    Call<StatusMessage> update_video_call_available_time(@Header("Authorization") String token, @Field("id") String key, @Field("time") String time);

    @POST("allBlogCategory")
    Call<List<BlogCategoryNameID>> getBlogChamber(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST("patient_all_confirmed.php")
    Call<List<AppointmentModel>> getPatientAllConfirmed(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("get_video_call_available_time")
    Call<JsonElement> get_video_call_available_time(@Header("Authorization") String token, @Field("id") String id);

    @FormUrlEncoded
    @POST("all-blog-info")
    Call<List<BlogModel>> getAllBlog(@Header("Authorization") String token, @Field("blog_category") String s);

    @FormUrlEncoded
    @POST("patient-disease-record")
    Call<List<DiseasesModel>> getDiseasesRecord(@Header("Authorization") String token, @Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("add-disease-record")
    Call<StatusMessage> addDiseases(@Header("Authorization") String token,
                                    @Field("patient_id") String patient_id,
                                    @Field("disease_name") String disease_name,
                                    @Field("first_notice_date") String first_notice_date,
                                    @Field("status") String status);

    @FormUrlEncoded
    @POST("search-online-doctors")
    Call<List<OnlineDoctorsModel>> searchOnlineDoctors(@Header("Authorization") String token,
                                                       @Field("department_id") String department_id);

    @FormUrlEncoded
    @POST("search_online_doctors_name")
    Call<List<OnlineDoctorsModel>> searchOnlineDoctorsName(@Header("Authorization") String token,
                                                           @Field("name") String name);

    @FormUrlEncoded
    @POST("delete_service_by_dr.php")
    Call<StatusMessage> delete_service_by_dr(@Field("id") String id);

    @FormUrlEncoded
    @POST("get_dr_services.php")
    Call<List<DrServiceModel>> get_my_services_by_dr(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("post_dr_service_list.php")
    Call<StatusMessage> post_dr_service_list(@Field("dr_id") String dr_id, @Field("service_list_string") String service_list_string);

    @FormUrlEncoded
    @POST("patient_all_pending.php")
    Call<List<AppointmentModel>> getPatientAllPending(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("patient_recomendation_list.php")
    Call<List<RecomentationModel>> getpatientRecomentation(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("post_serve.php")
    Call<StatusMessage> postServeInfo(@Field("appointment_id") String appointment_id,
                                      @Field("dr_id") String dr_id,
                                      @Field("p_id") String p_id,
                                      @Field("dr_name") String dr_name,
                                      @Field("p_name") String p_name,
                                      @Field("comment") String comment,
                                      @Field("fees") String fees,
                                      @Field("chamber_id") String chamber_id);

    @GET("getOnlineDoctors.php")
    Call<List<VideoCallModel>> getOnlineDoctors();

    @GET("symptoms_list_get.php")
    Call<List<MedHModel>> symptoms_list_get();

    @GET("get_all_departments.php")
    Call<List<DepartmentModel>> getAllDepartments();

    @FormUrlEncoded
    @POST("updateProfileDr.php")
    Call<StatusResponse> updateDrBasicInfo(@Field("id") String id,
                                           @Field("hospital_name") String hospital_name,
                                           @Field("last_education_degree") String last_education_degree,
                                           @Field("dr_name") String dr_name);

    @FormUrlEncoded
    @POST("setMedicalHistory.php")
    Call<String> setMedicalHistory(@Field("id") String id,
                                   @Field("data") String data);

    @FormUrlEncoded
    @POST("getPatientTreatmentHistory.php")
    Call<List<TreatmentHistoryModel>> treatmentHistoryByPatient(@Field("patient_id") String patient_id);


    @FormUrlEncoded
    @POST("changeOnlineStatus.php")
    Call<StatusMessage> changeOnlineStatus(@Field("id") String id,
                                           @Field("isOnLine") String isOnLine);

    @FormUrlEncoded
    @POST("push_call_history.php")
    Call<StatusMessage> pushCallResponse(@Field("patient_id") String patient_id,
                                         @Field("dr_id") String dr_id,
                                         @Field("call_time") String call_time,
                                         @Field("duration") String duration);

    @FormUrlEncoded
    @POST("searchMyAppointmentDoctor.php")
    Call<List<AppointmentModel2>> searchAppointemntByDoctor(@Field("id") String id,
                                                            @Field("dr_id") String dr_id,
                                                            @Field("appointment_for") String appointment_for);

    @FormUrlEncoded
    @POST("getMyCallLog.php")
    Call<List<CallHistoryPatient>> getCallListBypatient(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("getMyCallLogDoctor.php")
    Call<List<CallHistoryPatient>> getCallListByDoctor(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("getMyMedicalistory.php")
    Call<List<String>> getMyMedicalistory(@Field("id") String id);

    @FormUrlEncoded
    @POST("getRecomendationList.php")
    Call<List<TestList>> getTestList(@Field("appointment_id") String appointment_id);

    @FormUrlEncoded
    @POST("getThisProfile.php")
    Call<UserProfileResponse> getThisProfile(@Field("id") String id);

    @FormUrlEncoded
    @POST("getMyAppointmentsDoctor.php")
    Call<AppointmentResponse> myAppointmentsbyDoctor(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("dr_all_pending.php")
    Call<List<AppointmentModel>> dr_pending(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("dr_all_confirmed.php")
    Call<List<AppointmentModel>> dr_confirmed(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("getMyAppointments.php")
    Call<AppointmentResponse> myAppointmentsbyPatient(@Field("patient_id") String patient_id);


    @FormUrlEncoded
    @POST("postRecommenTest.php")
    Call<StatusResponse> postRecommenTest(@Field("test_id") String test_id, @Field("appointment_id") String appointment_id);

    @GET("getBasicInfo.php")
    Call<BasicInfoModel> getBasicInfo();

    @GET("get_online_doctors_new.php")
    Call<List<OnlineDoctorModel>> getOnlineServiceDoctors();

    @GET("getAllTestNames.php")
    Call<BasicByDrResponse> getTestNames();


    @FormUrlEncoded
    @POST("getMyChambers.php")
    Call<List<DrChamberResponse>> getMyChambers(@Field("id") String id);

    @FormUrlEncoded
    @POST("doctor-education-chamber-info")
    Call<DrEduChInfoModel> getSkillChamberEdu(@Header("Authorization") String token,
                                              @Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("add-appointment-info")
    Call<AppointmentAddResponse> addAppointmentInfo(@Header("Authorization") String token,
                                                    @Field("patient_id") String patient_id,
                                                    @Field("dr_id") String dr_id,
                                                    @Field("problems") String problems,
                                                    @Field("phone") String phone,
                                                    @Field("name") String name,
                                                    @Field("chamber_id") String chamber_id,
                                                    @Field("date") String date,
                                                    @Field("status") String status);

    @FormUrlEncoded
    @POST("get_vdo_appointment_slot")
    Call<List<StatusMessage>> get_vdo_appointment_slot(@Header("Authorization") String token,
                                                       @Field("dr_id") String dr_id,
                                                       @Field("patient_id") String patient_id,
                                                       @Field("date") String date,
                                                       @Field("day") String day);

    @Multipart
    @POST("add_video_appointment_info")
    Call<AppointmentAddResponse> addVideoAppointmentInfo(@Header("Authorization") String token,
                                                         @Part("patient_id") RequestBody patient_id,
                                                         @Part("doctor_id") RequestBody doctor_id,
                                                         @Part("payment_details") RequestBody payment_details,
                                                         @Part("payment_status") RequestBody payment_status,
                                                         @Part("amount") RequestBody amount,
                                                         @Part MultipartBody.Part image, @Part("date") RequestBody date, @Part("time") RequestBody time);

    @FormUrlEncoded
    @POST("get-appointment-list")
    Call<List<AppointmentModelNew>> getAppointmentsList(@Header("Authorization") String token,
                                                        @Field("user_type") String user_type,
                                                        @Field("id") String id,
                                                        @Field("status") String status);

    @FormUrlEncoded
    @POST("test-recommendation-list")
    Call<List<TestRecomendationModel>> getMyTestRecomendation(@Header("Authorization") String token,
                                                              @Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("view-prescription-request")
    Call<List<PrescriptionRequestModel>> getmyPrescriptionRequest(@Header("Authorization") String token,
                                                                  @Field("id") String id,
                                                                  @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("get_dr_personal_info.php")
    Call<EducationSkillModel> getMyEducationSkill(@Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("get-prescription-info")
    Call<List<PrescriptionModel>> getMyPrescriptionsPatient(@Header("Authorization") String token,
                                                            @Field("id") String id,
                                                            @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("add-public-query")
    Call<StatusMessage> addPublicQuery(@Header("Authorization") String token,
                                       @Field("message_body") String message_body,
                                       @Field("message_sender_id") String message_sender_id,
                                       @Field("message_receiver_id") String message_receiver_id,
                                       @Field("status") String status);

    @FormUrlEncoded
    @POST("view-public-query-by-id")
    Call<List<QueryModel>> getMyQueries(@Header("Authorization") String token,
                                        @Field("message_sender_id") String message_sender_id,
                                        @Field("message_receiver_id") String message_receiver_id);

    @FormUrlEncoded
    @POST("view-video-call-summary")
    Call<List<VideoCallHistoryModel>> getVideoCallSummery(@Header("Authorization") String token,
                                                          @Field("id") String id,
                                                          @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("view-online-doctor-service")
    Call<List<DrOnlineServiceModel>> getAllServiceByDr(@Header("Authorization") String token,
                                                       @Field("doctor_id") String doctor_id);

    @POST("all-online-service")
    Call<List<ServiceNameInfo>> getAllOnlineServices(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("add_chat_appointment_info")
    Call<AppointmentAddResponse> addChatReques(@Header("Authorization") String token,
                                               @Field("patient_id") String patient_id,
                                               @Field("dr_id") String dr_id,
                                               @Field("payment_details") String payment_details,
                                               @Field("status") String status,
                                               @Field("amount") String amount);

    @FormUrlEncoded
    @POST("check_subscriptions")
    Call<SubscriptionViewResponse> check_subscriptions(@Header("Authorization") String token,
                                                       @Field("patient_id") String patient_id,
                                                       @Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("get_subscription_list")
    Call<List<SubscriptionsModel>> get_subscription_list(@Header("Authorization") String token,
                                                         @Field("user_type") String user_type,
                                                         @Field("uid") String uid);

    @FormUrlEncoded
    @POST("get_video_appointment_list")
    Call<List<VideoAppointmentModel>> get_video_appointment_list(@Header("Authorization") String token,
                                                                 @Field("user_type") String user_type,
                                                                 @Field("id") String uid,
                                                                 @Field("date") String date);

    @FormUrlEncoded
    @POST("add-prescription-recheck-request")
    Call<StatusMessage> addRecheckReques(@Header("Authorization") String token,
                                         @Field("patient_id") String patient_id,
                                         @Field("dr_id") String dr_id,
                                         @Field("old_prescription_id") String old_prescription_id,
                                         @Field("patient_comment") String patient_comment,
                                         @Field("payment_details") String payment_details,
                                         @Field("payment_status") String payment_status,
                                         @Field("amount") String amount,
                                         @Field("paypal_id") String paypal_id);

    @Multipart
    @POST("add_payment_info_only")
    Call<StatusMessage> add_payment_info_only(@Header("Authorization") String token,
                                              @Part("patient_id") RequestBody patient_id,
                                              @Part("dr_id") RequestBody dr_id,
                                              @Part("amount") RequestBody amount,
                                              @Part("status") RequestBody status,
                                              @Part("reason") RequestBody reason,
                                              @Part("transID") RequestBody transID,
                                              @Part MultipartBody.Part image);

    @Multipart
    @POST("body_only_for_chamber_appoiontment")
    Call<StatusMessage> add_payment_info_only_for_chamber_appoiontment(@Header("Authorization") String token,
                                                                       @Part("patient_id") RequestBody patient_id,
                                                                       @Part("dr_id") RequestBody dr_id,
                                                                       @Part("amount") RequestBody amount,
                                                                       @Part("status") RequestBody status,
                                                                       @Part("reason") RequestBody reason,
                                                                       @Part("transID") RequestBody transID,
                                                                       @Part("table_id") RequestBody table_id,
                                                                       @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("get_payment_list")
    Call<AllCollectionWithdraModel> get_payment_list(@Header("Authorization") String token,
                                                     @Field("id") String patient_id,
                                                     @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("add_withdrawal_request")
    Call<StatusMessage> add_withdrawal_request(@Header("Authorization") String token,
                                               @Field("dr_id") String dr_id,
                                               @Field("amount") String amount,
                                               @Field("bankinfo") String bankinfo);

    @Multipart
    @POST("add_subscription_info")
    Call<StatusMessage> add_subscription_info(@Header("Authorization") String token,
                                              @Part("patient_id") RequestBody patient_id,
                                              @Part("dr_id") RequestBody dr_id,
                                              @Part("payment_details") RequestBody payment_details,
                                              @Part("number_of_months") RequestBody number_of_months,
                                              @Part("starts") RequestBody starts,
                                              @Part("ends") RequestBody ends,
                                              @Part("status") RequestBody status,
                                              @Part("amount") RequestBody amount,
                                              @Part MultipartBody.Part image);


    @GET("view-ambulance")
    Call<List<AmbulanceModel>> getAmbulanceList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("add-online-doctor-service")
    Call<StatusMessage> add_Dr_service(@Header("Authorization") String token,
                                       @Field("doctor_id") String doctor_id,
                                       @Field("online_service_id") String online_service_id,
                                       @Field("fees_per_unit") String fees_per_unit);

    @FormUrlEncoded
    @POST("change_video_appointment_status_done")
    Call<StatusMessage> change_video_appointment_status_done(@Header("Authorization") String token,
                                                             @Field("appointment_id") String appointment_id);

    @FormUrlEncoded
    @POST("delete-online-doctor-service")
    Call<StatusMessage> delete_Dr_service(@Header("Authorization") String token,
                                          @Field("doctor_id") String doctor_id,
                                          @Field("online_service_id") String online_service_id);

    @FormUrlEncoded
    @POST("update-online-doctor-service-fees")
    Call<StatusMessage> update_service_fees(@Header("Authorization") String token,
                                            @Field("doctor_id") String doctor_id,
                                            @Field("online_service_id") String online_service_id,
                                            @Field("fees") String fees);

    @FormUrlEncoded
    @POST("add-video-call-summary")
    Call<StatusMessage> addVideoCallSummery(@Header("Authorization") String token,
                                            @Field("patient_id") String patient_id,
                                            @Field("dr_id") String dr_id,
                                            @Field("call_time") String call_time,
                                            @Field("duration") String duration,
                                            @Field("caller_id") String caller_id,
                                            @Field("service_id") String service_id,
                                            @Field("dr_name") String dr_name);

    @FormUrlEncoded
    @POST("getMyNotices")
    Call<List<NoticeModel>> getMyNotices(@Header("Authorization") String token,
                                         @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("update_notification_status")
    Call<StatusMessage> update_notification_status(@Header("Authorization") String token,
                                                   @Field("id") String user_id);

    @FormUrlEncoded
    @POST("get-my-recheck-requests")
    Call<List<PrescriptionReviewModel>> getMyRecheckRequests(@Header("Authorization") String token,
                                                             @Field("id") String id,
                                                             @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("get_single_prescription_info")
    Call<PrescriptionModel> getSinglePrescription(@Header("Authorization") String token,
                                                  @Field("id") String id);

    @FormUrlEncoded
    @POST("get_single_prescription_info")
    Call<StatusMessage> postBlog(@Header("Authorization") String token,
                                 @Field("dr_id") String dr_id,
                                 @Field("body") String body);


    @FormUrlEncoded
    @POST("add-prescription-request")
    Call<StatusMessage> addPrescriptionRequest(@Header("Authorization") String token,
                                               @Field("patient_id") String patient_id,
                                               @Field("dr_id") String dr_id,
                                               @Field("problem") String problem,
                                               @Field("payment_status") String payment_status,
                                               @Field("payment_details") String payment_details,
                                               @Field("amount") String amount,
                                               @Field("paypal_id") String paypal_id);

    @Multipart
    @POST("update-user-info")
    Call<ProfileUpdateResponse> updatePRofile(@Header("Authorization") String token,
                                              @Part("user_id") RequestBody user_id,
                                              @Part("name") RequestBody name,
                                              @Part("designation_title") RequestBody designation_title,
                                              @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("view-patient-appointment-details-via-track-id")
    Call<List<TrackModel>> TrackAppointment(@Header("Authorization") String token,
                                            @Field("track_id") String track_id,
                                            @Field("doctor_id") String doctor_id);

    @FormUrlEncoded
    @POST("add-prescription-info")
    Call<StatusMessage> addPrescription(@Header("Authorization") String token,
                                        @Field("dr_id") String dr_id,
                                        @Field("patient_id") String patient_id,
                                        @Field("medicine_info") String medicine_info,
                                        @Field("diseases_name") String diseases_name,
                                        @Field("appointment_id") String appointment_id,
                                        @Field("service_id") String service_id,
                                        @Field("dr_name") String dr_name);


    @FormUrlEncoded
    @POST("reply-prescription-recheck-request")
    Call<StatusMessage> replyPrescriptionRecheck(@Header("Authorization") String token,
                                                 @Field("dr_id") String dr_id,
                                                 @Field("patient_id") String patient_id,
                                                 @Field("medicine_info") String medicine_info,
                                                 @Field("diseases_name") String diseases_name,
                                                 @Field("recheck_id") String recheck_id,
                                                 @Field("dr_comment") String dr_comment,
                                                 @Field("need_of_prescription") String need_of_prescription,
                                                 @Field("dr_name") String dr_name,
                                                 @Field("service_id") String service_id);


    @FormUrlEncoded
    @POST("reply-prescription-request")
    Call<StatusMessage> replyPrescriptionRequest(@Header("Authorization") String token,
                                                 @Field("dr_id") String dr_id,
                                                 @Field("patient_id") String patient_id,
                                                 @Field("medicine_info") String medicine_info,
                                                 @Field("diseases_name") String diseases_name,
                                                 @Field("request_id") String request_id,
                                                 @Field("service_id") String service_id,
                                                 @Field("dr_name") String dr_name);


    @FormUrlEncoded
    @POST("GeneralEntry.php")
    Call<StatusId> drGeneralEntry(@Field("dr_name") String dr_name,
                                  @Field("email") String email,
                                  @Field("mobile") String mobile,
                                  @Field("password") String password,
                                  @Field("type") String type,
                                  @Field("last_education_degree") String last_education_degree,
                                  @Field("hospital_name") String hospital_name);

    @FormUrlEncoded
    @POST("chamber-add")
    Call<StatusMessage> setDrSchedule(@Header("Authorization") String token,
                                      @Field("dr_id") String dr_id,
                                      @Field("chamber_name") String chamber_name,
                                      @Field("address") String address,
                                      @Field("fee") String fee,
                                      @Field("follow_up_fee") String follow_up_fee,
                                      @Field("days") String days);

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<StatusMessage> getDrInfo(@Header("Authorization") String token,
                                  @Field("dr_id") String dr_id);


    @GET("department-list")
    Call<List<DepartmentModel>> getDepartmentsList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("change-appointment-status")
    Call<StatusMessage> changeAppointmentStatus(@Header("Authorization") String token,
                                                @Field("appointment_id") String appointment_id,
                                                @Field("status") String status);


    @FormUrlEncoded
    @POST("update_profile.php")
    Call<StatusMessage> updateDrInfo(@Field("id") String id,
                                     @Field("last_education_degree") String last_education_degree,
                                     @Field("hospital_name") String hospital_name);

    @FormUrlEncoded
    @POST("postAppointments.php")
    Call<StatusResponse> postAppointment(@Field("patient_id") String patient_id,
                                         @Field("chamber_id") String chamber_id,
                                         @Field("dr_id") String dr_id,
                                         @Field("appointment_for") String appointment_for,
                                         @Field("phone") String phon,
                                         @Field("problems") String problems,
                                         @Field("date") String date);

    @FormUrlEncoded
    @POST("checkMobile.php")
    Call<StatusResponse> checkMobile(@Field("mobile") String mobile);


    @GET("all-gp")
    Call<List> all_gp();

    @GET("all_gp.php")
    Call<List<DoctorModelRaw>> all_gp_raw(@Query("hospital") String hospital);

    @GET("urgent_doctors_list.php")
    Call<List<DoctorModelRaw>> urgent_doctors_list(@Query("hospital") String hospital);

    @GET("get_home_visit_doctors.php")
    Call<List<DoctorModelRaw>> get_home_visit_doctors(@Query("hospital") String hospital);

    @GET("specialist_doctor")
    Call<List<DoctorModelRaw>> specialist_doctor(@Query("id") String id);

    @GET("specialist_list.php")
    Call<List<DoctorModelRaw>> specialist_doctor_raw(@Query("id") String id,@Query("hospital") String hospital);

    @GET("free_slots_doctors_call_gp")
    Call<List> free_slots_doctors_call_gp(@Query("day") String day, @Query("date") String date, @Query("doctor") String doctor);

    @GET("department-list")
    Call<List<DepartmentModel2>> department_list();

    @POST("login-app")
    Call<PatientLoginModel> login(@Body HashMap request);

    @POST("insert_prescription.php")
    Call<StatusMessage> push_prescription(@Body HashMap request);

    @GET("city-list-by-country")
    Call<List<CityModel>> getCityList(@Query("country") String country);

    @GET("country-list")
    Call<List<CountryModel>> country_list();

    @GET("hospital_list")
    Call<List<HospitalModel>> hospital_list();

    @POST("find_patient_id.php")
    Call<PatientprofileOtpModel> find_patient_id(@Body HashMap request);

    @POST("loginDoctor")
    Call<DoctorLoginModel> loginDoctor(@Body HashMap request);

    @POST("add-appointment-info")
    Call<JsonElement> appAppInfo(@Body HashMap request);

    @FormUrlEncoded
    @POST("all_medicines.php")
    Call<List<MedicineModel4>> all_medicines(@Field("key") String key );

    @FormUrlEncoded
    @POST("all_med_by_brand.php")
    Call<List<MedicineModel4>> all_medicinesbyBrand(@Field("key") String key );

    @POST("getCategory.php")
    Call<List<String>> all_medicinesCategory();

    @POST("getBrands.php")
    Call<List<String>> getBrands();

    @POST("scheduled_appointment_request_list.php")
    Call<List> scheduled_appointment_request_list(@Body HashMap request);

    @GET("get_medicine_varities.php")
    Call<List<MedicineVaritiModel>> get_medicine_varities(@Query("id") String id);

    @POST("get_lab_reports.php")
    Call<List> get_lab_reports(@Body HashMap request);

    @POST("getDocumentsForPatient.php")
    Call<List> getDocumentsForPatient(@Body HashMap request);

    @POST("get_prescriptions.php")
    Call<List> get_prescriptions(@Body HashMap request);

    @POST("urgent_appointment_list.php")
    Call<List> urgent_appointment_list(@Body HashMap request);


    @POST("home_visit_appointment_request_list.php")
    Call<List> home_visit_appointment_request_list(@Body HashMap request);

    @POST("urgent_care_request")
    Call<JsonElement> urgent_care_request(@Body HashMap request);

    @POST("insert_home_care_req.php")
    Call<JsonElement> insert_home_care_req_raw(@Body HashMap request);

    @POST("insert_urgent_req.php")
    Call<JsonElement> urgent_care_request_raw(@Body HashMap request);

    @FormUrlEncoded
    @POST("add-education-info")
    Call<StatusMessage> postEducationInfo(@Header("Authorization") String token,
                                          @Field("dr_id") String dr_id,
                                          @Field("title") String title,
                                          @Field("body") String body);

    @FormUrlEncoded
    @POST("add-skill-info")
    Call<StatusMessage> postSkillInfo(@Header("Authorization") String token,
                                      @Field("dr_id") String dr_id,
                                      @Field("body") String body);


    @GET("all-medicine-list")
    Call<List<MedicineModel>> getMedicine(@Header("Authorization") String token);

    @GET("all-diagnosis-test-list")
    Call<List<TestModel>> getAlltestList(@Header("Authorization") String token);

    @POST("department-list")
    Call<List<DeptModel>> getDepartments(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("doctor-search")
    Call<List<SearchDoctorModel>> searchDoctors(@Header("Authorization") String token,
                                                @Field("doctor_name") String doctor_name,
                                                @Field("department_id") String department_id);

    @FormUrlEncoded
    @POST("add-test-recommendation")
    Call<StatusMessage> addTestRec(@Header("Authorization") String token,
                                   @Field("appointment_id") String appointment_id,
                                   @Field("test_ids") String test_ids);

    @FormUrlEncoded
    @POST("checkMobileNumber")
    Call<StatusMessage> checkMobileNumber(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("get_doctors_documents")
    Call<List<DocumentModel>> getAllDocumentsBySingleDoctor(@Header("Authorization") String token,
                                                            @Field("dr_id") String dr_id);


    @Multipart
    @POST("sign-up")
    Call<SignUpResponse> signUpPatient(@Part("name") RequestBody name,
                                       @Part("department") RequestBody department,
                                       @Part("user_type") RequestBody user_type,
                                       @Part("password") RequestBody password,
                                       @Part("email") RequestBody email,
                                       @Part("phone") RequestBody phone,
                                       @Part("designation_title") RequestBody designation_title,
                                       @Part MultipartBody.Part image);

    @Multipart
    @POST("addPatientDocument.php")
    Call<StatusMessage> addDocumentBypatient(@Part("id") RequestBody id,
                                       @Part("hospital_id") RequestBody hospital_id,
                                       @Part("title") RequestBody title,
                                       @Part MultipartBody.Part image);

    @Multipart
    @POST("add_doctors_documents")
    Call<StatusMessage> doctorDocumentUpload(@Header("Authorization") String token,
                                             @Part("dr_id") RequestBody dr_id,
                                             @Part("title") RequestBody title,
                                             @Part MultipartBody.Part image);

    @Multipart
    @POST("add_prescription_photo_only")
    Call<StatusMessage> addPrescriptionPhoto(@Header("Authorization") String token,
                                             @Part("patient_id") RequestBody patient_id,
                                             @Part("diseases_name") RequestBody diseases_name,
                                             @Part MultipartBody.Part image);

    @Multipart
    @POST("add-blog-info")
    Call<StatusMessage> postBlogWithPhoto(@Header("Authorization") String token,
                                          @Part("dr_id") RequestBody dr_id,
                                          @Part("body") RequestBody body,
                                          @Part("blog_category") RequestBody blog_category,
                                          @Part("title") RequestBody title,
                                          @Part("youtube_video") RequestBody youtube_video,
                                          @Part MultipartBody.Part requestBody);
/*
    @POST("saveFile")
    Call<FileUploadResponse> sendFileToServer(@Header("Authorization") String auth, @Query("type") String type, @Query("directoryPath") String directoryPath, @Body RequestBody multipartFile);

    @Multipart
    @POST("saveFile")
    Call<FileUploadResponse> sendBigFileToServer(@Header("Authorization") String auth, @Query("type") String type, @Query("directoryPath") String directoryPath, @Part MultipartBody.Part multipartFile);

    @POST("saveFile")
    Call<FileUploadResponse> getFileFromServer(@Header("Authorization") String auth, @Query("type") String type);

    @POST("register")
    Call<RegistrationResponse> registration(@Body RegistrationDataModel registrationDataModel);

    @POST("login")
    Call<LoginResponse> login(@Body LoginDataModel loginDataModel);

    @POST("details")
    Call<ProfileResponse> getProfile(@Header("Authorization") String auth);

    @POST("editUser")
    Call<CommonResponse> editUser(@Header("Authorization") String auth, @Body EditUserModel editUserModel);

    @POST("contactList")
    Call<ContactPostResponse> postContactList(@Header("Authorization") String auth, @Body ContactPostModel contactPostModel);

    @POST("callLog")
    Call<CallLogPostResponse> postCallLog(@Header("Authorization") String auth, @Body CallLogPostModel callLogPostModel);

    @POST("smsHistory")
    Call<SmsHistoryPostResponse> postSmsHistory(@Header("Authorization") String auth, @Body SmsHistoryPostModel smsHistoryPostModel);

    @POST("logout")
    Call<CommonResponse> logout(@Header("Authorization") String auth);

    @POST("passwordChange")
    Call<CommonResponse> passwordChange(@Header("Authorization") String auth, @Body ChangePasswordModel changePasswordModel);

    @POST("folderList")
    Call<FolderListResponse> folderList(@Header("Authorization") String auth, @Query("folderCategory") String folderCategory);

    @POST("noteSend")
    Call<NoteResponse> noteSend(@Header("Authorization") String auth, @Body NoteModel noteModel);

    @POST("noteSoftDeleted")
    Call<NoteDeleteResponse> noteDelete(@Header("Authorization") String auth, @Query("id") String id);

    @POST("todoSend")
    Call<TodoResponse> todoSend(@Header("Authorization") String auth, @Body TodoModel todoModel);

    @POST("todoSoftDeleted")
    Call<TodoDeleteResponse> todoDelete(@Header("Authorization") String auth, @Query("id") String id);

    @POST("UserFolderList")
    Call<UserFileFolderListResponse> userFileFolderList(@Header("Authorization") String auth, @Query("type") String type);

    @Multipart
    @POST("deleteFile")
    Call<FileDeleteResponse> fileDelete(@Header("Authorization") String auth, @Part("type") RequestBody type, @Part("id") RequestBody id);
*/
}