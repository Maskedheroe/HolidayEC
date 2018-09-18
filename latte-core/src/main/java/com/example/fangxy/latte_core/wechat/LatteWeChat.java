package com.example.fangxy.latte_core.wechat;

import android.app.Activity;

import com.example.fangxy.latte_core.app.ConfigKeys;
import com.example.fangxy.latte_core.app.Latte;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LatteWeChat {

    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private static IWXAPI WXAPI;

    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }

    private LatteWeChat(){
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        //校验
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public IWXAPI getWXAPI(){
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);

    }

}




















