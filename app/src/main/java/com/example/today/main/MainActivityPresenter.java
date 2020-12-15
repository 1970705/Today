package com.example.today.main;


import androidx.fragment.app.Fragment;

import com.example.today.R;
import com.example.today.main.beijing.BeiJingFragment;
import com.example.today.main.hangzhou.HangZhouFragment;
import com.example.today.main.shanghai.ShangHaiFragment;
import com.example.today.main.shenzhen.ShenZhenFragment;
import com.example.today.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter{
    //当前fragment的脚标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mButtomPosition;


    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedId() {
        return mCurrentCheckedId;
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getButtomPosition() {
        return mButtomPosition;
    }

    @Override
    //切换fragment的方法
    public void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i <mFragments.length ; i++) {
            if (mCurrentFragmentIndex != i){
                if (mFragments[i] !=null){
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null){
            addAndShowFragment(mFragment);
            setCueChecked(mCurrentFragmentIndex);
        }else{
            newCurrentFragment(mCurrentFragmentIndex);
            setCueChecked(mCurrentFragmentIndex);
        }
    }

    //记录当前脚标
    private void setCueChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mTopPosition = 0;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mTopPosition = 1;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_beijing;
                mButtomPosition = 2;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_shenzhen;
                mButtomPosition = 3;
                break;
        }
    }

    //创建当前Fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment  = new ShangHaiFragment();
                break;
            case 1:
                fragment  = new HangZhouFragment();
                break;
            case 2:
                fragment  = new BeiJingFragment();
                break;
            case 3:
                fragment  = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    //显示Fragment
    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()){
            getView().showFragment(mFragment);
        }else {
            getView().addFragment(mFragment);
        }
    }

    //隐藏Fragment
    private void hideFragment(Fragment mFragment) {
        if (mFragment !=null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }

}
