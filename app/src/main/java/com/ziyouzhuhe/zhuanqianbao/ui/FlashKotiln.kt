package com.ziyouzhuhe.zhuanqianbao.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gyf.barlibrary.ImmersionBar
import com.ziyouzhuhe.zhuanqianbao.R
import java.util.*
import kotlin.concurrent.timerTask


/**
 * Created by Administrator on 2017/11/13.
 */
class FlashKotiln : Activity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotiln_flash)
        ImmersionBar.with(this).init() //初始化，默认透明状态栏和黑色导航栏
        context = this@FlashKotiln
        Timer().schedule(timerTask {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy() //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}