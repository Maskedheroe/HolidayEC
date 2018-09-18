package com.example.fangxy.latteec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fangxy.latte_core.delegates.LatteDelegate;
import com.example.fangxy.latte_core.net.RestClient;
import com.example.fangxy.latte_core.net.callback.ISuccess;
import com.example.fangxy.latte_core.util.log.LatteLogger;
import com.example.fangxy.latteec.R;
import com.example.fangxy.latteec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignInDelegate extends LatteDelegate{
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView iconSignInWechat;
    Unbinder unbinder;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    private boolean checkForm() {
        final String email = editSignInEmail.getText().toString();
        final String password = editSignInPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("錯誤的郵箱格式");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }


        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("至少六位密碼");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R2.id.tv_link_sign_up})
    public void linkToSignUp(){
        start(new SignUpDelegate());
    }

    @OnClick({R2.id.icon_sign_in_wechat})
    public void weChatSignIn(){

    }

    @OnClick({R2.id.btn_sign_in})
    public void signIn(){
        if (checkForm()) {
            RestClient.bulider()
                    .url("sign_up")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();
        }
    }

   /* @OnClick({R2.id.btn_sign_in, R2.id.icon_sign_in_wechat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R2.id.btn_sign_in:
                if (checkForm()) {
                    RestClient.bulider()
                            .url("sign_up")
                            .params("", "")
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    LatteLogger.json("USER_PROFILE", response);
                                    SignHandler.onSignIn(response,mISignListener);
                                }
                            })
                            .build()
                            .post();
                    Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R2.id.icon_sign_in_wechat:
                break;
            case R2.id.tv_link_sign_up:
                start(new SignUpDelegate());
                break;
            default:
                break;
        }
    }*/
}
