package com.example.watcht.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.model.PopularMovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val apiServices: ApiRepository) {



}