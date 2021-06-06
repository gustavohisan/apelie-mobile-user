package com.gustavohisan.apelieuser.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gustavohisan.apelieuser.main.databinding.LayoutHomeStoreBinding
import com.gustavohisan.apelieuser.main.model.Store

internal class HomeStoreListAdapter : RecyclerView.Adapter<HomeStoreListViewHolder>() {

    private val storeList: MutableList<Store> = ArrayList()

    private val imageList = listOf(
        R.mipmap.image1,
        R.mipmap.image2,
        R.mipmap.image3,
        R.mipmap.image4,
        R.mipmap.image5,
        R.mipmap.image6,
        R.mipmap.image7,
        R.mipmap.image8,
        R.mipmap.image9,
        R.mipmap.image10
    )

    fun addItems(items: List<Store>) {
        storeList.clear()
        storeList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeStoreListViewHolder {
        val binding =
            LayoutHomeStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeStoreListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeStoreListViewHolder,
        position: Int
    ) {
        val binding = holder.binding
        val storeData = storeList[position]
        val address = "${storeData.city}, ${storeData.state}"
        binding.storeName.text = storeData.name
        binding.storeAddress.text = address
        binding.storeCategory.text = storeData.theme
        binding.storeRating.text = storeData.rating.toString()
        Glide.with(holder.itemView)
            .load(storeData.logoUrl)
            .error(R.drawable.ic_store_placeholder)
            .into(binding.storeImage)
        Glide.with(holder.itemView)
            .load(storeData.bannerUrl)
            .error(R.mipmap.placeholder_banner)
            .into(binding.bannerImage)
        Glide.with(holder.itemView)
            .load(imageList.random())
            .error(R.mipmap.placeholder_banner)
            .into(binding.productOneImage)
        Glide.with(holder.itemView)
            .load(imageList.random())
            .error(R.mipmap.placeholder_banner)
            .into(binding.productTwoImage)
        Glide.with(holder.itemView)
            .load(imageList.random())
            .error(R.mipmap.placeholder_banner)
            .into(binding.productThreeImage)
    }

    override fun getItemCount(): Int = storeList.count()
}
