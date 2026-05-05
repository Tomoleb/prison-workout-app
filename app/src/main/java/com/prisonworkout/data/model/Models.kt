package com.prisonworkout.data.model

import java.time.DayOfWeek

enum class ProgramType(val displayName: String) {
    FIRST_BLOOD("Первая кровь"),
    GOOD_BEHAVIOR("Хорошее поведение"),
    VETERAN("Ветеран"),
    SOLITARY("Одиночное заключение"),
    SUPERMAX("Супермакс")
}

enum class ExerciseName(val displayName: String, val isTimed: Boolean = false) {
    PUSHUPS("Отжимания"),
    SQUATS("Приседания"),
    PULLUPS("Подтягивания"),
    LEG_RAISES("Подъемы ног"),
    BRIDGES("Мосты"),
    HANDSTAND_PUSHUPS("Отжимания в стойке на руках", isTimed = true),
    GRIP("Развитие хвата"),
    CALVES("Развитие икроножных мышц"),
    NECK("Развитие шеи")
}

data class LevelTarget(
    val beginnerReps: Int,
    val intermediateReps: Int,
    val advancedReps: Int
)

data class ExerciseLevel(
    val levelNumber: Int,          // 1..10, 10 = Master
    val levelName: String,
    val targets: LevelTarget,
    val description: String        // описание как выполнять
)

data class Exercise(
    val exerciseName: ExerciseName,
    val levels: List<ExerciseLevel>
)

data class DailyRoutine(
    val dayOfWeek: DayOfWeek,
    val exercises: List<ExerciseName> // в порядке выполнения
)

data class Program(
    val type: ProgramType,
    val weeklySchedule: List<DailyRoutine>
)

// Прогресс пользователя
data class ExerciseProgress(
    val currentLevel: Int = 1,          // индекс уровня (1-10)
    val currentStep: Int = 0,           // 0=beginner, 1=intermediate, 2=advanced
    val completedCyclesToday: Int = 0,
    val actualReps: List<Int> = emptyList() // повторы в подходах в последний раз
)

data class DayProgress(
    val date: String,          // "yyyy-MM-dd"
    val completed: Boolean,
    val isRestDay: Boolean = false
)

data class ProgramProgress(
    val programType: ProgramType,
    val completedLevels: Int = 0, // количество полностью пройденных ступеней (Level+Step)
    val totalLevels: Int = 0
)
