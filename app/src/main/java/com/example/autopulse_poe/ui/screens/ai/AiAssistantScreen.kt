package com.example.autopulse_poe.ui.screens.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AiAssistantScreen() {
    val messages = listOf(
        ChatMessage("Hello! I'm your AI Mechanic. How can I help you today?", false),
        ChatMessage("I noticed my engine is running a bit rough at idle.", true),
        ChatMessage("Based on your vehicle data, I see a pending P0300 code. This often points to worn spark plugs or a vacuum leak. Would you like me to guide you through a quick inspection?", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(NeonPurple.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = NeonPurple)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "AI Mechanic", fontSize = 18.sp, fontWeight = FontWeight.Black, color = Color.White)
                Text(text = "Online • Specialized in OBD-II", fontSize = 12.sp, color = NeonCyan)
            }
        }

        Divider(color = Color.White.copy(alpha = 0.1f))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message)
            }
        }

        // Input Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkSurface)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.weight(1f),
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Ask about a code or repair...",
                    color = Color.White.copy(alpha = 0.4f),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(
                onClick = { /* Send */ },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(NeonPurple)
            ) {
                Icon(Icons.Default.Send, contentDescription = null, tint = Color.White)
            }
        }
    }
}

data class ChatMessage(val text: String, val isUser: Boolean)

@Composable
fun ChatBubble(message: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Surface(
            color = if (message.isUser) NeonPurple else Color.White.copy(alpha = 0.1f),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 0.dp,
                bottomEnd = if (message.isUser) 0.dp else 16.dp
            )
        ) {
            Text(
                text = message.text,
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
