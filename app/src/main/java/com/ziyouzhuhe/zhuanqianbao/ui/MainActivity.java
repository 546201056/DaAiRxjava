package com.ziyouzhuhe.zhuanqianbao.ui;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziyouzhuhe.zhuanqianbao.R;
import com.ziyouzhuhe.zhuanqianbao.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

//    @BindView(R.id.base_navigation_home)
//    RelativeLayout tab_home;
//    @BindView(R.id.base_navigation_classify)
//    RelativeLayout tab_classify;
//    @BindView(R.id.base_navigation_jiaohu)
//    RelativeLayout tab_jiaohu;
//    @BindView(R.id.base_navigation_report)
//    RelativeLayout tab_report;
    private LinearLayout mViewsNavigationGroup;

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
        int viewChinldCount =  mViewsNavigationGroup.getChildCount();
        for (int  i =0 ; i< viewChinldCount;i++){
            View mChild = mViewsNavigationGroup.getChildAt(i);
            mChild.setOnClickListener(this);
        }
    }

   @Override
    public void onClick(View view) {
       final int vid = view.getId();
       onSwitchNavigationBackground(vid);
        switch (view.getId()){
            case R.id.base_navigation_home:
                break;
            case R.id.base_navigation_classify:
                break;
            case R.id.base_navigation_jiaohu:
                break;
            case R.id.base_navigation_report:
                break;
            default:
                break;
        }
    }

    private void onSwitchNavigationBackground (int vid){
        Resources r = this.getResources();
        int viewChinldCount =  mViewsNavigationGroup.getChildCount();
        for (int  i =0 ; i< viewChinldCount;i++){
            ViewGroup mChild =(ViewGroup) mViewsNavigationGroup.getChildAt(i);
           TextView textView = (TextView) mChild.findViewById(R.id.textview);
            if (mChild.getId() ==vid){
                textView.setTextColor(r.getColor(R.color.navigation_highlighted_bg));
                setCompoundDrawablesWithIntrinsicBoundsRes(mChild,textView,true);
            }else {
                textView.setTextColor(r.getColor(R.color.navigation_default_bg));
                setCompoundDrawablesWithIntrinsicBoundsRes(mChild,textView,false);
            }
        }
    }

    private void setCompoundDrawablesWithIntrinsicBoundsRes(ViewGroup viewGroup,TextView textView , boolean isChecked){
        int topRes = 0;
        switch (viewGroup.getId()){
            case R.id.base_navigation_home:
                topRes =isChecked?R.drawable.navigation_home_holo_light
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
        textView.setCompoundDrawablesWithIntrinsicBounds(0,topRes,0,0);//设置图片
    }
}
