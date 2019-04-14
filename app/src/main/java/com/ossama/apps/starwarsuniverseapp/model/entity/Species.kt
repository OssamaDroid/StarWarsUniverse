package com.ossama.apps.starwarsuniverseapp.model.entity

import com.ossama.apps.starwarsuniverseapp.model.entity.Planet

/**
 * Model for a given Species
 */

data class Species(
    val name: String,
    val language: String,
    val homeWorld: Planet
)