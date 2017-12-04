package com.velmurugan.nadus.politician.HomePages;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

/**
 * Created by admin on 27-11-2017.
 */

public class fragment_about extends Fragment {
    String lang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.aboutus,container,false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");

        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("எங்களை பற்றி");
        }
        else
        {
            SampleActivity.toolbar.setTitle("About Us" );
        }

        return view;
    }
}
