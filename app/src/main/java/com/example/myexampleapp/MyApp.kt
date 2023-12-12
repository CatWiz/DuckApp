package com.example.myexampleapp

import android.app.Application
import com.example.myexampleapp.database.MyDatabase
import com.example.myexampleapp.repo.MyRepo

class MyApp : Application() {

    val myDatabase by lazy {
        MyDatabase.getDatabase(
            this
        )
    }

    val myDao by lazy {
        myDatabase.myDao()
    }

    val myRepo by lazy {
        MyRepo(
            myDao
        )
    }
}