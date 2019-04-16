package com.ossama.apps.starwarsuniverseapp.model.data.api.service

import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.Planet
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.SearchResponse
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSpecies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataService {

    /**
     * Service for searching Star Wars characters by name
     */
    @GET("people")
    fun searchCharacter(@Query("search") name: String): Call<SearchResponse>

    @GET("planets/{id}")
    fun fetchPlanet(@Path("id") id: String): Call<Planet>

    @GET("films/{id}")
    fun fetchFilm(@Path("id") id: String): Call<Film>

    @GET("species/{id}")
    fun fetchSpecies(@Path("id") id: String): Call<RemoteSpecies>
}
