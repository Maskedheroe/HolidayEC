package com.example.fangxy.latte_core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.fangxy.latte_core.R;
import com.example.fangxy.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();  //子类必须重写改方法

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
        //TODO 在此初始化视图
    }

    private void initContainer(@Nullable Bundle savedInstaceState){
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);

        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstaceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());   //原型模式
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
    }
}
