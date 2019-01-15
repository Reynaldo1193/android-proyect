package com.example.desarrollo.proycetotrym;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.desarrollo.proycetotrym.Model.Notificacion;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "Noticias";
    private static final String KEY_DESCOUNT = "descount_key";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Notificacion notificacion = new Notificacion();
        notificacion.setId(remoteMessage.getFrom());
        notificacion.setTitle(remoteMessage.getNotification().getTitle());
        notificacion.setDescription(remoteMessage.getNotification().getBody());
        notificacion.setDescount(remoteMessage.getData().get(KEY_DESCOUNT));

        showNotification(notificacion);

    }

    public void showNotification(Notificacion notificacion){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_DESCOUNT, notificacion.getDescount());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder
                = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notificacion.getTitle())
                .setContentText(notificacion.getDescription())
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,notificationBuilder.build());
    }

}