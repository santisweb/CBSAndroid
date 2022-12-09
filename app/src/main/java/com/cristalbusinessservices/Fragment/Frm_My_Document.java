package com.cristalbusinessservices.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cristalbusinessservices.Adapter.AdapterDocument;
import com.cristalbusinessservices.Home;
import com.cristalbusinessservices.Model.Document.APIDocument;
import com.cristalbusinessservices.Model.Document.ResultDocument;
import com.cristalbusinessservices.R;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;
import com.cristalbusinessservices.UploadDocument;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frm_My_Document extends Fragment {
    int height, wwidth;
    AdapterDocument adapterDocument, adapterDocumentSearch;
    ArrayList<ResultDocument> arrData = new ArrayList<>();
    ArrayList<ResultDocument> arrDataSearch = new ArrayList<>();
    boolean loadmore = false;
    int pageNumber = 1;

    @BindView(R.id.rcv_my_document)
    RecyclerView rcv_my_document;
    @BindView(R.id.edt_search)
    AppCompatEditText edt_search;
    @BindView(R.id.pro_bar_loading)
    ProgressBar pro_bar_loading;
    @BindView(R.id.sw_document)
    SwipeRefreshLayout sw_document;

    public static Frm_My_Document newInstance(int page, String title) {
        Frm_My_Document frm_intro1 = new Frm_My_Document();
        return frm_intro1;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view = null;
        try {
            getWidthHeight();
            view = inflater.inflate(R.layout.frm_my_document, container, false);
            ButterKnife.bind(this, view);
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    @OnClick({R.id.img_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_add:
                startActivityForResult(new Intent(getActivity(), UploadDocument.class),12);
                break;
        }
    }

    private void init() {
        try {
            adapterDocumentSearch = new AdapterDocument(getActivity(), arrDataSearch, Frm_My_Document.this);
            adapterDocument = new AdapterDocument(getActivity(), arrData, Frm_My_Document.this);
            rcv_my_document.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcv_my_document.setAdapter(adapterDocument);
            APIDocument();
            LinearLayoutManager layoutManager = ((LinearLayoutManager)rcv_my_document.getLayoutManager());
            rcv_my_document.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    // super.onScrolled(recyclerView, dx, dy);
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition == recyclerView.getChildCount()) {
                        if (loadmore) {
                            loadmore = false;
                            pro_bar_loading.setVisibility(View.VISIBLE);
                            loadMoreDocument();
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
                        rcv_my_document.setAdapter(adapterDocumentSearch);
                        adapterDocumentSearch.notifyDataSetChanged();
                    }else {
                        rcv_my_document.setAdapter(adapterDocument);
                        adapterDocument.notifyDataSetChanged();
                    }
                }
            });

            sw_document.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    APIDocument();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void getWidthHeight() {
        try {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            wwidth = displaymetrics.widthPixels;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadMoreDocument() {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getDocument(
                    pageNumber,
                    20,
                    "bearer " + QTSHelp.getToken(getActivity())
            ).enqueue(new Callback<APIDocument>() {
                @Override
                public void onResponse(Call<APIDocument> call, Response<APIDocument> response) {
                    pro_bar_loading.setVisibility(View.GONE);
                    try {
                        ((Home)getActivity()).hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            arrData.addAll(response.body().getResult());
                            adapterDocument.notifyDataSetChanged();
                            if (response.body().getResult().size() == 20){
                                pageNumber = pageNumber + 1;
                                loadmore = true;
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        ((Home)getActivity()).hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIDocument> call, Throwable t) {
                    try {
                        pro_bar_loading.setVisibility(View.GONE);
                        ((Home)getActivity()).hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            pro_bar_loading.setVisibility(View.GONE);
            ((Home)getActivity()).hideLoading();
            e.printStackTrace();
        }
    }

    public void APIDocument(){
        try {
            sw_document.setRefreshing(true);
            pageNumber = 1;
            arrData.clear();
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getDocument(
                    pageNumber,
                    20,
                    "bearer " + QTSHelp.getToken(getActivity())
            ).enqueue(new Callback<APIDocument>() {
                @Override
                public void onResponse(Call<APIDocument> call, Response<APIDocument> response) {
                    sw_document.setRefreshing(false);
                    try {
                        ((Home)getActivity()).hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            if (response.body()!=null && response.body().getResult()!=null) {
                                arrData.addAll(response.body().getResult());
                            }
                            adapterDocument.notifyDataSetChanged();
                            if (response.body()!=null && response.body().getResult()!=null && response.body().getResult().size() == 20){
                                pageNumber = pageNumber + 1;
                                loadmore = true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIDocument> call, Throwable t) {
                    sw_document.setRefreshing(false);
                    try {
                        ((Home)getActivity()).hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            ((Home)getActivity()).hideLoading();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 12){
            try {
                ((Home) getActivity()).showLoading();
                APIDocument();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}