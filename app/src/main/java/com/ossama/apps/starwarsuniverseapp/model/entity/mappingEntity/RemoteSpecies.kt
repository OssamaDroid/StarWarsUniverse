package com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity

import com.google.gson.annotations.SerializedName
import com.ossama.apps.starwarsuniverseapp.model.entity.Species

/**
 * Model used only for mapping the Star wars species data coming from the web service
 */

data class RemoteSpecies(
    val name: String,
    val language: String,
    @SerializedName("homeworld")
    val homeWorld: String
) {
    fun toSpecies(): Species {
        return Species(name = name, language = language)
    }
}
