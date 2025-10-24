package com.example.urber.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.urber.ui.activity.ActivityScreen
import com.example.urber.ui.editprofile.EditProfileScreen
import com.example.urber.ui.home.HomeScreen
import com.example.urber.ui.register.RegisterScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"
    val rotasSemFooter = listOf("register","login")
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
            startDestination = "login",
            modifier = Modifier
                .padding(innerPadding)
        ){
            composable("register") { RegisterScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("services") { ServicesScreen(navController) }
            composable("activity") { ActivityScreen(navController) }
            composable("account") { AccountScreen(navController) }
            composable("edit") { EditProfileScreen() }

        }
    }
}