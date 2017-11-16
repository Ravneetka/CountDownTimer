package exousiatech.newpracticeapp.Utils;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import exousiatech.newpracticeapp.Interfaces.WebAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 14-11-2017.
 */

public class CommonMethods {
    static  String TAG = "Common_methods";
    public static WebAPI getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.85.11.88:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WebAPI.class);
    }

    // //////////////COUNT DOWN START/////////////////////////
    public static void countDownStart(Handler handler, Runnable runnable, final TextView tvday, final TextView tvhours, final TextView tvminutes, final TextView tvseconds, final String date) {
        Log.e(TAG,"method chala yr");
        handler = new Handler();
        final Handler finalHandler = handler;
        final Runnable finalRunnable = runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                finalHandler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "MMMM dd, yyyy hh:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    // Here Set your Event Date
                    Date eventDate = dateFormat.parse(date);
                    Log.e(TAG,"event date "+eventDate.getTime());
                    Date currentDate = new Date();
                    if (!currentDate.after(eventDate)) {
                        long diff = eventDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvday.setText("" + String.format("%02d", days));
                        tvhours.setText("" + String.format("%02d", hours));
                        tvminutes.setText("" + String.format("%02d", minutes));
                        tvseconds.setText("" + String.format("%02d", seconds));
                    } else {

                        finalHandler.removeCallbacks(finalRunnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    // //////////////COUNT DOWN END/////////////////////////
}
