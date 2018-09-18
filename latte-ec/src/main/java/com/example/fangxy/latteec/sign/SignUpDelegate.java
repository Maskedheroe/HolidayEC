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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;
    Unbinder unbinder;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
             mISignListener = (ISignListener)activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
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


    private boolean checkForm() {
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String rePassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            editSignUpName.setError("請輸入姓名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("錯誤的郵箱格式");
            isPass = false;
        } else {
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手機號碼錯誤");
            isPass = false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword.setError("至少六位密碼");
            isPass = false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            editSignUpRePassword.setError("密碼驗證錯誤");
            isPass = false;
        } else {
            editSignUpRePassword.setError(null);
        }


        return isPass;
    }

    @OnClick({R2.id.btn_sign_up})
    public void sigUp() {
        if (checkForm()) {
            RestClient.bulider()
                    .url("sign_up")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R2.id.tv_link_sign_in})
    public void linkToSignIn() {
        start(new SignInDelegate());
    }


}

