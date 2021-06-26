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
import com.telemedicine.maulaji.model.DepartmentModel3;
import com.telemedicine.maulaji.model.DeptModel;
import com.telemedicine.maulaji.model.DiseasesModel;
import com.telemedicine.maulaji.model.DoctorDetailProfile;
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
import com.telemedicine.maulaji.model.Success;
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

public class ApiListener {
    public interface UserBillDownloadListener {
        void onUserBillDownloadSuccess(List<BillItem> list);

        void onUserBillDownloadFailed(String msg);
    }
    public interface DocLoginListener {
        void onDocLoginSuccess(DoctorLoginModel res);

        void onDocLoginFailed(String msg);
    }
    public interface doctorSearchListener {
        void onSearchSuccess(List<DoctorModel> list);

        void onSuccessFailed(String msg);
    }

    public interface UserBillSummeryDownloadListener {
        void onUserBillSummeryDownloadSuccess(BillSummery list);

        void onUserBillSummeryDownloadFailed(String msg);
    }

    public interface profileFetchListener {
        void onprofileFetchSuccess(FetchProfileResponse list);

        void onprofileFetchFailed(String msg);
    }

    public interface updatePassword {
        void onUpdatePasswordSuccess(StatusMessage data);

        void onUpdatePasswordFailed(String msg);
    }

    public interface BlogCategoryDownloadListener {
        void onBlogCategoryDownloadSuccess(List<BlogCategoryNameID> list);

        void onBlogCategoryDownloadFailed(String msg);
    }

    public interface NoticesDownloadListener {
        void onNoticesDownloadSuccess(List<NoticeModel> list);

        void onNoticesDownloadFailed(String msg);
    }

    public interface NoticesStatusUpdateListener {
        void onNoticesStatusUpdateSuccess(StatusMessage list);

        void onNoticesStatusUpdateFailed(String msg);
    }

    public interface singlePrescriptionDownloadListener {
        void onPrescriptionDownloadSuccess(PrescriptionModel list);

        void onPrescriptionDownloaFailed(String msg);
    }

    public interface PrescriptionAddListener {
        void onrescriptionAddSuccess(StatusMessage data);

        void onrescriptionAddFailed(String msg);
    }

    public interface ReplyPrescriptionRequestListener {
        void onReplyPrescriptionRequestSuccess(StatusMessage data);

        void onReplyPrescriptionRequestFailed(String msg);
    }

    public interface DrAddServiceListener {
        void onDrAddServiceSuccess(StatusMessage data);

        void onDrAddServiceFailed(String msg);
    }

    public interface DrdeleteServiceListener {
        void onDrDeleteServiceSuccess(StatusMessage data);

        void onDrDeleteServiceFailed(String msg);
    }

    public interface updateDrServiceListener {
        void onDrUpdateServiceSuccess(StatusMessage data);

        void onDrUpdateServiceFailed(String msg);
    }

    public interface PatientSignUPListener {
        void onPatientSignUPSuccess(SignUpResponse list);

        void onPatientSignUPSuccessFailed(String msg);
    }

    public interface PatientDocUPListener {
        void onPatienttDocUPSuccess(StatusMessage list);

        void onPatienttDocUPSuccessFailed(String msg);
    }

    public interface BlogDownloadListener {
        void onBlogDownloaSuccess(List<BlogModel> list);

        void onBlogDownloaSuccessFailed(String msg);
    }

    public interface DocDocUploadListener {
        void onDocDocUploadSuccess(StatusMessage data);

        void onDocDocUploadFailed(String msg);
    }

    public interface prescriptionUploadListener {
        void onPrescriptionUploadSuccess(StatusMessage data);

        void onPrescriptionUploadFailed(String msg);
    }

    public interface DiseasesDownloadListener {
        void onDiseasesDownloadSuccess(List<DiseasesModel> list);

        void onDiseasesDownloadSuccessFailed(String msg);
    }

    public interface successListener {
        void Onsuccess(Success response);

        void Onfailed(String message);

    }

    public interface diseasesAddListener {
        void onDiseasesAddSuccess(StatusMessage list);

        void onDiseasesAddSuccessFailed(String msg);
    }

