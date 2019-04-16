package com.ossama.apps.starwarsuniverseapp.util

/**
 * Function extension to retrieve the "id" from a given URL
 * An "id" is normally positioned on the last part of the URL
 *
 * Sample: "https://swapi.co/api/people/5" --> id = 5
 */
fun String.retrieveId(): String {
    val newString = if (this[lastIndex] == '/') this.substring(0, lastIndex) else this
    return newString.substringAfterLast("/", "")
}
