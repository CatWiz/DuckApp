package com.example.myexampleapp.model

import java.util.Date

interface IDuckImage {
    fun getType(): Int

    fun filePath(): String

    fun dateAdded(): Date
    companion object {
        const val DUCK_IMAGE = 0
        const val DUCK_IMAGE_WITH_MESSAGE = 1
    }
}