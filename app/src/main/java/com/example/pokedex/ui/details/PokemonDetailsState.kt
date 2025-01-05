package com.example.pokedex.ui.details

import androidx.compose.runtime.Immutable
import com.example.pokedex.domain.PokemonDetailsUiModel

@Immutable
data class PokemonDetailsState(
    val isLoading: Boolean = false,
    val pokemonDetails: PokemonDetailsUiModel? = null,
    val errorMessage: String? = null,
)