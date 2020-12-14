package com.example.today.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.today.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //当前的class获取这个注解，然后拿到这个注解
        Viewinject annotation = this.getClass().getAnnotation(Viewinject.class);
        if (annotation!=null){
            int mainlayoutid = annotation.mainlayoutid();
            if (mainlayoutid > 0){//说明我们给他赋值了,然后我们把这个id放进去
                setContentView(mainlayoutid);
                bindView();
                afterBindView();
            }else{
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("annotation = null");
        }
    }
//模板方法设计模式
    public abstract void afterBindView();

    //View的依赖注入绑定
    private void bindView() {
        ButterKnife.bind(this);
    }
}
