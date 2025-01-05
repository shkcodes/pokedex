package com.example.pokedex.domain

import com.example.pokedex.api.model.ApiPokemonDetails

data class PokemonDetailsUiModel(val name: String, val height: Int, val imageUrl: String)

fun ApiPokemonDetails.toUiModel() =
    PokemonDetailsUiModel(
        name.replaceFirstChar { it.uppercase() },
        height,
        sprites.other.home.frontDefault
    )