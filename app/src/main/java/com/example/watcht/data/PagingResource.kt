package com.example.watcht.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.watcht.data.model.PopularMovieListResponse
import retrofit2.HttpException

class PagingResource(private val repository: ApiRepository) :
    PagingSource<Int, PopularMovieListResponse.Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieListResponse.Result> {
        return try {

            val currentPage = params.key ?: 1
            val response = repository.getPopularMovies(currentPage)
            val responseBody = response.body()!!.results

            val dataResponse = mutableListOf<PopularMovieListResponse.Result>()
            dataResponse.addAll(responseBody)

            LoadResult.Page(
                data = dataResponse,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovieListResponse.Result>): Int? {
        return null
    }
}