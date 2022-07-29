package com.example.movieapp.UI.Genres

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean
    ){
    override fun equals(other: Any?) = (other is Genre) && id == other.id

    override fun toString(): String {
        return "Genre(id=$id, name='$name', isSelected=$isSelected)"
    }
}

