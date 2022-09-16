package com.test.spacex.di

import com.test.spacex.data.api.repository.SpaceXLaunchesRepository
import com.test.spacex.domain.repository.interfaces.ISpaceXLaunchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideLaunchesRepository(implementation: SpaceXLaunchesRepository) : ISpaceXLaunchesRepository

}