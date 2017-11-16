package exousiatech.newpracticeapp.Get_Call_Logs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import exousiatech.newpracticeapp.R;
import exousiatech.newpracticeapp.Utils.RequiredPermission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Cursor cursor;
    RequiredPermission requiredPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requiredPermission = new RequiredPermission(MainActivity.this);
        requiredPermission.checkForPermissionTocall();
        button = (Button)findViewById(R.id.callLogs);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.callLogs:
                Intent intent = new Intent(MainActivity.this,CallLogs_Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
