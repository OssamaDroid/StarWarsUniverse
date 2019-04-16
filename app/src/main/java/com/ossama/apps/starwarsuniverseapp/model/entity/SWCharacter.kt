package com.ossama.apps.starwarsuniverseapp.model.entity

import java.io.Serializable


/**
 * Model for a given Star Wars character
 */

data class SWCharacter(
    val name: String,
    val birth_year: String,
    val height: String,
    val species: Species? = null,
    val films: List<Film>? = null
): Serializable
