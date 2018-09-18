package com.example.fangxy.latteec.sign;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.fangxy.latte_core.app.AccountManager;
import com.example.fangxy.latteec.database.DataBaseManager;
import com.example.fangxy.latteec.database.UserProFile;

public class SignHandler {


    public static void onSignUp(String response,ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProFile profile = new UserProFile(userId, name, avatar, gender, address);
        DataBaseManager.getInstance().getDao().insert(profile);


        //已经注册成功了
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();

    }

    public static void onSignIn(String response, ISignListener mISignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProFile profile = new UserProFile(userId, name, avatar, gender, address);
        DataBaseManager.getInstance().getDao().insert(profile);


        //已经注册成功了
        AccountManager.setSignState(true);
        mISignListener.onSignUpSuccess();
    }
}
