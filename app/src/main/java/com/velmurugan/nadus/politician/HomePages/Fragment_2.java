package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.velmurugan.nadus.politician.Adapters.PhotosAdapter;
import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
/**
 * Created by Belal on 18/09/16.
 */
public class Fragment_2 extends Fragment {
    private String lang;

    private ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.lists,R.drawable.back,R.drawable.timeline};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View v = inflater.inflate(R.layout.fragment_2, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");

        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("நிழற்படம்");
        }
        else{
            SampleActivity.toolbar.setTitle("Photos");
        }


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        //you can set the title for your toolbar here for different fragments different titles

        init();
    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);
        mPager.setAdapter(new PhotosAdapter(getActivity(),XMENArray));
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}