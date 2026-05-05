package com.prisonworkout.data

import com.prisonworkout.data.model.*
import java.time.DayOfWeek

object ProgramRepository {

    fun getProgram(type: ProgramType): Program {
        // Пока только Одиночное заключение
        val schedule = listOf(
            DailyRoutine(DayOfWeek.MONDAY, listOf(ExerciseName.PULLUPS, ExerciseName.SQUATS, ExerciseName.GRIP)),
            DailyRoutine(DayOfWeek.TUESDAY, listOf(ExerciseName.PUSHUPS, ExerciseName.LEG_RAISES, ExerciseName.CALVES)),
            DailyRoutine(DayOfWeek.WEDNESDAY, listOf(ExerciseName.HANDSTAND_PUSHUPS, ExerciseName.BRIDGES, ExerciseName.NECK)),
            DailyRoutine(DayOfWeek.THURSDAY, listOf(ExerciseName.PULLUPS, ExerciseName.SQUATS, ExerciseName.GRIP)),
            DailyRoutine(DayOfWeek.FRIDAY, listOf(ExerciseName.PUSHUPS, ExerciseName.LEG_RAISES, ExerciseName.CALVES)),
            DailyRoutine(DayOfWeek.SATURDAY, listOf(ExerciseName.HANDSTAND_PUSHUPS, ExerciseName.BRIDGES, ExerciseName.NECK)),
            DailyRoutine(DayOfWeek.SUNDAY, emptyList()) // отдых
        )
        return Program(type, schedule)
    }

    private val allExercises = listOf(/* здесь полные данные упражнений */)

    fun getAllExercises(): List<Exercise> = allExercises
}
