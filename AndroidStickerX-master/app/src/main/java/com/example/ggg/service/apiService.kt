package com.example.ggg.service

import com.example.myapplication.youtubeVD.pojo.Meal
import retrofit2.http.GET
import retrofit2.http.POST

interface apiService {

    @GET("/provinces.json")
    suspend fun loadProvinceList(): List<Meal>

    @POST("/login")
    suspend fun login(): String
}