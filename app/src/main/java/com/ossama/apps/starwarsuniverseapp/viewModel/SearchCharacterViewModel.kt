package com.ossama.apps.starwarsuniverseapp.viewModel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ossama.apps.starwarsuniverseapp.BR
import com.ossama.apps.starwarsuniverseapp.model.data.repository.SearchCharacterRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCharacterViewModel(private val repository: SearchCharacterRepository = SearchCharacterRepository()) : ObservableViewModel() {

    var input = ""

    private var _characters: MutableLiveData<List<SWCharacter>> = MutableLiveData()

    val characters: LiveData<List<SWCharacter>?> by lazy {
        _characters
    }

    val isListVisible: Boolean
        @Bindable get() = characters.value?.isNotEmpty() ?: false

    fun search() {
        if (input.isNotBlank()) {
            repository.searchCharacter(input, object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.e(SearchCharacterViewModel::class.java.simpleName, t.message)
                    setCharactersValue(null)
                }

                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    val responseBody = response.body()

                    if (responseBody == null) {
                        setCharactersValue(null)
                        return
                    }
                    val mappedResponse = mutableListOf<SWCharacter>()
                    responseBody.results.forEach{
                        mappedResponse.add(it.toBaseCharacterInfo())
                    }
                    setCharactersValue(mappedResponse.toList())
                }
            })
        }
    }

    fun setCharactersValue(characters: List<SWCharacter>?) {
        _characters.value = characters
        notifyPropertyChanged(BR.listVisible)
    }
}