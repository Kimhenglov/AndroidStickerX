package com.example.ggg.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.youtubeVD.pojo.Meal

interface MealDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)   //2 in 1 style insert + update
    fun upsert(meal: Meal)


    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM mealstickerInformation")  // Order by ...
    fun getAllMeals():LiveData<List<Meal>>


//    @Update
//    suspend fun updateMeal(meal: Meal)

}