    public interface DocSearchListener {
        void onDocSearchSuccess(List<SearchDoctorModel> list);

        void onDocSearchFailed(String msg);
    }

    public interface doctorEduSkillDownloadListener {
        void ondoctorEduSkillDownloadSuccess(EducationSkillModel list);

        void ondoctorEduSkillDownloadSuccessFailed(String msg);
    }

    public interface CommonappointmentDownloadListener {
        void onAppointmentDownloadSuccess(List<AppointmentModel> list);

        void onAppointmentDownloadFailed(String msg);
    }
    public interface PhoneUniqueCheckListener {
        void onPhoneUniqueCheckSuccess(String response);

        void onPhoneUniqueCheckFailed(String msg);
    }

    public interface SignupListener {
        void onSignupSuccess(String response);

        void onSignupFailed(String msg);
    }
    public interface AvailableInfoDownloadListener {
        void onAvailableInfoDownloadSuccess(JsonElement data);

        void onAvailableInfoDownloadFailed(String msg);
    }

    public interface DrRecomentationDownloadListener {
        void onRecomendationDownloadSuccess(List<RecomentationModel> list);

        void onRecomendationFailed(String msg);
    }

    public interface patientTreatmentHistoryListener {
        void onpatientTreatmentHistorySearchSuccess(List<TreatmentHistoryModel> list);

        void onpatientTreatmentHistorySuccessFailed(String msg);
    }

    public interface servePostListener {
        void onServePostSuccess(StatusMessage response);

        void onServePostFailed(String msg);
    }

    public interface drServicePostListener {
        void ondrServicePostSuccess(StatusMessage response);

        void ondrServicePostFailed(String msg);
    }

    public interface patientCallLogListener {
        void onPatientCallLogSuccess(List<CallHistoryPatient> list);

        void onPatientCallLogFailed(String msg);
    }

    public interface doctorCallLogListener {
        void onDoctorCallLogSuccess(List<CallHistoryPatient> list);

        void onDoctorCallLogFailed(String msg);
    }
    public interface MedicalHistoryDownloadListener {
        void onMedicalHistoryDownloadSuccess(List<String> list);

        void onMedicalHistoryDownloadFailed(String msg);
    }

    public interface doctorOnlineStatusChangeListener {
        void ondoctorOnlineStatusChangeSuccess(StatusMessage statusMessage);

        void ondoctorOnlineStatusChangeFailed(String msg);
    }

    public interface PushCallLogListener {
        void onPushCallLogSuccess(StatusMessage statusMessage);

        void onPushCallLogFailed(String msg);
    }

    public interface PresCriptionDownloadListenerPatient {
        void onPrescriptionDownloadSuccess(List<PrescriptionModel> data);

        void onPrescriptionDownloadFailed(String msg);
    }

    public interface DownloadAmbulanceListInfoListener {
        void onDownloadAmbulanceListInfoSuccess(List<AmbulanceModel> status);

        void onDownloadAmbulanceListFailed(String msg);
    }

    public interface VideoCallHistoryDownloadListenerPatient {
        void onVideoCallHistoryDownloadSuccess(List<VideoCallHistoryModel> data);

        void onVideoCallHistoryDownloadFailed(String msg);
    }

    public interface publicQueryPostListenerPatient {
        void onPublicQueryPostSuccess(StatusMessage data);

        void onPublicQueryPostFailed(String msg);
    }

    public interface publicQueryDownloadListenerPatient {
        void onPublicQueryDownloadSuccess(List<QueryModel> data);

        void onPublicQueryDownloadFailed(String msg);
    }

    public interface PRofileUpdateListenerPatient {
        void onPRofileUpdateSuccess(ProfileUpdateResponse data);

        void onPRofileUpdateFailed(String msg);
    }

    public interface DrServicesDownloadListener {
        void onDrServicesDownloadSuccess(List<DrOnlineServiceModel> data);

        void onDrServicesDownloadFailed(String msg);
    }

    public interface AllServiceDownloadListener {
        void onAllServiceDownloadSuccess(List<ServiceNameInfo> data);

        void onAllServiceDownloadFailed(String msg);
    }

