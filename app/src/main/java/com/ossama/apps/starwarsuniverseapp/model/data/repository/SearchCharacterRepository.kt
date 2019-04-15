package com.ossama.apps.starwarsuniverseapp.model.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ossama.apps.starwarsuniverseapp.model.data.api.APIClient
import com.ossama.apps.starwarsuniverseapp.model.data.api.service.DataService
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.SearchResponse
import com.ossama.apps.starwarsuniverseapp.viewModel.SearchCharacterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCharacterRepository {

    private val dataService by lazy {
        APIClient.client.create(DataService::class.java)
    }

    fun searchCharacter(name: String) : LiveData<List<SWCharacter>?> {
        val call = dataService.searchCharacter(name)

        val result = MutableLiveData<List<SWCharacter>?>()
        call.enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(SearchCharacterViewModel::class.java.simpleName, t.message)
                result.postValue(null)
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                val responseBody = response.body()

                if (responseBody == null) {
                    result.postValue(null)
                    return
                }
                val mappedResponse = mutableListOf<SWCharacter>()
                responseBody.results.forEach {
                    mappedResponse.add(it.toBaseCharacterInfo())
                }
                result.postValue(mappedResponse.toList())
            }
        })
        return result
    }
}
