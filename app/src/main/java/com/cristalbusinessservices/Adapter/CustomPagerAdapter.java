package com.cristalbusinessservices.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.cristalbusinessservices.Model.Slider_Image.SliderImage;
import com.cristalbusinessservices.R;

import java.util.ArrayList;
import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<SliderImage> arrData = new ArrayList<>();
    int width = 0;

    public CustomPagerAdapter(Context context,  List<SliderImage> arrData1, int width1) {
        mContext = context;
        arrData = arrData1;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        width = width1;
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.img_vpg, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        AppCompatTextView tv_title = itemView.findViewById(R.id.tv_title);
        Glide.with(mContext)
                .load(arrData.get(position).getImageFullUrl())
                .transform(new RoundedCorners(100))
                .into(imageView);
//        setLayoutView(imageView, width/4*3);
        tv_title.setText(arrData.get(position).getImageName());
        container.addView(itemView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (arrData.get(position).getLinkUrl()!=null) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrData.get(position).getLinkUrl().toString()));
                        mContext.startActivity(browserIntent);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return itemView;
    }

    private void setLayoutView(View view, int width) {
        if (view!=null) {
            view.getLayoutParams().width = width;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}