package com.example.myexampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myexampleapp.R
import com.example.myexampleapp.databinding.ImageItemBinding
import com.example.myexampleapp.databinding.ImageMessageItemBinding
import com.example.myexampleapp.model.DuckImage
import com.example.myexampleapp.model.DuckImageWithMessage
import com.example.myexampleapp.model.IDuckImage
import com.squareup.picasso.Picasso

class MyAdapter (
    private val myList: LiveData<List<IDuckImage>>,
    private val onClickDeleteListener: OnClickDeleteListener
) : RecyclerView.Adapter<MyAdapter.MyHolderBase>() {
    interface OnClickDeleteListener {
        fun onClickDelete(duckImage: IDuckImage, deleteType: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderBase {
        return when (viewType) {
            IDuckImage.DUCK_IMAGE -> {
                DuckHolder(
                    ImageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            IDuckImage.DUCK_IMAGE_WITH_MESSAGE -> {
                DuckWithMessageHolder(
                    ImageMessageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw Exception("Unknown viewType: $viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return myList.value!!.size
    }

    override fun onBindViewHolder(holder: MyHolderBase, position: Int) {
        holder.bind(myList.value!![position], onClickDeleteListener)
    }

    override fun getItemViewType(position: Int): Int {
        return myList.value!![position].getType()
    }

    abstract class MyHolderBase(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: IDuckImage, onClickDeleteListener: OnClickDeleteListener)
    }
    class DuckHolder(private val binding: ImageItemBinding) : MyHolderBase(binding) {
        override fun bind(item: IDuckImage, onClickDeleteListener: OnClickDeleteListener) {
            item as DuckImage
            Picasso
                .get()
                .load(item.filePath())
                .placeholder(R.drawable.load_icon)
                .into(binding.ivDuck)
            
            binding.btnDelete.setOnClickListener {
                 onClickDeleteListener.onClickDelete(item, IDuckImage.DUCK_IMAGE)
            }
        }
    }

    class DuckWithMessageHolder(private val binding: ImageMessageItemBinding) : MyHolderBase(binding) {
        override fun bind(item: IDuckImage, onClickDeleteListener: OnClickDeleteListener) {
            item as DuckImageWithMessage
            Picasso
                .get()
                .load(item.filePath())
                .placeholder(R.drawable.load_icon)
                .into(binding.ivDuck)

            binding.tvMessage.text = item.message

            binding.btnDelete.setOnClickListener {
                 onClickDeleteListener.onClickDelete(item, IDuckImage.DUCK_IMAGE_WITH_MESSAGE)
            }
        }
    }
}