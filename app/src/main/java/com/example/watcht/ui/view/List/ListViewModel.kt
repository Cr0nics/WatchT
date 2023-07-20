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

sealed class SavedData{
    object Loading : SavedData()
    data class Success(val response: List<PopularMovieListResponse.Result>) : SavedData()
}

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getMoviesListDataBaseUseCase: GetSavedMovieListFromDatabaseUseCase
) :ViewModel() {
    private val _dataSaved = MutableLiveData<SavedData>()
    val dataSaved: LiveData<SavedData> = _dataSaved

    fun getMoviesFromDataBase() {
        _dataSaved.postValue(SavedData.Loading)
        viewModelScope.launch{
            Log.i("JoakingData","DataReload")
            val savedData = getMoviesListDataBaseUseCase.getMovies()
            _dataSaved.postValue(SavedData.Success(savedData))
        }
    }



}