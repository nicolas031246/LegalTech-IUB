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

data class DerechoPeticionData(
    val tituloCategoria: String = "Derecho de Petición",
    val duracionLectura: String = "2:10 min",
    val tituloVideo: String = "Tu petición, paso a paso",
    val tituloPrincipal: String = "Una solicitud clara, fundamentada y oportuna",
    val resumenIntro: String = "El derecho de petición es un derecho fundamental consagrado en el artículo 23 de la Constitución Política de Colombia. Faculta a cualquier ciudadano para presentar solicitudes respetuosas ante autoridades públicas o entidades privadas por motivos de interés general o particular, obligando a la administración a emitir una respuesta pronta, clara, de fondo y congruente.",
    val cuandoAyuda: List<String> = listOf(
        "Solicitar información oficial detallada, expedición de copias autenticadas o certificaciones de documentos.",
        "Pedir aclaraciones formales sobre decisiones administrativas, liquidaciones de impuestos, cobros o estados de trámites.",
        "Requerir que una entidad del Estado actúe, intervenga o resuelva una situación concreta dentro de su órbita de competencia.",
        "Presentar quejas, reclamos o denuncias formales sobre fallas en la prestación de servicios públicos o institucionales."
    ),
    val tenAMano: List<String> = listOf(
        "Tus nombres y apellidos completos, número de cédula de ciudadanía y datos de notificación (dirección física y correo electrónico).",
        "Nombre exacto de la entidad, empresa u organismo público o privado al cual va dirigida la solicitud.",
        "Una relación clara, ordenada y cronológica de los hechos que motivan la petición.",
        "Peticiones específicas redactadas punto por punto, sin ambigüedades.",
        "Documentos de soporte, pruebas, recibos, facturas o radicados de solicitudes anteriores que poseas."
    ),
    val pasoDescripcion: String = "1. Organiza la información clave y redacta los fundamentos de hecho y de derecho de forma respetuosa.\n2. Radica el documento a través de los canales oficiales habilitados por la entidad (ventanilla física, correo electrónico institucional o plataforma web) y exige siempre tu número de radicado.\n3. Monitorea los plazos legales de respuesta (generalmente 15 días hábiles para información general).",
    val notaImportante: String = "Los términos legales varían según el objetivo específico de la petición (por ejemplo, consultas de documentos suelen resolverse en 10 días y solicitudes de copias en 10 días). El derecho garantiza una respuesta formal de fondo, pero no obliga a que la entidad resuelva de manera favorable a tus intereses."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerechoPeticionDetailScreen(
    onBackClick: () -> Unit
) {
    val guia = remember { DerechoPeticionData() }
    var selectedStep by remember { mutableStateOf(1) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Surface(modifier = Modifier.fillMaxWidth(), color = Color(0xFF0B192C)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "ASESORÍA JURÍDICA", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = Color(0xFF94A3B8), letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = guia.tituloCategoria, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "GUÍA PRÁCTICA • COLOMBIA", style = MaterialTheme.typography.labelMedium, color = Color(0xFF0061A4), fontWeight = FontWeight.Bold)
                Text(text = guia.duracionLectura, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }

            Box(
                modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f).clip(RoundedCornerShape(12.dp)).background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White, modifier = Modifier.size(48.dp))
                    Text(text = guia.tituloVideo, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }

            Text(text = "Una explicación detallada y en palabras sencillas.", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text(text = guia.tituloPrincipal, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(text = guia.resumenIntro, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray, lineHeight = 20.sp)

            PeticionChecklistCard(titulo = "¿Cuándo puede ayudarte?", items = guia.cuandoAyuda)
            PeticionChecklistCard(titulo = "Ten a mano", items = guia.tenAMano)
            PeticionPasosCard(selectedStep = selectedStep, onStepSelected = { step -> selectedStep = step }, descripcionPaso = guia.pasoDescripcion)

            Surface(color = Color(0xFFF0F4F9), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Color(0xFF0061A4), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = "Ten en cuenta", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF0061A4))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = guia.notaImportante, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray, lineHeight = 18.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun PeticionChecklistCard(titulo: String, items: List<String>) {
    Surface(color = Color(0xFFF8FAFC), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(10.dp))
            items.forEach { item ->
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = "• ", fontWeight = FontWeight.Bold, color = Color(0xFF0061A4))
                    Text(text = item, fontSize = 13.sp, color = Color(0xFF475569), lineHeight = 18.sp)
                }
            }
        }
    }
}

@Composable
fun PeticionPasosCard(selectedStep: Int, onStepSelected: (Int) -> Unit, descripcionPaso: String) {
    Surface(color = Color(0xFFF8FAFC), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "¿Cómo seguir?", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                PeticionPasoTab("1", "Caso", selectedStep == 1) { onStepSelected(1) }
                PeticionPasoTab("2", "Revisa", selectedStep == 2) { onStepSelected(2) }
                PeticionPasoTab("3", "Presenta", selectedStep == 3) { onStepSelected(3) }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = descripcionPaso, fontSize = 13.sp, color = Color(0xFF475569), lineHeight = 20.sp)
        }
    }
}

@Composable
fun PeticionPasoTab(numero: String, texto: String, isSelected: Boolean, onClick: () -> Unit) {
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

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC, widthDp = 392, heightDp = 800)
@Composable
fun DerechoPeticionDetailScreenPreview() {
    DerechoPeticionDetailScreen(onBackClick = {})
}