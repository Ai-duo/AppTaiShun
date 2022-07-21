package com.kd.apptaishun;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.kd.apptaishun.databinding.ActivityMainBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Typeface tf;
    ActivityMainBinding amb;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日  E  HH:mm", Locale.CHINA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tf = Typeface.createFromAsset(getAssets(), "fonts/simsun.ttc");
        amb = DataBindingUtil.setContentView(this, R.layout.activity_main);
        amb.setTf(tf);
        getinfo();
        initTime();
        LiveDataBus.get().with("live").postValue(new Live());
    }

    Timer timer, timer1;
    int index = 0;
    final Request live = new Request.Builder().url("http://61.153.246.242:8888/qxdata/QxService.svc/getnewzdzhourdata/K3062").build();
    final Request wea = new Request.Builder().url("http://61.153.246.242:8888/qxdata/QxService.svc/getdayybdata/58746").build();
    OkHttpClient client = new OkHttpClient();
    private void initTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LiveDataBus.get().with("time").postValue(sdf.format(new Date()));
            }
        }, 0, 60* 1000);
        timer1 =new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                client.newCall(live).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                       /* body = body.replace("℃","").replace("%","").replace("mm","").
                                replace("m/s","").replace("hPa","");*/
                        if (TextUtils.isEmpty(body) || body.length() < 5) return;
                        Gson gson = new Gson();
                        Live lives = gson.fromJson(body, Live.class);
                       LiveDataBus.get().with("live").postValue(lives);
                    }
                });
                client.newCall(wea).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                        if (TextUtils.isEmpty(body) || body.length() < 5) return;
                        Gson gson = new Gson();
                        Wea lives = gson.fromJson(body, Wea.class);
                       LiveDataBus.get().with("wea").postValue(lives);
                    }

                });
            }
        },0,15*60*1000);
    }

    private void getinfo() {
        LiveDataBus.get().with("time", String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                amb.setTime(s);

            }
        });
      LiveDataBus.get().with("live",Live.class).observe(this, new Observer<Live>() {
          @Override
          public void onChanged(Live live) {
              live.dealInfo();
              amb.setLive(live);
          }
      });
      LiveDataBus.get().with("wea",Wea.class).observe(this, new Observer<Wea>() {
          @Override
          public void onChanged(Wea wea) {
              amb.setWea(wea);
          }
      });
    }

}