package com.example.myexampleapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myexampleapp.model.DuckImage
import com.example.myexampleapp.model.DuckImageWithMessage
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuckImageWithMessage(duck: DuckImageWithMessage)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuckImagesWithMessage(vararg ducks: DuckImageWithMessage)

    @Update
    suspend fun updateDuckImageWithMessage(duck: DuckImageWithMessage)

    @Query("SELECT * FROM duck_images_with_message")
    suspend fun getAllDuckImagesWithMessage(): List<DuckImageWithMessage>

    @Query("SELECT * FROM duck_images_with_message WHERE save_date BETWEEN :startDate AND :endDate")
    suspend fun getDuckImagesWithMessageByDateRange(startDate: Date, endDate: Date): List<DuckImageWithMessage>

    @Query("DELETE FROM duck_images_with_message")
    suspend fun deleteAllDuckImagesWithMessage()

    @Query("DELETE FROM duck_images_with_message WHERE file_path = :filePath AND save_date = :saveDate")
    suspend fun deleteDuckImageWithMessage(filePath: String, saveDate: Date)
}