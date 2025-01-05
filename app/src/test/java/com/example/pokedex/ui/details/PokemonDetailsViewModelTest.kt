package com.example.pokedex.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.example.pokedex.MainDispatcherRule
import com.example.pokedex.Screen.PokemonDetails
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.domain.PokemonDetailsUiModel
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PokemonDetailsViewModelTest {

    private val pokemonRepository = mockk<PokemonRepository>()

    private val savedStateHandle = mockk<SavedStateHandle>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createViewModel() = PokemonDetailsViewModel(pokemonRepository, savedStateHandle)

    private fun mockArgs() {
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { savedStateHandle.toRoute<PokemonDetails>() } returns PokemonDetails("3")
    }

    @Test
    fun `fetching data successfully updates state correctly`() = runTest {
        mockArgs()
        val uiModel = PokemonDetailsUiModel("name", 5, "")
        val underTest = createViewModel()

        coEvery { pokemonRepository.getPokemonDetails(any()) } returns Result.success(uiModel)
        underTest.state.test {
            awaitItem().isLoading.shouldBeFalse()
            awaitItem().isLoading.shouldBeTrue()
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                pokemonDetails.shouldBe(uiModel)
            }
            expectNoEvents()
        }
    }

    @Test
    fun `fetching data unsuccessfully updates state correctly`() = runTest {
        mockArgs()
        val underTest = createViewModel()

        coEvery { pokemonRepository.getPokemonDetails(any()) } returns Result.failure(Exception("boom"))
        underTest.state.test {
            awaitItem().isLoading.shouldBeFalse()
            awaitItem().isLoading.shouldBeTrue()
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                pokemonDetails.shouldBeNull()
                errorMessage.shouldBe("boom")
            }
            expectNoEvents()
        }
    }
}
