package com.example.fangxy.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class PermissonCheckerDelegate extends BaseDelegate {
    @Override
    public abstract Object setLayout();

    @Override
    public abstract void onBindView(@Nullable Bundle saveInstanceState, View rootView);
}
