package com.example.watcht.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.modelResponse.PopularMovieListResponse
import com.example.watcht.data.modelResponse.movieDetails.MovieDetails
import com.example.watcht.ui.view.PopularMovies.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val apiServices: ApiRepository) {

    sealed class StatesMovies {
        object LoadingCall : StatesMovies()
        data class SuccessCall(val movieDetails: PopularMovieListResponse) : StatesMovies()
        object ErrorCall : StatesMovies()
    }

    fun getPopularMovies(page: Int): LiveData<StatesMovies> {
        val result = MutableLiveData<StatesMovies>()
        result.value = StatesMovies.LoadingCall

        apiServices.getPopularMovies(page).enqueue(object : Callback<PopularMovieListResponse> {
            override fun onResponse(
                call: Call<PopularMovieListResponse>,
                response: Response<PopularMovieListResponse>
            ) {

                if (response.isSuccessful) {
                    val movieList = response.body()
                    if (movieList != null) {
                        Log.i("King140","Success1")
                        result.value = StatesMovies.SuccessCall(movieList)
                    } else {
                        result.value = StatesMovies.ErrorCall
                    }
                } else {
                    result.value = StatesMovies.ErrorCall
                }

            }

            override fun onFailure(call: Call<PopularMovieListResponse>, t: Throwable) {
                result.value = StatesMovies.ErrorCall
                Log.i("Joaking", "error2")
            }

        })

        return result
    }

}