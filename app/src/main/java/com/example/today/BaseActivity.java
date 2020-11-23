package com.example.today;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //当前的class获取这个注解，然后拿到这个注解
        Viewinject annotation = this.getClass().getAnnotation(Viewinject.class);
        if (annotation!=null){
            int mainlayoutid = annotation.mainlayoutid();
            if (mainlayoutid > 0){//说明我们给他赋值了,然后我们把这个id放进去
                setContentView(mainlayoutid);
                ButterKnife.bind(this);
            }else{
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("annotation = null");
        }
    }
}
