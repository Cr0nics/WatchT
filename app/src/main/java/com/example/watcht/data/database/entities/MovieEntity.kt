package com.example.watcht.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieEntity(
    @PrimaryKey
    val id: Int,


//    @ColumnInfo(name = "genre_ids")
//    val genreIds: List<Int>,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,


    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "title")
    val title: String,


    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

)