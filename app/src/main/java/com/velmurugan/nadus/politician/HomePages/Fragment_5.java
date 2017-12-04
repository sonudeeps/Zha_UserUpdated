package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.velmurugan.nadus.politician.Adapters.ScheduleAdapter;
import com.velmurugan.nadus.politician.Adapters.ScheduleAdapter1;
import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_5 extends Fragment {
    private String lang;

    private List<ScheduleAdapter> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScheduleAdapter1 mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view=inflater.inflate(R.layout.fragment_5, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");

        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("அட்டவணை");
        }
        else{
            SampleActivity.toolbar.setTitle("Schedules");
        }

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);mAdapter = new ScheduleAdapter1(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();




        //you can set the title for your toolbar here for different fragments different titles
    }
    private void prepareMovieData() {
        ScheduleAdapter movie = new ScheduleAdapter("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new ScheduleAdapter("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new ScheduleAdapter("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new ScheduleAdapter("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new ScheduleAdapter("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new ScheduleAdapter("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new ScheduleAdapter("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new ScheduleAdapter("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}