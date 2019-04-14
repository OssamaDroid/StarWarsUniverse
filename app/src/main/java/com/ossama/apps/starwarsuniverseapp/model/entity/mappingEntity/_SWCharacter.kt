package com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity

import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter

/**
 * Model used only for mapping the results coming from the service
 * The ugly name is intended to avoid using it apart from the mapping
 */

data class _SWCharacter(
    var name: String,
    var birth_year: String,
    var height: String,
    var species: List<String>,
    var films: List<String>) {

    fun toBaseCharacterInfo(): SWCharacter {
        return SWCharacter(name = name, birth_year = birth_year, height = height)
    }
}