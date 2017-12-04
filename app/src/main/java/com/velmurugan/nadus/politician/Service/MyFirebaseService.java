package com.velmurugan.nadus.politician.Service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by admin on 25-11-2017.
 */

public class MyFirebaseService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.

        System.out.println("Message rec from : "+remoteMessage.getFrom());
        System.out.println("Message is : "+remoteMessage.getNotification().getBody());
    }
}