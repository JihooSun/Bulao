package com.jeno.movie.network;

import com.jeno.movie.networkBean.DetailRecommendListResp;
import com.jeno.movie.networkBean.GetAdvertResp;
import com.jeno.movie.networkBean.GetAppconfigResp;
import com.jeno.movie.networkBean.GetBarragesResp;
import com.jeno.movie.networkBean.GetBaseURLResp;
import com.jeno.movie.networkBean.GetListByIdsResp;
import com.jeno.movie.networkBean.GetMacTopicAllResp;
import com.jeno.movie.networkBean.GetMacTopicDetailResp;
import com.jeno.movie.networkBean.GetMemberShipResp;
import com.jeno.movie.networkBean.GetMemberValidityResp;
import com.jeno.movie.networkBean.GetParseResp;
import com.jeno.movie.networkBean.GetSResp;
import com.jeno.movie.networkBean.MacDetailResp;
import com.jeno.movie.networkBean.MacTypesResp;
import com.jeno.movie.networkBean.RecommendListResp;
import com.jeno.movie.networkBean.RedeemCamiResp;
import com.jeno.movie.networkBean.SaveBarrageResp;
import com.jeno.movie.networkBean.SearchResp;
import com.jeno.movie.networkBean.SwiperResp;
import com.jeno.movie.networkBean.UrgeUpdateResp;
import com.jeno.movie.networkBean.VodDetailResp;
import com.jeno.movie.networkBean.VodHotResp;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataSource {

    @GET("/x1/v1/vod/recommend")
    Call<DetailRecommendListResp> getDetailRecommendList(@Query("Id") Integer Id, @Query("sign") String sign, @Query("os") Integer os);

    @GET("x1/v1/app/getAppconfigRz1")
    Call<GetAppconfigResp> getAppconfig(@Query("sign") String sign, @Query("os") Integer os, @Query("deviceId") String deviceId, @Query("version") String version);

    @GET("/x1/v1/app/getAdvert")
    Call<GetAdvertResp> getAdvert(@Query("sign") String sign, @Query("os") Integer os, @Query("uuid") String uuid);

    @GET("/x1/v1/Search/search")
    Call<SearchResp> getSearchList(@Query("data") String searchKey, @Query("page") Integer page, @Query("limit") Integer limit, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/macTypes/types")
    Call<MacTypesResp> macTypes(@Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/macTopic/all")
    Call<GetMacTopicAllResp> getMacTopicAll(@Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/recommend/swiper")
    Call<SwiperResp> getSwiper(@Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/recommend/recommendList")
    Call<RecommendListResp> getRecommendList(@Query("page") Integer page, @Query("limit") Integer limit, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/macTopic/detail")
    Call<GetMacTopicDetailResp> getMacTopicDetail(@Query("topic_id") Integer topic_id, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/macTypes/detail")
    Call<MacDetailResp> getMacDetailList(@Query("classtype") String classtype, @Query("area") String area,
                                         @Query("year") String year, @Query("typeid") String typeid,
                                         @Query("page") Integer page, @Query("limit") Integer limit, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/vod/hot")
    Call<VodHotResp> getVodHotList(@Query("typeid") Integer typeid, @Query("limit") Integer limit, @Query("page") Integer page, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/vod/details")
    Call<VodDetailResp> getVodDetail(@Query("vodidstr") String vodId, @Query("sign") String sign, @Query("uuid") String uuid, @Query("os") Integer os);

    @GET("/x1/v1/analysis/newAnalysis")
    Call<GetParseResp> getParse(@Query("url") String url, @Query("from") String from, @Query("version") String version
            , @Query("skip") int skip, @Query("time") long time, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/vod/getListByIds")

    Call<GetListByIdsResp> getListByIds(@Query("ids") String Ids, @Query("sign") String sign, @Query("os") Integer os);

    @POST("/x1/v1/app/feedBack")
    Call<UrgeUpdateResp> feedBack(@Body RequestBody createBody);

    @GET("/x1/v1/vod/urgeUpdate")
    Call<UrgeUpdateResp> urgeUpdate(@Query("vod_id") String id, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/api.php/App/getRzysBaseURL")
    Call<GetBaseURLResp> getRzysBaseURL();

    @GET("/api.php/App/getRzysBaseURList")
    Call<GetBaseURLResp> getRzysBaseURList();

    @GET("/x1/v1/cami/getMembership")
    Call<GetMemberShipResp> getMembership(@Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/cami/checkMemberValidity")
    Call<GetMemberValidityResp> checkMemberValidity(@Query("uuid") String uuid, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/cami/redeemCami")
    Call<RedeemCamiResp> redeemCami(@Query("uuid") String uuid, @Query("cami") String cami, @Query("sign") String sign, @Query("os") Integer os);

    @GET("/x1/v1/barrage/getBarrages")
    Call<GetBarragesResp> getBarrages(@Query("vodId") String vodId, @Query("vodSerial") String vodSerial, @Query("vodDuringTime") Integer vodDuringTime, @Query("sign") String sign, @Query("os") Integer os);

    @POST("/x1/v1/barrage/saveBarrage")
    Call<SaveBarrageResp> saveBarrage(@Body RequestBody createBody);

    @GET("/x1/v1/app/getS")
    Call<GetSResp> getS(@Query("os") Integer os, @Query("version") String version);

}

