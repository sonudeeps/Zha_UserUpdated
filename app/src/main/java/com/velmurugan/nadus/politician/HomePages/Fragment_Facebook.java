package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

/**
 * Created by Belal on 18/09/16.
 */


public class Fragment_Facebook extends Fragment {
    WebView mWebView;
    private String lang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_fb, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");

        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("முகநூல்");
        }
        else
        {
            SampleActivity.toolbar.setTitle("FaceBook");
        }



        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = (WebView) view.findViewById(R.id.webviewf);


                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.setWebViewClient(new WebViewClient() {
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                    }
                });

                mWebView .loadUrl("https://www.facebook.com/panrutivelmurugan?fref=pb&hc_location=profile_browser");

        //you can set the title for your toolbar here for different fragments different titles
    }
}