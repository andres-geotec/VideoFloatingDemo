package ximhaitec.com.videofloatingdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class ServicioInBackground extends Service {

    private NotificationManager notificationManager;

    public ServicioInBackground() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int idProcess) {
        MostrarNotificacion();
        AbrirFloating();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Servicio Terminado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    private void AbrirFloating() {
        //startService(new Intent(getApplicationContext(), FloatingViewService.class));
        //startService(new Intent(getApplicationContext(), FloatingVideoService.class));
        startService(new Intent(getApplicationContext(), FloatingVideoService2.class));
    }

    private void MostrarNotificacion() {

        long vibrate[] = {0, 100, 100};

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notiBiuld = new NotificationCompat.Builder(getBaseContext());

        long time = System.currentTimeMillis();

        notiBiuld.setAutoCancel(true);
        notiBiuld.setSmallIcon(R.drawable.ic_camera_roll_white_24dp);
        //notiBiuld.setTicker("aaa time");
        notiBiuld.setContentTitle("Aplicación activa.");
        //notiBiuld.setContentText("Toca para terminar.");
        notiBiuld.setVibrate(vibrate);
        notiBiuld.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notiBiuld.setColor(Color.rgb(255, 121, 63));
        //notiBiuld.setChannelId("11");
        notiBiuld.setWhen(time);
        notiBiuld.setContentIntent(pendingIntent);


        notificationManager.notify((int) time, notiBiuld.build());

        startForeground((int) time, notiBiuld.getNotification());
    }
}
