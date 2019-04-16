package com.ossama.apps.starwarsuniverseapp.model.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ossama.apps.starwarsuniverseapp.model.data.api.APIClient
import com.ossama.apps.starwarsuniverseapp.model.data.api.service.DataService
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.SearchResponse
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter
import com.ossama.apps.starwarsuniverseapp.viewModel.SearchCharacterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCharacterRepository : ISearchCharacterRepository {

    private val dataService by lazy {
        APIClient.client.create(DataService::class.java)
    }

    override fun searchCharacter(name: String) : LiveData<List<RemoteSWCharacter>?> {
        val call = dataService.searchCharacter(name)

        val result = MutableLiveData<List<RemoteSWCharacter>?>()
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

                result.postValue(responseBody.results)
            }
        })
        return result
    }
}
