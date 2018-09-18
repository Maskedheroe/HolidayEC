package com.example.fangxy.holidayec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fangxy.latte_core.delegates.LatteDelegate;
import com.example.fangxy.latte_core.net.RestClient;
import com.example.fangxy.latte_core.net.callback.IError;
import com.example.fangxy.latte_core.net.callback.IFailure;
import com.example.fangxy.latte_core.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {



    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRestClient();
            }
        });
    }

    private void testRestClient() {
        RestClient.bulider()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "失敗了！", Toast.LENGTH_SHORT).show();

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "錯誤了", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }


}
