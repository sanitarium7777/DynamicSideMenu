package com.github.sanitarium7777.data.api

import com.github.sanitarium7777.data.model.MenuResponse
import retrofit2.http.GET

interface ApiService {

    @GET("s/fk3d5kg6cptkpr6/menu.json?dl=1")
    suspend fun getMenuJson(): MenuResponse

}