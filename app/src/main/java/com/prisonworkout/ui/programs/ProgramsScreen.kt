package com.prisonworkout.ui.programs

import com.prisonworkout.data.GoalType
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.data.model.ProgramType

@Composable
fun ProgramsScreen(prefs: UserPreferences, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Выберите программу", style = MaterialTheme.typography.headlineSmall)
        ProgramType.entries.forEach { type ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable {
                if (prefs.currentProgram != type) {
                    // показать диалог
                } else {
                    // перейти в детали программы
                    navController.navigate("program_detail/${type.name}")
                }
            }) {
                Text(text = type.displayName, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

// Диалог выбора цели и циклов
@Composable
fun StartProgramDialog(programType: ProgramType, onDismiss: () -> Unit, onConfirm: (goalType: GoalType, cycles: Int) -> Unit) {
    // реализация ...
}
