package com.example.pokedex.api

import com.example.pokedex.api.model.ApiPokemonDetails
import com.example.pokedex.api.model.ApiPokemonList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun fetchPokemons(): ApiPokemonList

    @GET("pokemon/{id}")
    suspend fun fetchPokemonDetails(@Path("id") id: String): ApiPokemonDetails
}