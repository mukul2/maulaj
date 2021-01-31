package com.telemedicine.maulaji.viewEngine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.model.CartItemsModel;
import com.telemedicine.maulaji.model.CityModel;
import com.telemedicine.maulaji.model.DoctorModelRaw;
import com.telemedicine.maulaji.model.MedHModel;
import com.telemedicine.maulaji.model.MedicineModel4;
import com.telemedicine.maulaji.widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE;
import static com.telemedicine.maulaji.Data.Data.PHOTO_BASE_PHARMACY;
import static com.telemedicine.maulaji.Data.Data.itemView;
import static com.telemedicine.maulaji.Data.Data.testList;

public class engineGridViews {
    public interface TapSelectListener {
        void onSelected(int pos, int optionalData);

    }

    TapSelectListener serviceTypeSelectListener;

    public void setServiceTypeSelectListener(TapSelectListener l) {
        this.serviceTypeSelectListener = l;
    }

    public void showHomeAppListpatient(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);
                CircleImageView img = (CircleImageView) holder.itemView.findViewById(R.id.img);
                tv_name.setText(data.get("doctor_name") != null ? data.get("doctor_name").toString() : "Doctor is not asigned yet");
                tv_time.setText(data.get("home_address").toString());
                try {
                    Glide.with(context).load(PHOTO_BASE + data.get("img_url") != null ? data.get("img_url").toString() : "").into(img);

                } catch (Exception e) {

                }
                holder.itemView.setOnClickListener((View view) -> {
                    //   sL.onSelected(position,0);

                });

            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showHomeAppList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);
                CircleImageView img = (CircleImageView) holder.itemView.findViewById(R.id.img);
                tv_name.setText(data.get("patient_name") != null ? data.get("patient_name").toString() : "");
                tv_time.setText(data.get("reason").toString() + "\n" + data.get("phone").toString() + "\n" + data.get("home_address").toString());
                CardView cardGuest = (CardView) holder.itemView.findViewById(R.id.cardGuest);
                CardView cardOther = (CardView) holder.itemView.findViewById(R.id.cardOther);
                if (data.get("appointment_for").toString().equals("2")) {
                    img.setVisibility(View.GONE);
                    cardGuest.setVisibility(View.VISIBLE);
                    cardOther.setVisibility(View.GONE);
                } else if (data.get("appointment_for").toString().equals("1")) {
                    img.setVisibility(View.GONE);
                    cardGuest.setVisibility(View.GONE);
                    cardOther.setVisibility(View.VISIBLE);
                } else if (data.get("appointment_for").toString().equals("0")) {
                    img.setVisibility(View.VISIBLE);
                    cardGuest.setVisibility(View.GONE);
                    cardOther.setVisibility(View.GONE);
                }

                try {
                    Glide.with(context).load(PHOTO_BASE + data.get("img_url").toString()).into(img);
                } catch (Exception e) {

                }
                //Glide.with(context).load(PHOTO_BASE+data.get("img_url").toString()).into(img);
                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });

            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }


    public void showCountryListPatient(List<CityModel> data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                // Log.i("mkl",list.get(position).toString());
                CityModel data_ = data.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_country);

                tv_name.setText(data_.getCity());
                //Glide.with(context).load("https://i.pinimg.com/originals/90/de/2b/90de2b0edcfd196cce7783f6dc0e4bb9.jpg").into(img);
                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });

            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showUrgentAppListPatient(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);
                CircleImageView img = (CircleImageView) holder.itemView.findViewById(R.id.img);
                tv_name.setText(data.get("doctor_name") != null ? data.get("doctor_name").toString() : "Doctor is not Asigned yet");
                tv_time.setText(data.get("reason").toString() + "\n" + data.get("inserted_at").toString());
                try {
                    Glide.with(context).load(PHOTO_BASE + (data.get("img_url") != null ? data.get("img_url").toString() : "")).into(img);

                } catch (Exception e) {

                }
                //Glide.with(context).load("https://i.pinimg.com/originals/90/de/2b/90de2b0edcfd196cce7783f6dc0e4bb9.jpg").into(img);
                holder.itemView.setOnClickListener((View view) -> {
                    //   sL.onSelected(position,0);

                });

            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showUrgentAppList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);
                CircleImageView img = (CircleImageView) holder.itemView.findViewById(R.id.img);
                CardView cardGuest = (CardView) holder.itemView.findViewById(R.id.cardGuest);
                CardView cardOther = (CardView) holder.itemView.findViewById(R.id.cardOther);
                tv_name.setText(data.get("patient_name") != null ? data.get("patient_name").toString() : "No Name");
                tv_time.setText(data.get("reason").toString() + "\n" + data.get("phone").toString() + "\n" + data.get("created_at").toString());
                try {
                    Glide.with(context).load(PHOTO_BASE + data.get("img_url").toString()).into(img);
                } catch (Exception e) {

                }
                if (data.get("appointment_for").toString().equals("2")) {
                    img.setVisibility(View.GONE);
                    cardGuest.setVisibility(View.VISIBLE);
                    cardOther.setVisibility(View.GONE);
                } else if (data.get("appointment_for").toString().equals("1")) {
                    img.setVisibility(View.GONE);
                    cardGuest.setVisibility(View.GONE);
                    cardOther.setVisibility(View.VISIBLE);
                } else if (data.get("appointment_for").toString().equals("0")) {
                    img.setVisibility(View.VISIBLE);
                    cardGuest.setVisibility(View.GONE);
                    cardOther.setVisibility(View.GONE);
                }
                holder.itemView.setOnClickListener((View view) -> {
                    // Log.i("23deb",data.toString());
                    if (data.get("appointment_for").toString().equals("2")) {
                        sL.onSelected(position, 0);//0 = patinet , 1 = guest
                    } else if (data.get("appointment_for").toString().equals("1")) {
                        sL.onSelected(position, 0);//0 = patinet , 1 = guest
                    } else if (data.get("appointment_for").toString().equals("0")) {
                        sL.onSelected(position, 0);//0 = patinet , 1 = guest
                    }


                });

            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showSymptomsListPatient(List<MedHModel> data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final MedHModel medHModel = data.get(position);
                context = holder.itemView.getContext();
                CheckBox checkbox = (CheckBox) holder.itemView.findViewById(R.id.checkbox);
                if (medHModel.getIsSelected() == 1) {
                    checkbox.setChecked(true);

                } else {
                    checkbox.setChecked(false);

                }


                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            sL.onSelected(position, 1);
                        } else {
                            sL.onSelected(position, 0);
                        }
                    }
                });


                checkbox.setText(medHModel.getName().toString());
