package com.example.watcht.data

import com.example.watcht.data.modelResponse.PopularMovieListResponse
import com.example.watcht.data.modelResponse.movieDetails.MovieDetails
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

sealed interface APIService {

    @GET("/movie/popular")
    suspend fun getMovieList(@Query("page") page: Int): Call<PopularMovieListResponse>

    @GET("/movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id")movieId:Int):Call<MovieDetails>

}