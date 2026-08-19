package com.example.autopulse_poe.ui.screens.fuel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Canvas
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun FuelManagementScreen() {

    var showFuelDialog by remember { mutableStateOf(false) }

    var litres by remember { mutableStateOf("") }
    var pricePerLitre by remember { mutableStateOf("") }

    val totalCost = remember(litres, pricePerLitre) {
        val l = litres.toDoubleOrNull() ?: 0.0
        val p = pricePerLitre.toDoubleOrNull() ?: 0.0
        l * p
    }

    if (showFuelDialog) {
        AlertDialog(
            onDismissRequest = {
                showFuelDialog = false
            },
            title = {
                Text(
                    text = "Add Fuel Purchase",
                    fontWeight = FontWeight.Black
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedTextField(
                        value = litres,
                        onValueChange = { litres = it },
                        label = {
                            Text("Litres")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = pricePerLitre,
                        onValueChange = { pricePerLitre = it },
                        label = {
                            Text("Price per litre (R)")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (totalCost > 0) {
                        Text(
                            text = "Estimated total: R${"%.2f".format(totalCost)}",
                            color = AutoPulseCyan,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showFuelDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AutoPulseCyan
                    )
                ) {
                    Text(
                        text = "SAVE",
                        color = Color.Black,
                        fontWeight = FontWeight.Black
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showFuelDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(bottom = 32.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Text(
            text = "Fuel Management",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = AutoPulseText
        )

        Text(
            text = "Track consumption, fuel costs & efficiency",
            color = AutoPulseTextSecondary,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // FUEL EFFICIENCY HERO
        // ----------------------------------------------------

        NeonCard(
            borderColor = AutoPulseSuccess.copy(alpha = 0.65f)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            AutoPulseSuccess.copy(alpha = 0.10f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.WaterDrop,
                        contentDescription = null,
                        tint = AutoPulseSuccess,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "CURRENT EFFICIENCY",
                        color = AutoPulseTextSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Text(
                            text = "8.1",
                            color = AutoPulseSuccess,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "L/100km",
                            color = AutoPulseTextMuted,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = AutoPulseSuccess,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "+6%",
                        color = AutoPulseSuccess,
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "vs last month",
                        color = AutoPulseTextMuted,
                        fontSize = 9.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            HorizontalDivider(
                color = AutoPulseText.copy(alpha = 0.05f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                FuelMiniStat(
                    label = "This Month",
                    value = "214 L"
                )

                FuelMiniStat(
                    label = "Fuel Cost",
                    value = "R4,382"
                )

                FuelMiniStat(
                    label = "Distance",
                    value = "2,640 km"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // RANGE
        // ----------------------------------------------------

        NeonCard(
            borderColor = AutoPulseCyan.copy(alpha = 0.35f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Speed,
                    contentDescription = null,
                    tint = AutoPulseCyan,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "ESTIMATED RANGE",
                        color = AutoPulseTextSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Text(
                        text = "486 km",
                        color = AutoPulseText,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = "FUEL LEVEL",
                        color = AutoPulseTextMuted,
                        fontSize = 9.sp
                    )

                    Text(
                        text = "72%",
                        color = AutoPulseCyan,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { 0.72f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = AutoPulseCyan,
                trackColor = AutoPulseText.copy(alpha = 0.08f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // FUEL PURCHASE
        // ----------------------------------------------------

        SectionHeading("FUEL PURCHASE")

        Spacer(modifier = Modifier.height(12.dp))

        NeonCard(
            borderColor = AutoPulseCyanDark.copy(alpha = 0.45f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            AutoPulseCyanDark.copy(alpha = 0.10f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.LocalGasStation,
                        contentDescription = null,
                        tint = AutoPulseCyanDark
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Record a fill-up",
                        color = AutoPulseText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    Text(
                        text = "Track your fuel spending and efficiency",
                        color = AutoPulseTextMuted,
                        fontSize = 11.sp
                    )
                }

                IconButton(
                    onClick = {
                        showFuelDialog = true
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add fuel purchase",
                        tint = AutoPulseCyanDark
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // CONSUMPTION TREND
        // ----------------------------------------------------

        SectionHeading("CONSUMPTION TREND")

        Spacer(modifier = Modifier.height(12.dp))

        NeonCard(
            borderColor = AutoPulsePurple.copy(alpha = 0.4f),
            modifier = Modifier.height(230.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "L/100km",
                        color = AutoPulseTextMuted,
                        fontSize = 10.sp
                    )

                    Text(
                        text = "Last 6 fill-ups",
                        color = AutoPulseTextMuted,
                        fontSize = 10.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        val path = Path().apply {

                            moveTo(
                                0f,
                                size.height * 0.75f
                            )

                            lineTo(
                                size.width * 0.18f,
                                size.height * 0.62f
                            )

                            lineTo(
                                size.width * 0.36f,
                                size.height * 0.68f
                            )

                            lineTo(
                                size.width * 0.54f,
                                size.height * 0.42f
                            )

                            lineTo(
                                size.width * 0.72f,
                                size.height * 0.50f
                            )

                            lineTo(
                                size.width,
                                size.height * 0.25f
                            )
                        }

                        drawPath(
                            path = path,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    AutoPulsePurple,
                                    AutoPulseCyan
                                )
                            ),
                            style = Stroke(
                                width = 4.dp.toPx()
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TrendLabel("JUL 10")
                    TrendLabel("JUL 18")
                    TrendLabel("JUL 25")
                    TrendLabel("AUG 02")
                    TrendLabel("AUG 10")
                    TrendLabel("AUG 18")
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // FUEL STATISTICS
        // ----------------------------------------------------

        SectionHeading("FUEL STATISTICS")

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            FuelStatCard(
                title = "Best",
                value = "7.4",
                unit = "L/100km",
                color = AutoPulseSuccess,
                modifier = Modifier.weight(1f)
            )

            FuelStatCard(
                title = "Average",
                value = "8.1",
                unit = "L/100km",
                color = AutoPulseCyan,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            FuelStatCard(
                title = "Worst",
                value = "9.8",
                unit = "L/100km",
                color = AutoPulseWarning,
                modifier = Modifier.weight(1f)
            )

            FuelStatCard(
                title = "Avg Cost",
                value = "R20.48",
                unit = "/ litre",
                color = AutoPulseCyanDark,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // RECENT FILL-UPS
        // ----------------------------------------------------

        SectionHeading("RECENT FILL-UPS")

        Spacer(modifier = Modifier.height(12.dp))

        FuelLogItem(
            date = "18 August 2026",
            amount = "42.5 L",
            cost = "R871.20",
            consumption = "7.9 L/100km"
        )

        FuelLogItem(
            date = "10 August 2026",
            amount = "38.2 L",
            cost = "R782.40",
            consumption = "8.1 L/100km"
        )

        FuelLogItem(
            date = "02 August 2026",
            amount = "45.1 L",
            cost = "R923.50",
            consumption = "8.6 L/100km"
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ------------------------------------------------------------
// COMPONENTS
// ------------------------------------------------------------

@Composable
private fun SectionHeading(text: String) {

    Text(
        text = text,
        color = AutoPulseTextSecondary,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
    )
}

@Composable
private fun FuelMiniStat(
    label: String,
    value: String
) {

    Column {

        Text(
            text = label,
            color = AutoPulseTextMuted,
            fontSize = 9.sp
        )

        Text(
            text = value,
            color = AutoPulseText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun TrendLabel(text: String) {

    Text(
        text = text,
        color = AutoPulseTextMuted,
        fontSize = 8.sp
    )
}

@Composable
private fun FuelStatCard(
    title: String,
    value: String,
    unit: String,
    color: Color,
    modifier: Modifier
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AutoPulseSurface
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            color.copy(alpha = 0.25f)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Text(
                text = title,
                color = AutoPulseTextMuted,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = value,
                color = color,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = unit,
                color = AutoPulseTextMuted,
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun FuelLogItem(
    date: String,
    amount: String,
    cost: String,
    consumption: String
) {

    NeonCard(
        borderColor = AutoPulseText.copy(alpha = 0.08f),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        AutoPulseSuccess.copy(alpha = 0.08f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.LocalGasStation,
                    contentDescription = null,
                    tint = AutoPulseSuccess,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = date,
                    color = AutoPulseText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$amount • $cost",
                    color = AutoPulseTextMuted,
                    fontSize = 11.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = consumption,
                    color = AutoPulseSuccess,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "efficiency",
                    color = AutoPulseTextMuted,
                    fontSize = 8.sp
                )
            }
        }
    }
}