package com.velmurugan.nadus.politician.HomePages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.velmurugan.nadus.politician.R;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
//        simulateDayNight(/* DAY */ 0);
//        Element adsElement = new Element();
//        adsElement.setTitle("Advertise with us");
//
//        View aboutPage = new AboutPage(this)
//                .isRTL(false)
//                .setImage(R.mipmap.ic_launcher)
//                .addItem(new Element().setTitle("Version 1.0"))
//                .addItem(adsElement)
//                .addGroup("Connect with us")
//                .addEmail("zhaagaram@gmail.com")
//                .addFacebook("the.medy")
////                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
////                .addPlayStore("com.ideashower.readitlater.pro")
////                .addInstagram("medyo80")
//                .addGitHub("medyo")
//                .addItem(getCopyRightsElement())
//                .create();


    }


//    Element getCopyRightsElement() {
//        Element copyRightsElement = new Element();
//        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
//        copyRightsElement.setTitle(copyrights);
//        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
//        copyRightsElement.setIconNightTint(android.R.color.white);
//        copyRightsElement.setGravity(Gravity.CENTER);
//        copyRightsElement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AboutUs.this, copyrights, Toast.LENGTH_SHORT).show();
//            }
//        });
//        return copyRightsElement;
//    }
//
//    void simulateDayNight(int currentSetting) {
//        final int DAY = 0;
//        final int NIGHT = 1;
//        final int FOLLOW_SYSTEM = 3;
//
//        int currentNightMode = getResources().getConfiguration().uiMode
//                & Configuration.UI_MODE_NIGHT_MASK;
//        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_NO);
//        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_YES);
//        } else if (currentSetting == FOLLOW_SYSTEM) {
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//        }
//    }
}
