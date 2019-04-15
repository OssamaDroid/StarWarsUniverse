package com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity

import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import java.io.Serializable

/**
 * Model used only for mapping the results coming from the service
 */

data class RemoteSWCharacter(
    var name: String,
    var birth_year: String,
    var height: String,
    var species: List<String>,
    var films: List<String>) : Serializable {

    fun toSWCharacter(): SWCharacter {
        return SWCharacter(name = name, birth_year = birth_year, height = height)
    }
}