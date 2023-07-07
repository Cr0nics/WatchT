package com.example.watcht.ui.view.PopularMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcht.core.modelResponse.PopularMovieListResponse
import com.example.watcht.data.ApiRepository
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
class MovieListViewModel @Inject constructor() : ViewModel() {

    private val _dataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = _dataState

    @Inject
    lateinit var repository: ApiRepository

    fun getMovieList(page:Int){
        _dataState.postValue(DataState.Loading)
        repository.getPopularMovies(page = page).enqueue(object : Callback<PopularMovieListResponse>{
            override fun onResponse(
                call: Call<PopularMovieListResponse>,
                response: Response<PopularMovieListResponse>
            ) {
                when (response.code()) {
                    200 -> {

                        response.body().let { itBody ->
                            if (itBody?.results!!.isNotEmpty()) {
                                _dataState.postValue(DataState.Success(response.body()!!))
                                Log.i("Joaking","success")
                            }
                        }
                    }
                    400 -> {
                        _dataState.postValue(DataState.Error)
                        Log.i("Joaking","error1")

                    }
                    401 -> {
                        _dataState.postValue(DataState.Error)
                        Log.i("Joaking","error2")


                    }
                }
            }

            override fun onFailure(call: Call<PopularMovieListResponse>, t: Throwable) {
                _dataState.postValue(DataState.Error)
                Log.i("Joaking","error2")

            }
        })

}




}