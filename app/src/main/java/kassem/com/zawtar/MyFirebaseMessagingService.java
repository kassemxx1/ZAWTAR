package kassem.com.zawtar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.media.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intet=new Intent(this,MainActivity.class);
        intet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingintet=PendingIntent.getActivity(this,0,intet,PendingIntent.FLAG_ONE_SHOT);
        android.support.v4.app.NotificationCompat.Builder notificationbuilder=new android.support.v4.app.NotificationCompat.Builder(this);
        notificationbuilder.setContentTitle("FCM NOTIFICATION");
        notificationbuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationbuilder.setContentIntent(pendingintet);
        NotificationManager notificationmanager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationmanager.notify(0,notificationbuilder.build());
    }
}