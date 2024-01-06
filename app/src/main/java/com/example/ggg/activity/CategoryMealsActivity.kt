package com.example.ggg.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ggg.R
import com.example.ggg.adapter.CategoryMealAdapter
import com.example.ggg.databinding.ActivityCategoryMealsBinding
import com.example.ggg.fragment.HomeFragment
import com.example.ggg.videoModel.CategoryMealViewModel

/////////MMMM
class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealViewModel: CategoryMealViewModel
    lateinit var categoryMealAdapter: CategoryMealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prepareRecyclerView()

        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryMealViewModel = ViewModelProviders.of(this)[CategoryMealViewModel::class.java]

        categoryMealViewModel.getMealByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealViewModel.observeMealsLiveData().observe(this, Observer { mealsList ->
//            mealsList.forEach {
//                Log.d("test",it.strMeal)
//            }

            binding.tvCategoryCount.text = mealsList.size.toString()
            categoryMealAdapter.setMealsList(mealsList)
        })

    }

    private fun prepareRecyclerView(){
        categoryMealAdapter = CategoryMealAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealAdapter
        }
    }
}