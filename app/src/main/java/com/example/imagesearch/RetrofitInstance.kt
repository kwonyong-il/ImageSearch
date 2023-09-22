package com.example.imagesearch

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.imagesearch.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val apiService: Retrofit
        get() = instance.create(Retrofit::class.java)

    private val instance: retrofit2.Retrofit
        private get() {
            val gson = GsonBuilder().setLenient().create()
            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}