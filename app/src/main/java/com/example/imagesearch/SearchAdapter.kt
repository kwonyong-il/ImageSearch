package com.example.imagesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.Utils.getDateFromTimestampWithFormat
import com.example.imagesearch.databinding.ListItemBinding

class SearchAdapter (private val mContext: Context) : RecyclerView.Adapter<SearchAdapter.Holder>() {

    var searchItem: ArrayList<ListItem> = ArrayList()

    fun clearItem() {
        searchItem.clear()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val searchItem = searchItem[position]

        Glide.with(mContext)
            .load(searchItem.thumbnail)
            .into(holder.thumbnail)
        holder.title.text = searchItem.title
        holder.dateTime.text = getDateFromTimestampWithFormat(
            searchItem.datetime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }
    override fun getItemCount(): Int {
        return searchItem.size
    }


    inner class Holder(val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        var thumbnail: ImageView = binding.thumbnail
        var title: TextView = binding.titleText
        var dateTime: TextView = binding.dateText
    }
}