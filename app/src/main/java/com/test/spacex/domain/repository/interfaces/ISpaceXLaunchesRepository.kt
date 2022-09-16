package com.test.spacex.domain.repository.interfaces

import com.test.spacex.domain.models.Launch
import com.test.spacex.domain.models.RepositoryResponse

interface ISpaceXLaunchesRepository {

    suspend fun getLaunches() : RepositoryResponse<List<Launch>>

}