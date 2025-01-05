package com.example.pokedex.data

import com.example.pokedex.api.PokemonApi
import com.example.pokedex.api.model.ApiHomeSprites
import com.example.pokedex.api.model.ApiOtherSprites
import com.example.pokedex.api.model.ApiPokemon
import com.example.pokedex.api.model.ApiPokemonDetails
import com.example.pokedex.api.model.ApiPokemonList
import com.example.pokedex.api.model.ApiPokemonSprites
import com.example.pokedex.domain.PokemonDetailsUiModel
import com.example.pokedex.domain.PokemonListUiModel
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PokemonRepositoryTest {

    private val pokemonApi = mockk<PokemonApi>()
    private fun createRepository() = PokemonRepositoryImpl(pokemonApi)

    @Test
    fun `fetching pokemon list successfully`() = runTest {
        val response = ApiPokemonList(2, listOf(ApiPokemon("name", "https://url.com/id/")))
        val model = PokemonListUiModel("id", "Name")
        val underTest = createRepository()
        coEvery { pokemonApi.fetchPokemons() } returns response
        underTest.getPokemons().shouldBe(Result.success(listOf(model)))
    }

    @Test
    fun `fetching pokemon list unsuccessfully`() = runTest {
        val exception = Exception()
        val underTest = createRepository()
        coEvery { pokemonApi.fetchPokemons() } throws exception
        underTest.getPokemons().shouldBe(Result.failure(exception))
    }

    @Test
    fun `fetching pokemon details successfully`() = runTest {
        val response =
            ApiPokemonDetails(
                "name", 3, ApiPokemonSprites(
                    ApiOtherSprites(ApiHomeSprites("url"))
                )
            )
        val model = PokemonDetailsUiModel("Name", 3, "url")
        val underTest = createRepository()
        coEvery { pokemonApi.fetchPokemonDetails(any()) } returns response
        underTest.getPokemonDetails("3").shouldBe(Result.success(model))
    }

    @Test
    fun `fetching pokemon details unsuccessfully`() = runTest {
        val exception = Exception()
        val underTest = createRepository()
        coEvery { pokemonApi.fetchPokemonDetails(any()) } throws exception
        underTest.getPokemonDetails("3").shouldBe(Result.failure(exception))
    }

}