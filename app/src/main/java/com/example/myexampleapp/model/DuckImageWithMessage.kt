package com.example.myexampleapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import java.util.Date

@Entity(
    tableName = "duck_images_with_message",
    primaryKeys = ["file_path"]
)
data class DuckImageWithMessage(
    @ColumnInfo(name = "file_path")
    val filePath: String,
    @ColumnInfo(name = "message")
    val message: String,
) :  IDuckImage {
    override fun getType(): Int {
        return IDuckImage.DUCK_IMAGE_WITH_MESSAGE
    }

    @Ignore
    override fun filePath(): String {
        return filePath
    }

    @Ignore
    override fun dateAdded(): Date {
        return dateAdded
    }

    @ColumnInfo(name = "save_date")
    var dateAdded = Date()

    companion object {
        fun fromDTO(duckImageDTO: DuckImageDTO): DuckImageWithMessage {
            return DuckImageWithMessage(
                filePath = duckImageDTO.url,
                message = duckImageDTO.message,
            )
        }
    }
}