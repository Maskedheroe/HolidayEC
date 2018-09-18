package com.example.fangxy.latte_core.app;

import com.example.fangxy.latte_core.util.storage.LattePreferences;

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    public static void setSignState(boolean state) {
        LattePreferences.setAppFlag(SignTag.SIGN_TAG.name(), state);

    }

    private static boolean isSignIn(){
        return LattePreferences.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNoteSignIn();
        }
    }

}