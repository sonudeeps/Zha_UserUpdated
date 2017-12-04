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
import android.widget.TextView;
import android.widget.Toast;

import com.velmurugan.nadus.politician.Adapters2.dummy2;
import com.velmurugan.nadus.politician.Adapters2.schAdap;
import com.velmurugan.nadus.politician.Adapters2.scheduleAdapter;
import com.velmurugan.nadus.politician.R;
import com.velmurugan.nadus.politician.RecyclerViews.scheduleItemAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HP on 11/18/2017.
 */

public class schedule_view extends Fragment
{
    TextView t1,t2;
    ImageView ig1;
    Firebase fb;
    RecyclerView recyclerView;
    ArrayList<scheduleAdapter> sad=new ArrayList<>();
    scheduleItemAdapter sID;
    public schedule_view() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view1;
        view1 = inflater.inflate(R.layout.schedulerecycler, container, false);
        t1=(TextView)view1.findViewById(R.id.add);
        t2=(TextView)view1.findViewById(R.id.date);
        ig1=(ImageView)view1.findViewById(R.id.imageButton);
        t2.setText(dummy2.date_picked);
        recyclerView=(RecyclerView)view1.findViewById(R.id.recycler_schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(view1.getContext()));
        Firebase.setAndroidContext(getActivity());
        fb= new Firebase("https://zha-admin.firebaseio.com/");
        new MyTask().execute();
//        sad.add(new scheduleAdapter());
//        sad.add(new scheduleAdapter());
//        sad.add(new scheduleAdapter());
//        sad.add(new scheduleAdapter());
//        sad.add(new scheduleAdapter());
        ig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // getFragmentManager().beginTransaction().replace(R.id.frame_container,new schedule()).commit();
            }
        });

//        sID=new scheduleItemAdapter(R.layout.schedulecard,sad);
//        recyclerView.setAdapter(sID);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               // getFragmentManager().beginTransaction().replace(R.id.frame_container,new create_schedule()).commit();
            }
        });

        return view1;
    }
    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            fb.child("Admin").child("Schedule").child(dummy2.date_picked).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for(DataSnapshot child: dataSnapshot.getChildren()){
                            schAdap schadap=child.getValue(schAdap.class);
                            sad.add(new scheduleAdapter(child.getKey(),schadap.getDesc()));
                        }
                        Collections.reverse(sad);
                        sID=new scheduleItemAdapter(R.layout.schedulecard,sad);
                        recyclerView.setAdapter(sID);
                    }
                    else{
                        Toast.makeText(getActivity(), "Not yet Scheduled...", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            return null;
        }
    }
}
