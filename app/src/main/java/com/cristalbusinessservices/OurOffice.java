package com.cristalbusinessservices;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.cristalbusinessservices.Fragment.Frm_Contact_Us;
import com.cristalbusinessservices.Model.Our_Office.APIOur;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSConstrains;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurOffice extends Fragment implements OnMapReadyCallback {
    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyBd_UHEsrpQq8IBCmmyqptRI2oLwVgeb0Q";
    int height, wwidth;
    GoogleMap mMap;
    APIOur apiOur;
    LatLngBounds.Builder builder;
    int padding;
    LatLngBounds bounds;

    @BindView(R.id.cv_get_directions)
    CardView cv_get_directions;
    @BindView(R.id.cv_send_mess)
    CardView cv_send_mess;
    @BindView(R.id.tv_name)
    AppCompatTextView tv_name;
    @BindView(R.id.tv_office_manager)
    AppCompatTextView tv_office_anager;
    @BindView(R.id.tv_location)
    AppCompatTextView tv_location;
    @BindView(R.id.img_fb)
    AppCompatImageView img_fb;
    @BindView(R.id.img_tw)
    AppCompatImageView img_tw;
    @BindView(R.id.img_li)
    AppCompatImageView img_li;
    @BindView(R.id.img_ytb)
    AppCompatImageView img_ytb;
    @BindView(R.id.img_in)
    AppCompatImageView img_in;

    public static OurOffice newInstance(int page, String title) {
        OurOffice frm_intro1 = new OurOffice();
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
            view = inflater.inflate(R.layout.activity_our_office, container, false);
            ButterKnife.bind(this, view);
            try {
                getWidthHeight();
                init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private void init() {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getOurOffice(
                    "v1/businesses/"+QTSConstrains.idBusinesses,
                    "bearer " + QTSHelp.getToken(getActivity())
            ).enqueue(new Callback<APIOur>() {
                @Override
                public void onResponse(Call<APIOur> call, Response<APIOur> response) {
                    try {
                        if (response.isSuccessful()) {
                            apiOur = response.body();
                            tv_name.setText(response.body().getResult().getName());
                            tv_office_anager.setText( response.body().getResult().getOfficeManager());
                            if (response.body().getResult().getDirections()!=null && !response.body().getResult().getDirections().equals("null")){
                                cv_get_directions.setVisibility(View.VISIBLE);
                            }else {
                                cv_get_directions.setVisibility(View.GONE);
                            }
                            if (response.body().getResult().getAddress()!=null && !response.body().getResult().getAddress().equals("null") && response.body().getResult().getAddress().length()>0){
                                SupportMapFragment mapFragment = (SupportMapFragment) OurOffice.this.getChildFragmentManager()
                                        .findFragmentById(R.id.mapView);
                                mapFragment.getMapAsync(OurOffice.this);
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        addMark(response.body().getResult().getAddress());
                                    }
                                }, 5000);
                            }else {
                                cv_get_directions.setVisibility(View.GONE);
                            }
                            tv_location.setText(response.body().getResult().getAddress());
                            if (response.body().getResult().getFacebookUrl()!=null && !response.body().getResult().getFacebookUrl().equals("null")){
                                img_fb.setVisibility(View.VISIBLE);
                            }else {
                                img_fb.setVisibility(View.GONE);
                            }
                            if (response.body().getResult().getTwitterUrl()!=null && !response.body().getResult().getTwitterUrl().equals("null")){
                                img_tw.setVisibility(View.VISIBLE);
                            }else {
                                img_tw.setVisibility(View.GONE);
                            }
                            if (response.body().getResult().getLinkedInUrl()!=null && !response.body().getResult().getLinkedInUrl().equals("null")){
                                img_li.setVisibility(View.VISIBLE);
                            }else {
                                img_li.setVisibility(View.GONE);
                            }
                            if (response.body().getResult().getInstagramUrl()!=null && !response.body().getResult().getInstagramUrl().equals("null")){
                                img_in.setVisibility(View.VISIBLE);
                            }else {
                                img_in.setVisibility(View.GONE);
                            }
                            if (response.body().getResult().getYoutubeUrl()!=null && !response.body().getResult().getYoutubeUrl().equals("null")){
                                img_ytb.setVisibility(View.VISIBLE);
                            }else {
                                img_ytb.setVisibility(View.GONE);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIOur> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMark(String name) {
        try {
            builder = new LatLngBounds.Builder();
//            LatLng p1 = new LatLng(40.8786286,-73.9076287);
            Marker marker = mMap.addMarker(new MarkerOptions().position(getLocationFromAddress(getActivity(), name)).title(name));
            builder.include(marker.getPosition());
            bounds = builder.build();
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(getLocationFromAddress(getActivity(), name), 18));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.cv_get_directions ,R.id.cv_send_mess, R.id.img_phone, R.id.img_loccation, R.id.img_back, R.id.img_in, R.id.img_ytb, R.id.img_li, R.id.img_tw, R.id.img_fb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_get_directions:
                try {
                    if (apiOur.getResult().getDirections()!=null) {
                        Intent iq = new Intent(Intent.ACTION_VIEW);
                        iq.setData(Uri.parse(apiOur.getResult().getDirections()));
                        getActivity().startActivity(iq);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.cv_send_mess:
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{apiOur.getResult().getEmail()});
//                i.putExtra(Intent.EXTRA_SUBJECT, "");
//                i.putExtra(Intent.EXTRA_TEXT   , "");
//                startActivity(Intent.createChooser(i, "Send mail..."));
                startActivity(new Intent(getActivity(), Frm_Contact_Us.class));
                break;
            case R.id.img_phone:
                if (Build.VERSION.SDK_INT >= 21 && (
                        ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, 101);
                }else {
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + apiOur.getResult().getPhone()));
                        if (ActivityCompat.checkSelfPermission(((Home)getActivity()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(callIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.img_loccation:
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+tv_location.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(((Home)getActivity()).getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            case R.id.img_back:
                ((Home)getActivity()).finish();
                break;
            case R.id.img_in:
                try {
                    if (apiOur.getResult().getInstagramUrl()!=null) {
                        Intent iq = new Intent(Intent.ACTION_VIEW);
                        iq.setData(Uri.parse(apiOur.getResult().getInstagramUrl()));
                        getActivity().startActivity(iq);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.img_ytb:
                try {
                    if (apiOur.getResult().getYoutubeUrl()!=null) {
                        Intent iq = new Intent(Intent.ACTION_VIEW);
                        iq.setData(Uri.parse(apiOur.getResult().getYoutubeUrl()));
                        getActivity().startActivity(iq);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.img_li:
                try {
                    if (apiOur.getResult().getLinkedInUrl()!=null) {
                        Intent iq = new Intent(Intent.ACTION_VIEW);
                        iq.setData(Uri.parse(apiOur.getResult().getLinkedInUrl()));
                        getActivity().startActivity(iq);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.img_tw:
                try {
                    if (apiOur.getResult().getTwitterUrl()!=null) {
                        Intent iq = new Intent(Intent.ACTION_VIEW);
                        iq.setData(Uri.parse(apiOur.getResult().getTwitterUrl()));
                        getActivity().startActivity(iq);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.img_fb:
                try {
                    if (apiOur.getResult().getFacebookUrl()!=null) {
                        Intent iq = new Intent(Intent.ACTION_VIEW);
                        iq.setData(Uri.parse(apiOur.getResult().getFacebookUrl()));
                        getActivity().startActivity(iq);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

//        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(40.8786286,-73.9076287);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            Log.e("strAddress", address.size()+"---"+strAddress);
            if (address == null) {
                return null;
            }
            if (address.size()>0) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }

}
