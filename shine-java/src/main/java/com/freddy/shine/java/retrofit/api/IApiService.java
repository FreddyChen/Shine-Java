package com.freddy.shine.java.retrofit.api;

import android.util.ArrayMap;

import com.freddy.shine.java.config.RequestOptions;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 统一的请求方式
 *
 * {@link #get(String)}
 * {@link #get(String, ArrayMap)}
 * {@link #post(String)}
 * {@link #post(String, RequestBody)}
 * {@link #put(String)}
 * {@link #put(String, RequestBody)}
 * {@link #delete(String)}
 * {@link #delete(String, ArrayMap)}
 * {@link #syncGet(String)}
 * {@link #syncGet(String, ArrayMap)}
 * {@link #syncPost(String)}
 * {@link #syncPost(String, RequestBody)}
 * {@link #syncPut(String)}
 * {@link #syncPut(String, RequestBody)}
 * {@link #syncDelete(String)}
 * {@link #syncDelete(String, ArrayMap)}
 * @author: FreddyChen
 * @date : 2022/01/14 11:17
 * @email : freddychencsc@gmail.com
 */
public interface IApiService {

    @GET
    Observable<String> get(@Url String function);

    @GET
    Observable<String> get(@Url String function, @QueryMap ArrayMap<String, Object> params);

    @POST
    Observable<String> post(@Url String function);

    @POST
    Observable<String> post(@Url String function, @Body RequestBody body);

    @PUT
    Observable<String> put(@Url String function);

    @PUT
    Observable<String> put(@Url String function, @Body RequestBody body);

    @DELETE
    Observable<String> delete(@Url String function);

    @DELETE
    Observable<String> delete(@Url String function, @QueryMap ArrayMap<String, Object> params);

    @GET
    Call<String> syncGet(@Url String function);

    @GET
    Call<String> syncGet(@Url String function, @QueryMap ArrayMap<String, Object> params);

    @POST
    Call<String> syncPost(@Url String function);

    @POST
    Call<String> syncPost(@Url String function, @Body RequestBody body);

    @PUT
    Call<String> syncPut(@Url String function);

    @PUT
    Call<String> syncPut(@Url String function, @Body RequestBody body);

    @DELETE
    Call<String> syncDelete(@Url String function);

    @DELETE
    Call<String> syncDelete(@Url String function, @QueryMap ArrayMap<String, Object> params);
}
