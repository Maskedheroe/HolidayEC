package com.example.fangxy.latte_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.fangxy.latte_core.app.Latte;
import com.example.fangxy.latte_core.net.callback.IRequrse;
import com.example.fangxy.latte_core.net.callback.ISuccess;
import com.example.fangxy.latte_core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequrse REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequrse request, ISuccess success) {
        REQUEST = request;
        SUCCESS = success;
    }


    @Override
    protected File doInBackground(Object... objects) {

        String downLoadDir = (String) objects[0];
        String extension = (String)objects[1];
        final ResponseBody body = (ResponseBody)objects[2];
        final String name = (String) objects[3];
        final InputStream is = body.byteStream();

        if (downLoadDir == null||downLoadDir.equals("")){
            downLoadDir = "down_loads";
        }
        if (extension == null|| extension.equals("")){
            extension = "";
        }
        if (name == null){
            return FileUtil.writeToDisk(is,downLoadDir,extension.toUpperCase());
        }else {
            return FileUtil.writeToDisk(is,downLoadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file){  //自动安装apk 自动更新时调用
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //开启一个栈
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplication().startActivity(install);
        }
    }

}
