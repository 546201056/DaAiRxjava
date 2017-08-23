package com.ziyouzhuhe.zhuanqianbao.ui;


import android.widget.TextView;

import com.ziyouzhuhe.zhuanqianbao.R;
import com.ziyouzhuhe.zhuanqianbao.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView textView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }
}
