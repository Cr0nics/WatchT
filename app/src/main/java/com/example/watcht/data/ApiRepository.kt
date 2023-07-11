package com.example.watcht.data

import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ApiRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    fun getPopularMovies(page: Int) = apiServices.getPopularMovies(page)
    fun getMovieDetail(id: Int) = apiServices.getMovieDetails(id)


}