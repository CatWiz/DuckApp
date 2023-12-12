package com.example.myexampleapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myexampleapp.dao.MyDao

@Database(
    entities = [
        com.example.myexampleapp.model.DuckImage::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    com.example.myexampleapp.database.converters.DateConverter::class,
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: android.content.Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}