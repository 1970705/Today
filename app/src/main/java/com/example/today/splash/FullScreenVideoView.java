package com.example.today.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class FullScreenVideoView extends VideoView {

    //主用于直接new出来的对象-----直接在Activity里new出来
    public FullScreenVideoView(Context context) {
        super(context);
    }
    //主要用于xml文件中构造出来的，支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //主要用于xml文件中，支持自定义属性，同时支持style样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec 回调回来包含两个主要内容  1.测量模式，通过getDefaultSize获得原始的宽和高度
        // 2.测量大小：原始设置的VideoView的大小，通过getDefaultSize得到，然后用setMeasuredDimension设置进去
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width,height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
