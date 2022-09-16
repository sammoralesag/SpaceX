package com.test.spacex.domain.models

sealed class RepositoryResponse<T>() {
    data class Success<T>(val data: T) : RepositoryResponse<T>()
    data class Failed<T>(val reason: String) : RepositoryResponse<T>()
}
