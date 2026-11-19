package com.example.legaltech

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Modelo de datos para el historial
data class PetitionItem(
    val id: String,
    val title: String,
    val entity: String,
    val date: String,
    val type: String,
    val status: String
)

// 2. Definición de las pantallas de la barra de navegación inferior
sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavScreen("home", "Inicio", Icons.Default.Home)
    object History : BottomNavScreen("history", "Historial", Icons.Default.History)
    object Plazos : BottomNavScreen("plazos", "Plazos", Icons.Default.DateRange)
    object Directorio : BottomNavScreen("directory", "Directorio", Icons.Default.Person)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val sampleHistory = remember {
        listOf(
            PetitionItem("1", "Reclamo de Facturación", "Empresa de Acueducto", "03 Nov 2026", "Derecho de Petición", "Generado"),
            PetitionItem("2", "Solicitud Cita Médica", "EPS Sanitas", "28 Oct 2026", "Tutela", "Generado"),
            PetitionItem("3", "Derecho de Petición - Cobro", "Alcaldía de Barranquilla", "15 Oct 2026", "Derecho de Petición", "Generado")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis Documentos",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0B192C) // Azul institucional superior
                )
            )
        },
        // La barra inferior vive SOLO en el Scaffold global de MainActivity
        containerColor = Color(0xFFF8FAFC),
        contentWindowInsets = WindowInsets(0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (sampleHistory.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aún no has generado ningún documento.",
                        color = Color(0xFF64748B),
                        fontSize = 15.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(sampleHistory) { item ->
                        HistoryCardItem(
                            item = item,
                            onClick = { onItemClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryCardItem(
    item: PetitionItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Tarjeta blanca limpia
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0F2FE)), // Fondo del ícono celeste claro
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    tint = Color(0xFF0284C7), // Ícono azul principal
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    color = Color(0xFF0F172A), // Texto oscuro
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.entity,
                    color = Color(0xFF64748B),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFE0F2FE)
                    ) {
                        Text(
                            text = item.type,
                            color = Color(0xFF0284C7),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }

                    Text(
                        text = item.date,
                        color = Color(0xFF94A3B8),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

// 3. Componente de la Barra de Navegación Inferior
@Composable
fun AppBottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.History,
        BottomNavScreen.Plazos,
        BottomNavScreen.Directorio
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screen ->
                val selected = currentRoute == screen.route

                NavigationItem(
                    screen = screen,
                    selected = selected,
                    onClick = { onNavigate(screen.route) }
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    screen: BottomNavScreen,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundModifier = if (selected) {
        Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFE0F2FE))
    } else {
        Modifier
    }

    val contentColor = if (selected) Color(0xFF0284C7) else Color(0xFF64748B)

    Surface(
        onClick = onClick,
        color = Color.Transparent,
        modifier = Modifier.clip(RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier
                .then(backgroundModifier)
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = screen.title,
                color = contentColor,
                fontSize = 11.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

// Previsualización para Android Studio
@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(
        onBackClick = {},
        onItemClick = {}
    )
}