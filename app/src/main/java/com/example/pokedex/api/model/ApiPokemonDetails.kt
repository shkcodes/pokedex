package com.example.pokedex.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPokemonDetails(
    val name: String,
    val height: Int,
    val sprites: PokemonSprites
)

@JsonClass(generateAdapter = true)
data class PokemonSprites(
    val other: OtherSprites
)

@JsonClass(generateAdapter = true)
data class OtherSprites(
    val home: HomeSprites
)

@JsonClass(generateAdapter = true)
data class HomeSprites(@Json(name = "front_default") val frontDefault: String)
