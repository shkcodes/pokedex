package com.example.pokedex.domain

import com.example.pokedex.api.model.ApiPokemon

data class PokemonListUiModel(val id: String, val name: String)

fun ApiPokemon.toUiModel(): PokemonListUiModel {
    val parts = url.split("/")
    return PokemonListUiModel(
        id = parts[parts.lastIndex - 1],
        name.replaceFirstChar { it.uppercase() })
}