    public interface onlineDoctorListener {
        void onOnlineDoctorSearchSuccess(List<VideoCallModel> list);

        void onOnlineDoctorSearchFailed(String msg);
    }

    public interface SymptomsDownloadListener {
        void onSymptomsDownloadSuccess(List<MedHModel> list);

        void onSymptomsDownloadFailed(String msg);
    }

    public interface TestDownloadListener {
        void onTestDownloadSuccess(List<TestList> list);

        void onTestDownloadFailed(String msg);
    }

    public interface OnlineDoctorsDownloadListener {
        void onOnlineDoctorsDownloadSuccess(List<OnlineDoctorModel> list);

        void onOnlineDoctorsDownloadFailed(String msg);
    }

    public interface DrServiceDownloadListener {
        void onDrServiceDownloadSuccess(List<DrServiceModel> list);

        void onDrServiceDownloadFailed(String msg);
    }

    public interface appointmentSearchListener {
        void onAppointmentSearchSuccess(List<AppointmentModel2> list);

        void onAppointmentSearchFailed(String msg);
    }

    public interface DeptDownloadListener {
        void onDepartmentDownloadSuccess(List<DeptModel> list);

        void onDepartmentDownloadFailed(String msg);
    }

    public interface chamberListDownloadListener {
        void onChamberListDownloadSuccess(List<DrChamberResponse> list);

        void onChamberListDownloadFailed(String msg);
    }

    public interface drChamberEduSkillDownloadListener {
        void onChamberEduSkillDownloadSuccess(DrEduChInfoModel list);

        void onChamberEduSkillDownloadFailed(String msg);
    }

    public interface departmentsDownloadListener {
        void onDepartmentsListDownloadSuccess(List<DepartmentModel> list);

        void onDepartmentsListDownloadFailed(String msg);
    }

    public interface basicInfoDownloadListener {
        void onBasicInfoDownloadSuccess(BasicInfoModel data);

        void onBasicInfoDownloadFailed(String msg);
    }

    public interface AppointmentPOstListener {
        void onAppointmentPOStSuccess(AppointmentAddResponse data);

        void onAppointmentPOStFailed(String msg);
    }
    public interface PrescriptionOrderPostListener {
        void onPrescriptionOrderPostSuccess(StatusMessage data);

        void onPrescriptionOrderPosttFailed(String msg);
    }
    public interface SlotSearchListener {
        void onSlotSearchSuccess(List<StatusMessage> data);

        void onSlotSearchFailed(String msg);
    }

    public interface SubscriptionViewListener {
        void onSubscriptionViewSuccess(SubscriptionViewResponse data);

        void onSubscriptionViewFailed(String msg);
    }

    public interface SubscriptionListDownlaodListener {
        void onSubscriptionListDownlaodSuccess(List<SubscriptionsModel> data);

        void onSubscriptionListDownlaodFailed(String msg);
    }

    public interface VideoCallReqListDownlaodListener {
        void onVideoCallReqListDownlaodSuccess(List<VideoAppointmentModel> data);

        void onVideoCallReqListDownlaodFailed(String msg);
    }

    public interface TestRecomDownloadListener {
        void onTestRecomDownloadSuccess(List<TestRecomendationModel> data);

        void onTestRecomDownloadFailed(String msg);
    }

    public interface MyPrescriptionRequestDownloadListener {
        void onMyPrescriptionRequestDownloadSuccess(List<PrescriptionRequestModel> data);

        void onMyPrescriptionRequestDownloadFailed(String msg);
    }

    public interface drBasicInfoPostListener {
        void onBasicInfoPostSuccess(StatusId data);

        void onBasicInfoPostFailed(String msg);
    }

    public interface drSchedulePostListener {
        void ondrSchedulePostSuccess(StatusMessage data);

        void ondrSchedulePostFailed(String msg);
    }

    public interface CheckMobileListener {
        void onMobileCheckSuccess(StatusResponse status);

        void onMobileCheckFailed(String msg);
    }

    public interface LoginUserListener {
        void onUserLoginSuccess(PatientLoginModel response);

        void onUserLoginFailed(String msg);
    }
    public interface PrescriptionPushListener {
        void onPrescriptionPushSuccess(StatusMessage response);

