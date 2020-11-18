package com.example.today;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today.FullScreenVideoView;
import com.example.today.MainActivity;
import com.example.today.R;

import java.io.File;

public class SplashActivity extends AppCompatActivity {
    private FullScreenVideoView mVideoView;
    private TextView mTvTimer;
    private com.example.today.CustomCountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVideoView=(FullScreenVideoView) findViewById(R.id.vv_play);
        mTvTimer=(TextView) findViewById(R.id.tv_splash_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
        mVideoView.setVideoURI(Uri.parse("android.resource://"
                + getPackageName()+ File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        timer = new com.example.today.CustomCountDownTimer(5, new com.example.today.CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time+"秒");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }
    //界面销毁时，系统会拿到这个方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
