package com.example.worldnews.api

import com.example.worldnews.data.ObjectCollection
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("news?apikey=pub_6472186fb37fa5b00f40c44775a10889560b&country=ru")
    fun getRussianNews(): Single<ObjectCollection>

    @GET("news?apikey=pub_6472186fb37fa5b00f40c44775a10889560b&country=us")
    fun getAmericanNews(): Single<ObjectCollection>
}