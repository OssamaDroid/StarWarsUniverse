package com.ossama.apps.starwarsuniverseapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ossama.apps.starwarsuniverseapp.model.data.repository.CharacterDetailsRepository
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.Planet
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.Species
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity._Species
import com.ossama.apps.starwarsuniverseapp.util.retrieveId

class CharacterDetailsViewModel(private val repository: CharacterDetailsRepository = CharacterDetailsRepository()) : ObservableViewModel() {

    private val _character = MediatorLiveData<SWCharacter?>()

    private val _films = MediatorLiveData<List<Film>?>()

    private val _species = MediatorLiveData<_Species?>()

    private val _planet = MediatorLiveData<Planet?>()

    val character: LiveData<SWCharacter?> by lazy {
        _character
    }

    fun setup(swCharacter: RemoteSWCharacter) {
        val listFilms = mutableListOf<Film>()
        swCharacter.films.forEach { film ->
            val liveData = repository.fetchFilm(film.retrieveId())
            _films.addSource(liveData) {
                if (it != null) {
                    listFilms.add(it)
                    _films.value = listFilms.toList()
                }
            }
        }


        var species = Species()
        swCharacter.species.takeIf { it.isNotEmpty() }?.take(1)?.forEach {
            val liveData = repository.fetchSpecies(it.retrieveId())
            _species.addSource(liveData) { species1 ->
                if (species1 != null) {
                    _species.value = species1
                    species = species1.toSpecies()

                    val liveData2 = repository.fetchPlanet(it.retrieveId())
                    _planet.addSource(liveData2) { planet ->
                        if (planet != null) {
                            _planet.value = planet
                            species = species.copy(homeWorld = planet)
                        }
                    }
                }
            }
        }


        _character.addSource(_films) {
            _character.value = swCharacter.toSWCharacter().copy(films = listFilms)
            swCharacter.films = listFilms.toList()
        }
        _character.addSource(_species) {
            _character.value = swCharacter.toSWCharacter().copy(species = species)
        }
    }
}
