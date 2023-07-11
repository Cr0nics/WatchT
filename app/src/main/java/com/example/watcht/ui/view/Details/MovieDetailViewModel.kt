package com.example.watcht.ui.view.Details


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.modelResponse.movieDetails.MovieDetails
import com.example.watcht.domain.GetMoviesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
class MovieDetailViewModel @Inject constructor(private val useCase: GetMoviesDetailsUseCase) :
    ViewModel() {
    private val _movieDetailState = MutableLiveData<DetailState>()
    val movieDetailState: LiveData<DetailState> = _movieDetailState

    fun getDetailsUseCase(id: Int) {
        _movieDetailState.value = DetailState.LoadingMovieDetails

        useCase.getDetails(id).observeForever { stateCall ->
            when (stateCall) {
                is GetMoviesDetailsUseCase.StateCall.SuccessCall -> {
                    _movieDetailState.value =
                        DetailState.SuccessMovieDetails(stateCall.movieDetails)
                }
                is GetMoviesDetailsUseCase.StateCall.LoadingCall -> {
                    _movieDetailState.value =
                        DetailState.LoadingMovieDetails
                }
                is GetMoviesDetailsUseCase.StateCall.ErrorCall -> {
                    _movieDetailState.value =
                        DetailState.ErrorMovieDetails
                }
            }
        }
    }
}