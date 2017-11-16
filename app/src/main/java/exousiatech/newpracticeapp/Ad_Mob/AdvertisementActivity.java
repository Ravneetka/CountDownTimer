package exousiatech.newpracticeapp.Ad_Mob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import exousiatech.newpracticeapp.R;

public class AdvertisementActivity extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    Button button;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        mAdView = (AdView)findViewById(R.id.adView);
        button = (Button)findViewById(R.id.btn_fullscreen_ad);
        adRequest = new AdRequest.Builder()
                .build();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("9478E69D1197CA3365AD776F3C277DF6")
                .build();
        mAdView.loadAd(adRequest);
        button.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fullscreen_ad:
                startActivity(new Intent(AdvertisementActivity.this,FullScreenActivity.class));
                break;
        }
    }
}
