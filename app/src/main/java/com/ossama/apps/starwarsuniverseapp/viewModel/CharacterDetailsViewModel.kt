package com.ossama.apps.starwarsuniverseapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ossama.apps.starwarsuniverseapp.model.data.repository.CharacterDetailsRepository
import com.ossama.apps.starwarsuniverseapp.model.data.repository.ICharacterDetailsRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.Species
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter
import com.ossama.apps.starwarsuniverseapp.util.retrieveId

class CharacterDetailsViewModel(private val repository: ICharacterDetailsRepository = CharacterDetailsRepository()) :
    ObservableViewModel() {

    private val _character = MediatorLiveData<SWCharacter?>()

    private val _films = MediatorLiveData<List<Film>?>()

    private val _species = MediatorLiveData<Species?>()

    val character: LiveData<SWCharacter?> by lazy {
        _character
    }

    fun setupObservers(remoteSWCharacter: RemoteSWCharacter) {
        // setup the observer for watching the emitted films data
        val listFilms = mutableListOf<Film>()
        remoteSWCharacter.films.forEach { film ->
            val filmsLiveData = repository.fetchFilm(film.retrieveId())
            _films.addSource(filmsLiveData) {
                if (it != null) {
                    listFilms.add(it)
                    _films.value = listFilms.toList()
                }
            }
        }

        remoteSWCharacter.species.takeIf { it.isNotEmpty() }?.take(1)?.forEach {
            // setup the observer for watching the emitted species and planet data
            val speciesLiveData = repository.fetchSpecies(it.retrieveId())
            _species.addSource(speciesLiveData) { species ->
                if (species != null) {
                    _species.value = species.toSpecies()

                    val planetLiveData = repository.fetchPlanet(it.retrieveId())
                    _species.addSource(planetLiveData) { planet ->
                        if (planet != null) {
                            val speciesValue = _species.value?.copy(homeWorld = planet)
                            _species.value = speciesValue
                        }
                    }
                }
            }
        }

        setupSWCharacterObservers(remoteSWCharacter)
    }

    /**
     * Setup the observers to update the character live data when new films and/or species are emitted data
     */
    private fun setupSWCharacterObservers(remoteSWCharacter: RemoteSWCharacter) {
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
