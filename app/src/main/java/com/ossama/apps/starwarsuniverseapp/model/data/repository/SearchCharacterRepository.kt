package com.ossama.apps.starwarsuniverseapp.model.data.repository

import com.ossama.apps.starwarsuniverseapp.model.data.api.APIClient
import com.ossama.apps.starwarsuniverseapp.model.data.api.service.DataService
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.SearchResponse
import retrofit2.Callback

class SearchCharacterRepository {

    private val dataService by lazy {
        APIClient.client.create(DataService::class.java)
    }

    fun searchCharacter(name: String, callback: Callback<SearchResponse>) {
        val call = dataService.searchCharacter(name)
        call.enqueue(callback)
    }
}