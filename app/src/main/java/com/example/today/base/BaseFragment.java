package com.example.today.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.today.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends LifeCircleMvpFragment {

    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = null;
        //当前的class获取这个注解，然后拿到这个注解
        Viewinject annotation = this.getClass().getAnnotation(Viewinject.class);
        if (annotation!=null){
            int mainlayoutid = annotation.mainlayoutid();
            if (mainlayoutid > 0){//说明我们给他赋值了,然后我们把这个id放进去
                mView = initFragmentView(inflater,mainlayoutid);
                bindView(mView);
                afterBindView();
            }else{
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("annotation = null");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private View initFragmentView(LayoutInflater inflater,int mainlayoutid) {
        //通过View的id创建View的对象,
        //activity有一个封装好的方法sentContentView，而fragment
        //需要把id生成一个View，传递给onCreatView
        return inflater.inflate(mainlayoutid,null);

    }

    //模板方法设计模式
    public abstract void afterBindView();

    //View的依赖注入绑定
    private void bindView(View mView) {
        ButterKnife.bind(this,mView);
    }
}
