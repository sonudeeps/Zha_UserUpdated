package com.velmurugan.nadus.politician.New_Fragments;

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

import com.velmurugan.nadus.politician.Adapters2.wallAdap;
import com.velmurugan.nadus.politician.Adapters2.wallAdapter;
import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;
import com.velmurugan.nadus.politician.RecyclerViews.wallItemAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by admin on 18-11-2017.
 */

public class Wall extends Fragment
{
    RecyclerView recyclerView;
    ArrayList<wallAdapter> wa=new ArrayList<>();

    wallItemAdapter wtd;
    Firebase fb;
    String lang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.wall_recycler,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_wall);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");


        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("சுவர்");
        }
        else{
            SampleActivity.toolbar.setTitle("Wall");
        }
        Firebase.setAndroidContext(getActivity());
        fb = new Firebase("https://zha-admin.firebaseio.com/");
        new MyTask().execute();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        wa.add(new wallAdapter());
//        wa.add(new wallAdapter());
//        wa.add(new wallAdapter());

        wtd=new wallItemAdapter(R.layout.wall_card,wa);
        recyclerView.setAdapter(wtd);
        return view;
    }
    public class MyTask extends AsyncTask<String , Integer, String > {

        @Override
        protected String doInBackground(String... strings) {
            fb.child("Admin").child("Post").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot child: dataSnapshot.getChildren()){
                        System.out.println("bow"+child.getKey());
                        wallAdap walladap=child.getValue(wallAdap.class);
                        System.out.println("bow"+walladap.getTitle());
                        String s=child.getKey();

                        String[] ss=s.split("@");
                        System.out.println("bow"+ss[0]);


                        wa.add(new wallAdapter(walladap.getTitle(),walladap.getImgurl(),ss[1],ss[0],ss[2]));

                    }
                    Collections.reverse(wa);
                    wallItemAdapter ptD=new wallItemAdapter(R.layout.wall_card,wa);
                    recyclerView.setAdapter(ptD);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            return null;
        }
    }
}
