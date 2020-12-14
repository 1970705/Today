package com.example.today.splash;

import com.example.today.mvp.ILifeCircle;
import com.example.today.mvp.IMvpView;
import com.example.today.mvp.MvpControler;

public interface ISplashActivityContract {
    interface Iview extends IMvpView {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

    Iview emptyView = new Iview() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
