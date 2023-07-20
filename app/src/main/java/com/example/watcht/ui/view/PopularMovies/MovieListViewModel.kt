package com.example.watcht.ui.view.PopularMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.PagingResource
import com.example.watcht.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject





@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {




    val movieList = Pager(PagingConfig(1)) {
        PagingResource(repository)
    }.flow.cachedIn(viewModelScope)


}



