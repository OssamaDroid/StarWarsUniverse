package com.ossama.apps.starwarsuniverseapp.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ossama.apps.starwarsuniverseapp.BR
import com.ossama.apps.starwarsuniverseapp.model.data.repository.CharacterDetailsRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity._SWCharacter

class CharacterDetailsViewModel(private val repository: CharacterDetailsRepository = CharacterDetailsRepository()) : ObservableViewModel() {

    var input = ""

    private val _character = MediatorLiveData<SWCharacter?>()

    private val films = MediatorLiveData<List<Film>?>()

    val character: LiveData<SWCharacter?> by lazy {
        _character
    }

    fun setup(swCharacter: _SWCharacter) {
        val listFilms = mutableListOf<Film?>()
        swCharacter.films.forEach { film ->
            val livedata = repository.fetchFilm(film)
            films.addSource(livedata) {
                listFilms.add(it)
                _character.value = swCharacter.toBaseCharacterInfo().copy(films = listFilms)
            }
        }
    }
}
