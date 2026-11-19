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

data class ServiciosConsumoData(
    val tituloCategoria: String = "Servicios/Consumo",
    val duracionLectura: String = "2:20 min",
    val tituloVideo: String = "Tus compras, tus servicios",
    val tituloPrincipal: String = "Reclama con información clara, soportes y garantías",
    val resumenIntro: String = "El Estatuto del Consumidor y las normativas de servicios públicos en Colombia protegen tus derechos frente a proveedores, comercializadores y empresas prestadoras. Esta guía te orienta de manera formal sobre cómo estructurar un reclamo efectivo cuando un bien presenta defectos, un servicio incumple lo pactado o existen cobros injustificados.",
    val cuandoAyuda: List<String> = listOf(
        "Adquisición de productos defectuosos, averiados o que no cumplen con las características y garantías anunciadas.",
        "Incumplimientos graves en los plazos de entrega pactados para mercancías o servicios contratados.",
        "Facturación desproporcionada, cobros no reconocidos, tarifas abusivas o cláusulas dudosas en contratos.",
        "Fallas reiteradas, suspensiones injustificadas o mala calidad en la prestación de servicios públicos domiciliarios o telecomunicaciones.",
        "Negativa injustificada por parte de los establecimientos comerciales para hacer efectiva una garantía legal."
    ),
    val tenAMano: List<String> = listOf(
        "Tus datos personales de identificación, número de cliente, contrato, factura o número de pedido.",
        "Soporte físico o digital de la factura de compra, recibo de pago, tiquete o comprobante de transacción.",
        "Evidencias fotográficas, videos del daño o defecto, y registros de las comunicaciones previas con la empresa.",
        "Un relato claro y detallado de lo ocurrido, especificando la solución concreta que exiges (reparación, cambio o devolución)."
    ),
    val pasoDescripcion: String = "1. Recolecta todas las facturas, recibos y evidencias fotográficas disponibles del producto o servicio.\n2. Presenta tu reclamación formal directa ante el departamento de atención al cliente de la empresa proveedora y conserva rigurosamente el número de radicado o ticket.\n3. Si la empresa no responde o niega el derecho sin justificación válida, evalúa escalar el caso ante la Superintendencia de Industria y Comercio (SIC) o la entidad de vigilancia respectiva.",
    val notaImportante: String = "Cada sector comercial y de servicios cuenta con plazos y normativas específicas de reclamación (por ejemplo, los servicios públicos exigen agotar los recursos de reposición y en subsidio de apelación ante la misma empresa). La devolución de dinero no siempre es inmediata y está sujeta a revisión técnica."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiciosConsumoDetailScreen(
    onBackClick: () -> Unit
) {
    val guia = remember { ServiciosConsumoData() }
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

            ServiciosChecklistCard(titulo = "¿Cuándo puede ayudarte?", items = guia.cuandoAyuda)
            ServiciosChecklistCard(titulo = "Ten a mano", items = guia.tenAMano)
            ServiciosPasosCard(selectedStep = selectedStep, onStepSelected = { step -> selectedStep = step }, descripcionPaso = guia.pasoDescripcion)

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
fun ServiciosChecklistCard(titulo: String, items: List<String>) {
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
fun ServiciosPasosCard(selectedStep: Int, onStepSelected: (Int) -> Unit, descripcionPaso: String) {
    Surface(color = Color(0xFFF8FAFC), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "¿Cómo seguir?", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ServiciosPasoTab("1", "Caso", selectedStep == 1) { onStepSelected(1) }
                ServiciosPasoTab("2", "Revisa", selectedStep == 2) { onStepSelected(2) }
                ServiciosPasoTab("3", "Presenta", selectedStep == 3) { onStepSelected(3) }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = descripcionPaso, fontSize = 13.sp, color = Color(0xFF475569), lineHeight = 20.sp)
        }
    }
}

@Composable
fun ServiciosPasoTab(numero: String, texto: String, isSelected: Boolean, onClick: () -> Unit) {
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
fun ServiciosConsumoDetailScreenPreview() {
    ServiciosConsumoDetailScreen(onBackClick = {})
}