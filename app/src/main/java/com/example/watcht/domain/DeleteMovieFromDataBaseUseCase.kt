package com.example.watcht.domain

import com.example.watcht.data.ApiRepository
import com.example.watcht.data.database.entities.toDatabaseEntity
import com.example.watcht.data.model.movieDetails.MovieDetails
import javax.inject.Inject

class DeleteMovieFromDataBaseUseCase@Inject constructor(
    private val apiServices: ApiRepository,
) {


    suspend fun deleteMovie(movie: MovieDetails){
        apiServices.deleteMovieFromDatabase(movie.toDatabaseEntity())
    }


}