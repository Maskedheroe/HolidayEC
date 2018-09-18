package com.example.fangxy.latte_core.net;

import android.content.Context;

import com.example.fangxy.latte_core.net.callback.IError;
import com.example.fangxy.latte_core.net.callback.IFailure;
import com.example.fangxy.latte_core.net.callback.IRequrse;
import com.example.fangxy.latte_core.net.callback.ISuccess;
import com.example.fangxy.latte_core.net.callback.RequestCallBacks;
import com.example.fangxy.latte_core.net.download.DownloadHandler;
import com.example.fangxy.latte_core.ui.LatteLoader;
import com.example.fangxy.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {    //该类大致属于分配任务, 自身也处理一些任务

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequrse REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final File FILE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;  //加载指示器
    private final Context CONTEXT;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequrse request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      String download_dir,
                      String extension,
                      String name,
                      File file,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
        this.FILE = file;
        params.putAll(params);
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClientBulider bulider(){
        return new RestClientBulider();
    }


    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE!=null){
            //初始化加载指示器
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAM:
                call = service.putRaw(URL,BODY);
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                //上传逻辑
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
                break;
            case POST_RAM:
                call = service.postRaw(URL,BODY);
            default:
                break;
        }

        if (call!=null){
            call.enqueue(getRequestCallback());
        }

    }


    private Callback<String> getRequestCallback(){
        return new RequestCallBacks(
                REQUEST,SUCCESS,FAILURE,ERROR,
                LOADER_STYLE);
    }


    //开放给外部的接口
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        if (BODY == null){
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAM);
        }
    }
    public final void put(){
        if (BODY == null){
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAM);
        }    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownloadHandler(URL,REQUEST,SUCCESS,
                FAILURE,ERROR,DOWNLOAD_DIR,
                EXTENSION,NAME)
                .handleDownload();
    }

}
