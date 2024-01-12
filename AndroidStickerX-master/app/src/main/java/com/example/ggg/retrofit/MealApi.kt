package com.example.myapplication.youtubeVD.retrofit

import com.example.ggg.pojo.CategoryList
import com.example.ggg.pojo.MealByCategory
import com.example.ggg.pojo.MealByCategoryList
import com.example.myapplication.youtubeVD.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRamdomMeal(): Call<MealList>

    @GET("lookup.php")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>

    @GET("filter.php")
    fun getPopularItems(@Query("c") categoryName:String) : Call<MealByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

    @GET("filter.php")
    fun getMealByCategory(@Query("c") categoryName: String) : Call<MealByCategoryList>


    @GET("search.php")
    fun searchMeals(@Query("s") searchQuery: String) : Call<MealList>

}