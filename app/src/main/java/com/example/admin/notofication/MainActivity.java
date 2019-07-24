package com.example.admin.notofication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

     Button button;
     public  final String Channerl_ID ="one";
    public  final String Channerl_NAME ="name";
    public  final String Channerl_DESC ="description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        Intent intent = new Intent(this,SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,0);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this,Channerl_ID).setSmallIcon(R.drawable.ic_launcher_background)
                                                    .setContentTitle("Notification Title")
                                                    .setContentTitle("Long text that cannnot feet in one line .....................")
                                                    .setContentIntent(pendingIntent)
                                                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Big Text"))
                                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create notifiacation channel
                createNotificationChannel();
                builder.setAutoCancel(true);
                notificationManagerCompat.notify(1,builder.build());
            }
        });


    }
    private  void  createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = Channerl_NAME;
            String description =Channerl_DESC;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(Channerl_ID,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
