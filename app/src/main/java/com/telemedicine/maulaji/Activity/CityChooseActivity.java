package com.telemedicine.maulaji.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.CitySelectedListener;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.api.getPlacesApiData;
import com.telemedicine.maulaji.model.CityModel;
import com.telemedicine.maulaji.model.CountryModel;
import com.telemedicine.maulaji.model.SearchedPlaceModel;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityChooseActivity extends AppCompatActivity {
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.ed_search)
    EditText ed_search;

    Context context = this;
    com.telemedicine.maulaji.viewEngine.engineGridViews engineGridViews;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    int locatedCountryIndex = 0;
    String countryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        ButterKnife.bind(this);
        //country
        engineGridViews = new engineGridViews();


        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String countryName = (String) b.get("country");
            // tv_country.setText(country);
            if(countryName.equals("0")){
                dowblaodAndPopulate(countryName);
            }

            Api.getInstance().country_list(new ApiListener.CountryCountryDownloadListener() {
                @Override
                public void onCountryCountryDownloadSuccess(List<CountryModel> response) {
                    Toast.makeText(context, "country size " + response.size(), Toast.LENGTH_SHORT).show();
                    List<String> country = new ArrayList<String>();

                    for (int i = 0; i < response.size(); i++) {
                        country.add(response.get(i).getName());
                        if (countryName.equals(response.get(i).getNicename())) {
                            locatedCountryIndex = i;
                            Toast.makeText(context, "found " + response.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.white_spinner, country);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);

                    spinner.setSelection(locatedCountryIndex);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            dowblaodAndPopulate(response.get(i).getNicename());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }

                @Override
                public void onCountryCountryDownloadFailed(String msg) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                }
            });
        }
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String key = s.toString();


                } else {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void dowblaodAndPopulate(String countryName) {
        Api.getInstance().getCityList(countryName, new ApiListener.CountryDownloadListener() {
            @Override
            public void onCountryDownloadSuccess(List<CityModel> response) {
                Toast.makeText(context, "" + response.size(), Toast.LENGTH_SHORT).show();
                //tv_country
                //country_item


                populateRecycler(response);

            }

            @Override
            public void onCountryDownloadFailed(String msg) {

            }
        });
    }

    private void populateRecycler(List<CityModel> response) {
        engineGridViews.showCountryListPatient(response, recycler_view, context, R.layout.country_item, new engineGridViews.TapSelectListener() {
            @Override
            public void onSelected(int pos, int optionalData) {

                CitySelectedListener.listener.onSelectedSucced(response.get(pos).getCity());
                onBackPressed();

            }
        });

    }

    public void back(View view) {
        onBackPressed();
    }
}