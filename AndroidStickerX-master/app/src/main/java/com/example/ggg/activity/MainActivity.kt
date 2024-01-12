package com.example.ggg.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ggg.R
import com.example.ggg.database.MealDatabase
import com.example.ggg.databinding.ActivityMainBinding
import com.example.ggg.fragment.CategoryFragment
import com.example.ggg.fragment.FavoriteFragment
import com.example.ggg.fragment.HomeFragment
import com.example.ggg.videoModel.HomeViewModelFactory
import com.example.myapplication.youtubeVD.videoModel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this,homeViewModelProviderFactory)[HomeViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.btnNv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.fav -> replaceFragment(FavoriteFragment())
                R.id.cat -> replaceFragment(CategoryFragment())

                else ->{

                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fram_ly,fragment)
        fragmentTransaction.commit()
    }
}