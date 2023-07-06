package com.example.watcht.ui.view.Details


import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.modelResponse.movieDetails.MovieDetails
import com.example.watcht.ui.view.PopularMovies.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

sealed class DetailState {


    object LoadingMovieDetails : DetailState()
    object ErrorMovieDetails : DetailState()
    data class SuccessMovieDetails(val response: MovieDetails) : DetailState()


}


@HiltViewModel
class MovieDetailViewModel @Inject constructor() : ViewModel() {


    private val _movieDetailState = MutableLiveData<DetailState>()
    val movieDetailState: LiveData<DetailState> = _movieDetailState

    @Inject
    lateinit var repository: ApiRepository

    fun getDetails(id: Int) {

        _movieDetailState.postValue(DetailState.LoadingMovieDetails)

        repository.getMovieDetail(id).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                when (response.code()) {
                    200 -> {
                        response.body().let { itBody ->

                            _movieDetailState.postValue(itBody?.let { DetailState.SuccessMovieDetails(it) })
                            Log.i("Joaking", "success")

                        }

                    }

                    401 -> {
                        _movieDetailState.postValue(DetailState.ErrorMovieDetails)
                    }
                    404 -> {
                        _movieDetailState.postValue(DetailState.ErrorMovieDetails)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                _movieDetailState.postValue(DetailState.ErrorMovieDetails)
            }
        })

    }


}