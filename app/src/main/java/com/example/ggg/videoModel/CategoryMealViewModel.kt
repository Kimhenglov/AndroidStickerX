package com.example.ggg.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ggg.pojo.MealByCategory
import com.example.ggg.pojo.MealByCategoryList
import com.example.myapplication.youtubeVD.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealViewModel: ViewModel(){

    val mealsLiveData = MutableLiveData<List<MealByCategory>>()

    fun getMealByCategory(categoryName:String){
        RetrofitInstance.api.getMealByCategory(categoryName).enqueue(object : Callback<MealByCategoryList>{
            override fun onResponse(
                call: Call<MealByCategoryList>,
                response: Response<MealByCategoryList>
            ) {
                response.body()?.let { mealList ->
                    mealsLiveData.postValue(mealList.meals)
                }
            }

            override fun onFailure(call: Call<MealByCategoryList>, t: Throwable) {
                Log.e("CategoryMealsViewModel",t.message.toString())
            }
        })

    }

    fun observeMealsLiveData():LiveData<List<MealByCategory>>{
        return mealsLiveData
    }
}