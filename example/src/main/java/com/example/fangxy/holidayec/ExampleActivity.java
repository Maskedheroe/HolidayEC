package com.example.fangxy.holidayec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fangxy.latte_core.activities.ProxyActivity;
import com.example.fangxy.latte_core.app.Latte;
import com.example.fangxy.latte_core.delegates.LatteDelegate;
import com.example.fangxy.latteec.launcher.LauncherDelegate;
import com.example.fangxy.latteec.launcher.LauncherScrollDelegate;
import com.example.fangxy.latteec.sign.ISignListener;
import com.example.fangxy.latteec.sign.SignInDelegate;
import com.example.fangxy.latteec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity implements ISignListener {


    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO  初始化控件
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);

    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }
}
