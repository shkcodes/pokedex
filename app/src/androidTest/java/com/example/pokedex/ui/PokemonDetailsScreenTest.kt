package com.example.pokedex.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.pokedex.HiltTestActivity
import com.example.pokedex.R
import com.example.pokedex.Screen
import com.example.pokedex.ui.details.PokemonDetailsScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonListScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.activity.intent.putExtra("pokemonId", "3") //needed so savedStateHandle can retrieve the id
        composeTestRule.setContent {
            PokemonDetailsScreen(Screen.PokemonDetails("3"), {})
        }
    }

    @Test
    fun displaying_pokemon_details() {
        val activity = composeTestRule.activity
        composeTestRule.onNodeWithText(activity.getString(R.string.pokemon_name, "Charmander"))
            .assertExists()
        composeTestRule.onNodeWithText(activity.getString(R.string.pokemon_height, 23))
            .assertExists()
    }
}