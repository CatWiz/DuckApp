package com.example.myexampleapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.myexampleapp.api.DuckImageAPI
import com.squareup.moshi.Json
import java.util.Date

@Entity(
    tableName = "duck_images",
    primaryKeys = ["file_path", "save_date"]
)
data class DuckImage(
    @ColumnInfo(name = "file_path")
    @field:Json(name = "url")
    val filePath: String,

    @ColumnInfo(name = "save_date")
    var dateAdded: Date = Date(),
) {
    companion object {
        fun fromAPIObject(duckImageAPI: DuckImageAPI): DuckImage {
            return DuckImage(
                filePath = duckImageAPI.url,
            )
        }
    }
}
