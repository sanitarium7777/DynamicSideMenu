package com.github.sanitarium7777.data.repository

import com.github.sanitarium7777.data.api.RemoteDataSource
import com.github.sanitarium7777.data.local.LocalDataSource
import com.github.sanitarium7777.data.model.MenuResponse

class MenuRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {

    suspend fun getMenuJson(): MenuResponse? {
        localDataSource.getMenuJson()?.let{
            return it
        }
        localDataSource.setMenuJson(remoteDataSource.getMenuJson())
        return localDataSource.getMenuJson()
    }

    fun getMenuJsonFromCache(): MenuResponse? {
        return localDataSource.getMenuJson()
    }

}