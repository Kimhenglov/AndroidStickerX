package com.example.ggg.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ggg.databinding.PopularItemBinding
import com.example.ggg.pojo.MealByCategory

class MostPopularAdapter (): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>(){
    class PopularMealViewHolder(var binding: PopularItemBinding):RecyclerView.ViewHolder(binding.root)

    private var mealList = ArrayList<MealByCategory>()
    lateinit var onItemClick:((MealByCategory) -> Unit)
    var onLongItemClick:((MealByCategory) -> Unit)?=null


    fun setMeals(mealByCategoryList:ArrayList<MealByCategory>){
        this.mealList = mealByCategoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return mealList.size

    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
                .load(mealList[position]
                .strMealThumb)
                .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealList[position])
        }

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealList[position])
            true
        }
    }

}