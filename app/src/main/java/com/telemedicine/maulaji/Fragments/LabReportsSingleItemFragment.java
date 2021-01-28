package com.telemedicine.maulaji.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.telemedicine.maulaji.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LabReportsSingleItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LabReportsSingleItemFragment extends Fragment {
    Map<String, Object>  labData ;
    View view ;
    Context context ;
    @BindView(R.id.webview)
    AdvancedWebView webview;


    public LabReportsSingleItemFragment(Map<String, Object> data) {
        this.labData = data ;

    }


    public static LabReportsSingleItemFragment newInstance(Map<String, Object> data) {
        LabReportsSingleItemFragment fragment = new LabReportsSingleItemFragment(data);

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
        view = inflater.inflate(R.layout.fragment_lab_reports_single_item, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        webview.loadHtml(labData.get("report").toString());
        return  view;
    }
}