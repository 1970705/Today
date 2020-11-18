package com.example.today;


import android.os.Handler;

import java.util.logging.LogRecord;

public class CustomCountDownTimer implements Runnable{
    private int time;
    private int countDowntime;
    private final ICountDownHandler countDownHandler;
    private final Handler handler;
    private boolean isRun;
    //时时去回调这个时候是什么时间  倒计时到几秒   用到观察者设计模式
    //支持动态传入总时间
    //每过一秒总秒数减一
    //总时间倒计时为零时要回调完成的状态

    public CustomCountDownTimer(int time,ICountDownHandler countDownHandler){
        handler = new Handler() ;

        this.time = time;
        this.countDowntime = time;
        this.countDownHandler=countDownHandler;


    }
    //实现的具体逻辑
    @Override
    public void run() {
        if (isRun) {
            if (countDownHandler !=null){
                countDownHandler.onTicker(countDowntime);
            }
            if(countDowntime==0){
                cancel();
                if (countDownHandler !=null){
                    countDownHandler.onFinish();
                }
            }else {
                countDowntime=time--;
                handler.postDelayed(this,500);
            }
        }
    }
    //开启run方法的开关
    public  void start(){
        isRun = true;
        handler.post(this);//this当前类继承Runnable接口的对象作为参数，继承Runnable接口重写了run方法，
        // 所以this对象被作为参数传进去，就自动调用了我们重写的run方法
    }
    //不想循环了------跳出循环的方法
    public void cancel(){
        isRun = false;
        handler.removeCallbacks(this);
    }
    //观察者回调接口（IOC  数据回调）
    public interface ICountDownHandler{
        //倒计时回调
        void onTicker(int time);
        //完成时回调
        void onFinish();
    }

}

