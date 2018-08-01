package com.example.fangxy.latte_core.net.callback;

import android.os.Handler;

import com.example.fangxy.latte_core.ui.LatteLoader;
import com.example.fangxy.latte_core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String>{  //回掉类 处理一系列完成后的业务
    private final IRequrse REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler handler = new Handler(); //为了测试设置的延时


    public RequestCallBacks(IRequrse requrse, ISuccess success, IFailure failure, IError error, LoaderStyle loader_style) {
        this.REQUEST = requrse;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        LOADER_STYLE = loader_style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()) {
                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }

       stopLoading();
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        if (LOADER_STYLE!=null){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }

}
