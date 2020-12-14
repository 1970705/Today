package com.example.today.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.today.mvp.base.BaseMvpPresenter;

//p层
public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter {


    private CustomCountDownTimer timer;

    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


    public void initTimer() {
        timer = new CustomCountDownTimer(1, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");

            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");

            }
        });
        timer.start();
    }

    public void cancel() {

        timer.cancel();
    }




    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }


    /*
    防止空指针
     */
    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
