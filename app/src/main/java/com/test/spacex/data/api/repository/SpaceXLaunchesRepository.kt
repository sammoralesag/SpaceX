package com.test.spacex.data.api.repository

import com.test.spacex.data.api.mappers.toLaunch
import com.test.spacex.data.api.retrofit.ISpaceXAPI
import com.test.spacex.domain.models.Launch
import com.test.spacex.domain.models.RepositoryResponse
import com.test.spacex.domain.repository.interfaces.ISpaceXLaunchesRepository
import javax.inject.Inject

@Suppress("BlockingMethodInNonBlockingContext")
class SpaceXLaunchesRepository @Inject constructor(private val spacexApi: ISpaceXAPI) : ISpaceXLaunchesRepository {

    override suspend fun getLaunches(): RepositoryResponse<List<Launch>> {
        return try {
            val response = spacexApi.getLaunches()
            if (response.isSuccessful){
                val responseBody = response.body()
                val items = responseBody?.map { it.toLaunch() }.orEmpty()
                RepositoryResponse.Success(items)
            } else {
                RepositoryResponse.Failed(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e:Exception) {
            RepositoryResponse.Failed(e.message ?: "Unknown error")
        }

    }

}