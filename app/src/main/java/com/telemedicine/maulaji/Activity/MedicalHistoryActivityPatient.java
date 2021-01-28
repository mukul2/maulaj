package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.MedHModel;
import com.telemedicine.maulaji.model.MedicalHistoryModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicalHistoryActivityPatient extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    List ids = new ArrayList();
    engineGridViews engineGridViews;
    Context context = this ;
    SessionManager sessionManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history_patient2);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        engineGridViews = new engineGridViews();
        HashMap<Integer, String> maps = new HashMap<Integer, String>();
        HashMap<Integer, String> mapsSub = new HashMap<Integer, String>();
        Api.getInstance().symptoms_list_get(new ApiListener.SymptomsDownloadListener() {
            @Override
            public void onSymptomsDownloadSuccess(List<MedHModel> list) {
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos, int optional) {
                       Log.i("mkl",list.get(0).toString());
                        final MedHModel data = list.get(pos);
                        // Toast.makeText(context, ""+optional, Toast.LENGTH_SHORT).show();

                        //data.get("name").toString()
                        if (optional == 1) {
                            maps.put(data.getId(), data.getName());
                        } else {
                            if(!maps.isEmpty()) maps.remove(data.getId());
                        }
                        mapsSub.putAll(maps);
                        ids.clear();
                        Iterator it = mapsSub.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            System.out.println(pair.getKey() + " = " + pair.getValue());
                            ids.add(pair.getValue());
                            it.remove(); // avoids a ConcurrentModificationException
                        }


                        if(!ids.isEmpty()) {
                            //update from here
                            String dataToUpdate = ids.toString();
                            dataToUpdate = dataToUpdate.substring(1, dataToUpdate.length() -1);
                             Api.getInstance().setMedicalHistory(sessionManager.getUserId(), dataToUpdate, new ApiListener.medicalHistoryUpdateListener() {
                                 @Override
                                 public void OnmedicalHistoryUpdateSuccess(String list) {
                                    // Toast.makeText(MedicalHistoryActivityPatient.this, list, Toast.LENGTH_SHORT).show();
                                 }

                                 @Override
                                 public void OnmedicalHistoryUpdateFailed(String msg) {
                                    // Toast.makeText(MedicalHistoryActivityPatient.this, msg, Toast.LENGTH_SHORT).show();

                                 }
                             });
                           // Toast.makeText(context, dataToUpdate, Toast.LENGTH_SHORT).show();
                        }

                    }
                };


                Api.getInstance().getMyMedicalistory(sessionManager.getUserId(), new ApiListener.MedicalHistoryDownloadListener() {
                    @Override
                    public void onMedicalHistoryDownloadSuccess(List<String> res) {


                        for (int i = 0;i<list.size();i++){
                            for(int j = 0;j<res.size();j++){
                                if(list.get(i).getName().replaceAll(" ","").equals(res.get(j).replaceAll(" ",""))){
                                    list.get(i).setIsSelected(1);
                                    break;
                                }
                            }
                        }
                        for (int i = 0;i<list.size();i++){
                           if(list.get(i).getIsSelected()==1){
                               maps.put(list.get(i).getId(), list.get(i).getName().trim());
                           }else {
                               if(!maps.isEmpty()) maps.remove(list.get(i).getId());
                           }
                        }

                        mapsSub.putAll(maps);
                        ids.clear();
                        Iterator it = mapsSub.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            System.out.println(pair.getKey() + " = " + pair.getValue());
                            ids.add(pair.getValue());
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                       // Toast.makeText(context, ids.toString(), Toast.LENGTH_SHORT).show();
                        engineGridViews.showSymptomsListPatient(list, recycler, context, R.layout.checkbox_symptoms_items, listener);


                    }

                    @Override
                    public void onMedicalHistoryDownloadFailed(String msg) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onSymptomsDownloadFailed(String msg) {

            }
        });


    }

    public void back(View view) {
        onBackPressed();
    }
}