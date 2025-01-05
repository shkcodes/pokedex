package com.example.pokedex.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.pokedex.Screen.PokemonDetails
import com.example.pokedex.base.BaseViewModel
import com.example.pokedex.data.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<PokemonDetailsState>(PokemonDetailsState()) {

    init {
        getPokemonDetails(savedStateHandle.toRoute<PokemonDetails>().pokemonId)
    }

    private fun getPokemonDetails(id: String) = viewModelScope.launch {
        updateState { state -> state.copy(isLoading = true) }
        pokemonRepository.getPokemonDetails(id).onSuccess {
            updateState { state -> state.copy(isLoading = false, pokemonDetails = it) }
        }.onFailure {
            updateState { state -> state.copy(isLoading = false, errorMessage = it.message) }
        }
    }
}