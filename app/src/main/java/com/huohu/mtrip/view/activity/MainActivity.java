package com.huohu.mtrip.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.UserData;
import com.huohu.mtrip.presenter.contract.MainContract;
import com.huohu.mtrip.presenter.presenter.MainPresenter;
import com.huohu.mtrip.util.ActivityUtils;
import com.huohu.mtrip.view.fragment.ArFragment;
import com.huohu.mtrip.view.fragment.HomeFragment;
import com.huohu.mtrip.view.fragment.MapFragment;
import com.huohu.mtrip.view.fragment.MineFragment;
import com.huohu.mtrip.view.fragment.TitleFragment;
import com.huohu.mtrip.view.wighet.MToast;
import com.huohu.mtrip.view.wighet.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends TitleActivity implements  MainContract.View{

    @BindView(R.id.m_main_tabs)
    CommonTabLayout mMainTabs;
    @BindView(R.id.tab_div)
    View tabDiv;
    @BindView(R.id.m_viewpager)
    NoScrollViewPager mViewpager;

    private String[] tabTitles = {"首页", "AR识别", "地图", "我的"};

    private static final int VIEW_SIZE = 4;

    private int[] mIconUnselectIds = {
            R.mipmap.main_tab_home_off, R.mipmap.main_tab_ar_off, R.mipmap.main_tab_map_off, R.mipmap.main_tab_mine_off
    };

    private int[] mIconSelectIds = {
            R.mipmap.main_tab_home_on, R.mipmap.main_tab_ar_on, R.mipmap.main_tab_map_on, R.mipmap.main_tab_mine_on
    };

    private List<TitleFragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private MainContract.Presenter mPresenter;
    private int lastIndex;



    public   void initView() {
        setContentLayout(R.layout.activity_main);
        fullScreenContent(true);
        ButterKnife.bind(this);
        new MainPresenter(this);
        mViewpager.setNoScroll(true);
        mFragments.clear();
        mFragments.add(new HomeFragment());
        mFragments.add(new ArFragment());
        mFragments.add(new MapFragment());
        mFragments.add(new MineFragment());
        for (int i = 0; i < mFragments.size(); i++) {
            mTabEntities.add(new TabEntity(tabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mMainTabs.setTabData(mTabEntities);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(adapter);
        mMainTabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 3 ) {
                    if (!UserData.getInstance().isLogin()) {
                        ActivityUtils.showLogin(MainActivity.this, false);
                        if (lastIndex == 0 || lastIndex == 1 || lastIndex == 2) {
                            mMainTabs.setCurrentTab(lastIndex);
                        } else {
                            mMainTabs.setCurrentTab(0);
                        }
                        MToast.showToast("请先登录");
                        return;
                    }
                }
                lastIndex = position;
                mViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mMainTabs.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void gotoTab(int index) {
        if (index > VIEW_SIZE - 1) {
            return;
        }
        mMainTabs.setCurrentTab(index);
        mViewpager.setCurrentItem(index);
    }
    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < mFragments.size()) {
                return mFragments.get(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            return VIEW_SIZE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < VIEW_SIZE) {

            }
            return null;
        }
    }
    class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

}
