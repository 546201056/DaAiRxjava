# 第一个github项目

记录自己的第一个github项目

大集合，学习锻炼

 	//视图绑定 butterknife
    compile 'com.jakewharton:butterknife:8.4.0'
	
    //引入glide
    compile 'com.github.bumptech.glide:glide:3.7.0'
	
    //引入okhttp
    compile 'com.squareup.okhttp3:okhttp:3.5.0'
	
    //引入retrofit
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
	
    //引入rxjava
    compile 'io.reactivex.rxjava2:rxjava:2.0.4'
	
    //引入Log拦截器，方便DEBUG模式输出log信息
    compile 'com.squareup.okhttp3:logging-interceptor:3.5.0'
	
    //引入rxjava适配器，方便rxjava与retrofit的结合
    compile 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'
	
    //引入json转换器，方便将返回的数据转换为json格式
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
	
    //引入rxandroid
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'

练习json数据
https://raw.githubusercontent.com/546201056/zhuanqianbao/master/json.md

首页主要tab是ViewGroup获取子控件然后实例化，在通过id比对，获取textview，然后 textView.setCompoundDrawablesWithIntrinsicBounds(0,topRes,0,0);//设置tab切换图片
