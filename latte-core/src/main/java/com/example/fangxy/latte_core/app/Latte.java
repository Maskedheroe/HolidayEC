package com.example.fangxy.latte_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Latte {

    public static Configurator init(Context context){   //只需要Context
        getConfigurations().put(ConfigKeys.APPLICATION_COINTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();  //获取配置信息
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_COINTEXT);
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

}
