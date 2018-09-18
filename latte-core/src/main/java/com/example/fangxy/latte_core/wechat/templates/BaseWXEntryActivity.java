package com.example.fangxy.latte_core.wechat.templates;

import com.example.fangxy.latte_core.wechat.BaseWXActivity;

public abstract class BaseWXEntryActivity extends BaseWXActivity{

    protected abstract void onSignInSuccess(String userInfo);

}
