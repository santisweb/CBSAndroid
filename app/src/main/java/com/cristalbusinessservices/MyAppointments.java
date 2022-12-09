package com.cristalbusinessservices;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cristalbusinessservices.Adapter.AdapterMyAppointments;
import com.cristalbusinessservices.Model.Appointments.APIAppointments;
import com.cristalbusinessservices.Model.Appointments.ResultAppointments;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAppointments extends BaseActivity {
    int height, wwidth;
    AdapterMyAppointments activity_my_appointments;
    ArrayList<ResultAppointments> arrData = new ArrayList<>();
    boolean loadmore = false;
    int pageNumber = 1;
    TextView tvNoData;
    @BindView(R.id.pro_bar_loading)
    ProgressBar pro_bar_loading;
    @BindView(R.id.img)
    AppCompatImageView img;
    @BindView(R.id.rcv_my_appointments)
    RecyclerView rcv_my_appointments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        tvNoData = findViewById(R.id.tv_no_data);
        ButterKnife.bind(this);
        try {
            getWidthHeight();
            setLayoutView(img, wwidth / 3, wwidth / 3);
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            activity_my_appointments = new AdapterMyAppointments(MyAppointments.this, arrData);
            rcv_my_appointments.setLayoutManager(new LinearLayoutManager(MyAppointments.this));
            rcv_my_appointments.setAdapter(activity_my_appointments);
            showLoading();
            APITaxYears("v1/appointments/list/"+getIntent().getIntExtra("contactID",0));
            LinearLayoutManager layoutManager = ((LinearLayoutManager)rcv_my_appointments.getLayoutManager());
            rcv_my_appointments.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    // super.onScrolled(recyclerView, dx, dy);
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition == recyclerView.getChildCount()) {
                        if (loadmore) {
                            loadmore = false;
                            pro_bar_loading.setVisibility(View.VISIBLE);
                            loadmoreAppointments("v1/appointments/list/"+getIntent().getIntExtra("contactID",0));
                        }
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.img_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
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
            Objects.requireNonNull(MyAppointments.this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            wwidth = displaymetrics.widthPixels;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadmoreAppointments(String url) {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getAppointments(
                    "bearer " + QTSHelp.getToken(MyAppointments.this),
                    url,
                    pageNumber,
                    20
            ).enqueue(new Callback<APIAppointments>() {
                @Override
                public void onResponse(Call<APIAppointments> call, Response<APIAppointments> response) {
                    pro_bar_loading.setVisibility(View.GONE);
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            arrData.addAll(response.body().getResult());
                            activity_my_appointments.notifyDataSetChanged();

                            if (response.body().getResult().size() == 20){
                                pageNumber = pageNumber + 1;
                                loadmore = true;
                            }
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());

                            if (inputJson.optString("message").contains("Authorization has been denied for this request")){
                                QTSHelp.setToken(MyAppointments.this, "Token");
                                startActivity(new Intent(MyAppointments.this, Login.class));
                                finish();
                                QTSHelp.showToast2(MyAppointments.this, "Authorization has been denied for this request");
                            }
                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIAppointments> call, Throwable t) {
                    try {
                        pro_bar_loading.setVisibility(View.GONE);
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            pro_bar_loading.setVisibility(View.GONE);
            hideLoading();
            e.printStackTrace();
        }
    }

    private void APITaxYears(String url){
        try {
            pageNumber = 1;
            arrData.clear();
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getAppointments(
                    "bearer " + QTSHelp.getToken(MyAppointments.this),
                    url,
                    pageNumber,
                    20
            ).enqueue(new Callback<APIAppointments>() {
                @Override
                public void onResponse(Call<APIAppointments> call, Response<APIAppointments> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            arrData.addAll(response.body().getResult());
                            activity_my_appointments.notifyDataSetChanged();
                            Log.d("TAG", "onResponse: "+activity_my_appointments.getItemCount());
                            if (activity_my_appointments.getItemCount() > 0){
                                tvNoData.setVisibility(View.GONE);
                            }else{
                                tvNoData.setVisibility(View.VISIBLE);
                            }
                            if (response.body().getResult().size() == 20){
                                pageNumber = pageNumber + 1;
                                loadmore = true;
                            }
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());

                            if (inputJson.optString("message").contains("Authorization has been denied for this request")){
                                QTSHelp.setToken(MyAppointments.this, "Token");
                                startActivity(new Intent(MyAppointments.this, Login.class));
                                finish();
                                QTSHelp.showToast2(MyAppointments.this, "Authorization has been denied for this request");
                            }
                        }
                    } catch (Exception e) {
                        tvNoData.setVisibility(View.VISIBLE);
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIAppointments> call, Throwable t) {
                    try {
                        tvNoData.setVisibility(View.VISIBLE);
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            tvNoData.setVisibility(View.VISIBLE);
            hideLoading();
            e.printStackTrace();
        }
    }

}
