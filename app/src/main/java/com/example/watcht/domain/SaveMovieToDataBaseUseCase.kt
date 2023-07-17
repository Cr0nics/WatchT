package com.example.watcht.domain

import com.example.watcht.data.ApiRepository
import com.example.watcht.data.database.entities.toDatabaseEntity
import com.example.watcht.data.model.movieDetails.MovieDetails
import javax.inject.Inject

class SaveMovieToDataBaseUseCase @Inject constructor(
    private val apiServices: ApiRepository,
) {


    suspend fun saveMovie(movie: MovieDetails){
        apiServices.insertMovieListToDatabase(movie.toDatabaseEntity())
    }


}