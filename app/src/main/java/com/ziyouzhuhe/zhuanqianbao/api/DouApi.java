package com.ziyouzhuhe.zhuanqianbao.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/12/13.
 */

public interface DouApi {
    /**
     *例子
     * @param owner 中间参数可以修改的
     * @return ResponseBody 可以指任何参数 JSON XML 等
     */
    @GET("repos/{owner}/contributors")
    Call<ResponseBody> getMainJson(@Path("owner") String owner);

    /**
     *  例子
     * 返回特定的数据模式
     * @param master 中间参数可以修改的
     * @return  MyMoudel 返回的参数类型
     */
    @GET("546201056/DaAiRxjava/{master}/json.md")
    Call<MyMoudel> getMyMyJson(@Path("master")String master);
}
