package com.example.myexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myexampleapp.adapter.MyAdapter
import com.example.myexampleapp.databinding.ActivityMainBinding
import com.example.myexampleapp.model.DuckImage
import com.example.myexampleapp.model.DuckImageWithMessage
import com.example.myexampleapp.model.IDuckImage
import com.example.myexampleapp.model.MyViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var vm: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MyViewModel::class.java]

        vm.currentDuckImage.observe(this) {
            if (it != null)
            {
                Picasso
                    .get()
                    .load(it.filePath())
                    .placeholder(R.drawable.load_icon)
                    .into(binding.ivDuck)
            }
        }

        binding.btnNewDuck.setOnClickListener {
            vm.getNewDuckImage()
        }
        binding.btnSaveDuck.setOnClickListener {
            vm.saveCurrentDuckImage()
        }

        val rv = binding.rvDuckList
        val onClick = object : MyAdapter.OnClickDeleteListener {
            override fun onClickDelete(duckImage: IDuckImage, deleteType: Int) {
                when (deleteType) {
                    IDuckImage.DUCK_IMAGE -> {
                        vm.deleteDuckImage(duckImage as DuckImage)
                    }
                    IDuckImage.DUCK_IMAGE_WITH_MESSAGE -> {
                        vm.deleteDuckImageWithMessage(duckImage as DuckImageWithMessage)
                    }
                }
            }
        }
        rv.adapter = MyAdapter(vm.duckImagesList, onClick)
        rv.layoutManager = GridLayoutManager(this, 3)

        vm.duckImagesList.observe(this) {
            rv.adapter!!.notifyDataSetChanged()
        }
    }
}