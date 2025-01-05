package com.example.pokedex.di

import com.example.pokedex.data.FakePokemonRepository
import com.example.pokedex.data.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryTestModule {

    @Binds
    abstract fun bindsFakePokemonRepository(pokemonRepository: FakePokemonRepository): PokemonRepository
}