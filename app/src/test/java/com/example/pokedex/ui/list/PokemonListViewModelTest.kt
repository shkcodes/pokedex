package com.example.pokedex.ui.list

import app.cash.turbine.test
import com.example.pokedex.MainDispatcherRule
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.domain.PokemonListUiModel
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PokemonListViewModelTest {

    private val pokemonRepository = mockk<PokemonRepository>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createViewModel() = PokemonListViewModel(pokemonRepository)

    @Test
    fun `fetching data successfully updates state correctly`() = runTest {
        val uiModel = PokemonListUiModel("id", "name")
        val underTest = createViewModel()

        coEvery { pokemonRepository.getPokemons() } returns Result.success(listOf(uiModel))
        underTest.state.test {
            awaitItem().isLoading.shouldBeFalse()
            awaitItem().isLoading.shouldBeTrue()
            with(awaitItem()){
                isLoading.shouldBeFalse()
                pokemons.shouldBe(listOf(uiModel))
            }
            expectNoEvents()
        }
    }

    @Test
    fun `fetching data unsuccessfully updates state correctly`() = runTest {
        val underTest = createViewModel()

        coEvery { pokemonRepository.getPokemons() } returns Result.failure(Exception("boom"))
        underTest.state.test {
            awaitItem().isLoading.shouldBeFalse()
            awaitItem().isLoading.shouldBeTrue()
            with(awaitItem()){
                isLoading.shouldBeFalse()
                pokemons.shouldBeEmpty()
                errorMessage.shouldBe("boom")
            }
            expectNoEvents()
        }
    }
}
