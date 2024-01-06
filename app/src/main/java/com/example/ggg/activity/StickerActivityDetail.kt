package com.example.ggg.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ggg.R
import com.example.ggg.database.MealDatabase
import com.example.ggg.databinding.ActivityMealDetailesBinding
import com.example.ggg.fragment.HomeFragment
import com.example.ggg.videoModel.MealViewModelFactory
import com.example.myapplication.youtubeVD.pojo.Meal
import com.example.myapplication.youtubeVD.videoModel.MealViewModel


class StickerActivityDetail : AppCompatActivity(){
    private lateinit var stickerId:String
    private lateinit var stickerName:String
    private lateinit var stickerThumb:String
    private lateinit var binding: ActivityMealDetailesBinding
    private lateinit var mealMvvm : MealViewModel
    private lateinit var youtubeLink:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_meal_detailes)

//        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]
        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        mealMvvm = ViewModelProvider(this,viewModelFactory)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInfotmationInViews()

        mealMvvm.getMealDetail(stickerId)
        observerMealDetailsLiveData()

        onYoutubeImageClick()

    }

    private fun onYoutubeImageClick(){
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData(){
        mealMvvm.observerMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                val meal = value

                binding.tvCateforyInfo.text = "Category : ${meal!!.strCategory}"
                binding.tvAreaInfo.text = "Area : ${meal.strArea}"
                binding.tvContent.text = meal.strInstructions

                youtubeLink = meal.strYoutube
            }
        })
    }

    private fun setInfotmationInViews(){
        Glide.with(applicationContext)
            .load(stickerThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = stickerName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent(){

        val intent = intent

        stickerId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        stickerName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        stickerThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnFav.visibility = View.INVISIBLE
        binding.tvInstruction.visibility = View.INVISIBLE
        binding.tvCateforyInfo.visibility = View.INVISIBLE
        binding.tvAreaInfo.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }

    private fun onResponeseCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnFav.visibility = View.VISIBLE
        binding.tvInstruction.visibility = View.VISIBLE
        binding.tvCateforyInfo.visibility = View.VISIBLE
        binding.tvAreaInfo.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

}