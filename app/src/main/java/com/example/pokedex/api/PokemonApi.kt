package com.example.pokedex.api

import com.example.pokedex.api.model.ApiPokemonList
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon")
    suspend fun fetchPokemons(): ApiPokemonList
}