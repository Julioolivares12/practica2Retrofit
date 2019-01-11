package com.develop.julio.practica2retrofit.Singleton

import com.develop.julio.practica2retrofit.API.Api
import com.develop.julio.practica2retrofit.Constantes.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSingleton {
    //creo la instancia de retrofit
private var retrofit : Retrofit? = null
    //metodo para obtener la instancia de retrofit
    fun getApiService() : Api {
        if (retrofit ==null)
            retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit!!.create(Api::class.java)
    }
}