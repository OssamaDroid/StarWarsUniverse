package com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity

import com.google.gson.annotations.SerializedName
import com.ossama.apps.starwarsuniverseapp.model.entity.Species


data class _Species(
    val name: String,
    val language: String,
    @SerializedName("homeworld")
    val homeWorld: String
) {
    fun toSpecies(): Species {
        return Species(name = name, language = language)
    }
}