//
//                holder.itemView.setOnClickListener((View view)->{
//                    //sL.onSelected(position);
//
//                });


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showSymptomsListDoc(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                String data = list.get(position).toString();
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                tv_name.setText(data);
                holder.itemView.setOnClickListener((View view) -> {
                    //sL.onSelected(position);

                });


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showMedicineList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_dose = (TextView) holder.itemView.findViewById(R.id.tv_dose);
                TextView tv_mg = (TextView) holder.itemView.findViewById(R.id.tv_mg);
                TextView tv_continue = (TextView) holder.itemView.findViewById(R.id.tv_continue);
                tv_name.setText(data.get("medName").toString());
                tv_dose.setText(data.get("dose").toString());
                tv_mg.setText(data.get("mg").toString());
                tv_continue.setText(data.get("continue").toString());


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showPrescriptionList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_date = (TextView) holder.itemView.findViewById(R.id.tv_date);
                TextView tv_symptoms = (TextView) holder.itemView.findViewById(R.id.tv_symptoms);
                TextView tv_advice = (TextView) holder.itemView.findViewById(R.id.tv_advice);
                TextView tv_doctor = (TextView) holder.itemView.findViewById(R.id.tv_doctor);
                RecyclerView recycler_viewMeds = (RecyclerView) holder.itemView.findViewById(R.id.recycler_viewMeds);
                Double d = Double.parseDouble(data.get("date").toString());
                // Long t = Long.getLong(data.get("date").toString());
                // java.util.Date time=new java.util.Date((long)Long.getLong(data.get("date").toString()));
                java.util.Date time = new java.util.Date(d.longValue());
                tv_date.setText(data.toString());
                tv_doctor.setText(data.get("doctorname").toString());
                tv_symptoms.setText(data.get("symptom").toString().trim());
                tv_advice.setText(data.get("advice").toString());
                //tv_date.setText(data.get("date").toString());
                //Log.i("uni",data.get("date").toString());

                List medList = (List) data.get("medicine_list");
                //showMedicineList

                showMedicineList(medList, recycler_viewMeds, context, R.layout.medicine_row, null);


                holder.itemView.setOnClickListener((View view) -> {
                    //sL.onSelected(position);

                });


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showShopCategoryList(List<String> data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final String name = data.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                tv_name.setText(name);


                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showCartList(List<CartItemsModel> data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final CartItemsModel medicineModel4 = data.get(position);
                context = holder.itemView.getContext();

                ImageView img = (ImageView) holder.itemView.findViewById(R.id.img);
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_price = (TextView) holder.itemView.findViewById(R.id.tv_price);
                TextView tv_quantity = (TextView) holder.itemView.findViewById(R.id.tv_quantity);

                tv_name.setText(medicineModel4.getName());
                tv_quantity.setText("QTY " + medicineModel4.getQuantity());
                tv_price.setText("MRP. " + medicineModel4.getPrice());

                Glide.with(context).load(PHOTO_BASE_PHARMACY + medicineModel4.getImage() + ".jpg").into(img);

//PHOTO_BASE_PHARMACY

                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showShopMedicineList(List<MedicineModel4> data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final MedicineModel4 medicineModel4 = data.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                ((TextView) holder.itemView.findViewById(R.id.tv_price)).setText("MRP " + medicineModel4.getPrice());
                ImageView img = (ImageView) holder.itemView.findViewById(R.id.img);
                tv_name.setText(medicineModel4.getName());

                Glide.with(context).load(PHOTO_BASE_PHARMACY + medicineModel4.getImg() + ".jpg").into(img);

//PHOTO_BASE_PHARMACY

                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showScheduledAppListPatient(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl23", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);
                TextView tv_date = (TextView) holder.itemView.findViewById(R.id.tv_date);
                CircleImageView img = (CircleImageView) holder.itemView.findViewById(R.id.img);
                ImageView cardVcall = (ImageView) holder.itemView.findViewById(R.id.cardVcall);
                ImageView cardAcall = (ImageView) holder.itemView.findViewById(R.id.cardAcall);
                ImageView cardChat = (ImageView) holder.itemView.findViewById(R.id.cardChat);
                CardView cardPatientJoin = (CardView) holder.itemView.findViewById(R.id.cardPatientJoin);
                tv_name.setText(data.get("doctor_name") != null ? data.get("doctor_name").toString() : "No Name in database");
                tv_time.setText(data.get("time_slot") != null ? data.get("time_slot").toString() : "");
                tv_date.setText(data.get("date") != null ? data.get("date").toString() : "");
                if (data.get("img_url") != null) {
                    img.setVisibility(View.VISIBLE);
                    Glide.with(context).load(PHOTO_BASE + data.get("img_url")).into(img);
                } else {
                    img.setVisibility(View.GONE);
                }
                // Glide.with(context).load(PHOTO_BASE +data.get("img_url")!=null?  data.get("img_url").toString():"").into(img);

                cardPatientJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(context, "wait, from master", Toast.LENGTH_SHORT).show();
                        sL.onSelected(position, 5);
                    }
                });
                cardChat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(context, "wait, from master", Toast.LENGTH_SHORT).show();
                        sL.onSelected(position, 4);
                    }
                });


                cardVcall.setVisibility(View.GONE);
                cardAcall.setVisibility(View.GONE);
              //  cardChat.setVisibility(View.GONE);


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public void showScheduledAppList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv_name = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);
                ImageView img = (ImageView) holder.itemView.findViewById(R.id.img);
                tv_name.setText(data.get("patientname").toString());
                tv_time.setText(data.get("time_slot") != null ? data.get("time_slot").toString() : "");
                ImageView cardVcall = (ImageView) holder.itemView.findViewById(R.id.cardVcall);
                ImageView cardAcall = (ImageView) holder.itemView.findViewById(R.id.cardAcall);
                ImageView cardChat = (ImageView) holder.itemView.findViewById(R.id.cardChat);
                ImageView cardMore = (ImageView) holder.itemView.findViewById(R.id.cardMore);


                try {
                    Glide.with(context).load(PHOTO_BASE + data.get("img_url") == null ? "" : data.get("img_url").toString()).into(img);
                } catch (Exception e) {

                }


                cardVcall.setOnClickListener((View view) -> {
                    sL.onSelected(position, 1);

                });
                cardAcall.setOnClickListener((View view) -> {
                    sL.onSelected(position, 2);

                });

                cardChat.setOnClickListener((View view) -> {
                    sL.onSelected(position, 3);

                });

                cardMore.setOnClickListener((View view) -> {
                    sL.onSelected(position, 4);

                });
                try {
                    Glide.with(context).load(PHOTO_BASE + data.get("img_url").toString()).into(img);
                } catch (Exception e) {

                }
                //Glide.with(context).load(PHOTO_BASE+data.get("img_url").toString()).into(img);


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), recyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    public DynamicListView showSlotList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {

        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                //  context = holder.tv_name.getContext();
                TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_name);
                tv.setText(data.get("s_time").toString());


                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });
            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return mAdapter;

    }

    public void showGuestGridList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {

        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                final HashMap data = (HashMap) list.get(position);
                context = holder.itemView.getContext();
                CardView card = (CardView) holder.itemView.findViewById(R.id.card);
                TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_name);
                ImageView imgTick = (ImageView) holder.itemView.findViewById(R.id.imgTick);
                card.setRadius(0f);
                tv.setText(data.get("name").toString());
                if ((int) data.get("isSelected") == 1) {
                    imgTick.setVisibility(View.VISIBLE);
                    card.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    tv.setTextColor(context.getResources().getColor(R.color.white));
                } else {
                    imgTick.setVisibility(View.GONE);
                    tv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                }
                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);
                    for (int i = 0; i < list.size(); i++) {
                        HashMap d = (HashMap) list.get(i);
                        d.put("isSelected", 0);
                    }
                    HashMap d = (HashMap) list.get(position);
                    d.put("isSelected", 1);
                    notifyDataSetChanged();
                });
            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public void showGridList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {

        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                final HashMap data = (HashMap) list.get(position);
                context = holder.itemView.getContext();
                CardView card = (CardView) holder.itemView.findViewById(R.id.card);
                TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_name);
                ImageView imgTick = (ImageView) holder.itemView.findViewById(R.id.imgTick);
                tv.setText(data.get("name").toString());
                if ((int) data.get("isSelected") == 1) {
                    imgTick.setVisibility(View.VISIBLE);
                    card.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    tv.setTextColor(context.getResources().getColor(R.color.white));
                } else {
                    imgTick.setVisibility(View.GONE);
                    tv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                }
                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);
                    for (int i = 0; i < list.size(); i++) {
                        HashMap d = (HashMap) list.get(i);
                        d.put("isSelected", 0);
                    }
                    HashMap d = (HashMap) list.get(position);
                    d.put("isSelected", 1);
                    notifyDataSetChanged();
                });
            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public void showDrGridList(List<DoctorModelRaw> data_, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data_, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                //  Log.i("mkl",list.get(position).toString());
                //   final Map<String, Object> data = (Map<String, Object>) list.get(position);
                //using for gp
                TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_name);
                TextView tv_department = (TextView) holder.itemView.findViewById(R.id.tv_dept);
                TextView tv_address = (TextView) holder.itemView.findViewById(R.id.tv_address);
                ImageView img = (ImageView) holder.itemView.findViewById(R.id.img);
                context = holder.itemView.getContext();
                try {
                    DoctorModelRaw data = data_.get(position);
                    if (data != null) {


                        tv.setText(data.getName() != null ? data.getName().toString() : "No data");
                        tv_department.setText(data.getDepartment() != null ? data.getDepartment().toString() : "No data");
                        tv_address.setText(data.getAddress() != null ? data.getAddress().toString() : "No data");
                        Glide.with(context).load(data.getImgUrl() != null ? PHOTO_BASE + data.getImgUrl().toString() : "").into(img);
                        holder.itemView.setOnClickListener((View view) -> {
                            sL.onSelected(position, 0);

                        });
                    }


                } catch (Exception e) {
                    //  Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public void showDeoptGridList(List data, RecyclerView recyclerView, Context context, int vie, TapSelectListener sL) {
        DynamicListView mAdapter = new DynamicListView(data, vie) {
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Log.i("mkl", list.get(position).toString());
                final Map<String, Object> data = (Map<String, Object>) list.get(position);
                context = holder.itemView.getContext();
                TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_title);

                tv.setText(data.get("speciality").toString());

                holder.itemView.setOnClickListener((View view) -> {
                    sL.onSelected(position, 0);

                });
            }
        };
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}


abstract class DynamicListView extends RecyclerView.Adapter<DynamicListView.MyViewHolder> {
    List list = new ArrayList<>();
    DynamicListView.DeptSelectListsner deptSelectListsner;
    int row;

    public void setDeptSelectListsner(DynamicListView.DeptSelectListsner deptSelectListsner) {
        this.deptSelectListsner = deptSelectListsner;
    }

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
        }
    }


    public DynamicListView(List lists, int r) {
        this.list = lists;
        this.row = r;

    }

    public void clearData() {
        this.list.clear();

        notifyDataSetChanged();

    }

    public void updateData(List l) {
        this.list.clear();
        this.list.addAll(l);
        notifyDataSetChanged();

    }

    public DynamicListView(List lists, DynamicListView.DeptSelectListsner listsner) {
        this.list = lists;
        this.deptSelectListsner = listsner;

    }

    public interface DeptSelectListsner {
        public void onSelected(int i);
    }

    @Override
    public DynamicListView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(row, parent, false);
        return new DynamicListView.MyViewHolder(itemView);
    }


    abstract class onBindViewHolder {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

