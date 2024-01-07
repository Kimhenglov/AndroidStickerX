package com.example.myapplication.youtubeVD.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ggg.database.MealDatabase
import com.example.ggg.pojo.Category
import com.example.ggg.pojo.CategoryList
import com.example.ggg.pojo.MealByCategoryList
import com.example.ggg.pojo.MealByCategory
import com.example.myapplication.youtubeVD.pojo.Meal
import com.example.myapplication.youtubeVD.pojo.MealList
import com.example.myapplication.youtubeVD.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val mealDatabase: MealDatabase
): ViewModel() {

    private var ramdomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealByCategory>>()

    private var catagoriesLiveData = MutableLiveData<List<Category>>()
    private var favoritesMealLiveData = mealDatabase.mealDao().getAllMeals()

    fun getRamdomMeal(){
        RetrofitInstance.api.getRamdomMeal().enqueue(object: Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null){
                    val ramdomMeal: Meal = response.body()!!.meals[0]
                    ramdomMealLiveData.value = ramdomMeal

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment2",t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealByCategoryList>{
            override fun onResponse(call: Call<MealByCategoryList>, response: Response<MealByCategoryList>) {
                if(response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealByCategoryList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }
        })
    }
    ///

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    catagoriesLiveData.postValue(categoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("HomeViewModel",t.message.toString())
            }
        })
    }


/////////////////

    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)

        }
    }
    fun delete(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }
    //////
    fun observeRandomMealLivedata():LiveData<Meal>{
        return ramdomMealLiveData

    }

    fun oberservePopularItemsLiveData():LiveData<List<MealByCategory>>{
        return popularItemsLiveData
    }

    fun observeCategoriesLiveData():LiveData<List<Category>>{
        return catagoriesLiveData
    }

    fun observeFavoritesMealsLiveData():LiveData<List<Meal>>{
        return favoritesMealLiveData
    }
}