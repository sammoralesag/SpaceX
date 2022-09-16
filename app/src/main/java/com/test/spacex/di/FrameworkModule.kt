package com.test.spacex.di

import com.test.spacex.domain.framework.implementations.GlideImageLoader
import com.test.spacex.domain.framework.interfaces.IImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FrameworkModule {

    @Singleton
    @Binds
    abstract fun provideImageLoader(implementation: GlideImageLoader) : IImageLoader
}