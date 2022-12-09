package com.cristalbusinessservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cristalbusinessservices.Fragment.Frm_Intro1;
import com.cristalbusinessservices.Fragment.Frm_Intro2;
import com.cristalbusinessservices.Fragment.Frm_Intro3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Intro extends AppCompatActivity {
    @BindView(R.id.vpg_intro)
    ViewPager vpg_intro;
    @BindView(R.id.img_c1)
    AppCompatImageView img_c1;
    @BindView(R.id.img_c2)
    AppCompatImageView img_c2;
    @BindView(R.id.img_c3)
    AppCompatImageView img_c3;
    @BindView(R.id.ln_skip)
    LinearLayout ln_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
        try{
            vpg_intro.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
            vpg_intro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    try{
                        switch (position){
                            case 0:
                                img_c1.setImageResource(R.drawable.boder_c_intro2);
                                img_c2.setImageResource(R.drawable.boder_c_intro);
                                img_c3.setImageResource(R.drawable.boder_c_intro);
                                break;
                            case 1:
                                img_c1.setImageResource(R.drawable.boder_c_intro);
                                img_c2.setImageResource(R.drawable.boder_c_intro2);
                                img_c3.setImageResource(R.drawable.boder_c_intro);
                                break;
                            case 2:
                                img_c1.setImageResource(R.drawable.boder_c_intro);
                                img_c2.setImageResource(R.drawable.boder_c_intro);
                                img_c3.setImageResource(R.drawable.boder_c_intro2);
                                break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.ln_skip, R.id.bt_start})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ln_skip:
                startActivity(new Intent(Intro.this, Login.class));
                break;
            case R.id.bt_start:
                startActivity(new Intent(Intro.this, Login.class));
                break;
        }
    }

    public class SampleFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private List<String> tabTitles = new ArrayList<>();

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            tabTitles.add("Trending Pembaca");
            tabTitles.add("Terpopuler");
            tabTitles.add("Terpopuler");
        }

        @Override
        public int getCount() {
            return tabTitles.size();
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return Frm_Intro1.newInstance(0, "Page # 1");
                case 1:
                    return Frm_Intro2.newInstance(0, "Page # 2");
                case 2:
                    return Frm_Intro3.newInstance(0, "Page # 2");
                default:
                    return null;
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles.get(position);
        }

    }
}
