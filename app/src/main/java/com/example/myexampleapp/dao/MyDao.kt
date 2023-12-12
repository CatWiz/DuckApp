package com.example.myexampleapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myexampleapp.model.DuckImage
import java.util.Date

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuckImage(duckImage: DuckImage)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuckImages(vararg duckImages: DuckImage)

    @Update
    suspend fun updateDuckImage(duckImage: DuckImage)

    @Query("SELECT * FROM duck_images")
    suspend fun getAllDuckImages(): List<DuckImage>

    @Query("SELECT * FROM duck_images WHERE save_date BETWEEN :startDate AND :endDate")
    suspend fun getDuckImagesByDateRange(startDate: Date, endDate: Date): List<DuckImage>

    @Query("DELETE FROM duck_images")
    suspend fun deleteAllDuckImages()

    @Query("DELETE FROM duck_images WHERE file_path = :filePath AND save_date = :saveDate")
    suspend fun deleteDuckImage(filePath: String, saveDate: Date)


}