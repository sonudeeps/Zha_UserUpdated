package com.velmurugan.nadus.politician.New_Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.velmurugan.nadus.politician.R;

/**
 * Created by admin on 27-11-2017.
 */

public class videoview extends Fragment {
    public videoview() {

    }
    ProgressDialog pDialog;

    VideoView videoview;
    String TimeLineUrl = "https://firebasestorage.googleapis.com/v0/b/zha-admin.appspot.com/o/Admin%2FPost%2F07%3A33%3A12%4027-11-2017?alt=media&token=c11b1064-b5a1-4d87-95af-8e72499dec61";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v1=inflater.inflate(R.layout.video,container,false);
        videoview = (VideoView) v1.findViewById(R.id.exerciseVideo);
        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(v1.getContext());
        // Set progressbar title
        pDialog.setTitle(R.string.app_name);
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();
        Log.e("Error", TimeLineUrl);

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    v1.getContext());
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(TimeLineUrl);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });

        return v1;
    }
}
