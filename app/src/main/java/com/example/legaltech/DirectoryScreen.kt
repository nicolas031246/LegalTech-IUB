package com.example.legaltech

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
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

// 1. Modelo de datos para las entidades del directorio
data class DirectoryItem(
    val id: String,
    val name: String,
    val category: String, // Ej: "Servicios Públicos", "Salud", "Entidad Pública"
    val address: String,
    val phone: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectoryScreen(
    onBackClick: () -> Unit
) {
    // Lista de ejemplo con entidades de interés
    val sampleEntities = remember {
        listOf(
            DirectoryItem("1", "Alcaldía Distrital de Barranquilla", "Entidad Pública", "Calle 34 # 43-31", "3182500"),
            DirectoryItem("2", "Empresa de Acueducto y Alcantarillado", "Servicios Públicos", "Cra. 54 # 68-150", "3718100"),
            DirectoryItem("3", "EPS Sanitas - Atención al Usuario", "Salud", "Calle 77B # 57-141", "018000919100"),
            DirectoryItem("4", "Personería Distrital", "Control y Vigilancia", "Paseo Bolívar # 34-52", "3392400"),
            DirectoryItem("5", "Consultorio Jurídico Universitario", "Apoyo Legal", "Calle 58 # 55-66", "3440000")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Directorio de Entidades",
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

            if (sampleEntities.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay entidades registradas en el directorio.",
                        color = Color(0xFF64748B),
                        fontSize = 15.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(sampleEntities) { entity ->
                        DirectoryCardItem(entity = entity)
                    }
                }
            }
        }
    }
}

@Composable
fun DirectoryCardItem(entity: DirectoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono de la entidad
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0F2FE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = null,
                    tint = Color(0xFF0284C7),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = entity.name,
                    color = Color(0xFF0F172A),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Categoría como badge sutil
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFFF1F5F9),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = entity.category,
                        color = Color(0xFF475569),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = entity.address,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = entity.phone,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

// Previsualización para Android Studio
@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
fun DirectoryScreenPreview() {
    DirectoryScreen(
        onBackClick = {}
    )
}