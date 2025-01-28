package com.example.myexampleapp.repo

import com.example.myexampleapp.api.MyApi
import com.example.myexampleapp.api.MyRetrofitClient
import com.example.myexampleapp.dao.MyDao
import com.example.myexampleapp.model.DuckImage
import com.example.myexampleapp.model.DuckImageWithMessage
import com.example.myexampleapp.model.IDuckImage
import java.util.Date
import java.util.Random

class MyRepo(private val myDao: MyDao) {
    private val myRetrofitClient = MyRetrofitClient.getClient()
    private val myApi = myRetrofitClient.create(MyApi::class.java)

    suspend fun loadDuckImage(): IDuckImage? {
        val response = myApi.getDuckImage()
        if (response.isSuccessful) {
            if (Random().nextBoolean()) {
                return DuckImage.fromDTO(response.body()!!)
            }
            else {
                return DuckImageWithMessage.fromDTO(response.body()!!)
            }
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

    suspend fun insertDuckImageWithMessage(duck: DuckImageWithMessage) {
        myDao.insertDuckImageWithMessage(duck)
    }

    suspend fun insertDuckImagesWithMessage(vararg ducks: DuckImageWithMessage) {
        myDao.insertDuckImagesWithMessage(*ducks)
    }

    suspend fun getAllDuckImagesWithMessage(): List<DuckImageWithMessage> {
        return myDao.getAllDuckImagesWithMessage()
    }

    suspend fun getDuckImagesWithMessageByDateRange(startDate: Date, endDate: Date): List<DuckImageWithMessage> {
        return myDao.getDuckImagesWithMessageByDateRange(startDate, endDate)
    }

    suspend fun deleteAllDuckImagesWithMessage() {
        myDao.deleteAllDuckImagesWithMessage()
    }

    suspend fun updateDuckImageWithMessage(duck: DuckImageWithMessage) {
        myDao.updateDuckImageWithMessage(duck)
    }

    suspend fun deleteDuckImageWithMessage(duck: DuckImageWithMessage) {
        myDao.deleteDuckImageWithMessage(duck.filePath, duck.dateAdded)
    }
}