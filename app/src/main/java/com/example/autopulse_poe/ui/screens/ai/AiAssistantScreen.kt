package com.example.autopulse_poe.ui.screens.ai

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AiAssistantScreen(
    onBack: () -> Unit
) {

    // ============================================================
    // CHAT MESSAGES
    // ============================================================

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "Hello! I'm your AI Mechanic. How can I help you today?",
                false
            ),
            ChatMessage(
                "I noticed my engine is running a bit rough at idle.",
                true
            ),
            ChatMessage(
                "Based on your vehicle data, I see a pending P0300 code. " +
                        "This often points to worn spark plugs or a vacuum leak. " +
                        "Would you like me to guide you through a quick inspection?",
                false
            )
        )
    }

    var messageText by remember { mutableStateOf("") }

    val listState = rememberLazyListState()

    // ============================================================
    // SEND MESSAGE
    // ============================================================

    fun sendMessage() {

        val trimmedMessage = messageText.trim()

        if (trimmedMessage.isEmpty()) return

        messages.add(
            ChatMessage(
                text = trimmedMessage,
                isUser = true
            )
        )

        messageText = ""
    }

    // ============================================================
    // AUTO SCROLL
    // ============================================================

    LaunchedEffect(messages.size) {

        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    // ============================================================
    // SCREEN
    // ============================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding()
    ) {

        // ========================================================
        // HEADER
        // ========================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        AutoPulsePurple.copy(alpha = 0.14f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = AutoPulsePurple,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "AI Mechanic",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Vehicle diagnostic assistant",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            // ----------------------------------------------------
            // ONLINE INDICATOR
            // ----------------------------------------------------

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(AutoPulseSuccess)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "ONLINE",
                    color = AutoPulseSuccess,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline
        )

        // ========================================================
        // CHAT
        // ========================================================

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(
                top = 20.dp,
                bottom = 20.dp
            )
        ) {

            items(messages) { message ->

                ChatBubble(
                    message = message
                )
            }
        }

        // ========================================================
        // INPUT AREA
        // ========================================================

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 14.dp,
                        vertical = 12.dp
                    ),
                verticalAlignment = Alignment.Bottom
            ) {

                // ------------------------------------------------
                // TEXT INPUT
                // ------------------------------------------------

                OutlinedTextField(
                    value = messageText,
                    onValueChange = {
                        messageText = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(
                            minHeight = 52.dp
                        ),
                    placeholder = {

                        Text(
                            text = "Ask about a code or repair...",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 13.sp
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    ),
                    maxLines = 4,
                    shape = RoundedCornerShape(22.dp),
                    colors = OutlinedTextFieldDefaults.colors(

                        focusedTextColor =
                            MaterialTheme.colorScheme.onSurface,

                        unfocusedTextColor =
                            MaterialTheme.colorScheme.onSurface,

                        focusedBorderColor =
                            AutoPulsePurple,

                        unfocusedBorderColor =
                            MaterialTheme.colorScheme.outline,

                        focusedContainerColor =
                            MaterialTheme.colorScheme.surfaceVariant,

                        unfocusedContainerColor =
                            MaterialTheme.colorScheme.surfaceVariant,

                        cursorColor =
                            AutoPulsePurple
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                // ------------------------------------------------
                // SEND BUTTON
                // ------------------------------------------------

                IconButton(
                    onClick = {
                        sendMessage()
                    },
                    enabled = messageText.isNotBlank(),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(
                            if (messageText.isNotBlank()) {
                                AutoPulsePurple
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            }
                        )
                ) {

                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send message",
                        tint =
                            if (messageText.isNotBlank()) {
                                MaterialTheme.colorScheme.onSurface
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


// ================================================================
// CHAT MESSAGE
// ================================================================

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)


// ================================================================
// CHAT BUBBLE
// ================================================================

@Composable
fun ChatBubble(
    message: ChatMessage
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment =
            if (message.isUser) {
                Alignment.End
            } else {
                Alignment.Start
            }
    ) {

        // ========================================================
        // AI LABEL
        // ========================================================

        if (!message.isUser) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    start = 4.dp,
                    bottom = 5.dp
                )
            ) {

                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = AutoPulsePurple,
                    modifier = Modifier.size(13.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "AUTO PULSE AI",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
            }
        }

        // ========================================================
        // MESSAGE BUBBLE
        // ========================================================

        Surface(
            color =
                if (message.isUser) {
                    AutoPulsePurple
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },

            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart =
                    if (message.isUser) {
                        16.dp
                    } else {
                        4.dp
                    },
                bottomEnd =
                    if (message.isUser) {
                        4.dp
                    } else {
                        16.dp
                    }
            ),

            border =
                if (!message.isUser) {
                    androidx.compose.foundation.BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.outline
                    )
                } else {
                    null
                }
        ) {

            Text(
                text = message.text,
                color = if (message.isUser) {
                    White
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 11.dp
                )
            )
        }
    }
}