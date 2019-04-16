package com.ossama.apps.starwarsuniverseapp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Model for a given film
 */

data class Film(
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String
)
