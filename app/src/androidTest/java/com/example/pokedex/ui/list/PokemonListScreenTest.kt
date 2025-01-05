package com.example.pokedex.ui.list

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import com.example.pokedex.HiltTestActivity
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
        composeTestRule.setContent { PokemonListScreen {  } }
    }

    @Test
    fun displaying_list_of_pokemon() {
        composeTestRule.onNodeWithTag(POKEMON_LIST_TEST_TAG)
            .onChildren().assertCountEquals(3)
        composeTestRule.onNodeWithTag(POKEMON_LIST_TEST_TAG)
            .onChildAt(0).assert(hasText("Bulbasaur"))
    }
}