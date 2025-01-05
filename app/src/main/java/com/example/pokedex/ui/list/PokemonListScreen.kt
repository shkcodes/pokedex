package com.example.pokedex.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedex.domain.PokemonListUiModel
import com.example.pokedex.ui.common.ErrorComponent
import com.example.pokedex.ui.common.LoadingIndicator

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Content(state, onClick)
}

@Composable
private fun Content(state: PokemonListState, onClick: (String) -> Unit) {
    if (state.isLoading) {
        LoadingIndicator()
    } else if (state.errorMessage != null) {
        ErrorComponent(state.errorMessage)
    } else {
        PokemonList(state.pokemons, onClick)
    }
}

@Composable
private fun PokemonList(pokemons: List<PokemonListUiModel>, onClick: (String) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(pokemons) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(it.id) }) {
                Text(it.name, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview(
    @PreviewParameter(PokemonListScreenPreviewProvider::class) state: PokemonListState
) {
    Content(state) { }
}
