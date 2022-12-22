package com.cristalbusinessservices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cristalbusinessservices.Adapter.AdapterMeeting;
import com.cristalbusinessservices.Model.Meeting;
import com.google.gson.JsonObject;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.cristalbusinessservices.Adapter.CustomPagerAdapter;
import com.cristalbusinessservices.Fragment.Frm_Contact_Us;
import com.cristalbusinessservices.Fragment.Frm_My_Document;
import com.cristalbusinessservices.Model.Appointments.APIAppointments;
import com.cristalbusinessservices.Model.Slider_Image.APISliderImage;
import com.cristalbusinessservices.Model.User_Info.APIUserInfo;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.Constants;
import com.cristalbusinessservices.Retrofit.QTSConstrains;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends BaseActivity implements Constants{
    @BindView(R.id.img_logo_top)
    AppCompatImageView img_logo_top;
    @BindView(R.id.vpg_home)
    ViewPager vpg_home;
    @BindView(R.id.rl_menuhome)
    RelativeLayout rl_menuhome;
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;
    @BindView(R.id.rl_animation)
    RelativeLayout rl_animation;
    @BindView(R.id.ln_1)
    LinearLayout ln_1;
    @BindView(R.id.ln_2)
    LinearLayout ln_2;
    @BindView(R.id.ln_3)
    LinearLayout ln_3;
    @BindView(R.id.view_1)
    View view_1;
    @BindView(R.id.view_2)
    View view_2;
    @BindView(R.id.view_3)
    View view_3;
    @BindView(R.id.view_hide_menu)
    View view_hide_menu;
    @BindView(R.id.img_1)
    AppCompatImageView img_1;
    @BindView(R.id.img_2)
    AppCompatImageView img_2;
    @BindView(R.id.img_3)
    AppCompatImageView img_3;
    @BindView(R.id.flo_contac_us)
    FrameLayout flo_contac_us;
    @BindView(R.id.flo_my_document)
    FrameLayout flo_my_document;
    @BindView(R.id.cv_file_ny_taxes)
    CardView cv_file_ny_taxes;
    @BindView(R.id.img_c1)
    AppCompatImageView img_c1;
    @BindView(R.id.img_c2)
    AppCompatImageView img_c2;
    @BindView(R.id.img_c3)
    AppCompatImageView img_c3;
    @BindView(R.id.tv_email)
    AppCompatTextView tv_email;
    @BindView(R.id.tv_name)
    AppCompatTextView tv_name;
    @BindView(R.id.img_avatar)
    CircleImageView img_avatar;
    @BindView(R.id.bt_delete)
    LinearLayout btDelete;

    int height, wwidth;
    private boolean IS_MENU_ANIMATING = false;
    private Animation aminLeft, aminRight;
    CustomPagerAdapter customPagerAdapter;
    Frm_My_Document frm_my_document;
    boolean checkClick = false;
    List<Meeting> list;
    AdapterMeeting adapterMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataUser = (APIUserInfo) getIntent().getExtras().getSerializable("objectUser");
        ButterKnife.bind(this);
        try {

            aminLeft = AnimationUtils.loadAnimation(this, R.anim.anim_left);
            aminRight = AnimationUtils.loadAnimation(this, R.anim.anim_right);
            getWidthHeight();
            setLayoutView(img_logo_top, wwidth / 2 *8/10, wwidth / 2 * 112 / 251 *8/10);
            setLayoutView(vpg_home, wwidth, height / 4);
//            vpg_home.setClipToPadding(false);
//            vpg_home.setPadding(30,0, 10,0);
            setLayoutView(rl_menuhome, wwidth * 2 / 3, height);

            FragmentManager fragmentManager = getSupportFragmentManager();
            OurOffice frm_contact_us = new OurOffice();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flo_contac_us, frm_contact_us);
            fragmentTransaction.addToBackStack("flo_contac_us");
            fragmentTransaction.commit();

            FragmentManager fragmentManager2 = getSupportFragmentManager();
            frm_my_document = new Frm_My_Document();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentTransaction2.replace(R.id.flo_my_document, frm_my_document);
            fragmentTransaction2.addToBackStack("flo_my_document");
            fragmentTransaction2.commit();

            initData();
            if (getIntent().getStringExtra("upload_document")!=null){
                rl_menuhome.setVisibility(View.GONE);
                view_hide_menu.setVisibility(View.GONE);
                flo_contac_us.setVisibility(View.GONE);
                flo_my_document.setVisibility(View.VISIBLE);
                view_1.setVisibility(View.INVISIBLE);
                view_2.setVisibility(View.VISIBLE);
                view_3.setVisibility(View.INVISIBLE);
                img_1.setImageResource(R.drawable.tab1);
                img_2.setImageResource(R.drawable.tab2_selected);
                img_3.setImageResource(R.drawable.tab3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float convertDpToPixel(float dp){
        return dp * ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void initData() {
        try {
            tv_name.setText(dataUser.getResult().getFullName());
            tv_email.setText(dataUser.getResult().getEmail());
            Picasso
                    .get()
                    .load(dataUser.getResult().getUserImageFullPath())
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(img_avatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            img_avatar.setImageResource(R.drawable.avatar);
                        }
                    });
            showLoading();
            APISliderImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = new ArrayList<>();
    }

    @OnClick({R.id.ln_refund_status, R.id.cv_join_video, R.id.ln_document, R.id.ln_contact_us, R.id.tv_logout, R.id.ln_my_taxes, R.id.ln_our_office,
            R.id.img_menu, R.id.ln_1, R.id.ln_2, R.id.ln_3, R.id.view_hide_menu, R.id.cv_file_ny_taxes, R.id.cv_my_appointments, R.id.bt_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_refund_status:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sa.www4.irs.gov/irfof/lang/en/irfofgetstatus.jsp"));
                startActivity(i);
                break;
            case R.id.cv_join_video:
                showLoading();
                APIAppointments("v1/appointments/list/"+dataUser.getResult().getContactId());
                break;
            case R.id.ln_document:
                rl_animation.setX(0);
                rl_animation.startAnimation(aminRight);
                IS_MENU_ANIMATING = false;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rl_menuhome.setVisibility(View.GONE);
                        view_hide_menu.setVisibility(View.GONE);
                        flo_contac_us.setVisibility(View.GONE);
                        flo_my_document.setVisibility(View.VISIBLE);
                        view_1.setVisibility(View.INVISIBLE);
                        view_2.setVisibility(View.VISIBLE);
                        view_3.setVisibility(View.INVISIBLE);
                        img_1.setImageResource(R.drawable.tab1);
                        img_2.setImageResource(R.drawable.tab2_selected);
                        img_3.setImageResource(R.drawable.tab3);
                        frm_my_document.APIDocument();
                    }
                }, 200);
                break;
            case R.id.ln_contact_us:
                startActivity(new Intent(Home.this, Frm_Contact_Us.class));
                break;
            case R.id.tv_logout:
                showDialogLogOut();
                break;
            case R.id.ln_my_taxes:
                startActivity(new Intent(Home.this, MyTaxes.class));
                break;
            case R.id.ln_our_office:
                rl_animation.setX(0);
                rl_animation.startAnimation(aminRight);
                IS_MENU_ANIMATING = false;
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rl_menuhome.setVisibility(View.GONE);
                        view_hide_menu.setVisibility(View.GONE);
                        flo_contac_us.setVisibility(View.VISIBLE);
                        flo_my_document.setVisibility(View.GONE);
                        view_1.setVisibility(View.INVISIBLE);
                        view_2.setVisibility(View.INVISIBLE);
                        view_3.setVisibility(View.VISIBLE);
                        img_1.setImageResource(R.drawable.tab1);
                        img_2.setImageResource(R.drawable.tab2);
                        img_3.setImageResource(R.drawable.tab3_selected);
                    }
                }, 200);
                break;
            case R.id.cv_my_appointments:
                startActivity(new Intent(Home.this, MyAppointments.class).putExtra("contactID", dataUser.getResult().getContactId()));
                break;
            case R.id.cv_file_ny_taxes:
                startActivity(new Intent(Home.this, MyTaxes.class));
                break;
            case R.id.img_menu:
                if (!IS_MENU_ANIMATING) {
                    IS_MENU_ANIMATING = true;
                    rl_menuhome.setVisibility(View.VISIBLE);
                    rl_animation.setX(wwidth * 2 / 3);
                    rl_animation.startAnimation(aminLeft);
                    view_hide_menu.setVisibility(View.VISIBLE);
                } else {
                    rl_animation.setX(0);
                    rl_animation.startAnimation(aminRight);
                    IS_MENU_ANIMATING = false;
                    final Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rl_menuhome.setVisibility(View.GONE);
                            view_hide_menu.setVisibility(View.GONE);
                        }
                    }, 200);
                }
                break;
            case R.id.view_hide_menu:
                rl_animation.setX(0);
                rl_animation.startAnimation(aminRight);
                IS_MENU_ANIMATING = false;
                final Handler handler4 = new Handler();
                handler4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rl_menuhome.setVisibility(View.GONE);
                        view_hide_menu.setVisibility(View.GONE);
                    }
                }, 200);
                break;
            case R.id.ln_1:
                flo_contac_us.setVisibility(View.GONE);
                flo_my_document.setVisibility(View.GONE);
                view_1.setVisibility(View.VISIBLE);
                view_2.setVisibility(View.INVISIBLE);
                view_3.setVisibility(View.INVISIBLE);
                img_1.setImageResource(R.drawable.tab1_selected);
                img_2.setImageResource(R.drawable.tab2);
                img_3.setImageResource(R.drawable.tab3);
                break;
            case R.id.ln_2:
                flo_contac_us.setVisibility(View.GONE);
                flo_my_document.setVisibility(View.VISIBLE);
                view_1.setVisibility(View.INVISIBLE);
                view_2.setVisibility(View.VISIBLE);
                view_3.setVisibility(View.INVISIBLE);
                img_1.setImageResource(R.drawable.tab1);
                img_2.setImageResource(R.drawable.tab2_selected);
                img_3.setImageResource(R.drawable.tab3);
                break;
            case R.id.ln_3:
                flo_contac_us.setVisibility(View.VISIBLE);
                flo_my_document.setVisibility(View.GONE);
                view_1.setVisibility(View.INVISIBLE);
                view_2.setVisibility(View.INVISIBLE);
                view_3.setVisibility(View.VISIBLE);
                img_1.setImageResource(R.drawable.tab1);
                img_2.setImageResource(R.drawable.tab2);
                img_3.setImageResource(R.drawable.tab3_selected);
                break;
            case R.id.bt_delete:
                sendRequestDelete();
                break;
        }
    }

    private void sendRequestDelete(){
        String mailto = "mailto:info@cristalbusinessservices.com" +
                "?cc=" +
                "&subject=" + Uri.encode("Remove my account") +
                "&body=" + Uri.encode("Hello,\n"+
                "\n" +
                "I'm requesting my account along with my data to be removed from your system. Here's my account information: \n"+
                "\n" +
                "UserId: "+dataUser.getResult().getUserId()+"\n" +
                "Name: "+tv_name.getText().toString()+ "\n" +
                "Email:  "+tv_email.getText().toString())+"\n" +
                "\n" +
                "Thank you";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));
        startActivity(emailIntent);
    }

    private void setLayoutView(View view, int width, int height) {
        if (view != null) {
            view.getLayoutParams().width = width;
            view.getLayoutParams().height = height;
        }
    }

    private void getWidthHeight() {
        try {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            Home.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            wwidth = displaymetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void APILogOut() {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.logout(
                    APIUtils.tokenPublic
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            QTSHelp.setToken(Home.this, "Token");
                            startActivity(new Intent(Home.this, Intro.class));
                            finish();
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());

                            if (inputJson.optString("message").contains("Authorization has been denied for this request")){
                                QTSHelp.setToken(Home.this, "Token");
                                startActivity(new Intent(Home.this, Intro.class));
                                finish();
                                QTSHelp.showToast2(Home.this, "Authorization has been denied for this request");
                            }else {
                                QTSHelp.setToken(Home.this, "Token");
                                startActivity(new Intent(Home.this, Intro.class));
                                finish();
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
                        if (t.getMessage().contains("End of input at line 1 column 1 path $")) {
                            QTSHelp.setToken(Home.this, "Token");
                            startActivity(new Intent(Home.this, Intro.class));
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    public void showDialogLogOut() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Home.this);
        builder1.setMessage(R.string.do_you_want_to_logout);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Cierre de sesión",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        showLoading();
                        APILogOut();
                    }
                });

        builder1.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void APIAppointments(String url){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getAppointments2(
                    "bearer " + QTSHelp.getToken(Home.this),
                    url
            ).enqueue(new Callback<APIAppointments>() {
                @Override
                public void onResponse(Call<APIAppointments> call, Response<APIAppointments> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            if (response.body().getIsSuccess()) {
                                list.clear();
                                for (int q=0; q<=response.body().getResult().size()-1; q++){
                                    try {
                                        String pattern_date0 = "yyyy-MM-dd'T'HH:mm:ss";
                                        String strDateFormat0 = "MM/dd/yyyy '@' hh:mm a";
                                        String reformattedStr0 = "";
                                        try {
                                            reformattedStr0 = new SimpleDateFormat(strDateFormat0, Locale.ENGLISH).format(new SimpleDateFormat(pattern_date0, Locale.ENGLISH).parse(response.body().getResult().get(q).getStartDate()));

                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        list.add(new Meeting(reformattedStr0, response.body().getResult().get(q).getShortDescription(), response.body().getResult().get(q).getMeetingUrl().toString()));
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                final Dialog dialog = new Dialog(Home.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.setContentView(R.layout.line_dialog);
                                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                RecyclerView rcv = dialog.findViewById(R.id.rcv);
                                Button btCancel = dialog.findViewById(R.id.bt_cancel);
                                adapterMeeting = new AdapterMeeting(Home.this, list);
                                rcv.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false));
                                rcv.addItemDecoration(new DividerItemDecoration(Home.this, DividerItemDecoration.VERTICAL));
                                rcv.setAdapter(adapterMeeting);
                                btCancel.setOnClickListener(v -> dialog.dismiss());
                                dialog.show();
//                                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
//                                builder.setItems(arrData.toArray(new String[0]), new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
//                                AlertDialog dialog = builder.create();
//                                dialog.show();
                            }else {
                                QTSHelp.showToast2(Home.this, response.body().getNotification().toString());
                            }
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());

                            if (inputJson.optString("message").contains("Authorization has been denied for this request")){
                                QTSHelp.setToken(Home.this, "Token");
                                startActivity(new Intent(Home.this, Intro.class));
                                finish();
                                QTSHelp.showToast2(Home.this, "Authorization has been denied for this request");
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

    private void APISliderImage() {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.sliderImage(
                    "v1/businesses/getsliderimages/"+ QTSConstrains.idBusinesses,
                    APIUtils.tokenPublic
            ).enqueue(new Callback<APISliderImage>() {
                @Override
                public void onResponse(Call<APISliderImage> call, Response<APISliderImage> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            if (response.body().getResult().size() == 1) {
                                img_c1.setVisibility(View.VISIBLE);
                            } else if (response.body().getResult().size() == 2) {
                                img_c1.setVisibility(View.VISIBLE);
                                img_c2.setVisibility(View.VISIBLE);
                            } else if (response.body().getResult().size() == 3) {
                                img_c1.setVisibility(View.VISIBLE);
                                img_c2.setVisibility(View.VISIBLE);
                                img_c3.setVisibility(View.VISIBLE);
                            }
                            customPagerAdapter = new CustomPagerAdapter(Home.this, response.body().getResult(), wwidth);
                            vpg_home.setAdapter(customPagerAdapter);
                            vpg_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {
                                    try {
                                        switch (position) {
                                            case 0:
                                                img_c1.setImageResource(R.drawable.boder_c_home);
                                                img_c2.setImageResource(R.drawable.boder_c_intro);
                                                img_c3.setImageResource(R.drawable.boder_c_intro);
                                                break;
                                            case 1:
                                                img_c1.setImageResource(R.drawable.boder_c_intro);
                                                img_c2.setImageResource(R.drawable.boder_c_home);
                                                img_c3.setImageResource(R.drawable.boder_c_intro);
                                                break;
                                            case 2:
                                                img_c1.setImageResource(R.drawable.boder_c_intro);
                                                img_c2.setImageResource(R.drawable.boder_c_intro);
                                                img_c3.setImageResource(R.drawable.boder_c_home);
                                                break;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                        } else {
                            JSONObject inputJson = null;
                            inputJson = new JSONObject(response.errorBody().string());

                            if (inputJson.optString("message").contains("Authorization has been denied for this request")){
                                QTSHelp.setToken(Home.this, "Token");
                                startActivity(new Intent(Home.this, Intro.class));
                                finish();
                                QTSHelp.showToast2(Home.this, "Authorization has been denied for this request");
                            }
                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APISliderImage> call, Throwable t) {
                    try {
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    public void onClickBtnJoinMeeting(String meetingNo) {
        // Check if the meeting number is empty.
        if (meetingNo.length() == 0) {
            Toast.makeText(this, "You need to enter a meeting number/ vanity id which you want to join.", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode"+requestCode+"\n"+"resultCode"+resultCode+"\n");
    }

    @Override
    public void onBackPressed() {
        if (checkClick) {
            finish();
            return;
        }

        checkClick = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                checkClick = false;
            }
        }, 2000);
    }
}
