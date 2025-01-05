package com.example.pokedex.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.Screen.PokemonDetails
import com.example.pokedex.domain.PokemonDetailsUiModel
import com.example.pokedex.ui.common.ErrorComponent
import com.example.pokedex.ui.common.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    pokemonDetails: PokemonDetails,
    close: () -> Unit,
    viewModel: PokemonDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.pokemon_details)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = close) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
    ) {
        Content(state, Modifier.padding(it))
    }
}

@Composable
private fun Content(state: PokemonDetailsState, modifier: Modifier = Modifier) {
    if (state.isLoading) {
        LoadingIndicator(modifier)
    } else if (state.errorMessage != null || state.pokemonDetails == null) {
        ErrorComponent(
            state.errorMessage ?: stringResource(R.string.generic_error),
            modifier
        )
    } else {
        PokemonDetails(state.pokemonDetails, modifier)
    }
}

@Composable
private fun PokemonDetails(uiModel: PokemonDetailsUiModel, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = uiModel.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Text(
            stringResource(R.string.pokemon_name, uiModel.name),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            stringResource(R.string.pokemon_height, uiModel.height),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview(
    @PreviewParameter(PokemonDetailsScreenPreviewProvider::class) state: PokemonDetailsState
) {
    Content(state)
}