package com.andikaputra.catatanharian;

/**
 * Nim   : 10119101
 * Nama  : Andika Putra
 * Kelas : IF-1
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderActivity extends AppCompatActivity {

    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;

    TextView[] dots;

    ViewPagerAdapater viewPagerAdapater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        backbtn = findViewById(R.id.backbtn);
        nextbtn = findViewById(R.id.nextbtn);
        skipbtn = findViewById(R.id.skipbtn);

        //Mendeklarasikan button kembalii

       backbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (getitem(0) > 0){

                   mSLideViewPager.setCurrentItem(getitem(-1),true);

               }
           }
       });

        //Mendeklarasikan button next(selanjutnya)

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) < 3)
                    mSLideViewPager.setCurrentItem(getitem(1),true);
                else {
                    Intent intent = new Intent(SliderActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //Mendeklarasikan button skip(lewati)
        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SliderActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        });

        mSLideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        viewPagerAdapater = new ViewPagerAdapater(this);
        mSLideViewPager.setAdapter(viewPagerAdapater);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

    }

    public void setUpindicator(int position) {

        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
            if (position > 0) {
                backbtn.setVisibility(View.VISIBLE);
            } else {
                backbtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {
        return mSLideViewPager
                .getCurrentItem() + i;
    }
}