package com.example.fangxy.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.fangxy.latte_core.R;
import com.example.fangxy.latte_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class LatteLoader {

    private static  final int LOADER_SIZE_SCALE = 8;

    private static  final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>(20);

    //默认Loading
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotateIndicator.name();

    public static void showLoading(Context context,String type){
        //初始化一个Dialog  和 values里的styles配合初始化

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        //控制宽高
        int devicewidth  =DimenUtil.getScreenWidth();

        int deviceheight = DimenUtil.getScreenHegiht();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width  = devicewidth/ LOADER_OFFSET_SCALE;
            lp.height = deviceheight/ LOADER_OFFSET_SCALE;
            //设置距离屏幕的偏移量
            lp.height = lp.height + deviceheight/LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;

        }

        LOADERS.add(dialog);

        dialog.show();

    }

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();
                    dialog.dismiss();
                }
            }
        }
    }

}
