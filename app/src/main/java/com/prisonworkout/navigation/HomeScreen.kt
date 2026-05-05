package com.prisonworkout.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.data.ProgramRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(prefs: UserPreferences) {
    val currentProgram = prefs.currentProgram
    val today = LocalDate.now()
    val dateStr = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val dayOfWeek = today.dayOfWeek
    val isCompleted = prefs.isDayCompleted(dateStr)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Блок текущей программы
        if (currentProgram != null) {
            val program = ProgramRepository.getProgram(currentProgram)
            val routine = program.weeklySchedule.find { it.dayOfWeek == dayOfWeek }
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Сегодня: ${currentProgram.displayName}", style = MaterialTheme.typography.headlineSmall)
                    if (routine != null && routine.exercises.isNotEmpty()) {
                        Text("Упражнения: ${routine.exercises.joinToString { it.displayName }}")
                        if (isCompleted) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = "Выполнено", tint = MaterialTheme.colorScheme.primary)
                        }
                    } else {
                        Text("День отдыха", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Последние 7 дней
            Text("Последние 7 дней", style = MaterialTheme.typography.titleMedium)
            val last7 = prefs.getLast7Days()
            LazyRow {
                items(last7.size) { idx ->
                    val day = last7[idx]
                    DayIndicator(day = day)
                }
            }
        } else {
            Text("Программа не выбрана", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun DayIndicator(day: com.prisonworkout.data.model.DayProgress) {
    val color = when {
        day.isRestDay -> MaterialTheme.colorScheme.outline
        day.completed -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.error
    }
    Card(modifier = Modifier.padding(4.dp).size(40.dp), colors = CardDefaults.cardColors(containerColor = color)) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = day.date.takeLast(2))
        }
    }
}
