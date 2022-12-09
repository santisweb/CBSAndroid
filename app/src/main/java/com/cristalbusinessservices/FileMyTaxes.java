package com.cristalbusinessservices;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.Model.Tax_Dropdowns.APITaxYears;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileMyTaxes extends BaseActivity {

    @BindView(R.id.img)
    AppCompatImageView img;
    @BindView(R.id.tv_type)
    AppCompatTextView tv_type;
    @BindView(R.id.tv_year)
    AppCompatTextView tv_year;
    @BindView(R.id.bt_cancel)
    AppCompatButton bt_cancel;
    @BindView(R.id.bt_save)
    AppCompatButton bt_save;

    String[] listType, listYear;
    APITaxYears data;
    int height, wwidth, posiType, posiYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_my_taxes);
        ButterKnife.bind(this);
        try {
            getWidthHeight();
            setLayoutView(img, wwidth / 3, wwidth / 3);
            showLoading();
            APITaxYears();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.img_back, R.id.tv_type, R.id.tv_year, R.id.bt_cancel, R.id.bt_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                finish();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_type:
                showDialogSelect(listType, "type");
                break;
            case R.id.tv_year:
                showDialogSelect(listYear, "year");
                break;
            case R.id.bt_save:
                showLoading();
                APIAddTaxYears();
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
            Objects.requireNonNull(FileMyTaxes.this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            wwidth = displaymetrics.widthPixels;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void APITaxYears(){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getTaxYears(
                    "bearer " + QTSHelp.getToken(FileMyTaxes.this)
            ).enqueue(new Callback<APITaxYears>() {
                @Override
                public void onResponse(Call<APITaxYears> call, Response<APITaxYears> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            data = response.body();
                            List<String> arrType = new ArrayList<>();
                            List<String> arrYear = new ArrayList<>();
                            for (int q=0; q<=response.body().getTaxTypes().size()-1; q++){
                                arrType.add(response.body().getTaxTypes().get(q).getDisplay());
                            }
                            for (int q=0; q<=response.body().getYearsDropdown().size()-1; q++){
                                arrYear.add(response.body().getYearsDropdown().get(q).getDisplay());
                            }
                            listType = arrType.toArray(new String[0]);
                            listYear = arrYear.toArray(new String[0]);
                            tv_type.setText(listType[0]);
                            tv_year.setText(listYear[0]);
                        } else {

                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APITaxYears> call, Throwable t) {
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

    private void showDialogSelect(String[] animals, String type){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(FileMyTaxes.this);
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (type.equals("type")) {
                        posiType = which;
                        tv_type.setText(animals[which]);
                    } else {
                        posiYear = which;
                        tv_year.setText(animals[which]);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void APIAddTaxYears(){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.addNewTax(
                    "bearer " + QTSHelp.getToken(FileMyTaxes.this),
                    data.getTaxTypes().get(posiType).getDisplay()+" for "+data.getYearsDropdown().get(posiYear).getDisplay(),
                    data.getYearsDropdown().get(posiYear).getValue(),
                    data.getTaxTypes().get(posiType).getValue()
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        hideLoading();
                        JSONObject inputJson = null;
                        if (response.isSuccessful() && response.code() == 200) {
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            if (inputJson.optBoolean("isSuccess")){
//                                QTSHelp.showToast2(FileMyTaxes.this, inputJson.optString("notification"));
                                setResult(12);
                                finish();
                            }else {
                                QTSHelp.showToast2(FileMyTaxes.this, inputJson.optString("notification"));
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
}
