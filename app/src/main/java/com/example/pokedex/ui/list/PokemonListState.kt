package com.example.pokedex.ui.list

import androidx.compose.runtime.Immutable
import com.example.pokedex.domain.PokemonListUiModel

@Immutable
data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemons: List<PokemonListUiModel> = emptyList(),
    val errorMessage: String? = null,
)