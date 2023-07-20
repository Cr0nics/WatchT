package com.example.watcht.data.network

import com.example.watcht.data.model.PopularMovieListResponse
import com.example.watcht.data.model.movieDetails.MovieDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {



    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<PopularMovieListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Call<MovieDetails>



}