package com.example.fangxy.latte_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.fangxy.latte_core.app.Latte;

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHegiht(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
