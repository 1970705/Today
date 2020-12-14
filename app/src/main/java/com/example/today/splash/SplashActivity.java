package com.example.today.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.today.base.BaseActivity;
import com.example.today.main.MainActivity;
import com.example.today.R;
import com.example.today.base.Viewinject;

import java.io.File;

import butterknife.BindView;

@Viewinject(mainlayoutid = R.layout.activity_splash)

public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview {
    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;

    private ISplashActivityContract.IPresenter timerPresenter;

    @Override
    public void afterBindView() {
        initTimerPresenter();
        initListener();
        intiVideo();
        //把初始化Timer及相关内容抽出到Presenter层中-------MVP---------P
        //initTimer();
    }

    private void initTimerPresenter() {
        //在V层创建一个P层强引用的对象
        timerPresenter = new SplashTimerPresenter(this);
        //activity持有p 层的强引用，然后把当前的activity传到了p层的构造方法中，
        // SplashTimerPresenter中也有一个成员变量寄住着activity，所以p层也是持有着activity的强引用的，
        // 如果activity的生命周期和p层的生命周期如果不一致的话，就相当于，
        // 长生命周期的对象持有activity短生命周期对象的强引用，
        // 如果说activity走到了onDestroy，但是p层有一些耗时任务，或者是异步任务的话，它是一个长生命周期的对象，
        // 此时正持有activity的对象，虽然说activity已经走到了onDestroy，但是p层的对象不会再堆内存中gc掉，就造成了内存泄漏

        timerPresenter.initTimer();
    }


    private void intiVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://"
                + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initListener() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }


    @Override
    public void setTvTimer(String s) {
        mTvTimer.setText(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
