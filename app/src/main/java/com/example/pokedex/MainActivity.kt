package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedex.Screen.PokemonDetails
import com.example.pokedex.Screen.PokemonList
import com.example.pokedex.ui.details.PokemonDetailsScreen
import com.example.pokedex.ui.list.PokemonListScreen
import com.example.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                MainApp()
            }
        }
    }
}

@Composable
private fun MainApp() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = PokemonList,
            modifier = Modifier.padding(it)
        ) {
            composable<PokemonList> {
                PokemonListScreen { id ->
                    navController.navigate(PokemonDetails(id))
                }
            }
            composable<PokemonDetails> { backStackEntry ->
                PokemonDetailsScreen(backStackEntry.toRoute(), close = {
                    navController.navigateUp()
                })
            }
        }
    }
}

sealed interface Screen {
    @Serializable
    data object PokemonList : Screen

    @Serializable
    data class PokemonDetails(val pokemonId: String) : Screen
}