package com.example.watcht.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.watcht.data.database.dao.MoviesDao
import com.example.watcht.data.database.entities.MovieEntity
import com.example.watcht.data.model.Converters

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoviesDataBase:RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

}