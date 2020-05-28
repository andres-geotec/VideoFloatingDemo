package ximhaitec.com.videofloatingdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2085;
    private boolean mIsDrawOverlaysPermission = false;

    private Hilo hilo;

    private ImageButton mOpenWindowImageButton, mStillImageButton, mRecordImageButton, mVisibilityPreviewImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckDrawOverlaysPermission();

        AbrirVentanaFlotante();
        finish();

        mOpenWindowImageButton = (ImageButton) findViewById(R.id.btn_open_floating);
        mOpenWindowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirVentanaFlotante();
            }
        });
    }

    private void AbrirVentanaFlotante() {
        //startService(new Intent(MainActivity.this, FloatingViewService.class));

        if (mIsDrawOverlaysPermission) {
            Toast.makeText(this,"Abriendo floating.",Toast.LENGTH_SHORT).show();
            //startService(new Intent(MainActivity.this, FloatingViewService.class));
            startService(new Intent(MainActivity.this, ServicioInBackground.class));
            //StartService();
        } else {
            Toast.makeText(this,"Por favor, otorga este permiso.",Toast.LENGTH_LONG).show();
            CheckDrawOverlaysPermission();
        }
    }

    private void StartService() {
        hilo = new Hilo(this);
        hilo.execute();
    }

    private void StopService() {
        stopService(new Intent(this, ServicioInBackground.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            /*
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                //initializeView();
            } else { //Permission is not available
                Toast.makeText(this,"Permiso denegado. Aplicación cerrada",Toast.LENGTH_LONG).show();
                finish();
            }
            */
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void CheckDrawOverlaysPermission() {
        //Check if the application has draw over other apps permission or not?
        //This permission is by default available for API<23. But for API > 23
        //you have to ask for the permission in runtime.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            mIsDrawOverlaysPermission = true;
        }
    }
}
