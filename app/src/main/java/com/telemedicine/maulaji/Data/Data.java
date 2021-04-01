package com.telemedicine.maulaji.Data;


import android.view.View;

import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.model.AmbulanceModel;
import com.telemedicine.maulaji.model.AppointmentModel2;
import com.telemedicine.maulaji.model.AppointmentModelNew;
import com.telemedicine.maulaji.model.BlogModel;
import com.telemedicine.maulaji.model.ChamberInfo;
import com.telemedicine.maulaji.model.Day;
import com.telemedicine.maulaji.model.DeptModel;
import com.telemedicine.maulaji.model.DoctorModel;
import com.telemedicine.maulaji.model.DoctorModelRaw;
import com.telemedicine.maulaji.model.MedicineModel4;
import com.telemedicine.maulaji.model.NoticeModel;
import com.telemedicine.maulaji.model.PrescriptionRequestModel;
import com.telemedicine.maulaji.model.SpacialistModel;
import com.telemedicine.maulaji.model.TestRecomendationModel;
import com.telemedicine.maulaji.model.VideoCallModel;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mkl on 2/14/2019.
 */

public class Data {
    public static String TEMP_SAVE_GUEST_NAME = "";
    public static String TEMP_SAVE_GUEST_EMAIL = "";
    public static String TEMP_SAVE_GUEST_PHONE = "";
    public static boolean isBadgeShowing = false;
    public static Map<String, Object> NOW_SHOWING_DYNAMIC ;
    public static DoctorModelRaw NOW_SHOWING_DOC ;
    public static String NOW_HITTING_SERVICE ;
    public static MedicineModel4 NOW_SHOWING_PRODUCT ;
    public static com.telemedicine.maulaji.model.Data NOTI_DATA ;
    public static String CURRENCY_USD = " RS" ;
    public static String CURRENCY_USD_SIGN = " ₹" ;
    public static String PAY_PAL_CLIENT_ID = "" ;
    public static String SELECTEDED_SLOT_DATE = "" ;
    public static String SELECTEDED_SLOT_TIME = "" ;
    public static String NOW_SHOWING_PATIENT_ID = "" ;


    public static BottomNavigationMenuView menuView;
    public static BottomNavigationItemView itemView;
    public static  View notificationBadge;
    public static BottomNavigationView bottom_nav;
    public static boolean USER_ENABLED=false;
    public static AmbulanceModel NOW_SHOWING_AMBULANCE;
    public static NoticeModel NOW_SHOWING_NOTICE;
    public static SessionManager SESSION_MANAGER;
    public static String PHOTO_BASE = "https://maulaji.com/";
    public static String PHOTO_BASE_PHARMACY = "https://maulaji.com/assets/images/image/";
    public static BlogModel NOW_SHOWING_BLOG;
    public static String TYPE_OF_ACTIVITY;
    public static String TYPE_OF_CALL;
    public static String ADMIN_ID="1";
    public static String CALL_TYPE_AUDIO="audio";
    public static String CALL_TYPE_VIDEO="video";
    public static PrescriptionRequestModel REQUEST_TO_PRESCRIBE;
    public static String TYPE_DOCTOR = "d";
    public static String TYPE_PATIENT = "p";
    public static List<SpacialistModel> spacialist = new ArrayList<>();
    public static List<DoctorModel> searchResult = new ArrayList<>();
    public static DoctorModel singleDrModel;
    public static TestRecomendationModel testList;
    public static AppointmentModelNew appointmentModel;
    public static AppointmentModel2 drServingModel;
    public static VideoCallModel CurentCallDr;
    public static ChamberInfo CHAMBER_TO_BOOK;

    public static List<Day> days = new ArrayList<>();
    public static String USER_NAME;
    public static String TEMP_LINK;

    public static String BaseUrl = "";
    public static String FACEBOOK_LINK = "";
    public static int STATUS_PENDING = 0;
    public static int STATUS_APPROVED = 1;
    public static int STATUS_TEST_RECOMMENED = 2;
    public static int STATUS_SERVED = 3;
    public static int STATUS_CANCEL = 4;
    public static int STATUS_DELETE = 5;

