package com.example.pokedex.data

import com.example.pokedex.api.PokemonApi
import com.example.pokedex.api.model.ApiPokemon
import com.example.pokedex.domain.PokemonDetailsUiModel
import com.example.pokedex.domain.PokemonListUiModel
import com.example.pokedex.domain.toUiModel
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemons(): Result<List<PokemonListUiModel>> = runCatching {
        pokemonApi.fetchPokemons().results.map(ApiPokemon::toUiModel)
    }

    override suspend fun getPokemonDetails(id: String): Result<PokemonDetailsUiModel> =
        runCatching {
            pokemonApi.fetchPokemonDetails(id).toUiModel()
        }

}
