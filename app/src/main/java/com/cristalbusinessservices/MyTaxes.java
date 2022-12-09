package com.cristalbusinessservices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;

import com.cristalbusinessservices.Adapter.AdapterTaxes;
import com.cristalbusinessservices.Model.My_Taxes.APIMyTaxes;
import com.cristalbusinessservices.Model.My_Taxes.ResultMyTaxes;
import com.cristalbusinessservices.Model.UnPaid.APIUnPaid;
import com.cristalbusinessservices.Model.UnPaid.ResultUnPaid;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTaxes extends BaseActivity {
    AdapterTaxes adapterTaxes, adapterTaxesSearch;
    ArrayList<ResultMyTaxes> arrData = new ArrayList<>();
    ArrayList<ResultMyTaxes> arrDataSearch = new ArrayList<>();

    List<ResultUnPaid> listUnPaid = new ArrayList<>();
    boolean loadmore = false;
    int pageNumber = 1;

    @BindView(R.id.rcv_my_taxes)
    RecyclerView rcv_my_taxes;
    @BindView(R.id.pro_bar_loading)
    ProgressBar pro_bar_loading;
    @BindView(R.id.edt_search)
    AppCompatEditText edt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_taxes);
        ButterKnife.bind(this);
        try{
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.img_back, R.id.img_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                startActivityForResult(new Intent(MyTaxes.this, FileMyTaxes.class),12);
                break;
        }
    }

    private void init() {
        try {
            adapterTaxes = new AdapterTaxes(MyTaxes.this, arrData);
            adapterTaxesSearch = new AdapterTaxes(MyTaxes.this, arrDataSearch);
            rcv_my_taxes.setLayoutManager(new LinearLayoutManager(MyTaxes.this));
            rcv_my_taxes.setAdapter(adapterTaxes);
            showLoading();
            APITaxYears();
            LinearLayoutManager layoutManager = ((LinearLayoutManager)rcv_my_taxes.getLayoutManager());
            rcv_my_taxes.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    // super.onScrolled(recyclerView, dx, dy);
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition == recyclerView.getChildCount()) {
                        if (loadmore) {
                            loadmore = false;
                            pro_bar_loading.setVisibility(View.VISIBLE);
                            loadmoTaxes();
                        }
                    }
                }
            });
            edt_search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length()>0) {
                        arrDataSearch.clear();
                        for (int q = 0; q <= arrData.size() - 1; q++) {
                            if (arrData.get(q).getTitle().toLowerCase().contains(editable.toString().toLowerCase())) {
                                arrDataSearch.add(arrData.get(q));
                            }
                        }
                        rcv_my_taxes.setAdapter(adapterTaxesSearch);
                        adapterTaxesSearch.notifyDataSetChanged();
                    }else {
                        rcv_my_taxes.setAdapter(adapterTaxes);
                        adapterTaxes.notifyDataSetChanged();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadmoTaxes() {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getMyTaxes(
                    pageNumber,
                    20,
                    "bearer " + QTSHelp.getToken(MyTaxes.this)
            ).enqueue(new Callback<APIMyTaxes>() {
                @Override
                public void onResponse(Call<APIMyTaxes> call, Response<APIMyTaxes> response) {
                    pro_bar_loading.setVisibility(View.GONE);
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            arrData.addAll(response.body().getResult());
                            adapterTaxes.notifyDataSetChanged();
                            if (response.body().getResult().size() == 20){
                                pageNumber = pageNumber + 1;
                                loadmore = true;
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIMyTaxes> call, Throwable t) {
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

    private void APITaxYears(){
        try {
            pageNumber = 1;
            arrData.clear();
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getMyTaxes(
                    pageNumber,
                    20,
                    "bearer " + QTSHelp.getToken(MyTaxes.this)
            ).enqueue(new Callback<APIMyTaxes>() {
                @Override
                public void onResponse(Call<APIMyTaxes> call, Response<APIMyTaxes> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            arrData.addAll(response.body().getResult());
                            adapterTaxes.notifyDataSetChanged();
                            if (response.body().getResult().size() == 20){
                                pageNumber = pageNumber + 1;
                                loadmore = true;
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIMyTaxes> call, Throwable t) {
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

    public void getPaid(String url) {
        showLoading();
        listUnPaid.clear();
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getPaid(
                    url,
                    "bearer " + QTSHelp.getToken(MyTaxes.this)
            ).enqueue(new Callback<APIUnPaid>() {
                @Override
                public void onResponse(Call<APIUnPaid> call, Response<APIUnPaid> response) {
                    hideLoading();
                    try {
                        if (response.isSuccessful()){
                            if (response.body().getIsSuccess()){
                                listUnPaid.addAll(response.body().getResult());
                                List<String> unPaid = new ArrayList<>();
                                for (int q=0; q<=response.body().getResult().size()-1; q++){
                                    unPaid.add(response.body().getResult().get(q).getInvoiceNumber()+" - "+"$"+response.body().getResult().get(q).getTotalAmount());
                                }
                                showDialogSelect(unPaid.toArray(new String[0]));
                            }else {
                                QTSHelp.showToast2(MyTaxes.this, response.body().getNotification().toString());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIUnPaid> call, Throwable t) {
                    hideLoading();
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    private void showDialogSelect(String[] animals){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MyTaxes.this);
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    startActivityForResult(new Intent(MyTaxes.this, MakePayment.class)
                            .putExtra("idInvoice",listUnPaid.get(which).getId())
                            .putExtra("total",listUnPaid.get(which).getTotalAmount())
                    ,12);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 12){
            showLoading();
            APITaxYears();
        }
    }
}
