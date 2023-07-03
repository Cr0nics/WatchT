package com.example.watcht.data

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    fun getPopularMovies(page:Int) = apiServices.getPopularMovies(page)
    fun getMovieDetail(id:Int) = apiServices.getMovieDetails(id)


}