package com.ossama.apps.starwarsuniverseapp.model.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ossama.apps.starwarsuniverseapp.model.data.api.APIClient
import com.ossama.apps.starwarsuniverseapp.model.data.api.service.DataService
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.Planet
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity._Species
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsRepository {

    private val dataService by lazy {
        APIClient.client.create(DataService::class.java)
    }

    fun fetchFilm(id: String) : LiveData<Film?> {
        val call = dataService.fetchFilm(id)

        val result = MutableLiveData<Film?>()
        call.enqueue(object : Callback<Film> {
            override fun onFailure(call: Call<Film>, t: Throwable) {
                Log.e(CharacterDetailsRepository::class.java.simpleName, t.message)
                result.postValue(null)
            }

            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                val responseBody = response.body()

                if (responseBody == null) {
                    result.postValue(null)
                    return
                }

                result.postValue(responseBody)
            }
        })
        return result
    }

    fun fetchPlanet(id: String) : LiveData<Planet?> {
        val call = dataService.fetchPlanet(id)

        val result = MutableLiveData<Planet?>()
        call.enqueue(object : Callback<Planet> {
            override fun onFailure(call: Call<Planet>, t: Throwable) {
                Log.e(CharacterDetailsRepository::class.java.simpleName, t.message)
                result.postValue(null)
            }

            override fun onResponse(call: Call<Planet>, response: Response<Planet>) {
                val responseBody = response.body()

                if (responseBody == null) {
                    result.postValue(null)
                    return
                }

                result.postValue(responseBody)
            }
        })
        return result
    }

    fun fetchSpecies(id: String) : LiveData<_Species?> {
        val call = dataService.fetchSpecies(id)

        val result = MutableLiveData<_Species?>()
        call.enqueue(object : Callback<_Species> {
            override fun onFailure(call: Call<_Species>, t: Throwable) {
                Log.e(CharacterDetailsRepository::class.java.simpleName, t.message)
                result.postValue(null)
            }

            override fun onResponse(call: Call<_Species>, response: Response<_Species>) {
                val responseBody = response.body()

                if (responseBody == null) {
                    result.postValue(null)
                    return
                }

                result.postValue(responseBody)
            }
        })
        return result
    }
}
