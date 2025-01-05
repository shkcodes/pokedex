package com.example.pokedex.ui.list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.pokedex.domain.PokemonListUiModel

class PokemonListScreenPreviewProvider : PreviewParameterProvider<PokemonListState> {
    override val values: Sequence<PokemonListState> = sequenceOf(
        PokemonListState(
            pokemons = (1..20).map { PokemonListUiModel("", "Pokemon $it") }
        ),
        PokemonListState(isLoading = true),
        PokemonListState(errorMessage = "Boom"),
    )
}
