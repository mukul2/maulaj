package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.telemedicine.maulaji.Data.DataStore;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.adapter.LastChatListAdapter;
import com.telemedicine.maulaji.model.ChatModelFullBody;
import com.telemedicine.maulaji.widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment implements  View.OnClickListener{
    LastChatListAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    Context context;
    View view;
    List<ChatModelFullBody> list = new ArrayList<>();
    DatabaseReference databaseReference;

    String CLIEND_ID = "maulaji";

    public ChatFragment() {
        // Required empty public constructor
    }


    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);

        init_recycler();
        list.clear();
        mAdapter.notifyDataSetChanged();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(CLIEND_ID).child("lastChatHistory").child(DataStore.USER_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    ChatModelFullBody employeeModelFullProfile = childSnapshot.getValue(ChatModelFullBody.class);

                    list.add(employeeModelFullProfile);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return  view ;
    }

    private void init_recycler() {
        mAdapter = new LastChatListAdapter(context, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerview.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));

        recyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}