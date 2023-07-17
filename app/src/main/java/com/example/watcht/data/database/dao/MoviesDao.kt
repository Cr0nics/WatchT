package com.example.watcht.data.database.dao

import androidx.room.*
import com.example.watcht.data.database.entities.MovieEntity


@Dao
interface MoviesDao {


    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies:MovieEntity)
    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}