package exousiatech.newpracticeapp.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin1 on 20-08-2016.
 */
public class RequiredPermission {
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String TAG = getClass().getSimpleName();

    public RequiredPermission(Activity activity) {
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences(MyConstants.SHAREDPREFRENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        checkForPermissionTocall();
    }

    public void checkForPermissionTocall() {
        List<String> stockList = new ArrayList<String>();
        if (checkCallLogPermission()){
            editor.putBoolean(MyConstants.READ_CALL_LOG,true);
            editor.commit();
        }
        if (!sharedPreferences.getBoolean(MyConstants.READ_CALL_LOG, false)) {
            stockList.add(Manifest.permission.WRITE_CALL_LOG);
            stockList.add(Manifest.permission.READ_CALL_LOG);
        }
//        if (checkReadWritePermission()) {
//            editor.putBoolean(MyConstants.READ_EXTERNAL_STORAGE, true);
//            editor.commit();
//        }
//        if (checkLocationPermission()) {
//            editor.putBoolean(MyConstants.ACCESS_FINE_LOCATION, true);
//            editor.commit();
//        }
//        if (checkCameraPermission()) {
//            editor.putBoolean(MyConstants.CAMERA_PERMISSION, true);
//            editor.commit();
//        }
//        if (!sharedPreferences.getBoolean(MyConstants.READ_EXTERNAL_STORAGE, false)) {
//            stockList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            stockList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
//        if (!sharedPreferences.getBoolean(MyConstants.ACCESS_FINE_LOCATION, false)) {
//            stockList.add(Manifest.permission.ACCESS_FINE_LOCATION);
//            stockList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//        }
//        if (!sharedPreferences.getBoolean(MyConstants.CAMERA_PERMISSION, false)) {
//            stockList.add(Manifest.permission.CAMERA);
//        }
        if (stockList.isEmpty()) {
            editor.putBoolean(MyConstants.READ_CALL_LOG, true);
//            editor.putBoolean(MyConstants.READ_EXTERNAL_STORAGE, true);
//            editor.putBoolean(MyConstants.ACCESS_FINE_LOCATION, true);
            editor.commit();
        } else {
            String[] PERMISSIONS = new String[stockList.size()];
            PERMISSIONS = stockList.toArray(PERMISSIONS);
            if (!hasPermissions(activity, PERMISSIONS)) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS, 1);
            }
        }

    }

    private static boolean hasPermissions(Context context, String... permissions) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstants.SHAREDPREFRENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            boolean checkKro = true;
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    checkKro = false;
                    return false;
                }
            }
            return checkKro;
        } else {
            editor.putBoolean(MyConstants.READ_CALL_LOG,true);
//            editor.putBoolean(MyConstants.READ_EXTERNAL_STORAGE, true);
//            editor.putBoolean(MyConstants.ACCESS_FINE_LOCATION, true);
//            editor.putBoolean(MyConstants.CAMERA_PERMISSION, true);
            editor.commit();
            return true;
        }
    }

    public boolean checkReadWritePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean checkCallLogPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheckLocation = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (permissionCheckLocation != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheckLocation = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
            if (permissionCheckLocation != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


}
