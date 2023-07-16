package com.example.watcht.ui.view.List

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcht.data.model.PopularMovieListResponse
import com.example.watcht.domain.GetSavedMovieListFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val getMoviesListDataBaseUseCase: GetSavedMovieListFromDatabaseUseCase
) :ViewModel() {
    private val _dataSaved = MutableLiveData<List<PopularMovieListResponse.Result>>()
    val dataSaved: LiveData<List<PopularMovieListResponse.Result>> = _dataSaved

    fun getMoviesFromDataBase() {

        viewModelScope.launch{
            Log.i("JoakingData","DataReload")
            val savedData = getMoviesListDataBaseUseCase.getMovies()
            _dataSaved.postValue(savedData)
        }


    }


}