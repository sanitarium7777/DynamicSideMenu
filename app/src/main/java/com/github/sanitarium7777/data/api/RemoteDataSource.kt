package com.github.sanitarium7777.data.api

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMenuJson() = apiService.getMenuJson()

}