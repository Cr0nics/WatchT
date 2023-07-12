package com.example.watcht.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.watcht.data.database.dao.MoviesDao
import com.example.watcht.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDataBase:RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

}