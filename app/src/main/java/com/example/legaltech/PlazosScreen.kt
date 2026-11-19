package com.example.legaltech

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Modelo de datos para los plazos legales
data class PlazoItem(
    val id: String,
    val title: String,
    val entity: String,
    val diasRestantes: Int,
    val fechaLímite: String,
    val tipoDocumento: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlazosScreen(
    onBackClick: () -> Unit
) {
    // Lista de ejemplo de trámites con sus plazos
    val samplePlazos = remember {
        listOf(
            PlazoItem("1", "Reclamo de Facturación", "Empresa de Acueducto", 3, "07 Nov 2026", "Derecho de Petición"),
            PlazoItem("2", "Solicitud Cita Médica", "EPS Sanitas", 1, "05 Nov 2026", "Tutela"),
            PlazoItem("3", "Cobro Indebido", "Alcaldía de Barranquilla", 12, "18 Nov 2026", "Derecho de Petición")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Seguimiento de Plazos",
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
                    containerColor = Color(0xFF0B192C) // Azul institucional
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

            // Tarjeta Informativa de Plazos Legales en Colombia
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2FE)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = Color(0xFF0284C7),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Recuerda: Los derechos de petición tienen un plazo legal de 15 días hábiles y las tutelas de 10 días.",
                        fontSize = 12.sp,
                        color = Color(0xFF0369A1),
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (samplePlazos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No tienes trámites pendientes con plazos activos.",
                        color = Color(0xFF64748B),
                        fontSize = 15.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(samplePlazos) { item ->
                        PlazoCardItem(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun PlazoCardItem(item: PlazoItem) {
    // Definimos si el plazo está urgente (3 días o menos)
    val esUrgente = item.diasRestantes <= 3
    val badgeBg = if (esUrgente) Color(0xFFFEE2E2) else Color(0xFFDCFCE7)
    val badgeText = if (esUrgente) Color(0xFFDC2626) else Color(0xFF15803D)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    color = Color(0xFF0F172A),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                // Badge de días restantes
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = badgeBg
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (esUrgente) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = badgeText,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = "${item.diasRestantes} días restantes",
                            color = badgeText,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.entity,
                color = Color(0xFF64748B),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Divider(color = Color(0xFFF1F5F9), thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.tipoDocumento,
                    color = Color(0xFF0284C7),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Vence: ${item.fechaLímite}",
                    color = Color(0xFF94A3B8),
                    fontSize = 12.sp
                )
            }
        }
    }
}

// Previsualización para Android Studio
@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
fun PlazosScreenPreview() {
    PlazosScreen(
        onBackClick = {}
    )
}