package com.velmurugan.nadus.politician.HomePages;

/**
 * Created by nadus on 16-11-2017.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.velmurugan.nadus.politician.Home.SampleActivity;
import com.velmurugan.nadus.politician.New_Fragments.Wall;
import com.velmurugan.nadus.politician.New_Fragments.myact;
import com.velmurugan.nadus.politician.New_Fragments.photos;
import com.velmurugan.nadus.politician.New_Fragments.schedule;
import com.velmurugan.nadus.politician.R;
import com.goka.blurredgridmenu.GridMenuFragment;

/**
 * Created by Belal on 18/09/16.
 */


public class Fragment_Home extends Fragment implements View.OnClickListener{

    private GridMenuFragment mGridMenuFragment;
    CardView c1,c2,c3,c4,c5,c6,c7,c8;
    Fragment selectedScreen;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    private String lang;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
         v =  inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");


         selectedScreen = null;
         t1=(TextView)v.findViewById(R.id.lead);
        t2=(TextView)v.findViewById(R.id.wall);
        t3=(TextView)v.findViewById(R.id.photos);
        t4=(TextView)v.findViewById(R.id.videos);
        t5=(TextView)v.findViewById(R.id.schedule1);
        t6=(TextView)v.findViewById(R.id.events);
        t7=(TextView)v.findViewById(R.id.fb);
        t8=(TextView)v.findViewById(R.id.abus);


        c1=(CardView)v.findViewById(R.id.card_view1);
        c2=(CardView)v.findViewById(R.id.card_view2);
        c3=(CardView)v.findViewById(R.id.card_view3);
        c4=(CardView)v.findViewById(R.id.card_view4);
        c5=(CardView)v.findViewById(R.id.card_view5);
        c6=(CardView)v.findViewById(R.id.card_view6);
        c7=(CardView)v.findViewById(R.id.card_view7);
        c8=(CardView)v.findViewById(R.id.card_view8);
        SampleActivity.toolbar.setTitle("Dashboard");
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
               //v.getContext() .startActivity(new Intent(v.getContext(),AboutUs.class));
                showFragment(new fragment_about());
//                if(lang.equals("tamil"))
//                {
//                    SampleActivity.toolbar.setTitle("எங்களை பற்றி");
//                }
//                else
//                {
//                    SampleActivity.toolbar.setTitle("About Us");
//                }
            }
        });
        if(lang.equals("tamil"))
        {
            t1.setText("தலைவரின் வாழ்க்கை குறிப்பு");
            t2.setText("சுவர்");
            t3.setText("நிழற்படம்");
            t4.setText("காணொளி");
            t5.setText("அட்டவணை");
            t6.setText("நிகழ்வுகள்");
            t7.setText("முகநூல்");
            t8.setText("எங்களை பற்றி");
        }


        return v;
    }
    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

    }

    @Override
    public void onClick(View v2)
    {
        int id=v2.getId();
        if(id == R.id.card_view1)
        {
            selectedScreen=new Fragment_1();
        }
        else if (id==R.id.card_view2)
        {
            selectedScreen = new Wall();
        }
        else if (id==R.id.card_view3)
        {
            selectedScreen = new photos();
        }
        else if (id==R.id.card_view4)
        {
            selectedScreen = new Fragment_3();
        }
        else if (id==R.id.card_view5)
        {
            selectedScreen = new schedule();
        }
        else if (id==R.id.card_view6)
        {
            selectedScreen = new myact();
        }
        else if (id==R.id.card_view7)
        {
          selectedScreen = new Fragment_Facebook();
        }

        showFragment(selectedScreen);


    }
}