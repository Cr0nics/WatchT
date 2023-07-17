package com.example.watcht.domain

import com.example.watcht.data.ApiRepository
import com.example.watcht.data.database.dao.MoviesDao
import com.example.watcht.data.model.PopularMovieListResponse
import javax.inject.Inject

class GetSavedMovieListFromDatabaseUseCase @Inject constructor(
    private val apiServices: ApiRepository,
    private val dao: MoviesDao
) {


    suspend fun getMovies():List<PopularMovieListResponse.Result>{
        return apiServices.getMovieListFromDatabase()
    }


}