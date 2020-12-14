package com.example.today.main;

import com.example.today.mvp.ILifeCircle;
import com.example.today.mvp.IMvpView;
import com.example.today.mvp.MvpControler;

public interface IMainActivityContract {
    interface Iview extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();
    }

    Iview emptyView = new Iview() {

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
