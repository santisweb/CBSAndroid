package com.cristalbusinessservices;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;


import com.cristalbusinessservices.Model.OfficeModel;
import com.google.gson.JsonObject;
import com.cristalbusinessservices.Model.User_Info.APIUserInfo;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSConstrains;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends BaseActivity {
    @BindView(R.id.edt_confirm_pasword)
    AppCompatEditText edt_confirm_pasword;
    @BindView(R.id.edt_password)
    AppCompatEditText edt_password;
    @BindView(R.id.edt_phone)
    AppCompatEditText edt_phone;
    @BindView(R.id.edt_email)
    AppCompatEditText edt_email;
    @BindView(R.id.edt_last_name)
    AppCompatEditText edt_last_name;
    @BindView(R.id.edt_first_name)
    AppCompatEditText edt_first_name;
    @BindView(R.id.bt_register)
    AppCompatButton bt_register;
    @BindView(R.id.img_eye_password)
    AppCompatImageView img_eye_password;
    @BindView(R.id.img_eye_confirm_password)
    AppCompatImageView img_eye_confirm_password;
    @BindView(R.id.img_check_register)
    AppCompatImageView img_check_register;
    @BindView(R.id.tv_term)
    AppCompatTextView tv_term;
    @BindView(R.id.tv_office)
    AppCompatTextView tv_office;

    boolean checkTerms = false, check_show_pass = true, check_show_comfirm_pass = true;
    int posiType = -1;
    ArrayList<String> listSelectOffice = new ArrayList<>();
    ArrayList<OfficeModel> listOffice = new ArrayList<>();
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        getOffice();
    }

    @OnClick({R.id.rl_office,R.id.tv_term, R.id.img_back, R.id.bt_register, R.id.img_check_register, R.id.img_eye_password, R.id.img_eye_confirm_password})
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.rl_office:
                    if (dialog!=null){
                        dialog.show();
                    }
                    break;
                case R.id.tv_term:
                    termP();
                    break;
                case R.id.img_back:
                    finish();
                    break;
                case R.id.img_eye_confirm_password:
                    try {
                        check_show_comfirm_pass = !check_show_comfirm_pass;
                        if (!check_show_comfirm_pass) {
                            edt_confirm_pasword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            img_eye_confirm_password.setImageResource(R.drawable.ic_eye);
                        } else {
                            edt_confirm_pasword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            img_eye_confirm_password.setImageResource(R.drawable.ic_eye2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.img_eye_password:
                    try {
                        check_show_pass = !check_show_pass;
                        if (!check_show_pass) {
                            edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            img_eye_password.setImageResource(R.drawable.ic_eye);
                        } else {
                            edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            img_eye_password.setImageResource(R.drawable.ic_eye2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.img_check_register:
                    try {
                        checkTerms = !checkTerms;
                        if (checkTerms) {
                            img_check_register.setImageResource(R.drawable.ic_checked);
                        } else {
                            img_check_register.setImageResource(R.drawable.ic_check);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.bt_register:
                    try {
                        if (checkValid()) {
                            showLoading();
                            APIRegisterUser();
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

    private boolean checkValid() {
        try {
//            if (tv_office.getText().toString().length()>0 && posiType>-1) {
                if (edt_first_name.getText().toString().length() > 0) {
                    if (edt_last_name.getText().toString().length() > 0) {
                        if (edt_email.getText().toString().length() > 0) {
                            if (QTSHelp.isEmailValid(edt_email.getText().toString())) {
                                if (edt_phone.getText().toString().length() > 0) {
                                    if (edt_password.getText().toString().length() > 0) {
                                        if (edt_confirm_pasword.getText().toString().length() > 0) {
                                            if (edt_password.getText().toString().equals(edt_confirm_pasword.getText().toString())) {
                                                if (checkTerms) {
                                                    return true;
                                                } else {
                                                    QTSHelp.showToast2(Register.this, getString(R.string.accept_term_privacy));
                                                    return false;
                                                }
                                            } else {
                                                QTSHelp.showToast2(Register.this, getString(R.string.pw_do_not_match));
                                                return false;
                                            }
                                        } else {
                                            QTSHelp.showToast2(Register.this, getString(R.string.cf_pw_required));
                                            return false;
                                        }
                                    } else {
                                        QTSHelp.showToast2(Register.this, getString(R.string.pw_required));
                                        return false;
                                    }
                                } else {
                                    QTSHelp.showToast2(Register.this, getString(R.string.phone_required));
                                    return false;
                                }
                            } else {
                                QTSHelp.showToast2(Register.this, getString(R.string.email_invalid));
                                return false;
                            }
                        } else {
                            QTSHelp.showToast2(Register.this, getString(R.string.email_required));
                            return false;
                        }
                    } else {
                        QTSHelp.showToast2(Register.this, getString(R.string.lname_required));
                        return false;
                    }
                } else {
                    QTSHelp.showToast2(Register.this, getString(R.string.fname_required));
                    return false;
                }
//            }else {
//                QTSHelp.showToast2(Register.this, "Please select office");
//                return false;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void APIRegisterUser() {
        ApiService mAPIService = APIUtils.getAPIService();
        mAPIService.registerUser(
                APIUtils.tokenPublic2,
                Integer.parseInt(QTSConstrains.idBusinesses),
                edt_first_name.getText().toString(),
                edt_last_name.getText().toString(),
                edt_email.getText().toString(),
                edt_password.getText().toString(),
                edt_phone.getText().toString()
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
//                            if (inputJson.optString("notification").length() > 0) {
//                                QTSHelp.showToast2(Register.this, inputJson.optString("notification"));
//                            }
                            showLoading();
                            APILogin(edt_email.getText().toString(), edt_password.getText().toString());
                        }else {
                            inputJson.optString("notification");
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Register.this, inputJson.optString("notification"));
                            }
                        }
                    } else {
                        inputJson = new JSONObject(response.errorBody().string());
                        if (inputJson.optString("notification").length() > 0) {
                            QTSHelp.showToast2(Register.this, inputJson.optString("notification"));
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

    private void APILogin(String email, String password) {
        ApiService mAPIService = APIUtils.getAPIService();
        mAPIService.login(
                email,
                password,
                "password"
        ).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject inputJson = null;
                    if (response.isSuccessful() && response.code() == 200) {
                        inputJson = new JSONObject(String.valueOf(response.body()));
                        if (inputJson.optString("access_token").length()>0) {
                            QTSHelp.setToken(Register.this, inputJson.optString("access_token"));
                            APIUtils.tokenPublic = "bearer "+inputJson.optString("access_token");
                            userInfo();
                        }else {
                            if (inputJson.optString("error_description").length() > 0) {
                                QTSHelp.showToast2(Register.this, inputJson.optString("error_description"));
                            }
                        }
                    } else {
                        hideLoading();
                        inputJson = new JSONObject(response.errorBody().string());
                        if (inputJson.optString("error_description").length() > 0) {
                            QTSHelp.showToast2(Register.this, inputJson.optString("error_description"));
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
                    "bearer " + QTSHelp.getToken(Register.this)
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
                                    QTSHelp.showToast2(Register.this, response.body().getNotification().toString());
                                }
                            }
                            startActivity(new Intent(Register.this, Home.class)
                                    .putExtra("objectUser", response.body())
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Register.this, inputJson.optString("notification"));
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

    private void getOffice(){
        listOffice.clear();
        listSelectOffice.clear();
        showLoading();
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getOffice(
                    "/v1/businesses/getbusinessbranchusers/"+QTSConstrains.idBusinesses,
                    APIUtils.tokenPublic2
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        if (response.isSuccessful() && response.code() == 200) {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            JSONArray jsonArrayResult = inputJson.optJSONArray("result");
                            if (jsonArrayResult.length()>0) {
                                for (int q = 0; q <= jsonArrayResult.length() - 1; q++) {
                                    JSONObject data = jsonArrayResult.getJSONObject(q);
                                    OfficeModel officeModel = new OfficeModel(
                                            data.optInt("contactId"),
                                            data.optString("fullName")
                                    );
                                    listOffice.add(officeModel);
                                    listSelectOffice.add(data.optString("fullName"));
                                }
                                String[] animals = listSelectOffice.toArray(new String[0]);
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setItems(animals, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            posiType = listOffice.get(which).getId();
                                            tv_office.setText(animals[which]);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                dialog = builder.create();
                            }
                            hideLoading();
                        } else {
                            hideLoading();
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());
                            if (inputJson.optString("notification").length() > 0) {
                                QTSHelp.showToast2(Register.this, inputJson.optString("notification"));
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
        }catch (Exception e){
            hideLoading();
            e.printStackTrace();
        }
    }
}
