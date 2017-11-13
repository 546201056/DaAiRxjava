package com.ziyouzhuhe.zhuanqianbao.ui.fragments;

import android.widget.TextView;

import com.ziyouzhuhe.zhuanqianbao.R;
import com.ziyouzhuhe.zhuanqianbao.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/29.
 */
public class ClassyFragment extends BaseFragment {
    @BindView(R.id.home_text)
    public TextView textView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_classly;
    }

    @Override
    protected void initView() {
        textView.setText("第2个页面");
    }
}
