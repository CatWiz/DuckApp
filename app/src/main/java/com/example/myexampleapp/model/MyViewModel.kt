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

    private var _currentDuckImage: MutableLiveData<IDuckImage?> = MutableLiveData<IDuckImage?>().apply {
        value = null
    }
    var currentDuckImage: LiveData<IDuckImage?> = _currentDuckImage

    private var _duckImagesList: MutableLiveData<List<IDuckImage>> = MutableLiveData<List<IDuckImage>>().apply {
        value = emptyList()
    }
    val duckImagesList: LiveData<List<IDuckImage>> = _duckImagesList

    init {
        viewModelScope.launch {
            _currentDuckImage.value = repo.loadDuckImage()
            refreshDuckImagesList()
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
            _duckImagesList.value = repo.getAllDuckImages()
                .plus(repo.getAllDuckImagesWithMessage())
                .sortedByDescending { it.dateAdded() }
        }
    }

    fun saveCurrentDuckImage() {
        viewModelScope.launch {
            _currentDuckImage.value?.let {
                if (it is DuckImage) {
                    repo.insertDuckImage(it)
                } else if (it is DuckImageWithMessage) {
                    repo.insertDuckImageWithMessage(it)
                }
            }
            refreshDuckImagesList()
        }
    }

    fun deleteDuckImage(duckImage: DuckImage) {
        viewModelScope.launch {
            repo.deleteDuckImage(duckImage)
            refreshDuckImagesList()
        }
    }

    fun deleteDuckImageWithMessage(duckImage: DuckImageWithMessage) {
        viewModelScope.launch {
            repo.deleteDuckImageWithMessage(duckImage)
            refreshDuckImagesList()
        }
    }
}