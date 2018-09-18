package com.example.fangxy.holidayec;

import android.app.Application;

import android.os.Bundle;

import com.example.fangxy.latte_core.app.Latte;
import com.example.fangxy.latte_core.net.interceptors.DebugInterceptor;
import com.example.fangxy.latteec.database.DataBaseManager;
import com.example.fangxy.latteec.icon.FontEcMoudle;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;  //imp会导致此失效

public class ExampleApp extends Application {

    @Override
    public void onCreate() {  //配置app
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcMoudle())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        initStetho();

        DataBaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
