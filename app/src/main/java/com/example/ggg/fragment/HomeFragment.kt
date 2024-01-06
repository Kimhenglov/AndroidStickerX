package com.example.ggg.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ggg.activity.CategoryMealsActivity
import com.example.ggg.activity.StickerActivityDetail
import com.example.ggg.adapter.CategoriesAdapter
import com.example.ggg.adapter.MostPopularAdapter
import com.example.ggg.databinding.FragmentHomeBinding
import com.example.ggg.pojo.MealByCategory
import com.example.myapplication.youtubeVD.pojo.Meal
import com.example.myapplication.youtubeVD.videoModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomSticker: Meal
    private lateinit var popularItemAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    ///

    companion object{
        const val MEAL_ID = "com.example.ggg.fragment.idMeal"
        const val MEAL_NAME = "com.example.ggg.fragment.nameMeal"
        const val MEAL_THUMB = "com.example.ggg.fragment.thumbMeal"
        const val CATEGORY_NAME = "com.example.ggg.fragment.categoryName"
    }

    //


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

        popularItemAdapter = MostPopularAdapter()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()


        homeMvvm.getRamdomMeal()
        observerRamdomMeal()

        onRamdomMealClick()

        homeMvvm.getPopularItems()
        observePopularItemsLiveData()

        onPopularItemClick()

        homeMvvm.getCategories()
        observeCategoryLiveData()

        prepareCategoriesRecyclerView()

        onCategoryCLick()   ////MMM


    }

    /////

    /////MMM<

    private fun onCategoryCLick(){
        categoriesAdapter.onItemClick = {
                category -> val intent = Intent(activity,CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)

        }
    }

    /////MMMM/>

    private fun prepareCategoriesRecyclerView(){
        categoriesAdapter = CategoriesAdapter()
        binding.recyclerView2.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoryLiveData(){
        homeMvvm.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
//            categories.forEach {
////                category ->  Log.d("test",category.strCategory)
//                categoriesAdapter.setCategoryList(categories)
//            }
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun onPopularItemClick(){
        popularItemAdapter.onItemClick = { meal ->
            val intent = Intent(activity,StickerActivityDetail::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = popularItemAdapter
        }
    }

    private fun observePopularItemsLiveData(){
        homeMvvm.oberservePopularItemsLiveData().observe(viewLifecycleOwner,
        ){ mealList -> popularItemAdapter.setMeals(mealList = mealList as ArrayList<MealByCategory>)  }
    }

    private fun onRamdomMealClick(){
        binding.cardPower.setOnClickListener {
            val intent = Intent(activity, StickerActivityDetail::class.java)

            intent.putExtra(MEAL_ID,randomSticker.idMeal)
            intent.putExtra(MEAL_NAME,randomSticker.strMeal)
            intent.putExtra(MEAL_THUMB,randomSticker.strMealThumb)

            startActivity(intent)
        }

//        binding.cardPower.setOnClickListener {
//            val Intent = Intent(activity,StickerActivityDetail::class.java)
//            startActivity(Intent)
//        }


    }

    /////


    private fun observerRamdomMeal(){
        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner,object : Observer<Meal> {
            override fun onChanged(t: Meal) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb)
                    .into(binding.powerpuffGirlImg)
            }

        })

        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner,
            { Meal ->
                Glide.with(this@HomeFragment)
                    .load(Meal!!.strMealThumb)
                    .into(binding.powerpuffGirlImg)


                this.randomSticker = Meal

            })
    }
}