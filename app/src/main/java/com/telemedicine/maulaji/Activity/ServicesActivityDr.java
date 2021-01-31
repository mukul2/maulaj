package com.telemedicine.maulaji.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.DrServiceModel;
import com.telemedicine.maulaji.model.StatusMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.DataStore.USER_ID;

public class ServicesActivityDr extends AppCompatActivity implements ApiListener.DrServiceDownloadListener,ApiListener.drServicePostListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
  //  public static List<ServiceWithBoolean> SERVICES_LIST=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_dr);
        ButterKnife.bind(this);
        //SERVICES_LIST.clear();
        Api.getInstance().downloadDrServiceList(USER_ID,this);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onDrServiceDownloadSuccess(List<DrServiceModel> list) {
//        for (int i=0;i<serviceNameList.size();i++){
//            SERVICES_LIST.add(new ServiceWithBoolean(false,serviceNameList.get(i)));
//
//        }
//        for (int i=0;i<SERVICES_LIST.size();i++){
//            for (int j=0;j<list.size();j++){
//                if (SERVICES_LIST.get(i).getServiceName().getId().equals(list.get(j).getServiceId())){
//                    SERVICES_LIST.get(i).setSelected(true);
//                    break;
//
//                }
//            }
//        }
//        DrServicesListAdapter mAdapter = new DrServicesListAdapter();
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        //recyclerView.addItemDecoration(new_ DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//
//        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onDrServiceDownloadFailed(String msg) {

    }

    public void update(View view) {
//        List<ServiceIdPrice>list=new ArrayList<>();
//        int counter =0;
//        for (int i=0;i<SERVICES_LIST.size();i++){
//            if (SERVICES_LIST.get(i).isSelected()){
//                list.add(new ServiceIdPrice(SERVICES_LIST.get(i).getServiceName().getId(),"500"));
//
//            }
//
//        }
//        Gson gson=new Gson();
//        Toast.makeText(this, gson.toJson(list), Toast.LENGTH_SHORT).show();
//        Api.getInstance().postDrServices(USER_ID,gson.toJson(list),this);
    }

    @Override
    public void ondrServicePostSuccess(StatusMessage response) {
        if (response!=null){
           // Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ondrServicePostFailed(String msg) {
       // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
