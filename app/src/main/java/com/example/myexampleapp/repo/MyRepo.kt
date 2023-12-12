package com.example.myexampleapp.repo

import com.example.myexampleapp.api.MyApi
import com.example.myexampleapp.api.MyRetrofitClient
import com.example.myexampleapp.dao.MyDao
import com.example.myexampleapp.model.DuckImage
import java.util.Date

class MyRepo(private val myDao: MyDao) {
    private val myRetrofitClient = MyRetrofitClient.getClient()
    private val myApi = myRetrofitClient.create(MyApi::class.java)

    suspend fun loadDuckImage(): DuckImage? {
        val response = myApi.getDuckImage()
        if (response.isSuccessful) {
            return DuckImage.fromAPIObject(response.body()!!)
        }
        return null
    }

    suspend fun insertDuckImage(duckImage: DuckImage) {
        myDao.insertDuckImage(duckImage)
    }

    suspend fun insertDuckImages(vararg duckImages: DuckImage) {
        myDao.insertDuckImages(*duckImages)
    }

    suspend fun getAllDuckImages(): List<DuckImage> {
        return myDao.getAllDuckImages()
    }

    suspend fun getDuckImagesByDateRange(startDate: Date, endDate: Date): List<DuckImage> {
        return myDao.getDuckImagesByDateRange(startDate, endDate)
    }

    suspend fun deleteAllDuckImages() {
        myDao.deleteAllDuckImages()
    }

    suspend fun updateDuckImage(duckImage: DuckImage) {
        myDao.updateDuckImage(duckImage)
    }

    suspend fun deleteDuckImage(duckImage: DuckImage) {
        myDao.deleteDuckImage(duckImage.filePath, duckImage.dateAdded)
    }
}