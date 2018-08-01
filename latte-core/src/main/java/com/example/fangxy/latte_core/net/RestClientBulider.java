package com.example.fangxy.latte_core.net;

import android.content.Context;

import com.example.fangxy.latte_core.net.callback.IError;
import com.example.fangxy.latte_core.net.callback.IFailure;
import com.example.fangxy.latte_core.net.callback.IRequrse;
import com.example.fangxy.latte_core.net.callback.ISuccess;
import com.example.fangxy.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBulider {

    private  String mUrl;
    private static WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private  IRequrse mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailue;
    private  IError mIError;
//    private  String mDownLoad;
    private  String mExtension;
    private  String mName;
    private  RequestBody mBody;
    private  LoaderStyle mLoaderStyle;
    private  Context mContext;
    private  File mFile;

    public final RestClientBulider url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBulider params(WeakHashMap<String,Object> params){  //强制限定传入WeakHashMap
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBulider params(String key,Object value ){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RestClientBulider file(File file){
//        PARAMS.put(key,value);
        this.mFile = file;
        return this;
    }
    public final RestClientBulider file(String filePath){
//        PARAMS.put(key,value);
        this.mFile = new File(filePath);
        return this;
    }

    public final RestClientBulider extension(String extension){
        this.mExtension = extension;
        return this;
    }
  /*  public final RestClientBulider downloadDir(String dir){
        this.mDownLoad = dir;
        return this;
    }*/

    public final RestClientBulider name(String name){
        this.mName = name;
        return this;
    }

    public final RestClientBulider raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBulider onRequest(IRequrse iRequrse){  //回调处理
        this.mIRequest = iRequrse;
        return this;
    }

    public final RestClientBulider success(ISuccess iSuccess){    //回调处理
        this.mISuccess = iSuccess;
        return this;
    }
    public final RestClientBulider failure(IFailure iFailure){    //回调处理
        this.mIFailue = iFailure;
        return this;
    }
    public final RestClientBulider error(IError iError){          //回调处理
        this.mIError = iError;
        return this;
    }



    public final RestClientBulider loader(Context context, LoaderStyle style){ //加载Loading
        this.mContext =context;
        this.mLoaderStyle =style;
        return this;
    }

    public final RestClientBulider loader(Context context ){ //加载Loading  默认的
        this.mContext =context;
        this.mLoaderStyle =LoaderStyle.BallPulseIndicator;
        return this;
    }

    public final RestClient build(){  //完成建造 build();!!!
        return new RestClient(mUrl, PARAMS,mIRequest,mISuccess,mIFailue,mIError, mFile, mBody,mLoaderStyle,mContext);
    }

}