    public static String getColorCode(int pos) {
        List<String> colors = new ArrayList<>();
       /* colors.add("#DC7633");
        colors.add("#2E4053");
        colors.add("#2ECC71");
        colors.add("#27AE60");
        colors.add("#48C9B0");
        colors.add("#2980B9");
        colors.add("#8E44AD");*/
        // colors.add("#E74C3C");
        colors.add("#3498DB");
        colors.add("#45B39D");
        colors.add("#1F618D");
        return colors.get(pos % colors.size());
    }

    public static List<DeptModel> allDeptOffline() {
        String data = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Allergists/Immunologists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Anesthesiologists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Cardiologists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"Colon and Rectal Surgeons \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Critical Care Medicine Specialists\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"Dermatologists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"name\": \"Gastroenterologists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"name\": \"Infectious Disease Specialists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"name\": \"Neurologists \"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 10,\n" +
                "        \"name\": \"Urologists \"\n" +
                "    }\n" +
                "]";

   return  null;


    }

    public static List<String> getDistricts() {
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("DHAKA");
        categories.add("FARIDPUR");
        categories.add("GAZIPUR");
        categories.add("GOPALGANJ");
        categories.add("JAMALPUR");
        categories.add("KISHOREGONJ");
        categories.add("MADARIPUR");
        categories.add("MANIKGANJ");
        categories.add("MUNSHIGANJ");
        categories.add("MYMENSINGH");
        categories.add("NARAYANGANJ");
        categories.add("NARSINGDI");
        categories.add("NETRAKONA");
        categories.add("RAJBARI");
        categories.add("SHARIATPUR");
        categories.add("SHERPUR");
        categories.add("TANGAIL");
        categories.add("BARGUNA");
        categories.add("BARISAL");
        categories.add("BHOLA");
        categories.add("JHALOKATI");
        categories.add("PATUAKHALI");
        categories.add("PIROJPUR ");
        categories.add("BANDARBAN");
        categories.add("BRAHMANBARIA");
        categories.add("CHANDPUR");
        categories.add("CHITTAGONG");
        categories.add("COMILLA");
        categories.add("COX'S BAZAR");
        categories.add("COX'S BAZAR");
        categories.add("KHAGRACHHARI");
        categories.add("LAKSHMIPUR");
        categories.add("NOAKHALI");
        categories.add("RANGAMATI ");
        categories.add("BAGERHAT");
        categories.add("CHUADANGA");
        categories.add("JESSORE");
        categories.add("JHENAIDAH");
        categories.add("KHULNA");
        categories.add("KUSHTIA");
        categories.add("MAGURA");
        categories.add("MEHERPUR");
        categories.add("NARAIL");
        categories.add("SATKHIRA");
        categories.add("BOGRA");
        categories.add("CHAPAINABABGANJ");
        categories.add("JOYPURHAT");
        categories.add("PABNA");
        categories.add("NAOGAON");
        categories.add("NATORE");
        categories.add("RAJSHAHI");
        categories.add("SIRAJGANJ");
        categories.add("DINAJPUR");
        categories.add("GAIBANDHA");
        categories.add("KURIGRAM");
        categories.add("LALMONIRHAT");
        categories.add("NILPHAMARI");
        categories.add("PANCHAGARH");
        categories.add("RANGPUR");
        categories.add("THAKURGAON");
        categories.add("HABIGANJ");
        categories.add("MAULVIBAZAR");
        categories.add("SUNAMGANJ");
        categories.add("SYLHET");
        return categories;
    }

    public static List<String> getAllStatusTypes() {
        List<String> list = new ArrayList<>();
        list.add("Pending");
        list.add("Approved");
        list.add("Test Recommened");
        list.add("Served");
        list.add("Cancel");
        list.add("Delete");
        return list;
    }


}
