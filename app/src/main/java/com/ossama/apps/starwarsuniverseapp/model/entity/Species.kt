package com.ossama.apps.starwarsuniverseapp.model.entity


/**
 * Model for a given Species
 */

data class Species(
    val name: String? = null,
    val language: String? = null,
    val homeWorld: Planet? = null
)
