package com.example.myexampleapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myexampleapp.MyApp
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = (application as MyApp).myRepo

    private var _currentDuckImage: MutableLiveData<DuckImage?> = MutableLiveData<DuckImage?>().apply {
        value = null
    }
    var currentDuckImage: LiveData<DuckImage?> = _currentDuckImage

    private var _duckImagesList: MutableLiveData<List<DuckImage>> = MutableLiveData<List<DuckImage>>().apply {
        value = emptyList()
    }
    val duckImagesList: LiveData<List<DuckImage>> = _duckImagesList

    init {
        viewModelScope.launch {
            _currentDuckImage.value = repo.loadDuckImage()
            _duckImagesList.value = repo.getAllDuckImages()
        }
    }

    fun getNewDuckImage() {
        viewModelScope.launch {
            val duckImage = repo.loadDuckImage()
            duckImage?.let {
                _currentDuckImage.value = it
            }
        }
    }

    private fun refreshDuckImagesList() {
        viewModelScope.launch {
            _duckImagesList.value = repo.getAllDuckImages().sortedByDescending { it.dateAdded }
        }
    }

    fun saveCurrentDuckImage() {
        viewModelScope.launch {
            _currentDuckImage.value?.let { repo.insertDuckImage(it) }
            refreshDuckImagesList()
        }
    }

    fun deleteDuckImage(duckImage: DuckImage) {
        viewModelScope.launch {
            repo.deleteDuckImage(duckImage)
            refreshDuckImagesList()
        }
    }
}