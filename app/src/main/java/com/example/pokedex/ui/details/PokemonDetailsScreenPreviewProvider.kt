package com.example.pokedex.ui.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.pokedex.domain.PokemonDetailsUiModel

class PokemonDetailsScreenPreviewProvider : PreviewParameterProvider<PokemonDetailsState> {
    override val values: Sequence<PokemonDetailsState> = sequenceOf(
        PokemonDetailsState(
            pokemonDetails = PokemonDetailsUiModel(
                "Charizard",
                23,
                "",
            )
        ),
        PokemonDetailsState(isLoading = true),
        PokemonDetailsState(errorMessage = "Boom"),
    )
}
