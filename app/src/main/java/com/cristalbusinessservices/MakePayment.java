package com.cristalbusinessservices;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.FourDigitCardFormatWatcher;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cards.pay.paycardsrecognizer.sdk.Card;
import cards.pay.paycardsrecognizer.sdk.ScanCardIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakePayment extends BaseActivity {
    @BindView(R.id.edt_expiry)
    AppCompatEditText edt_expiry;
    @BindView(R.id.cv_scan)
    CardView cv_scan;
    @BindView(R.id.edt_cvv)
    AppCompatEditText edt_cvv;
    @BindView(R.id.edt_card_number)
    AppCompatEditText edt_card_number;
    @BindView(R.id.edt_name_card)
    AppCompatEditText edt_name_card;
    @BindView(R.id.img_save)
    AppCompatImageView img_save;
    @BindView(R.id.tv_total)
    AppCompatTextView tv_total;
    @BindView(R.id.tv_term)
    AppCompatTextView tv_term;

    boolean isSlash = false, checkSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        ButterKnife.bind(this);
        String text = "By submitting order you agree with our <font color='#3d73dd'>Terms of Use &amp; Privacy</font>";
        tv_term.setText(Html.fromHtml(text));
        try {
            init();
//            showLoading();
//            getPaid();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            edt_expiry.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    formatCardExpiringDate(editable);
                }
            });
            edt_card_number.addTextChangedListener(new FourDigitCardFormatWatcher());
            if (QTSHelp.getSave(MakePayment.this)){
                checkSave = true;
                img_save.setImageResource(R.drawable.ic_checked);
                edt_name_card.setText(QTSHelp.getCardName(MakePayment.this));
                edt_card_number.setText(QTSHelp.getCarNumber(MakePayment.this));
                edt_expiry.setText(QTSHelp.getEx(MakePayment.this));
                edt_cvv.setText(QTSHelp.getCVV(MakePayment.this));
            }
            tv_total.setText("$"+getIntent().getDoubleExtra("total",0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void getPaid() {
//        try {
//            ApiService mAPIService = APIUtils.getAPIService();
//            mAPIService.getPaid(
//                    "/v1/invoices/tax/" + getIntent().getIntExtra("taxID", 0) + "/unpaid?contactId=" + getIntent().getIntExtra("ContactId", 0),
//                    "bearer " + QTSHelp.getToken(MakePayment.this)
//            ).enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                    hideLoading();
//                    try {
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JsonObject> call, Throwable t) {
//                    hideLoading();
//                }
//            });
//        } catch (Exception e) {
//            hideLoading();
//            e.printStackTrace();
//        }
//    }

    private void pay(int invoiceid){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.pay(
                    "/v1/payments/"+invoiceid+"/pay",
                    "bearer " + QTSHelp.getToken(MakePayment.this),
                    invoiceid,
                    edt_name_card.getText().toString(),
                    edt_card_number.getText().toString().replace(" ", ""),
                    Integer.parseInt(edt_expiry.getText().toString().substring(0,2)),
                    Integer.parseInt(20+edt_expiry.getText().toString().substring(3,5)),
                    Integer.parseInt(edt_cvv.getText().toString())
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    hideLoading();
                    try {
                        JSONObject inputJson = null;
                        if (response.isSuccessful() && response.code() == 200) {
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            if (inputJson.optBoolean("isSuccess")){
                                QTSHelp.showToast2(MakePayment.this, inputJson.optString("notification"));
                                setResult(12);
                                finish();
                            }else {
                                QTSHelp.showToast2(MakePayment.this, inputJson.optString("notification"));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    hideLoading();
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    private void formatCardExpiringDate(Editable s) {
        String input = s.toString();
        String mLastInput = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MM/yy", Locale.ENGLISH);
        Calendar expiryDateDate = Calendar.getInstance();

        try {
            expiryDateDate.setTime(formatter.parse(input));
        } catch (java.text.ParseException e) {
            if (s.length() == 2 && !mLastInput.endsWith("/") && isSlash) {
                isSlash = false;
                int month = Integer.parseInt(input);
                if (month <= 12) {
                    edt_expiry.setText(edt_expiry.getText().toString().substring(0, 1));
                    edt_expiry.setSelection(edt_expiry.getText().toString().length());
                } else {
                    s.clear();
                    edt_expiry.setText("");
                    edt_expiry.setSelection(edt_expiry.getText().toString().length());
                }
            } else if (s.length() == 2 && !mLastInput.endsWith("/") && !isSlash) {
                isSlash = true;
                int month = Integer.parseInt(input);
                if (month <= 12) {
                    edt_expiry.setText(edt_expiry.getText().toString() + "/");
                    edt_expiry.setSelection(edt_expiry.getText().toString().length());
                } else if (month > 12) {
                    edt_expiry.setSelection(edt_expiry.getText().toString().length());
                    s.clear();
                }


            } else if (s.length() == 1) {

                int month = Integer.parseInt(input);
                if (month > 1 && month < 12) {
                    isSlash = true;
                    edt_expiry.setText("0" + edt_expiry.getText().toString() + "/");
                    edt_expiry.setSelection(edt_expiry.getText().toString().length());
                }
            }

            mLastInput = edt_expiry.getText().toString();
            return;
        }
    }

    private boolean checkValid(){
        if (edt_name_card.getText().toString().equals("")){
            QTSHelp.showToast2(MakePayment.this, getString(R.string.name_on_card_invalid));
            return false;
        }else if (edt_card_number.getText().toString().equals("")){
            QTSHelp.showToast2(MakePayment.this, getString(R.string.card_number_invalid));
            return false;
        }else if (edt_card_number.getText().toString().length()<14){
            QTSHelp.showToast2(MakePayment.this, getString(R.string.card_number_invalid));
            return false;
        }else if (edt_expiry.getText().toString().equals("")){
            QTSHelp.showToast2(MakePayment.this, getString(R.string.exp_invalid));
            return false;
        }else if (edt_cvv.getText().toString().equals("")){
            QTSHelp.showToast2(MakePayment.this, getString(R.string.cvv_invalid));
            return false;
        }else if (edt_cvv.getText().toString().length()<3){
            QTSHelp.showToast2(MakePayment.this, getString(R.string.cvv_invalid));
            return false;
        }
        return true;
    }

    @OnClick({R.id.tv_term, R.id.img_back, R.id.cv_scan, R.id.bt_make_payment, R.id.ln_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_term:
                termP();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.cv_scan:
                Intent intent = new ScanCardIntent.Builder(this).build();
                startActivityForResult(intent, 121);
                break;
            case R.id.bt_make_payment:
                if (checkValid()){
                    if (checkSave){
                        QTSHelp.setCardName(MakePayment.this, edt_name_card.getText().toString());
                        QTSHelp.setCarNumber(MakePayment.this, edt_card_number.getText().toString());
                        QTSHelp.setEx(MakePayment.this, edt_expiry.getText().toString());
                        QTSHelp.setCVV(MakePayment.this, edt_cvv.getText().toString());
                    }
                    showLoading();
                    pay(getIntent().getIntExtra("idInvoice",0));
                }
                break;
            case R.id.ln_save:
                checkSave = !checkSave;
                if(checkSave){
                    img_save.setImageResource(R.drawable.ic_checked);
                    QTSHelp.setSave(MakePayment.this, checkSave);
                }else {
                    img_save.setImageResource(R.drawable.ic_check);
                    QTSHelp.setSave(MakePayment.this, checkSave);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 121) {
            Log.e("error", "error");
            if (resultCode == Activity.RESULT_OK) {
                Log.e("error2", "error2");
                Card card = data.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD);
                String cardData = "Card number: " + card.getCardNumberRedacted() + "\n"
                        + "Card holder: " + card.getCardHolderName() + "\n"
                        + "Card expiration date: " + card.getExpirationDate();
                Log.i("TAG", "Card info: " + cardData);
                edt_name_card.setText(card.getCardHolderName());
                edt_card_number.setText(card.getCardNumber());
                edt_expiry.setText(card.getExpirationDate());
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("TAG", "Scan canceled");
            } else {
                Log.i("TAG", "Scan failed");
            }
        }
    }
}
