package com.prisonworkout.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prisonworkout.data.model.*

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prison_workout", Context.MODE_PRIVATE)
    private val gson = Gson()

    var currentProgram: ProgramType?
        get() = prefs.getString("current_program", null)?.let { ProgramType.valueOf(it) }
        set(value) = prefs.edit().putString("current_program", value?.name).apply()

    var goalType: GoalType?
        get() = prefs.getString("goal_type", null)?.let { GoalType.valueOf(it) }
        set(value) = prefs.edit().putString("goal_type", value?.name).apply()

    var cyclesCount: Int
        get() = prefs.getInt("cycles_count", 3)
        set(value) = prefs.edit().putInt("cycles_count", value).apply()

    var restBetweenSets: Long // в секундах
        get() = prefs.getLong("rest_between_sets", 120)
        set(value) = prefs.edit().putLong("rest_between_sets", value).apply()

    var restBetweenCycles: Long
        get() = prefs.getLong("rest_between_cycles", 240)
        set(value) = prefs.edit().putLong("rest_between_cycles", value).apply()

    fun saveExerciseProgress(exName: ExerciseName, progress: ExerciseProgress) {
        val json = gson.toJson(progress)
        prefs.edit().putString("progress_${exName.name}", json).apply()
    }

    fun getExerciseProgress(exName: ExerciseName): ExerciseProgress {
        val json = prefs.getString("progress_${exName.name}", null) ?: return ExerciseProgress()
        return gson.fromJson(json, ExerciseProgress::class.java)
    }

    fun saveDayCompletion(date: String, completed: Boolean) {
        prefs.edit().putBoolean("day_$date", completed).apply()
    }

    fun isDayCompleted(date: String): Boolean {
        return prefs.getBoolean("day_$date", false)
    }

    // Последние 7 дней с отметками
    fun getLast7Days(): List<DayProgress> {
        val today = java.time.LocalDate.now()
        val program = currentProgram ?: return emptyList()
        val schedule = ProgramRepository.getProgram(program).weeklySchedule
        return (0 until 7).map { i ->
            val date = today.minusDays(i.toLong())
            val dayOfWeek = date.dayOfWeek
            val routine = schedule.find { it.dayOfWeek == dayOfWeek }
            val isRest = routine == null || routine.exercises.isEmpty()
            DayProgress(
                date = date.toString(),
                completed = if (isRest) false else isDayCompleted(date.toString()),
                isRestDay = isRest
            )
        }.reversed()
    }

    fun getCompletedLevelsCount(exerciseName: ExerciseName): Int {
        val p = getExerciseProgress(exerciseName)
        // Уровень 1..10, ступень 0..2 => всего 30 комбинаций
        return (p.currentLevel - 1) * 3 + p.currentStep
    }

    fun totalLevelsCount(exerciseName: ExerciseName): Int {
        val ex = ProgramRepository.getAllExercises().find { it.exerciseName == exerciseName }
        return (ex?.levels?.size ?: 0) * 3
    }
}

enum class GoalType { ENDURANCE, MASS }
