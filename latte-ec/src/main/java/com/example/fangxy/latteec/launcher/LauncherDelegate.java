package com.example.fangxy.latteec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.fangxy.latte_core.R2;
import com.example.fangxy.latte_core.delegates.LatteDelegate;
import com.example.fangxy.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.fangxy.latte_core.util.storage.LattePreferences;
import com.example.fangxy.latte_core.util.timer.BaseTimerTask;
import com.example.fangxy.latte_core.util.timer.ITimerListener;
import com.example.fangxy.latteec.R;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends LatteDelegate implements ITimerListener{


    private Timer mTimer = null;
    private int mCount = 5;

    @BindView(com.example.fangxy.latteec.R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(com.example.fangxy.latteec.R2.id.tv_launcher_timer)
    void onClickTimerView(){
        checkIsShowScroll();
    }

    private void initTimer(){  //初始化timer  开始倒计时
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);  //倒计时方法
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        initTimer();
    }

    //判斷是否展示啓動頁
    private void checkIsShowScroll(){
        if (!LattePreferences.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherDelegate(),SINGLETASK);  //頁面跳轉

        }else {
            //TODO 檢查是否登陸
        }
    }

    @Override
    public void onTimer() {   //啓動倒計時頁面
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳過\n{0}s",mCount));  //設置文字 以及換行的寫法
                    mCount--;
                    if (mCount<0){
                        if (mTvTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
