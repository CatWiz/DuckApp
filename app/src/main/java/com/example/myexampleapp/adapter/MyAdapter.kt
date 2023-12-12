package com.example.myexampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myexampleapp.databinding.ImageItemBinding
import com.example.myexampleapp.model.DuckImage
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class MyAdapter (
    private val myList: LiveData<List<DuckImage>>,
    private val onClickDeleteListener: OnClickDeleteListener
) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    interface OnClickDeleteListener {
        fun onClickDelete(duckImage: DuckImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return myList.value!!.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(myList.value!![position], onClickDeleteListener)
    }

    class MyHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DuckImage, onClickDeleteListener: OnClickDeleteListener) {
            Picasso
                .get()
                .load(item.filePath)
                .into(binding.ivDuck)
            binding.btnDelete.setOnClickListener {
                 onClickDeleteListener.onClickDelete(item)
            }
        }
    }
}