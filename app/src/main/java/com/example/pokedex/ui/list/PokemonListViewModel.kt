package com.example.pokedex.ui.list

import androidx.lifecycle.viewModelScope
import com.example.pokedex.base.BaseViewModel
import com.example.pokedex.data.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseViewModel<PokemonListState>(PokemonListState()) {

    init {
        getPokemons()
    }

    private fun getPokemons() = viewModelScope.launch {
        updateState { state -> state.copy(isLoading = true) }
        pokemonRepository.getPokemons().onSuccess {
            updateState { state -> state.copy(isLoading = false, pokemons = it) }
        }.onFailure {
            updateState { state -> state.copy(isLoading = false, errorMessage = it.message) }
        }
    }
}