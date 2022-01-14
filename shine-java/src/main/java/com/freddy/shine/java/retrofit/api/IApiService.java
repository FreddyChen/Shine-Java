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

/**
 * 统一的请求方式
 *
 * {@link #get(RequestOptions)}
 * {@link #get(RequestOptions, ArrayMap)}
 * {@link #post(RequestOptions)}
 * {@link #post(RequestOptions, RequestBody)}
 * {@link #put(RequestOptions)}
 * {@link #put(RequestOptions, RequestBody)}
 * {@link #delete(RequestOptions)}
 * {@link #delete(RequestOptions, ArrayMap)}
 * {@link #syncGet(RequestOptions)}
 * {@link #syncGet(RequestOptions, ArrayMap)}
 * {@link #syncPost(RequestOptions)}
 * {@link #syncPost(RequestOptions, RequestBody)}
 * {@link #syncPut(RequestOptions)}
 * {@link #syncPut(RequestOptions, RequestBody)}
 * {@link #syncDelete(RequestOptions)}
 * {@link #syncDelete(RequestOptions, ArrayMap)}
 * @author: FreddyChen
 * @date : 2022/01/14 11:17
 * @email : freddychencsc@gmail.com
 */
public interface IApiService {

    @GET
    Observable<String> get(RequestOptions options);

    @GET
    Observable<String> get(RequestOptions options, @QueryMap ArrayMap<String, Object> params);

    @POST
    Observable<String> post(RequestOptions options);

    @POST
    Observable<String> post(RequestOptions options, @Body RequestBody body);

    @PUT
    Observable<String> put(RequestOptions options);

    @PUT
    Observable<String> put(RequestOptions options, @Body RequestBody body);

    @DELETE
    Observable<String> delete(RequestOptions options);

    @DELETE
    Observable<String> delete(RequestOptions options, @QueryMap ArrayMap<String, Object> params);

    @GET
    Call<String> syncGet(RequestOptions options);

    @GET
    Call<String> syncGet(RequestOptions options, @QueryMap ArrayMap<String, Object> params);

    @POST
    Call<String> syncPost(RequestOptions options);

    @POST
    Call<String> syncPost(RequestOptions options, @Body RequestBody body);

    @PUT
    Call<String> syncPut(RequestOptions options);

    @PUT
    Call<String> syncPut(RequestOptions options, @Body RequestBody body);

    @DELETE
    Call<String> syncDelete(RequestOptions options);

    @DELETE
    Call<String> syncDelete(RequestOptions options, @QueryMap ArrayMap<String, Object> params);
}
