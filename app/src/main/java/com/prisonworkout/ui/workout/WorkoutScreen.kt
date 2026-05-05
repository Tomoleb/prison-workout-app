package com.prisonworkout.ui.workout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.prisonworkout.data.ProgramRepository
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.data.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    exerciseNameStr: String,
    prefs: UserPreferences,
    navController: NavController
) {
    val exerciseName = ExerciseName.valueOf(exerciseNameStr)
    val exercise = ProgramRepository.getAllExercises().find { it.exerciseName == exerciseName }!!
    val progress = prefs.getExerciseProgress(exerciseName)
    val currentLevel = exercise.levels[progress.currentLevel - 1]
    val target = when (progress.currentStep) {
        0 -> currentLevel.targets.beginnerReps
        1 -> currentLevel.targets.intermediateReps
        2 -> currentLevel.targets.advancedReps
        else -> currentLevel.targets.advancedReps
    }
    val isTimed = exerciseName.isTimed

    var actualReps by remember { mutableIntStateOf(if (progress.actualReps.isNotEmpty()) progress.actualReps.last() else 0) }
    val canAdvance = if (isTimed) actualReps >= target else actualReps >= target

    Scaffold(
        topBar = { TopAppBar(title = { Text(exerciseName.displayName) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Уровень ${currentLevel.levelNumber}: ${currentLevel.levelName}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = if (isTimed) "Цель: ${target} сек" else "Цель: $target",
                fontSize = 48.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (actualReps > 0) actualReps-- }) { Text("-") }
                Text(
                    text = "$actualReps",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 32.sp
                )
                Button(onClick = { actualReps++ }) { Text("+") }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newProgress = progress.copy(
                        actualReps = progress.actualReps + actualReps,
                        completedCyclesToday = progress.completedCyclesToday + 1
                    )
                    prefs.saveExerciseProgress(exerciseName, newProgress)
                    // Здесь будет логика повышения ступени/уровня
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Подтвердить")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = currentLevel.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun rememberMutableIntStateOf(value: Int): MutableIntState {
    return remember { mutableIntStateOf(value) }
}
