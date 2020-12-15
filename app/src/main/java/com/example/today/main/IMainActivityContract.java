package com.example.today.main;

import androidx.fragment.app.Fragment;

import com.example.today.mvp.ILifeCircle;
import com.example.today.mvp.IMvpView;
import com.example.today.mvp.MvpControler;

public interface IMainActivityContract {
    interface Iview extends IMvpView {

        void addFragment(Fragment mFragment);

        void showFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();
        int getCurrentCheckedId();
        void replaceFragment(int mCurrentFragmentIndex);
        int getCurrentCheckedIndex();
        int getTopPosition();
        int getButtomPosition();
    }

    Iview emptyView = new Iview() {

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
