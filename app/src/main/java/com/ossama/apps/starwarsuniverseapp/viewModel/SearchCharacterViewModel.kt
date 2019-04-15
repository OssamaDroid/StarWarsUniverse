package com.ossama.apps.starwarsuniverseapp.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ossama.apps.starwarsuniverseapp.BR
import com.ossama.apps.starwarsuniverseapp.model.data.repository.SearchCharacterRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter

class SearchCharacterViewModel(private val repository: SearchCharacterRepository = SearchCharacterRepository()) : ObservableViewModel() {

    var input = ""

    private val _characters = MediatorLiveData<List<SWCharacter>?>()

    val characters: LiveData<List<SWCharacter>?> by lazy {
        _characters
    }

    val isListVisible: Boolean
        @Bindable get() = characters.value?.isNotEmpty() ?: false

    fun search() {
        if (input.isNotBlank()) {
            val liveData = repository.searchCharacter(input)
            _characters.addSource(liveData) {
                _characters.value = it
                notifyPropertyChanged(BR.listVisible)
                _characters.removeSource(liveData)
            }
        }
    }
}
