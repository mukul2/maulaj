package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.StackView;
import android.widget.Toast;

import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.StackPageTransformer;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.Utils.SessionManager;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;
import com.telemedicine.maulaji.model.StackItems;
import com.telemedicine.maulaji.viewEngine.engineGridViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrescriptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrescriptionsFragment extends Fragment {
    View view ;
    Context context ;
    SessionManager sessionManager ;
    engineGridViews engineGridViews;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.stack_view)
    StackView stackView;
    @BindView(R.id.vpPager)
    ViewPager vpPager;
    List<StackItems> list = new ArrayList<>();

     static final int NUMBER_OF_FRAGMENTS = 15;

     FlippableStackView mFlippableStack;

     ColorFragmentAdapter mPageAdapter;

     List<Fragment> mViewPagerFragments;
     String targetUserID ;

    public PrescriptionsFragment(String uid) {
        // Required empty public constructor
        this.targetUserID = uid;
    }


    public static PrescriptionsFragment newInstance(String uid) {
        PrescriptionsFragment fragment = new PrescriptionsFragment(uid);

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
        view = inflater.inflate(R.layout.fragment_prescriptions, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        sessionManager  = new SessionManager(context);
        engineGridViews = new engineGridViews();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("id",targetUserID);
        Toast.makeText(context, "downloading prescriptions for id "+targetUserID, Toast.LENGTH_SHORT).show();
        //setupViewPager(vpPager);

        //createViewPagerFragments(response);

//        mFlippableStack.initStack(4, portrait ?
////                StackPageTransformer.Orientation.VERTICAL :
////                StackPageTransformer.Orientation.HORIZONTAL,
////                1f,
////                1f,
////                1
////        );




//        list = new ArrayList();
//
//        for (int i = 0; i < 5; i++) {
//            list.add(new StackItems("Item " + i, R.drawable.ap_ic2));
//        }
//        StackAdapter adapter = new StackAdapter(context, list);
//        stackView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


        Api.getInstance().get_prescriptions(request, new ApiListener.PrescriptionsListDownloadListener() {
            @Override
            public void onPrescriptionsListDownloadSuccess(List response) {

                //prescription_row
                com.telemedicine.maulaji.viewEngine.engineGridViews.TapSelectListener listener = new engineGridViews.TapSelectListener() {
                    @Override
                    public void onSelected(int pos, int optionalData) {

                    }
                };


                createViewPagerFragments(response);

                mPageAdapter = new ColorFragmentAdapter(getChildFragmentManager(), mViewPagerFragments);
                mFlippableStack = (FlippableStackView) view.findViewById(R.id.flippable_stack_view);
                mFlippableStack. initStack(response.size()>3?4:response.size(),
                        StackPageTransformer.Orientation.VERTICAL,
                        0.95f,
                        0.7f,
                        0.6f,
                        StackPageTransformer.Gravity.CENTER);
                mFlippableStack.setAdapter(mPageAdapter);

                //engineGridViews.showPrescriptionList(response, recycler_view, context, R.layout.prescription_row, listener);

            }

            @Override
            public void onPrescriptionsListDownloadFailed(String msg) {

            }
        });
        return  view ;
    }
/*
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new SingleprescriptionFragment(), "Prescription");
        adapter.addFragment(new SingleprescriptionFragment(), "Prescription");
        adapter.addFragment(new SingleprescriptionFragment(), "Prescription");
        adapter.addFragment(new SingleprescriptionFragment(), "Prescription");




        viewPager.setAdapter(adapter);
    }




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

 */

    private void createViewPagerFragments(List response) {
        mViewPagerFragments = new ArrayList<>();



        for (int i = 0; i < response.size(); i++) {
            Map<String, Object> data = (Map<String, Object>) response.get(i);
            mViewPagerFragments.add(SingleprescriptionFragment.newInstance(data));
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