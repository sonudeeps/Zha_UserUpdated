package com.velmurugan.nadus.politician.New_Fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.velmurugan.nadus.politician.Adapters2.postAdap;
import com.velmurugan.nadus.politician.Adapters2.postAdapter;
import com.velmurugan.nadus.politician.R;
import com.velmurugan.nadus.politician.RecyclerViews.postItemAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HP on 11/16/2017.
 */

public class post extends Fragment
{
    ImageView ig1,ig2;
    RecyclerView recyclerView;
    ArrayList<postAdapter> pa=new ArrayList<>();
    Firebase fb;
    String url="https://zha-admin.firebaseio.com/";
    public post()
    {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.post,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        pa.add(new postAdapter());
//        pa.add(new postAdapter());
//        postItemAdapter ptD=new postItemAdapter(R.layout.post_card,pa);
//        recyclerView.setAdapter(ptD);
        Firebase.setAndroidContext(view.getContext());
        fb=new Firebase(url);

        new MyTask().execute();
        ig1=(ImageView)view.findViewById(R.id.imageButton);
        ig2=(ImageView)view.findViewById(R.id.imageView2);
        ig1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getActivity().finish();
            }
        });
        ig2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //getFragmentManager().beginTransaction().add(R.id.frame_container,new create_post()).commit();
            }
        });
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
                        postAdap postadap=child.getValue(postAdap.class);
                        System.out.println("bow"+postadap.getTitle());
                        String s=child.getKey();
                        String[] ss=s.split("@");
                        System.out.println("bow"+ss[0]);


                        pa.add(new postAdapter(postadap.getTitle(),postadap.getImgurl(),ss[0],ss[1]));

                    }
                    Collections.reverse(pa);
                    postItemAdapter ptD=new postItemAdapter(R.layout.post_card,pa);
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
