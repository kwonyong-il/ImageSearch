package com.example.imagesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Retrofit {

    @GET("v2/search/image")
    fun SearchImage(
        @Header("Authorization") apiKey: String = "Constants.AUTH_HEADER",
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<KakaoSearchData>
}