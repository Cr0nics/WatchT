package com.example.watcht.data

import com.example.watcht.data.database.dao.MoviesDao
import com.example.watcht.data.database.entities.MovieEntity
import com.example.watcht.data.model.PopularMovieListResponse
import com.example.watcht.data.model.toDomain
import com.example.watcht.data.network.ApiServices
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ApiRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val moviesDao: MoviesDao
) {

    suspend fun getPopularMovies(page: Int) = apiServices.getPopularMovies(page)
    fun getMovieDetail(id: Int) = apiServices.getMovieDetails(id)

    suspend fun getMovieListFromDatabase():List<PopularMovieListResponse.Result>{

        val response = moviesDao.getAllMovies()
        return response.map { it.toDomain() }

    }

    suspend fun deleteMovieFromDatabase(item : MovieEntity){

        moviesDao.deleteMovie(item)


    }

    suspend fun insertMovieListToDatabase(item : MovieEntity){

        moviesDao.insertMovie(item)

    }




}