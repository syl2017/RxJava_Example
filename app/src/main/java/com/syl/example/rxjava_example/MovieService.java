package com.syl.example.rxjava_example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by syl on 2017/6/23.
 */

public interface MovieService {
    //    @GET("top250")
//    Call<MovieEntity> getTopMoive(@Query("start") int start,@Query("count") int count);
    @GET("top250")
    Observable<MovieEntity> getTopMoive(@Query("start") int start, @Query("count") int count);

}
