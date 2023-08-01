package com.android.cinemaapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PopularResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<ResultResponse>,
    @SerialName("total_pages") val totalPager: Int,
    @SerialName("total_results") val totalResults: Int
)