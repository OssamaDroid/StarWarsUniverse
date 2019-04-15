package com.ossama.apps.starwarsuniverseapp.model.entity

import java.io.Serializable


/**
 * Model for a given Star Wars character
 */

data class SWCharacter(
    var name: String,
    var birth_year: String,
    var height: String,
    var species: Species? = null,
    var films: List<Film?>? = null
): Serializable
