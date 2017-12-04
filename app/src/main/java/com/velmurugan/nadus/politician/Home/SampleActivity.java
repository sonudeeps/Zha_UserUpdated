package com.velmurugan.nadus.politician.Home;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.velmurugan.nadus.politician.Home.menu.DrawerAdapter;
import com.velmurugan.nadus.politician.Home.menu.DrawerItem;
import com.velmurugan.nadus.politician.Home.menu.SimpleItem;
import com.velmurugan.nadus.politician.Home.menu.SpaceItem;
import com.velmurugan.nadus.politician.HomePages.Fragment_1;
import com.velmurugan.nadus.politician.HomePages.Fragment_3;
import com.velmurugan.nadus.politician.HomePages.Fragment_Home;
import com.velmurugan.nadus.politician.HomePages.fragment_about;
import com.velmurugan.nadus.politician.LoginSignup.Login;
import com.velmurugan.nadus.politician.New_Fragments.Wall;
import com.velmurugan.nadus.politician.New_Fragments.myact;
import com.velmurugan.nadus.politician.New_Fragments.photos;
import com.velmurugan.nadus.politician.New_Fragments.schedule;
import com.velmurugan.nadus.politician.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;


import java.util.Arrays;

/**
 * Created by yarolegovich on 25.03.2017.
 */

public class SampleActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_DASHBOARD = 0;
    private static final int POS_LEADERBIO = 1;
    private static final int POS_PHOTOS= 2;
    private static final int POS_VIDEOS = 3;
    private static final int POS_WALL = 4;
    private static final int POS_SCEHDULE = 5;
    private static final int POS_EVENTS = 6;
    private static final int POS_ABOUTUS = 8;
    private static final int POS_LOGOUT = 9;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
   // public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    TextView name;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String lang;
    public static Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences  = getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        int storage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (storage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();

        toolbar.setTitle("Dashboard");

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        name = (TextView) findViewById(R.id.name);
        name.setText(firebaseUser.getDisplayName());

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_LEADERBIO),
                createItemFor(POS_PHOTOS),
                createItemFor(POS_VIDEOS),
                createItemFor(POS_WALL),
                createItemFor(POS_SCEHDULE),
                createItemFor(POS_EVENTS),
                new SpaceItem(34),
                createItemFor(POS_ABOUTUS),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onItemSelected(int position) {
        Fragment selectedScreen = null;
        if (position == POS_DASHBOARD) {
            selectedScreen = new Fragment_Home();
        }
        else if (position == POS_LEADERBIO) {
            selectedScreen = new Fragment_1();
        }
        else if (position == POS_PHOTOS) {
            selectedScreen = new photos();
        }
        else if (position == POS_VIDEOS) {
            selectedScreen = new Fragment_3();
        }
        else if (position == POS_WALL) {
            selectedScreen = new Wall();
        }
        else if (position == POS_SCEHDULE) {
            selectedScreen = new schedule();
        }
        else if (position == POS_EVENTS) {
            selectedScreen = new myact();
        }
        else if (position == POS_ABOUTUS) {
            //startActivity(new Intent(SampleActivity.this,AboutUs.class));
            selectedScreen=new fragment_about();
            //return;
        }
        else if (position == POS_LOGOUT) {
            firebaseAuth.signOut();
            editor.clear();
            editor.commit();
            startActivity(new Intent(SampleActivity.this, Login.class));
            finish();
            return;
        }

        slidingRootNav.closeMenu();

        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private DrawerItem createItemFor(int position)
    {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.blue))
                .withSelectedTextTint(color(R.color.blue));
    }

    private String[] loadScreenTitles()
    {
        if(lang.equals("tamil"))
        {
            return getResources().getStringArray(R.array.ld_TamilTitles);
        }
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}
