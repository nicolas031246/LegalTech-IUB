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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LaboralGuiaData(
    val tituloCategoria: String = "Laboral",
    val duracionLectura: String = "2:15 min",
    val tituloVideo: String = "Tu trabajo y tus derechos",
    val tituloPrincipal: String = "Entiende y protege tus derechos laborales en Colombia",
    val resumenIntro: String = "El derecho laboral colombiano regula de manera integral el vínculo entre empleadores y trabajadores. Su propósito fundamental es garantizar condiciones de labor dignas, el pago oportuno de salarios, el respeto a las jornadas legales, el reconocimiento de prestaciones sociales obligatorias y la protección ante terminaciones injustificadas del contrato.",
    val cuandoAyuda: List<String> = listOf(
        "Falta de pago o retrasos injustificados en salarios, horas extras, recargos nocturnos, dominicales o festivos.",
        "Inconsistencias, liquidaciones erróneas o desconocimiento de cesantías, primas de servicios y vacaciones.",
        "Inquietudes respecto al cálculo de indemnizaciones por despido sin justa causa o finalización unilateral del vínculo.",
        "Situaciones delicadas de acoso laboral (mobbing), sobrecarga abusiva de funciones o modificación arbitraria de turnos.",
        "Omisiones graves por parte del empleador en los aportes obligatorios al sistema de seguridad social integral (salud, pensión y riesgos laborales)."
    ),
    val tenAMano: List<String> = listOf(
        "Tus datos de identificación completos y los datos de contacto y razón social de la empresa o empleador.",
        "Copia del contrato de trabajo firmado, anexos, otros sí o reglamentos internos de la compañía.",
        "Desprendibles de nómina, extractos bancarios de recepción de pagos y registros o planillas de control de horarios.",
        "Comunicaciones escritas, correos electrónicos, chats corporativos o notificaciones de memorandos y descargos.",
        "Un resumen cronológico y detallado de los hechos, especificando fechas clave y lo que pretendes reclamar."
    ),
    val pasoDescripcion: String = "1. Analiza con calma las pruebas y los soportes de tu situación laboral.\n2. Redacta de forma respetuosa pero firme tu solicitud de aclaración o pago ante el departamento de recursos humanos o gerencia, guardando copia y constancia de recibido (radicado).\n3. En caso de no obtener una solución satisfactoria, acude ante el Ministerio del Trabajo para solicitar una citación de conciliación o busca asesoría especializada.",
    val notaImportante: String = "Esta guía tiene un carácter puramente informativo y de orientación general; no constituye una asesoría jurídica vinculante ni reemplaza el acompañamiento de un abogado o autoridad competente. Los plazos para reclamar derechos laborales prescriben generalmente a los 3 años."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaboralDetailScreen(
    onBackClick: () -> Unit
) {
    val guia = remember { LaboralGuiaData() }
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

            LaboralChecklistCard(titulo = "¿Cuándo puede ayudarte?", items = guia.cuandoAyuda)
            LaboralChecklistCard(titulo = "Ten a mano", items = guia.tenAMano)
            LaboralPasosCard(selectedStep = selectedStep, onStepSelected = { step -> selectedStep = step }, descripcionPaso = guia.pasoDescripcion)

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
fun LaboralChecklistCard(titulo: String, items: List<String>) {
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
fun LaboralPasosCard(selectedStep: Int, onStepSelected: (Int) -> Unit, descripcionPaso: String) {
    Surface(color = Color(0xFFF8FAFC), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "¿Cómo seguir?", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                LaboralPasoTab("1", "Caso", selectedStep == 1) { onStepSelected(1) }
                LaboralPasoTab("2", "Revisa", selectedStep == 2) { onStepSelected(2) }
                LaboralPasoTab("3", "Presenta", selectedStep == 3) { onStepSelected(3) }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = descripcionPaso, fontSize = 13.sp, color = Color(0xFF475569), lineHeight = 20.sp)
        }
    }
}

@Composable
fun LaboralPasoTab(numero: String, texto: String, isSelected: Boolean, onClick: () -> Unit) {
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
fun LaboralDetailScreenPreview() {
    LaboralDetailScreen(onBackClick = {})
}