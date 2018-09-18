package com.example.fangxy.latteec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.fangxy.latte_core.delegates.LatteDelegate;
import com.example.fangxy.latte_core.ui.launcher.LauncherHolderCreator;
import com.example.fangxy.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.fangxy.latte_core.util.storage.LattePreferences;
import com.example.fangxy.latteec.R;

import java.util.ArrayList;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>(20);

    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }


    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果點擊的是最後一個
        if (position == INTEGERS.size() -1){
            LattePreferences.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //檢查用戶是否已經登錄
            //TODO

            //
        }

    }
}
