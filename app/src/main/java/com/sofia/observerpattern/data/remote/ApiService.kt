package com.sofia.observerpattern.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface HeroeApiService {

    @GET("heroes.json")
    suspend fun finAllHeroes(): Response<List<HeroApiModel>>

}