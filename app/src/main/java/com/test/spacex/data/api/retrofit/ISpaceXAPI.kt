package com.test.spacex.data.api.retrofit

import com.test.spacex.data.dto.launch.LaunchesResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface ISpaceXAPI {

    @GET("launches")
    suspend fun getLaunches(): Response<LaunchesResponseDTO>

}