package com.cristalbusinessservices.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.cristalbusinessservices.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Frm_Intro1 extends Fragment {
    int height, wwidth;
    @BindView(R.id.img)
    AppCompatImageView img;

    public static Frm_Intro1 newInstance(int page, String title) {
        Frm_Intro1 frm_intro1 = new Frm_Intro1();
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
            view = inflater.inflate(R.layout.frm_intro1, container, false);
            ButterKnife.bind(this, view);
            setLayoutView(img, wwidth *2/3, wwidth *2/3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
    private void setLayoutView(View view, int width, int height) {
        if (view!=null) {
            view.getLayoutParams().width = width;
            view.getLayoutParams().height = height;
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
}