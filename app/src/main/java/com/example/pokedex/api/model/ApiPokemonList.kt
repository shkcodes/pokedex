package com.example.pokedex.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPokemonList(
    val count: Int,
    val results: List<ApiPokemon>
)

@JsonClass(generateAdapter = true)
data class ApiPokemon(
    val name: String,
    val url: String,
)
