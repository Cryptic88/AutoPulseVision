package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun EditProfileScreen(
    onBack: () -> Unit
) {

    var name by remember { mutableStateOf("Alex Smith") }
    var email by remember { mutableStateOf("alex.smith@example.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {

                Text(
                    text = "Edit Profile",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = AutoPulseText
                )

                Text(
                    text = "Manage your account information",
                    fontSize = 10.sp,
                    color = AutoPulseTextMuted
                )
            }
        }


        // ----------------------------------------------------
        // CONTENT
        // ----------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))


            // ------------------------------------------------
            // PROFILE IMAGE
            // ------------------------------------------------

            NeonCard(
                borderColor = AutoPulseMagenta.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        contentAlignment = Alignment.BottomEnd
                    ) {

                        // Profile image
                        Box(
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .background(
                                    AutoPulseMagenta.copy(alpha = 0.12f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = AutoPulseMagenta,
                                modifier = Modifier.size(58.dp)
                            )
                        }

                        // Camera button
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(AutoPulseCyan)
                        ) {

                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = "Change profile picture",
                                tint = AutoPulseBackground,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Profile Picture",
                        color = AutoPulseText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Tap the camera to change your picture",
                        color = AutoPulseTextMuted,
                        fontSize = 10.sp
                    )
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            // ------------------------------------------------
            // PERSONAL INFORMATION
            // ------------------------------------------------

            Text(
                text = "PERSONAL INFORMATION",
                color = AutoPulseCyan.copy(alpha = 0.75f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.4f)
            ) {

                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text(
                            text = "Full Name",
                            color = AutoPulseCyan
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = AutoPulseCyan
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = AutoPulseText,
                        unfocusedTextColor = AutoPulseText,
                        focusedBorderColor = AutoPulseCyan,
                        unfocusedBorderColor = AutoPulseBorder,
                        focusedLabelColor = AutoPulseCyan,
                        unfocusedLabelColor = AutoPulseTextMuted,
                        cursorColor = AutoPulseCyan
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text(
                            text = "Email Address",
                            color = AutoPulseCyan
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = AutoPulseCyan
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = AutoPulseText,
                        unfocusedTextColor = AutoPulseText,
                        focusedBorderColor = AutoPulseCyan,
                        unfocusedBorderColor = AutoPulseBorder,
                        focusedLabelColor = AutoPulseCyan,
                        unfocusedLabelColor = AutoPulseTextMuted,
                        cursorColor = AutoPulseCyan
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }


            Spacer(modifier = Modifier.height(24.dp))


            // ------------------------------------------------
            // SAVE BUTTON
            // ------------------------------------------------

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AutoPulseCyan,
                    contentColor = AutoPulseBackground
                ),
                shape = RoundedCornerShape(12.dp)
            ) {

                Text(
                    text = "Save Changes",
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
            }


            Spacer(modifier = Modifier.height(36.dp))


            // ------------------------------------------------
            // DANGER ZONE
            // ------------------------------------------------

            Text(
                text = "DANGER ZONE",
                color = AutoPulseError.copy(alpha = 0.8f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseError.copy(alpha = 0.45f)
            ) {

                Column {

                    Text(
                        text = "Delete Account",
                        color = AutoPulseText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Permanently delete your AutoPulse account and associated data.",
                        color = AutoPulseTextMuted,
                        fontSize = 10.sp,
                        lineHeight = 14.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AutoPulseError
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            AutoPulseError.copy(alpha = 0.7f)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Delete Account",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}