package com.example.movieapp.UI.Movies

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean
){
    override fun equals(other: Any?) = (other is Movie) && id == other.id

    override fun toString(): String {
        return "Movie(id=$id, title='$title', isSelected=$isSelected)"
    }
}