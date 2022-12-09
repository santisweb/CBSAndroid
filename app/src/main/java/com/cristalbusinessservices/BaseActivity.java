package com.cristalbusinessservices;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.cristalbusinessservices.Model.User_Info.APIUserInfo;

public class BaseActivity extends AppCompatActivity {
    Dialog dialog;
    public APIUserInfo dataUser;

    public void showLoading(){
        try {
            dialog = new Dialog(BaseActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_loading);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hideLoading (){
        try {
            dialog.cancel();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void termP(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tbppro.com/privacy-policy"));
        startActivity(browserIntent);
    }

}
