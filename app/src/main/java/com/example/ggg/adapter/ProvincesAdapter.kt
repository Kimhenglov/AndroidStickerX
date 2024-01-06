package kh.edu.rupp.fe.visitme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ggg.databinding.ViewHolderStickerBinding
import com.example.ggg.model.Province
import com.squareup.picasso.Picasso

class ProvincesAdapter: ListAdapter<Province, ProvincesAdapter.ProvinceViewHolder>(

    object : DiffUtil.ItemCallback<Province>() {
        override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem.id == newItem.id
        }

    }

) {

    var onProvinceClickListener: ((Int, Province) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderStickerBinding.inflate(layoutInflater, parent, false)
        return ProvinceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onProvinceClickListener?.invoke(position, item)
        }
    }

    class ProvinceViewHolder(val itemBinding: ViewHolderStickerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(province: Province) {
            Picasso.get().load(province.imageUrl).into(itemBinding.imgProvince)
            itemBinding.txtName.text = province.name
        }

    }

}