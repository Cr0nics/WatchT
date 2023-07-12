package com.example.watcht.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watcht.data.database.entities.MovieEntity


@Dao
interface MoviesDao {


    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies:MovieEntity)

}