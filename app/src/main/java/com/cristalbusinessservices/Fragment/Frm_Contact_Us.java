package com.cristalbusinessservices.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.BaseActivity;
import com.cristalbusinessservices.R;
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

public class Frm_Contact_Us extends BaseActivity {
    int height, wwidth;
    @BindView(R.id.edt_contact_us)
    AppCompatEditText edt_contact_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_contac_us);
        ButterKnife.bind(this);
        try {
            getWidthHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLayoutView(View view, int width, int height) {
        if (view!=null) {
            view.getLayoutParams().width = width;
            view.getLayoutParams().height = height;
        }
    }

    @OnClick({R.id.bt_send, R.id.img_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_send:
                if (edt_contact_us.getText().toString().length()>0){
                    showLoading();
                    APISendMessage();
                }
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void APISendMessage(){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.sendMessageContact(
                    "bearer " + QTSHelp.getToken(Frm_Contact_Us.this),
                    edt_contact_us.getText().toString()
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        hideLoading();
                        JSONObject inputJson = null;
                        if (response.isSuccessful() && response.code() == 200) {
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            if (inputJson.optBoolean("isSuccess")){
                                edt_contact_us.setText("");
                                QTSHelp.showToast2(Frm_Contact_Us.this, inputJson.optString("notification"));
                            }else {
                                QTSHelp.showToast2(Frm_Contact_Us.this, inputJson.optString("notification"));
                            }
                        } else {

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
        }catch (Exception e){
            hideLoading();
            e.printStackTrace();
        }
    }

    private void getWidthHeight() {
        try {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            Frm_Contact_Us.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            wwidth = displaymetrics.widthPixels;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}