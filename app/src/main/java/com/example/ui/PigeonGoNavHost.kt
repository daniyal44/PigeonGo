package com.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.Project

@Composable
fun PigeonGoNavHost(
    projects: List<Project>,
    onConnect: (String) -> Unit,
    onDelete: (Project) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        composable("splash") {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate("dashboard") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        
        composable("dashboard") {
            DashboardScreen(
                projects = projects,
                onConnect = { url ->
                    onConnect(url)
                    val encodedUrl = java.net.URLEncoder.encode(url, "UTF-8")
                    navController.navigate("tunnel/$encodedUrl")
                },
                onDelete = onDelete
            )
        }
        
        composable("tunnel/{url}") { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: "localhost:3000"
            val url = java.net.URLDecoder.decode(encodedUrl, "UTF-8")
            TunnelScreen(
                url = url,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
