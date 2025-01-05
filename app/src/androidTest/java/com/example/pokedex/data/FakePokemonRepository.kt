package com.example.pokedex.data

import com.example.pokedex.domain.PokemonDetailsUiModel
import com.example.pokedex.domain.PokemonListUiModel
import javax.inject.Inject

class FakePokemonRepository @Inject constructor(): PokemonRepository {
    override suspend fun getPokemons(): Result<List<PokemonListUiModel>> {
        return Result.success(
            listOf(
                PokemonListUiModel("1", "Bulbasaur"),
                PokemonListUiModel("2", "Pikachu"),
                PokemonListUiModel("3", "Charmander"),
            )
        )
    }

    override suspend fun getPokemonDetails(id: String): Result<PokemonDetailsUiModel> {
        return Result.success(PokemonDetailsUiModel("Charmander", 23, ""))
    }
}