        void onPrescriptionPushFailed(String msg);
    }

    public interface CountryDownloadListener {
        void onCountryDownloadSuccess(List<CityModel> response);

        void onCountryDownloadFailed(String msg);
    }
    public interface CountryCountryDownloadListener {
        void onCountryCountryDownloadSuccess(List<CountryModel> response);

        void onCountryCountryDownloadFailed(String msg);
    }
    public interface HospitalDownloadListener {
        void onHospitalDownloadSuccess(List<HospitalModel> response);

        void onHospitalDownloadFailed(String msg);
    }

    public interface patientAccountFindListener {
        void onpatientAccountFindSuccess(PatientprofileOtpModel response);

        void onpatientAccountFindFailed(String msg);
    }

    public interface AppointmentInsertListener {
        void onAppointmentInsertSuccess(JsonElement response);
        void onAppointmentInsertFailed(String msg);
    }

    public interface MedDownloadListener {
        void onMedDownloadSuccess(List<MedicineModel4> response);
        void onMedDownloadFailed(String msg);
    }
    public interface MedCatDownloadListener {
        void onMedCatDownloadSuccess(List<String> response);
        void onMedCatDownloadFailed(String msg);
    }
    public interface MedBrandDownloadListener {
        void onMedBrandDownloadSuccess(List<String> response);
        void onMedBrandDownloadFailed(String msg);
    }

    public interface ScheduledListDownloadListener {
        void onScheduledListDownloadSuccess(List response);
        void onScheduledListDownloadFailed(String msg);
    }

    public interface MedVaritiListDownloadListener {
        void onMedVaritiListListDownloadSuccess(List<MedicineVaritiModel> response);
        void onMedVaritiListListDownloadFailed(String msg);
    }
    public interface LabReportListDownloadListener {
        void onLabReportListDownloadSuccess(List response);
        void onLabReportListDownloadFailed(String msg);
    }
    public interface PatientDpcumenttListDownloadListener {
        void onPatientDpcumentListDownloadSuccess(List response);
        void onPatientDpcumentListDownloadFailed(String msg);
    }

    public interface PrescriptionsListDownloadListener {
        void onPrescriptionsListDownloadSuccess(List response);
        void onPrescriptionsListDownloadFailed(String msg);
    }

    public interface UrgentListDownloadListener {
        void onUrgentListDownloadSuccess(List<DoctorModelRaw> response);
        void onUrgentListDownloadFailed(String msg);
    }

    public interface HomeVisitListDownloadListener {
        void onHomeVisitDownloadSuccess(List response);
        void onHomeVisitDownloadFailed(String msg);
    }
    public interface AllGPDownloadListener {
        void onAllGPDownloadSuccess(List<DoctorModelRaw> response);

        void onAllGPDownloadFailed(String msg);
    }

    public interface RawDocDownloadListener {
        void onAllDocDownloadSuccess(List<DoctorModelRaw> response);

        void onAllDocDownloadFailed(String msg);
    }

    public interface RawDocProfileDownloadListener {
        void onDocDownloadSuccess(DoctorDetailProfile response);

        void onADocDownloadFailed(String msg);
    }

    public interface AllSloatloadListener {
        void onAllSloatDownloadSuccess(List response);

        void onAllSloatDownloadFailed(String msg);
    }
    public interface DeptListDownload {
        void onDeptListDownloadSuccess(List<DepartmentModel2> response);

        void onDeptListDownloadFailed(String msg);
    }

    public interface DeptListDownload2Listener {
        void onDeptListDownloadSuccess(List<DepartmentModel3> response);

        void onDeptListDownloadFailed(String msg);
    }

    public interface NotificationSentListener {
        void onNotificationSentSuccess(JsonElement status);

        void onNotificationSentFailed(String msg);
    }

    public interface PostEducationInfoListener {
        void onPostEducationInfoSuccess(StatusMessage status);

        void onPostEducationInfoFailed(String msg);
    }
    public interface PaymentMethodsDownloadListener {
        void onPaymentMethodsDownloadSuccess(List<PaymentMethodsModel> data);

        void onPaymentMethodsDownloadFailed(String msg);
    }

