package com.prisonworkout.ui.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prisonworkout.data.ProgramRepository
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.data.model.ExerciseName

@Composable
fun StatisticsScreen(prefs: UserPreferences) {
    val currentProgram = prefs.currentProgram
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (currentProgram != null) {
            Text("Прогресс программ", style = MaterialTheme.typography.titleMedium)
            // Считаем процент завершения каждой начатой программы
            val program = ProgramRepository.getProgram(currentProgram)
            val exercises = program.weeklySchedule.flatMap { it.exercises }.distinct()
            val completedSteps = exercises.sumOf { prefs.getCompletedLevelsCount(it) }
            val totalSteps = exercises.sumOf { prefs.totalLevelsCount(it) }
            val percent = if (totalSteps > 0) completedSteps * 100 / totalSteps else 0
            Text("$percent% (шагов завершено)")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Общая статистика", style = MaterialTheme.typography.titleMedium)
        // Дни с тренировками
        // Здесь должна быть логика подсчета всех дней с завершенными тренировками
        // Упрощенно:
        Text("Дней с тренировками: 0 (заглушка)")
        // Список упражнений по количеству повторений
        // ...
    }
}
