package com.example.myexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myexampleapp.adapter.MyAdapter
import com.example.myexampleapp.databinding.ActivityMainBinding
import com.example.myexampleapp.model.MyViewModel
import com.squareup.picasso.MemoryPolicy
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
                    .load(it.filePath)
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

        rv.adapter = MyAdapter(vm.duckImagesList)
        rv.layoutManager = GridLayoutManager(this, 3)

        vm.duckImagesList.observe(this) {
            rv.adapter!!.notifyItemChanged(it.size - 1)
        }
    }
}