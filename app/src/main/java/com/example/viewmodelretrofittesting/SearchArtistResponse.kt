package com.example.viewmodelretrofittesting

data class SearchArtistResponse(
    val resultCount: Int,
    val results: List<Result>
)