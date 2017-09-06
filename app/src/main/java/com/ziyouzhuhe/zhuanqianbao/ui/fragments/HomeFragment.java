package com.ziyouzhuhe.zhuanqianbao.ui.fragments;

import android.widget.TextView;

import com.ziyouzhuhe.zhuanqianbao.R;
import com.ziyouzhuhe.zhuanqianbao.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/29.
 */
public class HomeFragment extends BaseFragment {
@BindView(R.id.home_text)
public TextView textView;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        textView.setText("第一个页面");
    }
}
