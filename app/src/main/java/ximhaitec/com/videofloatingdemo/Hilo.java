package ximhaitec.com.videofloatingdemo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by HP on 20/03/2018.
 */

public class Hilo extends AsyncTask<String, Void, String> {

    private Context context;

    public Hilo(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected String doInBackground(String... strings) {

        this.context.startService(new Intent(this.context, ServicioInBackground.class));
        //this.context.startService(new Intent(this.context, FloatingViewService.class));

        return null;
    }
}
