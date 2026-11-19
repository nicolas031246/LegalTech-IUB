package com.example.legaltech

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf // <--- Esta es la importación que faltaba
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ChatMessage(
    val text: String,
    val isFromUser: Boolean,
    val timestamp: String
)

class AssistantViewModel : ViewModel() {

    var messageText by mutableStateOf("")
        private set

    val messages = mutableStateListOf(
        ChatMessage(
            text = "Hola, soy tu asistente jurídico. ¿En qué puedo orientarte hoy con tu derecho de petición o tutela?",
            isFromUser = false,
            timestamp = "9:41"
        )
    )

    var showProfessionalContact by mutableStateOf(false)
        private set

    fun onMessageChange(newText: String) {
        messageText = newText
    }

    fun sendMessage() {
        if (messageText.isBlank()) return

        val userQuery = messageText
        messages.add(ChatMessage(text = userQuery, isFromUser = true, timestamp = "9:41"))
        messageText = ""

        if (isCaseComplex(userQuery)) {
            showProfessionalContact = true
            messages.add(
                ChatMessage(
                    text = "Tu caso excede un trámite simple y requiere representación legal formal.",
                    isFromUser = false,
                    timestamp = "9:42"
                )
            )
        } else {
            messages.add(
                ChatMessage(
                    text = "He registrado tu consulta. Para este caso te sugiero estructurar un derecho de petición formal.",
                    isFromUser = false,
                    timestamp = "9:42"
                )
            )
        }
    }

    private fun isCaseComplex(query: String): Boolean {
        val complexKeywords = listOf("demanda", "proceso judicial", "fiscalía", "juez", "penal", "estafa", "embargo")
        return complexKeywords.any { query.contains(it, ignoreCase = true) }
    }
}