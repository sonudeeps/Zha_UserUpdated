package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.velmurugan.nadus.politician.Adapters.YTVideos;
import com.velmurugan.nadus.politician.Adapters2.VideoAdapter;
import com.velmurugan.nadus.politician.Adapters2.YoutubeVideo;
import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Fragment_3 extends Fragment
{
    Firebase fb;
    String Base_url = "https://zha-admin.firebaseio.com/";
    private String lang;
    VideoAdapter videoAdapter;
    ArrayList Links;
    //RECYCLER VIEW FIELD
    RecyclerView recyclerView;

    //VECTOR FOR VIDEO URLS
    Vector<YoutubeVideo> youtubeVideos = new Vector<YoutubeVideo>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_3, container, false);
        Firebase.setAndroidContext(getActivity());
        fb = new Firebase(Base_url);
        new MyTask().execute();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));

//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OFK3zXbJ_BA?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/aBcsBcO7Lj8?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/di_HTg5mWpA&t=24s?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/bcLPLch5azo?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/nYG0JUJeC-g&t=4s?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/T24HgsurpII?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/BeOC6sf1F8c?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xj0yc5evIqE?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gSn1nX-tTT0?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );
//        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JCr0Zio_ERY&t=264s?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );

         videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("காணொளி");
        }
        else
        {
            SampleActivity.toolbar.setTitle("Videos");
        }



        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Video Gallery");
    }
    public class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            fb.child("Admin").child("YTVideos").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    Links = new ArrayList<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {




                        System.out.println("child: " + dataSnapshot.getKey());
                        YTVideos ytVideos = child.getValue(YTVideos.class);
                        System.out.println("$$$$$$"+YTVideos.YTLink);
                        String[] S = YTVideos.YTLink.split("v=");
                        System.out.print("ADDINGGG "+S[1]);
                        Links.add(S[1]);
                    }
                    Collections.reverse(Links);
                    System.out.print("KEYSSSS "+Links);
                    for (int i=0;i<Links.size();i++)
                    {
                        System.out.println("@@@@@@@@"+Links.get(i));

                        youtubeVideos.add( new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+Links.get(i)+"?ecver=1\" frameborder=\"0\" allowfullscreen></iframe>") );

                    }
//                    VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
                    recyclerView.setAdapter(videoAdapter);
//                    Collections.reverse(adapters);
//                    itemAdapter itemArrayAdapter= new itemAdapter(R.layout.row2, adapters);
//                    recyclerView1.setAdapter(itemArrayAdapter);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}