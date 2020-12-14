package com.example.today.main;

import android.app.Fragment;

import com.example.today.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter{
    //当前fragment的脚标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];

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

    //切换fragment的方法
    private void replaceFragment(int mCurrentFragmentIndex) {
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
        }else{

        }
    }

    private void addAndShowFragment(Fragment mFragment) {

    }

    private void hideFragment(Fragment mFragment) {
    }
}
