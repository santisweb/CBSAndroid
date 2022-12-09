package com.cristalbusinessservices;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;

import com.google.gson.JsonObject;
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

public class Forgot extends BaseActivity {
    @BindView(R.id.edt_email)
    AppCompatEditText edt_email;
    @BindView(R.id.bt_send_pass)
    AppCompatButton bt_send_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.bt_send_pass})
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.img_back:
                    finish();
                    break;
                case R.id.bt_send_pass:
                    try {
                        if (checkValid()){
                            showLoading();
                            APIForgotPassword();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void APIForgotPassword() {
        ApiService mAPIService = APIUtils.getAPIService();
        mAPIService.resetPassword(
                APIUtils.tokenPublic2,
                edt_email.getText().toString()
        ).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    hideLoading();
                    JSONObject inputJson = null;
                    if (response.isSuccessful() && response.code() == 200) {
                        inputJson = new JSONObject(String.valueOf(response.body()));
                        if (inputJson.optBoolean("isSuccess")) {
                            inputJson.optString("notification");
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Forgot.this, inputJson.optString("notification"));
                            }
                            finish();
                        }else {
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Forgot.this, inputJson.optString("notification"));
                            }
                        }
                    } else {
                        inputJson = new JSONObject(response.errorBody().string());
                        if (inputJson.optString("notification").length() > 0) {
                            QTSHelp.showToast2(Forgot.this, inputJson.optString("notification"));
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

    private boolean checkValid() {
        try {
            if (edt_email.getText().toString().length() > 0) {
                if (QTSHelp.isEmailValid(edt_email.getText().toString())) {
                    return true;
                } else {
                    QTSHelp.showToast2(Forgot.this, getString(R.string.email_invalid));
                    return false;
                }
            } else {
                QTSHelp.showToast2(Forgot.this, getString(R.string.email_required));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
