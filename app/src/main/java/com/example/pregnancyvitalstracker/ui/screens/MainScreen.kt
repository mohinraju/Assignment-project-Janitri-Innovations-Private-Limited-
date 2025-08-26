package com.example.pregnancyvitalstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pregnancyvitalstracker.Vital
import com.example.pregnancyvitalstracker.ui.screens.components.AddVitalDialog
import com.example.pregnancyvitalstracker.viewmodel.VitalViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MainScreen(viewModel: VitalViewModel, modifier: Modifier = Modifier) {
    val vitals by viewModel.vitals.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Vital")
            }
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Title Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0BBE4))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Track My Pregnancy",
                    style = MaterialTheme.typography.h5
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(vitals) { vital ->
                    VitalCard(vital)
                }
            }
        }

        if (showDialog) {
            AddVitalDialog(
                onDismiss = { showDialog = false },
                onSubmit = {
                    viewModel.addVital(it)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun VitalCard(vital: Vital) {
    val date = remember(vital.timestamp) {
        val formatter = SimpleDateFormat("EEE, d MMM yyyy, h:mm a", Locale.getDefault())
        formatter.format(Date(vital.timestamp))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        backgroundColor = Color(0xFFFDCFE8),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TrackRow("🩺 Blood Pressure", "${vital.systolic}/${vital.diastolic} mmHg")
            TrackRow("❤️ Heart Rate", "${vital.heartRate} bpm")
            TrackRow("⚖️ Weight", "${vital.weight} kg")
            TrackRow("🤰 Baby Kicks", "${vital.kicks}")

            Spacer(modifier = Modifier.height(12.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🕒 $date",
                style = MaterialTheme.typography.caption,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun TrackRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.body1)
        Text(text = value, style = MaterialTheme.typography.body1)
    }
}
