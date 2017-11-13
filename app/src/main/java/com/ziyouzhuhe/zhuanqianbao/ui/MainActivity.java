package com.ziyouzhuhe.zhuanqianbao.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziyouzhuhe.zhuanqianbao.R;
import com.ziyouzhuhe.zhuanqianbao.base.BaseActivity;
import com.ziyouzhuhe.zhuanqianbao.ui.fragments.ClassyFragment;
import com.ziyouzhuhe.zhuanqianbao.ui.fragments.HomeFragment;
import com.ziyouzhuhe.zhuanqianbao.ui.fragments.JiaoShuFragment;
import com.ziyouzhuhe.zhuanqianbao.ui.fragments.UserFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //    @BindView(R.id.base_navigation_home)
//    RelativeLayout tab_home;
//    @BindView(R.id.base_navigation_classify)
//    RelativeLayout tab_classify;
//    @BindView(R.id.base_navigation_jiaohu)
//    RelativeLayout tab_jiaohu;
//    @BindView(R.id.base_navigation_report)
//    RelativeLayout tab_report;
    private LinearLayout mViewsNavigationGroup;
    private Fragment mContentFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mViewsNavigationGroup = (LinearLayout) findViewById(R.id.base_views_navigations);
        int viewChinldCount = mViewsNavigationGroup.getChildCount();
        for (int i = 0; i < viewChinldCount; i++) {
            View mChild = mViewsNavigationGroup.getChildAt(i);
            mChild.setOnClickListener(this);
        }
        //默认第一个
        this.onSwitchContentFrom(HomeFragment.class.getName(), null);
    }

    @Override
    public void onClick(View view) {
        final int vid = view.getId();
        onSwitchNavigationBackground(vid);
        switch (view.getId()) {
            case R.id.base_navigation_home:
                this.onSwitchContentFrom(HomeFragment.class.getName(), null);
                break;
            case R.id.base_navigation_classify:
                this.onSwitchContentFrom(ClassyFragment.class.getName(), null);
                break;
            case R.id.base_navigation_jiaohu:
                this.onSwitchContentFrom(JiaoShuFragment.class.getName(), null);
                break;
            case R.id.base_navigation_report:
                this.onSwitchContentFrom(UserFragment.class.getName(), null);
                break;
            default:
                break;
        }
    }

    /**
     * 切换不同的页面展示
     *
     * @param newFragmentName 将要被切换到的新页面
     * @param bundle          创建新页面时，指定初始化的相关数据
     */
    private void onSwitchContentFrom(String newFragmentName, Bundle bundle) {
        FragmentManager mFragmentManager = this.getSupportFragmentManager();
        Fragment mFragment = mFragmentManager.findFragmentByTag(newFragmentName);
        if (null == mFragment)
            mFragment = Fragment.instantiate(getApplicationContext(), newFragmentName, bundle);

        // 若当前显示的视图与将要被显示的视图冲突或不符，则以新的要显示的视图为准
        if (mContentFragment != mFragment) {
            FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
            if (null == mContentFragment)
                mTransaction.add(R.id.base_views_container, mFragment, newFragmentName);
            else {
                if (!mFragment.isAdded()) {
                    mTransaction.hide(mContentFragment).add(R.id.base_views_container, mFragment, newFragmentName);
                    mContentFragment.onPause();
                } else {
                    mTransaction.hide(mContentFragment).show(mFragment);
                    mContentFragment.onPause();
                    mFragment.onAttach(this);
                    mFragment.onResume();
                }
            }
            mTransaction.commitAllowingStateLoss();
            mContentFragment = mFragment;
        }
    }

    private void onSwitchNavigationBackground(int vid) {
        Resources r = this.getResources();
        int viewChinldCount = mViewsNavigationGroup.getChildCount();
        for (int i = 0; i < viewChinldCount; i++) {
            ViewGroup mChild = (ViewGroup) mViewsNavigationGroup.getChildAt(i);
            TextView textView = (TextView) mChild.findViewById(R.id.textview);
            if (mChild.getId() == vid) {
                textView.setTextColor(r.getColor(R.color.navigation_highlighted_bg));
                setCompoundDrawablesWithIntrinsicBoundsRes(mChild, textView, true);
            } else {
                textView.setTextColor(r.getColor(R.color.navigation_default_bg));
                setCompoundDrawablesWithIntrinsicBoundsRes(mChild, textView, false);
            }
        }
    }

    private void setCompoundDrawablesWithIntrinsicBoundsRes(ViewGroup viewGroup, TextView textView, boolean isChecked) {
        int topRes = 0;
        switch (viewGroup.getId()) {
            case R.id.base_navigation_home:
                topRes = isChecked ? R.drawable.navigation_home_holo_light
                        : R.drawable.navigation_home_holo_drak;
                break;
            case R.id.base_navigation_classify:
                topRes = isChecked ? R.drawable.navigation_classify_holo_light
                        : R.drawable.navigation_classify_holo_drak;
                break;
            case R.id.base_navigation_jiaohu:
                topRes = isChecked ? R.drawable.navigation_jiaohu_holo_light
                        : R.drawable.navigation_jiaohu_holo_drak;
                break;
            case R.id.base_navigation_report:
                topRes = isChecked ? R.drawable.navigation_personal_holo_light
                        : R.drawable.navigation_personal_holo_drak;
                break;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(0, topRes, 0, 0);//设置图片
    }
}
