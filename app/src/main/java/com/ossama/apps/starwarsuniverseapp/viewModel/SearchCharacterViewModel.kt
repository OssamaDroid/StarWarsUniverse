package com.ossama.apps.starwarsuniverseapp.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.ossama.apps.starwarsuniverseapp.BR
import com.ossama.apps.starwarsuniverseapp.model.data.repository.ISearchCharacterRepository
import com.ossama.apps.starwarsuniverseapp.model.data.repository.SearchCharacterRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter

class SearchCharacterViewModel(private val repository: ISearchCharacterRepository = SearchCharacterRepository()) : ObservableViewModel() {

    var input = ""

    private val _characters = MediatorLiveData<List<RemoteSWCharacter>?>()

    val characters: LiveData<List<RemoteSWCharacter>?> by lazy {
        _characters
    }

    val isListVisible: Boolean
        @Bindable get() = characters.value?.isNotEmpty() ?: false

    private var isProgressBarVisibleLiveData = MutableLiveData<Boolean>()

    val isProgressBarVisible: Boolean
        @Bindable get() = isProgressBarVisibleLiveData.value ?: false

    fun search() {
        if (input.isNotBlank()) {
            changeProgressBarVisibility(isVisible = true)

            val swCharacterLiveData = repository.searchCharacter(input)
            _characters.addSource(swCharacterLiveData) {
                _characters.value = it
                _characters.removeSource(swCharacterLiveData)

                // Notifying the recycler view to update its visibility when new values are emitted
                notifyPropertyChanged(BR.listVisible)

                changeProgressBarVisibility(isVisible = false)
            }
        }
    }

    private fun changeProgressBarVisibility(isVisible: Boolean) {
        isProgressBarVisibleLiveData.value = isVisible

        // Notifying the progress bar to update its visibility
        notifyPropertyChanged(BR.progressBarVisible)
    }
}
