package com.example.watcht.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.ApiServices
import com.example.watcht.data.modelResponse.movieDetails.MovieDetails
import com.example.watcht.ui.view.Details.DetailState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.State
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val apiServices: ApiRepository) {

    sealed class StateCall {
        object LoadingCall : StateCall()
        data class SuccessCall(val movieDetails: MovieDetails) : StateCall()
        object ErrorCall : StateCall()
    }

    fun getDetails(id: Int): LiveData<StateCall> {
        val result = MutableLiveData<StateCall>()
        result.value = StateCall.LoadingCall

        apiServices.getMovieDetail(id).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.isSuccessful) {
                    val movieDetails = response.body()
                    if (movieDetails != null) {
                        result.value = StateCall.SuccessCall(movieDetails)
                    } else {
                        result.value = StateCall.ErrorCall
                    }
                } else {
                    result.value = StateCall.ErrorCall
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                result.value = StateCall.ErrorCall
            }
        })

        return result
    }
}