package com.example.watcht.data.modelResponse

data class PopularMovieListResponse(
    val page: Int,
    val results: List<PupularMovieListResult>,
    val total_pages: Int,
    val total_results: Int
)