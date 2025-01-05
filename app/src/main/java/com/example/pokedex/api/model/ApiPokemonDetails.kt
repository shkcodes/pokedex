package com.example.pokedex.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPokemonDetails(
    val name: String,
    val height: Int,
    val sprites: ApiPokemonSprites
)

@JsonClass(generateAdapter = true)
data class ApiPokemonSprites(
    val other: ApiOtherSprites
)

@JsonClass(generateAdapter = true)
data class ApiOtherSprites(
    val home: ApiHomeSprites
)

@JsonClass(generateAdapter = true)
data class ApiHomeSprites(@Json(name = "front_default") val frontDefault: String)
