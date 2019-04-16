package com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity

/**
 * Model used only for mapping the search response data coming from the web service
 */

data class SearchResponse(
    var results: List<RemoteSWCharacter>
)
