package com.develop.julio.practica2retrofit.API

import com.develop.julio.practica2retrofit.Data.Post
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface Api {
    @GET("posts")
    fun getAllPost() : Observable<MutableList<Post>>
}