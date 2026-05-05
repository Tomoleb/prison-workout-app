package com.prisonworkout.data

import com.prisonworkout.data.model.*
import java.time.DayOfWeek

object ProgramRepository {

    fun getProgram(type: ProgramType): Program {
        val schedule = when (type) {
            ProgramType.SOLITARY -> listOf(
                DailyRoutine(DayOfWeek.MONDAY, listOf(ExerciseName.PULLUPS, ExerciseName.SQUATS, ExerciseName.GRIP)),
                DailyRoutine(DayOfWeek.TUESDAY, listOf(ExerciseName.PUSHUPS, ExerciseName.LEG_RAISES, ExerciseName.CALVES)),
                DailyRoutine(DayOfWeek.WEDNESDAY, listOf(ExerciseName.HANDSTAND_PUSHUPS, ExerciseName.BRIDGES, ExerciseName.NECK)),
                DailyRoutine(DayOfWeek.THURSDAY, listOf(ExerciseName.PULLUPS, ExerciseName.SQUATS, ExerciseName.GRIP)),
                DailyRoutine(DayOfWeek.FRIDAY, listOf(ExerciseName.PUSHUPS, ExerciseName.LEG_RAISES, ExerciseName.CALVES)),
                DailyRoutine(DayOfWeek.SATURDAY, listOf(ExerciseName.HANDSTAND_PUSHUPS, ExerciseName.BRIDGES, ExerciseName.NECK)),
                DailyRoutine(DayOfWeek.SUNDAY, emptyList())
            )
            ProgramType.FIRST_BLOOD -> listOf() // заглушка
            ProgramType.GOOD_BEHAVIOR -> listOf()
            ProgramType.VETERAN -> listOf()
            ProgramType.SUPERMAX -> listOf()
        }
        return Program(type, schedule)
    }

