package com.github.qing.multtypeimagelayout.photo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.github.qing.multtypeimagelayout.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends FragmentActivity {

    public static final String KEY_IMG_URL = "img_url";
    public static final String KEY_RECT = "rect";
    public static final String KEY_INDEX = "index";

    @BindView(R.id.viewPager)
    PhotoViewPager viewPager;
    @BindView(R.id.indicator)
    TextView indicator;

    private ArrayList<Rect> rects;
    private int currentIndex;

    private List<PhotoFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        initArgs();
        initViewPager();

    }

    private void initArgs() {
        Intent intent = getIntent();
        String[] imgUrls = intent.getStringArrayExtra(KEY_IMG_URL);
        rects = intent.getParcelableArrayListExtra(KEY_RECT);
        currentIndex = intent.getIntExtra(KEY_INDEX, 0);

        if (imgUrls != null && rects != null && imgUrls.length == rects.size()) {
            for (int i = 0; i < imgUrls.length; i++) {
                PhotoFragment fragment = new PhotoFragment();
                Bundle bundle = new Bundle();
                bundle.putString(PhotoFragment.KEY_IMG_URL, imgUrls[i]);
                bundle.putParcelable(PhotoFragment.KEY_START_BOUND, rects.get(i));
                bundle.putBoolean(PhotoFragment.KEY_TRANS_PHOTO, currentIndex == i);
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
        } else {
            finish();
        }
    }

    private void initViewPager() {
        PhotoPagerAdapter adapter = new PhotoPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setText((position + 1) + " / " + fragments.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(currentIndex);


        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                final PhotoFragment fragment = fragments.get(currentIndex);
                fragment.transformIn();
                indicator.setText((currentIndex + 1) + " / " + fragments.size());

            }
        });

        if (fragments.size() == 1) {
            indicator.setVisibility(View.GONE);
        }
    }

    private boolean isTransformOut = false;

    public void transformOut() {
        if (isTransformOut) {
            return;
        }
        isTransformOut = true;
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < rects.size()) {
            PhotoFragment fragment = fragments.get(currentItem);
            indicator.setVisibility(View.GONE);
            fragment.changeBg(Color.TRANSPARENT);
            fragment.transformOut(new SmoothImageView.onTransformListener() {
                @Override
                public void onTransformCompleted(SmoothImageView.Status status) {
                    exit();
                }
            });
        } else {
            exit();
        }
    }

    private void exit() {
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            transformOut();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class PhotoPagerAdapter extends FragmentPagerAdapter {

        PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
