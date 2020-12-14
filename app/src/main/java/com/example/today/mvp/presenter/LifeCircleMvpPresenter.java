package com.example.today;

import java.lang.ref.WeakReference;

public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle{

    protected WeakReference weakReference;
    protected LifeCircleMvpPresenter(){
        super();
    }
    public LifeCircleMvpPresenter(IMvpView iMvpView){
        super();
        attachView(iMvpView);
    }
//为了确保传递过来的参数，被我们的p层进行数据同步
    @Override
    public void attachView(IMvpView iMvpView) {
        if(weakReference == null){
            weakReference = new WeakReference(iMvpView);
        }else{
            T view = (T) weakReference.get();//如果弱引用存在，我们就把它获取出来
            if (view !=iMvpView){//如果跟当前传递过来的对象不一致，我们就重新创建一次
                weakReference =new WeakReference(iMvpView);
            }
        }
    }

}