    fun getAllExercises(): List<Exercise> = listOf(
        Exercise(ExerciseName.PUSHUPS, listOf(
            ExerciseLevel(1, "Отжимания от стены", LevelTarget(10, 25, 50), "Встать лицом к стене, руки на ширине плеч, ноги на расстоянии ~60 см от стены. Сгибать руки, касаясь грудью стены."),
            ExerciseLevel(2, "Наклонные отжимания", LevelTarget(10, 20, 40), "Руки на возвышении (стол, стул). Тело прямое, опускаться до касания грудью опоры."),
            ExerciseLevel(3, "Отжимания на коленях", LevelTarget(10, 15, 30), "Колени на полу, скрестить лодыжки. Отжиматься, удерживая корпус прямым."),
            ExerciseLevel(4, "Половинные отжимания", LevelTarget(8, 12, 25), "Из положения лёжа, под грудью мяч или подушка. Опускаться только до касания предмета."),
            ExerciseLevel(5, "Полные отжимания", LevelTarget(5, 10, 20), "Классические отжимания от пола, грудь почти касается пола."),
            ExerciseLevel(6, "Плотные отжимания", LevelTarget(5, 10, 20), "Ладони вместе под грудью, локти вдоль тела."),
            ExerciseLevel(7, "Неравномерные отжимания", LevelTarget(5, 10, 20), "Одна рука на мяче или стопке книг, другая на полу."),
            ExerciseLevel(8, "Половинные отжимания на одной руке", LevelTarget(5, 10, 20), "Одна рука на полу, вторая на возвышении. Половина веса на рабочей руке."),
            ExerciseLevel(9, "Рычажные отжимания", LevelTarget(5, 10, 20), "Одна рука вытянута в сторону, упор на другую руку."),
            ExerciseLevel(10, "Отжимания на одной руке", LevelTarget(5, 10, 100), "Ноги широко, одна рука за спиной, опускаться до касания грудью пола.")
        )),
        Exercise(ExerciseName.SQUATS, listOf(
            ExerciseLevel(1, "Плечи на полу", LevelTarget(10, 25, 50), "Лёжа на спине, согнуть ноги, подтягивать колени к плечам, отрывая таз."),
            ExerciseLevel(2, "Приседание «Складной ножичек»", LevelTarget(10, 20, 40), "Сидя на стуле, наклон вперёд и вставание без помощи рук."),
            ExerciseLevel(3, "Поддержанный присед", LevelTarget(10, 15, 30), "Приседания, держась за опору (столб, дверной косяк)."),
            ExerciseLevel(4, "Полуприсяд", LevelTarget(8, 35, 50), "Приседать до параллели бедер с полом."),
            ExerciseLevel(5, "Полный присед", LevelTarget(5, 10, 30), "Приседать до касания икр бёдрами."),
            ExerciseLevel(6, "Закрытые приседания", LevelTarget(5, 10, 20), "Ступни вместе, приседать не разводя колени."),
            ExerciseLevel(7, "Неравномерные приседания", LevelTarget(5, 10, 20), "Одна нога на подставке 5-10 см."),
            ExerciseLevel(8, "Половинные приседания на одной ноге", LevelTarget(5, 10, 20), "Приседать на одной ноге до касания ягодицей мяча/стула."),
            ExerciseLevel(9, "Приседания на одной ноге с поддержкой", LevelTarget(5, 10, 20), "Держаться одной рукой за опору, приседать на одной ноге."),
            ExerciseLevel(10, "Приседание на одной ноге", LevelTarget(5, 10, 50), "Полный присед «пистолетик» без поддержки.")
        )),
        Exercise(ExerciseName.PULLUPS, listOf(
            ExerciseLevel(1, "Вертикальное подтягивание", LevelTarget(10, 20, 40), "Встать у стены, руки вверх, тянуться вверх, не отрывая пяток."),
            ExerciseLevel(2, "Горизонтальное подтягивание", LevelTarget(10, 20, 30), "Взяться за перекладину на уровне пояса, ноги вперёд, подтягиваться."),
            ExerciseLevel(3, "Подтягивание «Перочинный ножик»", LevelTarget(10, 15, 20), "Вис на перекладине, согнуть ноги в тазобедренных суставах."),
            ExerciseLevel(4, "Полуподтягивания", LevelTarget(8, 11, 15), "Подтягиваться до угла 90° в локтях."),
            ExerciseLevel(5, "Полное подтягивание", LevelTarget(5, 8, 10), "Классические подтягивания до подбородка."),
            ExerciseLevel(6, "Подтягивания узким хватом", LevelTarget(5, 8, 10), "Ладони вместе, подтягиваться."),
            ExerciseLevel(7, "Неравные подтягивания", LevelTarget(5, 7, 9), "Одна рука на полотенце, другая на перекладине."),
            ExerciseLevel(8, "Половинные подтягивания на одной руке", LevelTarget(4, 6, 8), "Держаться одной рукой за запястье другой, подтягиваться."),
            ExerciseLevel(9, "Ассистированные подтягивания на одной руке", LevelTarget(3, 5, 7), "Вторая рука держится за перекладину ниже рабочей."),
            ExerciseLevel(10, "Подтягивание на одной руке", LevelTarget(1, 3, 6), "Чистое подтягивание на одной руке.")
        )),
        Exercise(ExerciseName.LEG_RAISES, listOf(
            ExerciseLevel(1, "Подтягивание колен к животу", LevelTarget(10, 25, 40), "Лёжа на спине, подтягивать колени к груди."),
            ExerciseLevel(2, "Подъёмы коленей лёжа", LevelTarget(10, 20, 35), "Согнутые ноги, поднимать бёдра."),
            ExerciseLevel(3, "Подъёмы согнутых коленей лежа", LevelTarget(10, 15, 30), "Поднимать согнутые ноги до вертикали."),
            ExerciseLevel(4, "Лягушачьи подъёмы лёжа", LevelTarget(8, 15, 25), "Ступни вместе, разводить колени."),
            ExerciseLevel(5, "Ровные прямые подъёмы ног", LevelTarget(5, 10, 20), "Поднимать прямые ноги."),
            ExerciseLevel(6, "Подъём коленей из виса", LevelTarget(5, 10, 15), "Вис на перекладине, поднимать колени."),
            ExerciseLevel(7, "Подъём согнутых ног", LevelTarget(5, 10, 15), "Из виса поднимать согнутые ноги."),
            ExerciseLevel(8, "Висячие подъёмы лягушкой", LevelTarget(5, 10, 15), "Ступни вместе, колени в стороны."),
            ExerciseLevel(9, "Частичные подъёмы прямых ног", LevelTarget(5, 10, 15), "Прямые ноги до горизонтали."),
            ExerciseLevel(10, "Подъём ног", LevelTarget(5, 10, 30), "Прямые ноги до перекладины.")
        )),
        Exercise(ExerciseName.BRIDGES, listOf(
            ExerciseLevel(1, "Короткие мосты", LevelTarget(10, 25, 50), "Лёжа, согнуть ноги, поднимать таз."),
            ExerciseLevel(2, "Ровные мосты", LevelTarget(10, 20, 40), "Таз выше, упор на плечи и стопы."),
            ExerciseLevel(3, "Мосты под углом", LevelTarget(10, 15, 30), "Руки на скамье, толкать таз вверх."),
            ExerciseLevel(4, "Мосты на голове", LevelTarget(8, 15, 25), "Упор на голову и стопы."),
            ExerciseLevel(5, "Половинные мосты", LevelTarget(8, 15, 20), "Опускаться до касания пола серединой спины."),
            ExerciseLevel(6, "Полные мосты", LevelTarget(6, 10, 15), "Стойка на руках и ногах прогибом."),
            ExerciseLevel(7, "Шагающие вниз по стене мосты", LevelTarget(3, 6, 10), "Спуск в мост по стене."),
            ExerciseLevel(8, "Шагающие вверх по стене мосты", LevelTarget(2, 4, 8), "Подъём из моста по стене."),
            ExerciseLevel(9, "Закрытые мосты", LevelTarget(1, 3, 6), "Ноги вместе, руки вместе."),
            ExerciseLevel(10, "Двухопорные мосты", LevelTarget(1, 3, 30), "Мост с опорой на одну руку и одну ногу.")
        )),
        Exercise(ExerciseName.HANDSTAND_PUSHUPS, listOf(
            ExerciseLevel(1, "Стойка на голове у стены", LevelTarget(30, 60, 120), "Удержание стойки на голове. (в секундах)"),
            ExerciseLevel(2, "Поза ворона", LevelTarget(10, 30, 60), "Баланс на руках. (в секундах)"),
            ExerciseLevel(3, "Стойка на руках у стены", LevelTarget(30, 60, 120), "Удержание на руках. (в секундах)"),
            ExerciseLevel(4, "Частичные отжимания в стойке на руках", LevelTarget(5, 10, 20), "Опускаться на половину амплитуды."),
            ExerciseLevel(5, "Отжимания в стойке на руках", LevelTarget(5, 10, 15), "Полные отжимания."),
            ExerciseLevel(6, "Закрытые отжимания в стойке на руках", LevelTarget(5, 9, 12), "Руки вместе."),
            ExerciseLevel(7, "Неравномерные отжимания в стойке на руках", LevelTarget(5, 8, 10), "Одна рука на подставке."),
            ExerciseLevel(8, "Половинные отжимания в стойке на одной руке", LevelTarget(4, 6, 8), "Вторая рука на возвышении."),
            ExerciseLevel(9, "Рычажные отжимания в стойке на руках", LevelTarget(3, 4, 6), "Одна рука широко."),
            ExerciseLevel(10, "Отжимание в стойке на одной руке", LevelTarget(1, 2, 5), "Отжимание на одной руке.")
        )),
        // Базовые упражнения (по одному уровню)
        Exercise(ExerciseName.GRIP, listOf(
            ExerciseLevel(1, "Развитие хвата", LevelTarget(0, 0, 0), "Выполнять вис на перекладине на время или подтягивание на полотенце. Целевые показатели как для вспомогательного упражнения.")
        )),
        Exercise(ExerciseName.CALVES, listOf(
            ExerciseLevel(1, "Развитие икроножных мышц", LevelTarget(0, 0, 0), "Подъёмы на носки стоя или сидя. Работа до жжения.")
        )),
        Exercise(ExerciseName.NECK, listOf(
            ExerciseLevel(1, "Развитие шеи", LevelTarget(0, 0, 0), "Борцовский мост или статическое напряжение мышц шеи. Выполнять 2-4 подхода.")
        ))
    )
}
