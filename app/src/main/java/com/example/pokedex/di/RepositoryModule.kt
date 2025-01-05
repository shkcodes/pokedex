package com.example.pokedex.di

import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.data.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPokemonRepository(pokemonRepository: PokemonRepositoryImpl): PokemonRepository
}