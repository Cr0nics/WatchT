package com.example.watcht.ui.view.PopularMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.watcht.data.model.PopularMovieListResponse
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.PagingResource
import com.example.watcht.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class DataState {
    object Loading : DataState()
    object Error : DataState()
    data class Success(val response: PopularMovieListResponse) : DataState()
}


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: GetPopularMoviesUseCase,
    private val repository: ApiRepository
) : ViewModel() {


    private val _dataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = _dataState


    val loading = MutableLiveData<Boolean>()
    val movieList = Pager(PagingConfig(1)) {
        PagingResource(repository)
    }.flow.cachedIn(viewModelScope)


/*
    fun getMovieList(page: Int) {

        _dataState.value = DataState.Loading

        useCase.getPopularMovies(page).observeForever { stateCall ->
            when (stateCall) {
                is GetPopularMoviesUseCase.StatesMovies.SuccessCall -> {
                    Log.i("King140", "Success2")
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


*/
}



