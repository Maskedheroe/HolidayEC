package com.example.fangxy.holidayec;

import android.app.Application;

import android.os.Bundle;

import com.example.fangxy.latte_core.app.Latte;
import com.example.fangxy.latteec.icon.FontEcMoudle;
import com.joanzapata.iconify.fonts.FontAwesomeModule;  //imp会导致此失效

public class ExampleApp extends Application {

    @Override
    public void onCreate() {  //配置app
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcMoudle())
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
