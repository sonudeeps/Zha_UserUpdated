package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

/**
 * Created by Belal on 18/09/16.
 */


public class Fragment_8 extends Fragment {
    SharedPreferences sharedpreferences ;
    private String lang;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_8, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        sharedpreferences=getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        lang = sharedpreferences.getString("lang","");
        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("எம்மை பற்றி");
        }
        else{
            SampleActivity.toolbar.setTitle("About Us");
        }


        getActivity().setTitle("About Us");
    }
}