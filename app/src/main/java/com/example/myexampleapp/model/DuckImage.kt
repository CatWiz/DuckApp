package com.example.myexampleapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
) : IDuckImage {
    @ColumnInfo(name = "save_date")
    var dateAdded: Date = Date()
    companion object {
        fun fromDTO(duckImageDTO: DuckImageDTO): DuckImage {
            return DuckImage(
                filePath = duckImageDTO.url,
            )
        }
    }

    override fun getType(): Int {
        return IDuckImage.DUCK_IMAGE
    }

    @Ignore
    override fun filePath(): String {
        return filePath
    }

    @Ignore
    override fun dateAdded(): Date {
        return dateAdded
    }
}
