package com.example.pokedex.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pokedex.Screen.PokemonDetails

@Composable
fun PokemonDetailsScreen(
    pokemonDetails: PokemonDetails,
) {
    Text("Showing details for $pokemonDetails")
}