package com.example.watcht.ui.view.menuDetails.settings

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
class SettingsViewModel @Inject constructor(
    private val getMoviesListDataBaseUseCase: GetSavedMovieListFromDatabaseUseCase
) :ViewModel() {
    private val _data = MutableLiveData<List<PopularMovieListResponse.Result>>()
    val data: LiveData<List<PopularMovieListResponse.Result>> = _data

    fun getMovies() {

        viewModelScope.launch{
            val saved_data = getMoviesListDataBaseUseCase.getMovies()
            _data.postValue(saved_data)
            Log.i("Joaking","$data")
        }


    }


}