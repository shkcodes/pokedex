package com.example.pokedex.data

import com.example.pokedex.domain.PokemonDetailsUiModel
import com.example.pokedex.domain.PokemonListUiModel

interface PokemonRepository {
    suspend fun getPokemons(): Result<List<PokemonListUiModel>>
    suspend fun getPokemonDetails(id: String): Result<PokemonDetailsUiModel>
}