    public interface DownloadMedicinesListInfoListener {
        void onDownloadMedicinesListInfoSuccess(List<MedicineModel> status);

        void onDownloadMedicinesListFailed(String msg);
    }

    public interface DownloadTestListInfoListener {
        void onDownloadTestListInfoSuccess(List<TestModel> status);

        void onDownloadTestListFailed(String msg);
    }

    public interface addTestRecListener {
        void onAddTestRecSuccess(StatusMessage status);

        void onAddTestRecFailed(String msg);
    }

    public interface DoctorDocDownloadListener {
        void onDoctorDocDownloadSuccess(List<DocumentModel> status);

        void onDoctorDocDownloadFailed(String msg);
    }

    public interface NumberUniqueCheckListener {
        void onNumberUniqueCheckSuccess(StatusMessage status);

        void onNumberUniqueCheckFailed(String msg);
    }

    public interface DownloadOnlineDocListener {
        void onOnlineDocSearchSuccess(List<OnlineDoctorsModel> status);

        void onOnlineDocSearchFailed(String msg);
    }

    public interface PostSkillInfoListener {
        void onPostSkillInfoSuccess(StatusMessage status);

        void onPostSkillInfoFailed(String msg);
    }

    public interface appoinetmentPOstListener {
        void onAppointmentPostSuccess(StatusResponse status);

        void onAppointmentPostFailed(String msg);
    }

    public interface appoinetmentsDownloadListener {
        void onAppointmentDownloadSuccess(List<AppointmentModelNew> status);

        void onAppointmentDownloadFailed(String msg);
    }

    public interface dataDownloadListener {
        void onDownloaded(List<AppointmentModel> status);
    }

    public interface patientAllDataDownloadListener {
        void onDownloaded(AppointmentResponse status);
    }

    public interface patientNotificationDataDownloadListener {
        void onNotificationDownloaded(List<RecomentationModel> status);
    }

    public interface appointmentStateChangeListener {
        void onAppointmentChangeSuccess(StatusMessage list);

        void onPppointmentChangeFailed(String msg);
    }

    public interface profileDownloadListener {
        void onprofileDownloadSuccess(UserProfileResponse list);

        void onprofileDownloadFailed(String msg);
    }

    public interface drprofileUpdateListener {
        void ondrprofileUpdateSuccess(StatusResponse list);

        void ondrprofileUpdateFailed(String msg);
    }
    public interface medicalHistoryUpdateListener {
        void OnmedicalHistoryUpdateSuccess(String list);

        void OnmedicalHistoryUpdateFailed(String msg);
    }

    public interface testNamesDownloadListener {
        void ontestNamesDownloadSuccess(BasicByDrResponse data);

        void ontestNamesDownloadFailed(String msg);
    }

    public interface recomendationTestPostListener {
        void onrecomendationTestPostSuccess(StatusResponse response);

        void onrecomendationTestPostFailed(String msg);
    }

    public interface prescriptionPostListener {
        void onPrescriptionPostSuccess(StatusMessage response);

        void onPrescriptionPostFailed(String msg);
    }
    public interface basicApiListener {
        void onBasicSuccess(StatusMessage response);

        void onBasicApiFailed(String msg);
    }

    public interface PaymentListDownloadListener {
        void onPaymentListDownloadSuccess(AllCollectionWithdraModel response);

        void onPaymentListDownloadFailed(String msg);
    }

    public interface VideoCallPostListener {
        void onVideoCallPostSuccess(StatusMessage response);

        void onVideoCallPostFailed(String msg);
    }

    public interface ReviewRequestDownloadListener {
        void onReviewRequestDownloadSuccess(List<PrescriptionReviewModel> response);

        void onReviewRequestDownloadFailed(String msg);
    }

    public interface BlogPostListener {
        void onBlogPostSuccess(StatusMessage response);

        void onBlogPostFailed(String msg);
    }

    public interface TrackListener {
        void onTrackSuccess(List<TrackModel> response);

        void onTrackFailed(String msg);
    }

    public interface PrescriptionRequestListener {
        void onPrescriptionRequestSuccess(StatusMessage response);

        void onPrescriptionRequestFailed(String msg);
    }
}
