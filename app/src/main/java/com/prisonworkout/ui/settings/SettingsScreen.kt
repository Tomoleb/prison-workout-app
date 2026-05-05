package com.prisonworkout.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.data.GoalType

@Composable
fun SettingsScreen(prefs: UserPreferences) {
    var showResetDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Настройки", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        // Сбросить текущую программу
        Button(onClick = { showResetDialog = true }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
            Text("Сбросить программу")
        }
        if (showResetDialog) {
            AlertDialog(
                onDismissRequest = { showResetDialog = false },
                title = { Text("Сброс программы") },
                text = { Text("Весь прогресс будет потерян. Продолжить?") },
                confirmButton = {
                    TextButton(onClick = {
                        prefs.currentProgram = null
                        prefs.goalType = null
                        showResetDialog = false
                    }) { Text("Сбросить") }
                },
                dismissButton = { TextButton(onClick = { showResetDialog = false }) { Text("Отмена") } }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Уведомления (заглушка)
        var notificationsEnabled by remember { mutableStateOf(false) }
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text("Напоминания о тренировке")
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
        }
    }
}
