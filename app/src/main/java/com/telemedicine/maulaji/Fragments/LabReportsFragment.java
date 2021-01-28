package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.StackPageTransformer;
import com.telemedicine.maulaji.R;
import com.telemedicine.maulaji.api.Api;
import com.telemedicine.maulaji.api.ApiListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

import static com.telemedicine.maulaji.Data.Data.testList;


public class LabReportsFragment extends Fragment {
    View view ;
    Context context ;
    FlippableStackView mFlippableStack;
    String targetuserID;
    ColorFragmentAdapter mPageAdapter;
    List<Fragment> mViewPagerFragments;
    public LabReportsFragment(String uid) {
        this.targetuserID = uid ;
    }


    public static LabReportsFragment newInstance(String uid) {
        LabReportsFragment fragment = new LabReportsFragment(uid);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lab_reports, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("id", targetuserID);
        Api.getInstance().get_lab_reports(request, new ApiListener.LabReportListDownloadListener() {
            @Override
            public void onLabReportListDownloadSuccess(List response) {
                Toast.makeText(context, "lab rep"+response.size(), Toast.LENGTH_SHORT).show();

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
            }

            @Override
            public void onLabReportListDownloadFailed(String msg) {

            }
        });

        return  view;
    }

    private void createViewPagerFragments(List response) {
        mViewPagerFragments = new ArrayList<>();



        for (int i = 0; i < response.size(); i++) {
            Map<String, Object> data = (Map<String, Object>) response.get(i);
            mViewPagerFragments.add(LabReportsSingleItemFragment.newInstance(data));
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