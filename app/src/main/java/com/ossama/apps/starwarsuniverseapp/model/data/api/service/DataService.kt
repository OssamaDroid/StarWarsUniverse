package com.ossama.apps.starwarsuniverseapp.model.data.api.service

import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    /**
     * Service for searching Star Wars characters by name
     */
    @GET("people")
    fun searchCharacter(@Query("search") name: String): Call<SearchResponse>
}