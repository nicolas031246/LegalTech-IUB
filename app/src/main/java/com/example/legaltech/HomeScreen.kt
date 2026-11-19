package com.example.legaltech

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CallMade
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
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

@Composable
fun MainScreenContainer(
    onNavigateToLaboral: () -> Unit,
    onNavigateToPeticion: () -> Unit,
    onNavigateToServicios: () -> Unit,
    onNavigateToTutelas: () -> Unit,
    onNavigateToAssistant: () -> Unit,
    onNavigateToOfflineGuides: () -> Unit,
    currentRoute: String?,
    onTabSelected: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            LegalBottomBar(
                currentRoute = currentRoute,
                onTabSelected = onTabSelected
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HomeScreen(
                onNavigateToLaboral = onNavigateToLaboral,
                onNavigateToPeticion = onNavigateToPeticion,
                onNavigateToServicios = onNavigateToServicios,
                onNavigateToTutelas = onNavigateToTutelas,
                onNavigateToAssistant = onNavigateToAssistant,
                onNavigateToOfflineGuides = onNavigateToOfflineGuides
            )
        }
    }
}

@Composable
fun HomeScreen(
    onNavigateToLaboral: () -> Unit,
    onNavigateToPeticion: () -> Unit,
    onNavigateToServicios: () -> Unit,
    onNavigateToTutelas: () -> Unit,
    onNavigateToAssistant: () -> Unit,
    onNavigateToOfflineGuides: () -> Unit
) {
    val DarkBlueHeader = Color(0xFF0B192C)
    val LightBackground = Color(0xFFF4F8FA)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- PARTE 1: ENCABEZADO SUPERIOR OSCURO ---
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = DarkBlueHeader,
                        shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
                    )
                    .padding(24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            modifier = Modifier.size(56.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Gavel,
                                    contentDescription = "Logo",
                                    tint = DarkBlueHeader,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "ASESORÍA JURÍDICA",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "NICOLÁS GUERRERO",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF94A3B8),
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Orientación clara. Decisiones informadas.",
                        fontSize = 14.sp,
                        color = Color(0xFFE2E8F0)
                    )
                }
            }
        }

        // --- PARTE 2: TÍTULO DE SECCIÓN ---
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "ESTAMOS PARA ORIENTARTE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0088CC),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "¿En qué área legal necesitas orientación hoy?",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A),
                    lineHeight = 28.sp
                )
            }
        }

        // --- PARTE 3: TARJETAS DE ÁREAS (Cada una conectada a su pantalla específica) ---
        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LegalAreaCardNew(
                        title = "Laboral",
                        subtitle = "Tu trabajo y tus derechos",
                        icon = Icons.Default.Work,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToLaboral
                    )
                    LegalAreaCardNew(
                        title = "Derecho de Petición",
                        subtitle = "Solicitudes a entidades",
                        icon = Icons.Default.Assignment,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToPeticion
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LegalAreaCardNew(
                        title = "Servicios/Consumo",
                        subtitle = "Compras y servicios",
                        icon = Icons.Default.Home,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToServicios
                    )
                    LegalAreaCardNew(
                        title = "Tutelas",
                        subtitle = "Derechos fundamentales",
                        icon = Icons.Default.Gavel,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToTutelas
                    )
                }
            }
        }

        // --- PARTE 4: BOTONES DE ACCIÓN INFERIOR CON ÍCONOS ---
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onNavigateToOfflineGuides,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "🚨 Datos personales",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Descargar",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Button(
                    onClick = onNavigateToAssistant,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0077BE)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Iniciar Asistente Virtual",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ChatBubbleOutline,
                            contentDescription = "Chat",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Cuéntanos tu situación con tus propias palabras.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun LegalAreaCardNew(
    title: String,
    subtitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(115.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color(0xFF0077BE),
                    modifier = Modifier.size(24.dp)
                )
                Icon(
                    imageVector = Icons.Default.CallMade,
                    contentDescription = "Ir a",
                    tint = Color(0xFF64748B),
                    modifier = Modifier.size(16.dp)
                )
            }

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B),
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun LegalBottomBar(
    currentRoute: String?,
    onTabSelected: (String) -> Unit
) {
    // Usamos directamente las constantes seguras de AppRoutes para evitar crasheos
    val items = listOf(
        Triple("Inicio", Icons.Default.Home, AppRoutes.HOME),
        Triple("Historial", Icons.Default.History, AppRoutes.HISTORIAL),
        Triple("Plazos", Icons.Default.CalendarToday, AppRoutes.PLAZOS),
        Triple("Directorio", Icons.Default.Person, AppRoutes.DIRECTORIO)
    )

    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (title, icon, route) ->
                val isSelected = currentRoute == route

                val backgroundColor = if (isSelected) Color(0xFFE6F2FF) else Color.Transparent
                val contentColor = if (isSelected) Color(0xFF0077BE) else Color(0xFF64748B)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(backgroundColor)
                        .clickable { onTabSelected(route) }
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = contentColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = title,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = contentColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        MainScreenContainer(
            onNavigateToLaboral = {},
            onNavigateToPeticion = {},
            onNavigateToServicios = {},
            onNavigateToTutelas = {},
            onNavigateToAssistant = {},
            onNavigateToOfflineGuides = {},
            currentRoute = AppRoutes.HOME,
            onTabSelected = {}
        )
    }
}