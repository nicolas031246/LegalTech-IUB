package com.example.legaltech

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
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

// 1. Modelo de datos exclusivo para esta guía
data class TutelaGuiaData(
    val tituloCategoria: String = "Tutelas",
    val duracionLectura: String = "1:56 min",
    val tituloVideo: String = "Protege lo fundamental.",
    val tituloPrincipal: String = "Protección para tus derechos fundamentales",
    val resumenIntro: String = "La acción de tutela permite pedir a un juez la protección de derechos fundamentales amenazados o vulnerados. En Colombia, por regla general procede cuando no existe otro medio judicial eficaz; excepcionalmente, puede evitar un perjuicio irremediable.",
    val cuandoAyuda: List<String> = listOf(
        "Barreras de acceso a la salud que puedan afectar tus derechos fundamentales.",
        "Amenazas a derechos como educación, igualdad, vida digna o mínimo vital.",
        "Falta de respuesta a una petición, según las circunstancias del caso."
    ),
    val tenAMano: List<String> = listOf(
        "Tus datos y los de la entidad o persona que causa la afectación.",
        "Los hechos con fechas, el derecho afectado y la protección concreta que solicitas.",
        "Pruebas, gestiones previas y razones de la urgencia. Indica si ya presentaste otra tutela por los mismos hechos."
    ),
    val pasoDescripcion: String = "Verifica que la tutela sea la vía adecuada. Revisa, firma y presenta el escrito por un canal judicial habilitado; conserva la constancia y atiende las notificaciones. Puedes presentarla sin abogado.",
    val notaImportante: String = "La tutela no reemplaza todos los trámites ni garantiza un fallo favorable. El juez evalúa su procedencia. Si hay riesgo inmediato, busca atención urgente y apoyo de la Personería o la Defensoría del Pueblo."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutelasGuiaScreen(
    onBackClick: () -> Unit
) {
    val guia = remember { TutelaGuiaData() }
    var selectedStep by remember { mutableStateOf(1) } // 1: Cuenta tu caso, 2: Revisa, 3: Presenta
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF0B192C) // Azul institucional oscuro
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "ASESORÍA JURÍDICA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF94A3B8),
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = guia.tituloCategoria,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Guía + Tiempo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GUÍA PRÁCTICA • COLOMBIA",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF0061A4),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = guia.duracionLectura,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            // Reproductor de Video
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = guia.tituloVideo,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                text = "Una explicación breve, en palabras sencillas.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            // Título principal y descripción
            Text(
                text = guia.tituloPrincipal,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = guia.resumenIntro,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )

            // Secciones en tarjetas
            TutelaGuiaChecklistCard(
                titulo = "¿Cuándo puede ayudarte?",
                items = guia.cuandoAyuda
            )

            TutelaGuiaChecklistCard(
                titulo = "Ten a mano",
                items = guia.tenAMano
            )

            // Tarjeta interactiva de pasos a seguir ("¿Cómo seguir?")
            TutelaGuiaPasosCard(
                selectedStep = selectedStep,
                onStepSelected = { step -> selectedStep = step },
                descripcionPaso = guia.pasoDescripcion
            )

            // Disclaimer Legal
            Surface(
                color = Color(0xFFF0F4F9),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color(0xFF0061A4),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Ten en cuenta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFF0061A4)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = guia.notaImportante,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.DarkGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun TutelaGuiaChecklistCard(titulo: String, items: List<String>) {
    Surface(
        color = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(10.dp))
            items.forEach { item ->
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = "• ", fontWeight = FontWeight.Bold, color = Color(0xFF0061A4))
                    Text(text = item, fontSize = 13.sp, color = Color(0xFF475569))
                }
            }
        }
    }
}

@Composable
fun TutelaGuiaPasosCard(selectedStep: Int, onStepSelected: (Int) -> Unit, descripcionPaso: String) {
    Surface(
        color = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "¿Cómo seguir?", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TutelaGuiaPasoTab("1", "Cuenta tu caso", selectedStep == 1) { onStepSelected(1) }
                TutelaGuiaPasoTab("2", "Revisa", selectedStep == 2) { onStepSelected(2) }
                TutelaGuiaPasoTab("3", "Presenta", selectedStep == 3) { onStepSelected(3) }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = descripcionPaso, fontSize = 13.sp, color = Color(0xFF475569), lineHeight = 18.sp)
        }
    }
}

@Composable
fun TutelaGuiaPasoTab(numero: String, texto: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(onClick = onClick, shape = RoundedCornerShape(16.dp), color = if (isSelected) Color(0xFFE0F2FE) else Color.Transparent) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)) {
            Surface(shape = RoundedCornerShape(50), color = if (isSelected) Color(0xFF0061A4) else Color(0xFFE2E8F0), modifier = Modifier.size(20.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = numero, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (isSelected) Color.White else Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = texto, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal, color = if (isSelected) Color(0xFF0061A4) else Color.DarkGray)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
fun TutelasGuiaScreenPreview() {
    TutelasGuiaScreen(onBackClick = {})
}