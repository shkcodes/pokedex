package com.example.pokedex.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonListScreen(
    onClick: (String) -> Unit
) {
    Text("List of Pokemons", modifier = Modifier
        .padding(16.dp)
        .clickable {
            onClick("1")
        })
}