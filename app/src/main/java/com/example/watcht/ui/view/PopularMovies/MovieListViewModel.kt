package com.example.watcht.ui.view.PopularMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcht.data.modelResponse.PopularMovieListResponse
import com.example.watcht.data.ApiRepository
import com.example.watcht.domain.GetMoviesDetailsUseCase
import com.example.watcht.domain.GetPopularMoviesUseCase
import com.example.watcht.ui.view.Details.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject



sealed class DataState {
    object Loading : DataState()
    object Error : DataState()
    data class Success(val response: PopularMovieListResponse) : DataState()
}



@HiltViewModel
class MovieListViewModel @Inject constructor(private val useCase: GetPopularMoviesUseCase) : ViewModel() {

    private val _dataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = _dataState

    @Inject
    lateinit var repository: ApiRepository

    fun getMovieList(page:Int){

        _dataState.value = DataState.Loading

        useCase.getPopularMovies(page).observeForever { stateCall ->
            when (stateCall) {
                is GetPopularMoviesUseCase.StatesMovies.SuccessCall -> {
                    Log.i("King140","Success2")
                    _dataState.value =
                        DataState.Success(stateCall.movieDetails)
                }
                is GetPopularMoviesUseCase.StatesMovies.LoadingCall -> {
                    _dataState.value =
                        DataState.Loading
                }
                is GetPopularMoviesUseCase.StatesMovies.ErrorCall -> {
                    _dataState.value =
                        DataState.Error
                }
            }
        }
    }

}




