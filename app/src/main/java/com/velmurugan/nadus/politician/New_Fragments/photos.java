package com.velmurugan.nadus.politician.New_Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.velmurugan.nadus.politician.Adapters2.dummy2;
import com.velmurugan.nadus.politician.Adapters2.photoAdap;
import com.velmurugan.nadus.politician.Adapters2.photoAdapter;
import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.R;
import com.velmurugan.nadus.politician.RecyclerViews.photoItemAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HP on 11/16/2017.
 */

public class photos extends Fragment {
         RecyclerView recyclerView;
         ImageView ig1,ig2;
         TextView tamillong;
         String lang;
    Firebase fb;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;


        ArrayList<photoAdapter> pa=new ArrayList<>();
        photoItemAdapter pTD;
    public photos()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.photos,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_photos);
        tamillong=(TextView)view.findViewById(R.id.longtamil);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");

        if(lang.equals("tamil"))
        {
            SampleActivity.toolbar.setTitle("நிழற்படம்");
            tamillong.setText(" படத்தை பதிவிறக்கம் செய்ய அழுத்துக");

        }
        else{
            SampleActivity.toolbar.setTitle("Photos");
        }
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        Firebase.setAndroidContext(getActivity());
        fb = new Firebase("https://zha-admin.firebaseio.com/");
        ig1=(ImageView)view.findViewById(R.id.imageButton);
        ig2=(ImageView)view.findViewById(R.id.imageView2);
        ig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        ig2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dummy2.photokeyvalues.clear();
               // getFragmentManager().beginTransaction().add(R.id.frame_container,new create_photo()).commit();
            }
        });
        new MyTask().execute();
//        int i=10;
//        while(i>=0) {
//            pa.add(new photoAdapter());
//            i--;
//        }
//        pTD=new photoItemAdapter(R.layout.photos_card,pa);
//        recyclerView.setAdapter(pTD);
        return view;
    }
    public class MyTask extends AsyncTask<String , Integer, String > {

        @Override
        protected String doInBackground(String... strings) {
            fb.child("Admin").child("Photos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot child: dataSnapshot.getChildren()){
                        System.out.println("bow"+child.getKey());
                        photoAdap photoadap=child.getValue(photoAdap.class);
                        System.out.println("bow"+photoadap.getTitle());
                        dummy2.photokeyvalues.add(child.getKey());


                        pa.add(new photoAdapter(photoadap.getTitle(),photoadap.getPurl()));

                    }
                    Collections.reverse(pa);
                 //   Collections.reverse(dummy2.photokeyvalues);
                    photoItemAdapter ptD=new photoItemAdapter(R.layout.photos_card,pa);
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
