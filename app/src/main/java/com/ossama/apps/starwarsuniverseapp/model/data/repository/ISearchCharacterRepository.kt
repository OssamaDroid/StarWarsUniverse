package com.ossama.apps.starwarsuniverseapp.model.data.repository

import androidx.lifecycle.LiveData
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter

interface ISearchCharacterRepository {
    fun searchCharacter(name: String) : LiveData<List<RemoteSWCharacter>?>
}
