package com.cristalbusinessservices;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.Model.User_Info.APIUserInfo;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity {
    @BindView(R.id.edt_password)
    AppCompatEditText edt_password;
    @BindView(R.id.edt_email)
    AppCompatEditText edt_email;
    @BindView(R.id.bt_login)
    AppCompatButton bt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        edt_password.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (checkValid()) {
                    showLoading();
                    APILogin();
                }
            }
            return true;
        });
    }

    @OnClick({R.id.tv_forgot, R.id.img_back, R.id.ln_register, R.id.bt_login})
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.bt_login:
                    if (checkValid()) {
                        QTSHelp.hideKeyboard(Login.this);
                        showLoading();
                        APILogin();
                    }
                    break;
                case R.id.tv_forgot:
                    startActivity(new Intent(Login.this, Forgot.class));
                    break;
                case R.id.img_back:
                    finish();
                    break;
                case R.id.ln_register:
                    startActivityForResult(new Intent(Login.this, Register.class),10);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10){
            try {
                if (data!=null){
                    edt_email.setText(data.getStringExtra("email"));
                    edt_password.setText(data.getStringExtra("password"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private boolean checkValid() {
        try {
            if (edt_email.getText().toString().length() > 0) {
                if (QTSHelp.isEmailValid(edt_email.getText().toString())) {
                    if (edt_password.getText().toString().length() > 0) {
                        return true;
                    }else {
                        QTSHelp.showToast2(Login.this, getString(R.string.pw_required));
                        return false;
                    }
                } else {
                    QTSHelp.showToast2(Login.this, getString(R.string.email_invalid));
                    return false;
                }
            } else {
                QTSHelp.showToast2(Login.this, getString(R.string.email_required));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void APILogin() {
        ApiService mAPIService = APIUtils.getAPIService();
        mAPIService.login(
                edt_email.getText().toString(),
                edt_password.getText().toString(),
                "password"
        ).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject inputJson = null;
                    if (response.isSuccessful() && response.code() == 200) {
                        inputJson = new JSONObject(String.valueOf(response.body()));
                        if (inputJson.optString("access_token").length()>0) {
                            QTSHelp.setToken(Login.this, inputJson.optString("access_token"));
                            APIUtils.tokenPublic = "bearer "+inputJson.optString("access_token");
                            userInfo();
                        }else {
                            if (inputJson.optString("error_description").length() > 0) {
                                QTSHelp.showToast2(Login.this, inputJson.optString("error_description"));
                            }
                        }
                    } else {
                        hideLoading();
                        inputJson = new JSONObject(response.errorBody().string());
                        if (inputJson.optString("error_description").length() > 0) {
                            QTSHelp.showToast2(Login.this, inputJson.optString("error_description"));
                        }
                    }
                } catch (Exception e) {
                    hideLoading();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                try {
                    hideLoading();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void userInfo(){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.userInfo(
                    "bearer " + QTSHelp.getToken(Login.this)
            ).enqueue(new Callback<APIUserInfo>() {
                @Override
                public void onResponse(Call<APIUserInfo> call, Response<APIUserInfo> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            if (response.body().getIsSuccess()){
                                dataUser = response.body();
                            }else {
                                if (response.body().getNotification()!=null && response.body().getNotification().toString().length() > 0) {
                                    QTSHelp.showToast2(Login.this, response.body().getNotification().toString());
                                }
                            }
                            startActivity(new Intent(Login.this, Home.class)
                                    .putExtra("objectUser", response.body())
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Login.this, inputJson.optString("notification"));
                            }
                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIUserInfo> call, Throwable t) {
                    try {
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            hideLoading();
            e.printStackTrace();
        }
    }
}
