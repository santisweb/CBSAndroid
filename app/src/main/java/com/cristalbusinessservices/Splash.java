package com.cristalbusinessservices;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import com.cristalbusinessservices.Model.User_Info.APIUserInfo;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends BaseActivity {
    int height, wwidth;
    @BindView(R.id.img_logo)
    AppCompatImageView img_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWidthHeight();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (QTSHelp.getToken(Splash.this).equals("Token")) {
                        startActivity(new Intent(Splash.this, Intro.class));
                        finish();
                    }else {
                        userInfo();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 3000);
    }

    private void setLayoutView(View view, int width, int height) {
        if (view!=null) {
            view.getLayoutParams().width = width;
            view.getLayoutParams().height = height;
        }
    }

    private void getWidthHeight() {
        try {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            wwidth = displaymetrics.widthPixels;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void userInfo(){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.userInfo(
                    "bearer " + QTSHelp.getToken(Splash.this)
            ).enqueue(new Callback<APIUserInfo>() {
                @Override
                public void onResponse(Call<APIUserInfo> call, Response<APIUserInfo> response) {
                    try {
                        if (response.isSuccessful() && response.code() == 200) {
                            if (response.body().getIsSuccess()){
                                dataUser = response.body();
                                APIUtils.tokenPublic = "bearer "+QTSHelp.getToken(Splash.this);

                            }else {
                                if (response.body().getNotification()!=null && response.body().getNotification().toString().length() > 0) {
                                    QTSHelp.showToast2(Splash.this, response.body().getNotification().toString());
                                }
                            }
                            startActivity(new Intent(Splash.this, Home.class).putExtra("objectUser", response.body()));
                            finish();
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Splash.this, inputJson.optString("notification"));
                            }
                            if (inputJson.optString("message").contains("Authorization has been denied for this request")){
                                QTSHelp.setToken(Splash.this, "Token");
                                startActivity(new Intent(Splash.this, Login.class));
                                finish();
                                QTSHelp.showToast2(Splash.this, "Authorization has been denied for this request");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIUserInfo> call, Throwable t) {
                    userInfo();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
