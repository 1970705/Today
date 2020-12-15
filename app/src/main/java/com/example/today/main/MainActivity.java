package com.example.today.main;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.today.R;
import com.example.today.base.BaseActivity;
import com.example.today.base.Viewinject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;


@Viewinject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview{

    //
    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

//    FloatingActionButton actionButton;
    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rg_main_top)
    RadioGroup rgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;

    private boolean isChangeTopOrBottom;


    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnima(rgMainBottom,rgMainTop);
        initClick();
    }
    //初始化Fragment
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    public void
    initClick() {
        facMainHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClick(v);
            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fac_main_home:
            isChangeTopOrBottom = !isChangeTopOrBottom;
            if (isChangeTopOrBottom){
                changeAnima(rgMainTop,rgMainBottom);
            }else {
                changeAnima(rgMainBottom,rgMainTop);
            }
            break;
        }
    }

    private void changeAnima(RadioGroup gone,RadioGroup show) {
        //消失的动画
        gone.clearAnimation();//清除动画
        Animation animationGone = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_hide );
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);//动画结束后把控件隐藏掉

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_show );
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,mFragment).commit();
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
