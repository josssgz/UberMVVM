package com.example.urber

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"
    val rotasSemFooter = listOf("login","register")
    val deveMostrarFooter = currentRoute !in rotasSemFooter

    Scaffold (
        bottomBar = { if (deveMostrarFooter) {
            Footer(navController, currentRoute ?: "home")
            }
        }
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "register",
            modifier = Modifier
                .padding(innerPadding)
        ){
            composable("register") { RegisterScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("services") { ServicesScreen(navController) }
            composable("activity") { ActivityScreen(navController) }
            composable("account") { AccountScreen(navController) }
        }
    }


}