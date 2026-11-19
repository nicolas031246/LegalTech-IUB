package com.example.legaltech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LegalApp()
                }
            }
        }
    }
}

// Rutas centralizadas de la aplicación.
object AppRoutes {
    const val HOME = "home"
    const val HISTORIAL = "history"
    const val PLAZOS = "plazos"
    const val DIRECTORIO = "directory"

    const val LABORAL = "laboral_detail"
    const val PETICION = "peticion_detail"
    const val SERVICIOS = "servicios_detail"
    const val TUTELAS = "tutelas_detail"
    const val PASO2 = "peticion_paso2"
    const val ASISTENTE = "assistant"

    val TABS = setOf(HOME, HISTORIAL, PLAZOS, DIRECTORIO)
}

@Composable
fun LegalApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Solo muestra la barra inferior si la ruta actual es exactamente una de las 4 pestañas principales
    val showBottomBar = currentRoute != null && currentRoute in AppRoutes.TABS

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (showBottomBar) {
                LegalBottomBar(
                    currentRoute = currentRoute,
                    onTabSelected = { route ->
                        if (route != currentRoute) {
                            navController.navigate(route) {
                                popUpTo(AppRoutes.HOME) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LegalAppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LegalAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    fun open(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }
    val back: () -> Unit = { navController.popBackStack() }

    val backToHome: () -> Unit = {
        navController.navigate(AppRoutes.HOME) {
            popUpTo(AppRoutes.HOME) { inclusive = false }
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                onNavigateToLaboral = { open(AppRoutes.LABORAL) },
                onNavigateToPeticion = { open(AppRoutes.PETICION) },
                onNavigateToServicios = { open(AppRoutes.SERVICIOS) },
                onNavigateToTutelas = { open(AppRoutes.TUTELAS) },
                onNavigateToAssistant = { open(AppRoutes.ASISTENTE) },
                onNavigateToOfflineGuides = { open(AppRoutes.PASO2) }
            )
        }

        // Pestañas inferiores
        composable(AppRoutes.HISTORIAL) {
            HistoryScreen(
                onBackClick = backToHome,
                onItemClick = { }
            )
        }
        composable(AppRoutes.PLAZOS) {
            PlazosScreen(onBackClick = backToHome)
        }
        composable(AppRoutes.DIRECTORIO) {
            DirectoryScreen(onBackClick = backToHome)
        }

        // Pantallas de detalle
        composable(AppRoutes.LABORAL) {
            LaboralDetailScreen(onBackClick = back)
        }
        composable(AppRoutes.PETICION) {
            DerechoPeticionDetailScreen(onBackClick = back)
        }
        composable(AppRoutes.SERVICIOS) {
            ServiciosConsumoDetailScreen(onBackClick = back)
        }
        composable(AppRoutes.TUTELAS) {
            TutelasGuiaScreen(onBackClick = back)
        }

        composable(AppRoutes.PASO2) {
            DerechoPeticionPaso2Screen(onBackClick = back, onNavigateToHome = {})
        }

        // Registra el Asistente en el sistema de navegación
        composable(AppRoutes.ASISTENTE) {
            AssistantScreen(onBackClick = back)
        }
    }
}