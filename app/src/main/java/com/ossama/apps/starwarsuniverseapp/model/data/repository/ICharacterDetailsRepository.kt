package com.ossama.apps.starwarsuniverseapp.model.data.repository

import androidx.lifecycle.LiveData
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.Planet
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity._Species

interface ICharacterDetailsRepository {
    fun fetchFilm(id: String) : LiveData<Film?>
    fun fetchPlanet(id: String) : LiveData<Planet?>
    fun fetchSpecies(id: String) : LiveData<_Species?>
}
