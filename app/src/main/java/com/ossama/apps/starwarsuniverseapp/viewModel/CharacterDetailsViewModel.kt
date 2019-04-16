package com.ossama.apps.starwarsuniverseapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ossama.apps.starwarsuniverseapp.model.data.repository.CharacterDetailsRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.Species
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter
import com.ossama.apps.starwarsuniverseapp.util.retrieveId

class CharacterDetailsViewModel(private val repository: CharacterDetailsRepository = CharacterDetailsRepository()) :
    ObservableViewModel() {

    private val _character = MediatorLiveData<SWCharacter?>()

    private val _films = MediatorLiveData<List<Film>?>()

    private val _species = MediatorLiveData<Species?>()

    val character: LiveData<SWCharacter?> by lazy {
        _character
    }

    fun setupObservers(remoteSWCharacter: RemoteSWCharacter) {
        val listFilms = mutableListOf<Film>()
        remoteSWCharacter.films.forEach { film ->
            val liveData = repository.fetchFilm(film.retrieveId())
            _films.addSource(liveData) {
                if (it != null) {
                    listFilms.add(it)
                    _films.value = listFilms.toList()
                }
            }
        }

        remoteSWCharacter.species.takeIf { it.isNotEmpty() }?.take(1)?.forEach {
            val liveData = repository.fetchSpecies(it.retrieveId())
            _species.addSource(liveData) { species ->
                if (species != null) {
                    _species.value = species.toSpecies()

                    val liveData2 = repository.fetchPlanet(it.retrieveId())
                    _species.addSource(liveData2) { planet ->
                        if (planet != null) {
                            val speciesValue = _species.value?.copy(homeWorld = planet)
                            _species.value = speciesValue
                        }
                    }
                }
            }
        }

        setupSWCharacterObserver(remoteSWCharacter)
    }

    private fun setupSWCharacterObserver(remoteSWCharacter: RemoteSWCharacter) {
        _character.addSource(_films) {
            _character.value =
                if (_character.value == null) remoteSWCharacter.toSWCharacter().copy(films = it)
                else _character.value!!.copy(films = it)
        }
        _character.addSource(_species) {
            _character.value =
                if (_character.value == null) remoteSWCharacter.toSWCharacter().copy(species = it)
                else _character.value!!.copy(species = it)
        }
    }
}
