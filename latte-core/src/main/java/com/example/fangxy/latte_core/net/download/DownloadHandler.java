package com.example.fangxy.latte_core.net.download;

import android.os.AsyncTask;

import com.example.fangxy.latte_core.net.RestCreator;
import com.example.fangxy.latte_core.net.callback.IError;
import com.example.fangxy.latte_core.net.callback.IFailure;
import com.example.fangxy.latte_core.net.callback.IRequrse;
import com.example.fangxy.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {


    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequrse REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public DownloadHandler(String URL, IRequrse REQUEST, ISuccess SUCCESS,
                           IFailure FAILURE, IError ERROR, String DOWNLOAD_DIR,
                           String EXTENSION, String NAME) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public final void handleDownload(){
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    final ResponseBody responseBody = response.body();

                    final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);
                    //注意判断， 否则下载不全
                    if (task.isCancelled()){
                        if (REQUEST!=null){
                            //判断完成后调用结束的回掉
                            REQUEST.onRequestEnd();
                        }
                    }
                }else {
                    if (ERROR!=null){
                        ERROR.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                if (FAILURE!=null){
                    FAILURE.onFailure();
                }

            }
        });

    }

}
