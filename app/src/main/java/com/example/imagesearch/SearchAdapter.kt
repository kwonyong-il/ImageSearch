package com.example.imagesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.imagesearch.databinding.ListItemBinding

class SearchAdapter (val searchitem: MutableList<ListItem>) : RecyclerView.Adapter<Adapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Holder {
        val binding = ListItem.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.Img.
    }


    inner class Holder(val binding = ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}