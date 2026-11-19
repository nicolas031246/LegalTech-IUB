package com.example.legaltech

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerechoPeticionPaso2Screen(
    onBackClick: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val DarkBlueHeader = Color(0xFF0B192C)
    val PrimaryBlue = Color(0xFF0077BE)
    val LightBackground = Color(0xFFF4F8FA)

    // Control de pasos del formulario (1: Datos, 2: Peticiones/Pruebas, 3: Vista Previa)
    var currentStep by remember { mutableStateOf(1) }

    // Estados para los campos de datos (Paso 1)
    var nombrePeticionario by remember { mutableStateOf("") }
    var cedulaPeticionario by remember { mutableStateOf("") }
    var entidadDestinataria by remember { mutableStateOf("") }
    var hechosDelCaso by remember { mutableStateOf("") }

    // Estados para el Paso 2 (Peticiones y Pruebas)
    var peticionesConcretas by remember { mutableStateOf("") }
    var pruebasAnexas by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentStep) {
                            1 -> "Derecho de petición - Datos"
                            2 -> "Derecho de petición - Solicitud"
                            else -> "Vista Previa del Documento"
                        },
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep > 1) {
                            currentStep -= 1
                        } else {
                            onBackClick()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBlueHeader)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBackground)
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (currentStep) {
                // ==========================================
                // PASO 1: DATOS Y HECHOS
                // ==========================================
                1 -> {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "PASO 1 DE 3",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryBlue
                                )
                                Text(
                                    text = "Datos y hechos",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            LinearProgressIndicator(
                                progress = { 0.33f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp),
                                color = PrimaryBlue,
                                trackColor = Color(0xFFE2E8F0)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Datos y hechos",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = "Completa tus datos y describe lo ocurrido para preparar tu petición.",
                                fontSize = 14.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Nombre", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                            OutlinedTextField(
                                value = nombrePeticionario,
                                onValueChange = { nombrePeticionario = it },
                                placeholder = { Text("María Fernanda López") },
                                leadingIcon = {
                                    Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = PrimaryBlue)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Cédula", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                            OutlinedTextField(
                                value = cedulaPeticionario,
                                onValueChange = { cedulaPeticionario = it },
                                placeholder = { Text("1.023.456.789") },
                                leadingIcon = {
                                    Icon(imageVector = Icons.Default.Badge, contentDescription = null, tint = PrimaryBlue)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Entidad Destinataria", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                            OutlinedTextField(
                                value = entidadDestinataria,
                                onValueChange = { entidadDestinataria = it },
                                placeholder = { Text("Empresa de Acueducto") },
                                leadingIcon = {
                                    Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = PrimaryBlue)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Hechos del caso", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                            OutlinedTextField(
                                value = hechosDelCaso,
                                onValueChange = { hechosDelCaso = it },
                                placeholder = { Text("Describe qué sucedió y cuándo ocurrió.") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { currentStep = 2 },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Continuar",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                            Text(
                                text = "Siguiente: solicitud y revisión",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // ==========================================
                // PASO 2: PETICIONES Y PRUEBAS
                // ==========================================
                2 -> {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "PASO 2 DE 3",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryBlue
                                )
                                Text(
                                    text = "Solicitud y Pruebas",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            LinearProgressIndicator(
                                progress = { 0.66f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp),
                                color = PrimaryBlue,
                                trackColor = Color(0xFFE2E8F0)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Peticiones y Anexos",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = "Indica claramente qué solicitas a la entidad y qué pruebas aportas.",
                                fontSize = 14.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Peticiones Concretas (Pretensiones)", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                            OutlinedTextField(
                                value = peticionesConcretas,
                                onValueChange = { peticionesConcretas = it },
                                placeholder = { Text("Ej: 1. Que se realice la revisión del cobro...\n2. Que se devuelva el excedente...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Pruebas y Documentos Anexos", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                            OutlinedTextField(
                                value = pruebasAnexas,
                                onValueChange = { pruebasAnexas = it },
                                placeholder = { Text("Ej: Copia de factura, fotos del contador, cédula...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { currentStep = 1 },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(52.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Atrás")
                            }

                            Button(
                                onClick = { currentStep = 3 },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(52.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Ver Vista Previa", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // ==========================================
                // PASO 3: VISTA PREVIA Y FINALIZACIÓN
                // ==========================================
                3 -> {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "PASO 3 DE 3",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryBlue
                                )
                                Text(
                                    text = "Revisión final",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            LinearProgressIndicator(
                                progress = { 1.0f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp),
                                color = PrimaryBlue,
                                trackColor = Color(0xFFE2E8F0)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "BARRANQUILLA, COLOMBIA",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Señores:\n${if (entidadDestinataria.isNotBlank()) entidadDestinataria.uppercase() else "[ENTIDAD DESTINATARIA]"}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )

                                Text(
                                    text = "Asunto: Derecho de Petición (Artículo 23 Constitución Política de Colombia)",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PrimaryBlue
                                )

                                HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)

                                Text(
                                    text = "Yo, ${if (nombrePeticionario.isNotBlank()) nombrePeticionario else "[NOMBRE]"}, identificado(a) con Cédula de Ciudadanía número ${if (cedulaPeticionario.isNotBlank()) cedulaPeticionario else "[CÉDULA]"}, actuando en nombre propio, presento Derecho de Petición:\n\nHECHOS:\n${if (hechosDelCaso.isNotBlank()) hechosDelCaso else "[Hechos...]"}\n\nPETICIONES:\n${if (peticionesConcretas.isNotBlank()) peticionesConcretas else "[Peticiones...]"}\n\nPRUEBAS:\n${if (pruebasAnexas.isNotBlank()) pruebasAnexas else "[Pruebas...]"}",
                                    fontSize = 13.sp,
                                    color = Color(0xFF334155),
                                    lineHeight = 20.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Atentamente,\n\n_______________________\n${if (nombrePeticionario.isNotBlank()) nombrePeticionario else "Firma"}\nC.C. ${if (cedulaPeticionario.isNotBlank()) cedulaPeticionario else "_________________"}",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = { currentStep = 2 },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Modificar")
                            }

                            Button(
                                onClick = { onNavigateToHome() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Guardar", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DerechoPeticionPaso2Preview() {
    MaterialTheme {
        DerechoPeticionPaso2Screen(
            onBackClick = {},
            onNavigateToHome = {}
        )
    }
}