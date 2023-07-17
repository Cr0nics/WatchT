package com.example.watcht.di

import android.content.Context
import androidx.room.Room
import com.example.watcht.data.database.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MoviesDataBase:: class.java, "movies_database"
    ).build()

    @Singleton
    @Provides
    fun provideMoviesDao(db:MoviesDataBase) = db.getMovieDao()
}