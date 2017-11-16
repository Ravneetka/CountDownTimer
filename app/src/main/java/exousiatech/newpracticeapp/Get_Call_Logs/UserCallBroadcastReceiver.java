package exousiatech.newpracticeapp.Get_Call_Logs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Admin on 11-11-2017.
 */

public class UserCallBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            Toast.makeText(context, "Phone call is Ringing", Toast.LENGTH_SHORT).show();
        }else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            Toast.makeText(context, "Phone call Received", Toast.LENGTH_SHORT).show();
        }else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
            Toast.makeText(context, "Phone call is rejected", Toast.LENGTH_SHORT).show();
        }


    }
}
