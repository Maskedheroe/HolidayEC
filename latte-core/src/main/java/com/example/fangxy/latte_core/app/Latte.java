package com.example.fangxy.latte_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Latte {

    public static Configurator init(Context context){   //只需要Context
        getConfigurations().put(ConfigType.APPLICATION_COINTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();  //获取配置信息
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_COINTEXT.name());
    }

}
