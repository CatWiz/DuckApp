package com.example.myexampleapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import java.util.Date

@Entity(
    tableName = "duck_images",
    primaryKeys = ["file_path"]
)
data class DuckImage(
    @ColumnInfo(name = "file_path")
    @field:Json(name = "url")
    val filePath: String,
) {
    @ColumnInfo(name = "save_date")
    var dateAdded: Date = Date()
    companion object {
        fun fromAPIObject(duckImageDTO: DuckImageDTO): DuckImage {
            return DuckImage(
                filePath = duckImageDTO.url,
            )
        }
    }
}
