package com.prisonworkout.ui.programs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prisonworkout.data.ProgramRepository
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.data.model.ProgramType

@Composable
fun ProgramDetailScreen(
    programTypeStr: String,
    prefs: UserPreferences,
    navController: NavController
) {
    val programType = ProgramType.valueOf(programTypeStr)
    val program = ProgramRepository.getProgram(programType)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(programType.displayName, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        program.weeklySchedule.forEach { day ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(day.dayOfWeek.toString(), style = MaterialTheme.typography.titleSmall)
                    if (day.exercises.isEmpty()) {
                        Text("Отдых")
                    } else {
                        day.exercises.forEach { ex ->
                            Text(
                                text = ex.displayName,
                                modifier = Modifier
                                    .clickable { navController.navigate("workout/${ex.name}") }
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
