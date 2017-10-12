package com.xiufeng.wedleairplane.view;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.xiufeng.wedleairplane.R;
import com.xiufeng.wedleairplane.common.LocationHelper;
import com.xiufeng.wedleairplane.common.StaticData;
import com.xiufeng.wedleairplane.models.MapMod;


/*
1、用于判断是否更新
2、判断是不是需要启动 WelcomeActivity
 */
public class StartActivity extends AppCompatActivity implements LocationHelper.LocationCallBack {

    private LocationHelper helper;
    private TextView tv;
    public  double lLng = 120.132726;
    public  double lLat = 30.28198;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_start);
        tv = (TextView) findViewById(R.id.tv);
        helper = LocationHelper.getInstance();
        helper.setCallBack(this);
        helper.start();
        goinMain();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LocationHelper.getInstance().stop();
        StartActivity.this.finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    private void goinMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent  intent =new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("lLat",lLat);
                    intent.putExtra("lLng",lLng);
                    startActivity(intent);

                    StartActivity.this.finish();
                }
        }, 3000);
    }

    @Override
    public void callBack(String addr, double lat, double lng) {
        if (addr != null) {
            Toast.makeText(StartActivity.this,"6666",Toast.LENGTH_SHORT).show();
            lLat = lat;
            lLng = lng;
            tv.setText(lat+"|"+lng);
            helper.stop();
        }else {
            Toast.makeText(StartActivity.this,"7777",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationHelper.getInstance().stop();
    }
}
