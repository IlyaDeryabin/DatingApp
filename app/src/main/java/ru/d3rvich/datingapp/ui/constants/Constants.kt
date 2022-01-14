package ru.d3rvich.datingapp.ui.constants

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.ui.model.DateRangeUiModel
import ru.d3rvich.datingapp.ui.model.DateWithoutYearUiModel

enum class Personalities(@StringRes val stringRes: Int) {
    INFP(R.string.pers_healer),
    INTJ(R.string.pers_mastermind),
    INFJ(R.string.pers_counselor),
    INTP(R.string.pers_architect),

    ENFP(R.string.pers_champion),
    ENTJ(R.string.pers_commander),
    ENTP(R.string.pers_visionary),
    ENFJ(R.string.pers_teacher),

    ISFJ(R.string.pers_protector),
    ISFP(R.string.pers_composer),
    ISTJ(R.string.pers_inspector),
    ISTP(R.string.pers_craftsperson),

    ESFJ(R.string.pers_provider),
    ESFP(R.string.pers_performer),
    ESTJ(R.string.pers_supervisor),
    ESTP(R.string.pers_dynamo)
}

val SocionicTypes by lazy {
    listOf(
        "Штирлиц",
        "Максим Горький",
        "Джек Лондон",
        "Робеспьер",
        "Гюго",
        "Драйзер",
        "Гамлет",
        "Достоевский",
        "Жуков",
        "Габен",
        "Наполеон",
        "Дюма",
        "Дон Кихот",
        "Бальзак",
        "Гексли",
        "Есенин",
    )
}

enum class Zodiac(
    @StringRes val stringRes: Int,
    private val range: Pair<DateRangeUiModel, DateRangeUiModel>
) {
    Aries(
        R.string.zod_aries,
        DateRangeUiModel.parse("21.3", "31.3") to DateRangeUiModel.parse("1.4", "20.4")
    ),
    Taurus(
        R.string.zod_taurus,
        DateRangeUiModel.parse("21.4", "30.4") to DateRangeUiModel.parse("1.5", "21.5")
    ),
    Gemini(
        R.string.zod_gemini,
        DateRangeUiModel.parse("22.5", "31.5") to DateRangeUiModel.parse("1.6", "21.6")
    ),
    Cancer(
        R.string.zod_cancer,
        DateRangeUiModel.parse("22.6", "30.6") to DateRangeUiModel.parse("1.7", "22.7")
    ),
    Leo(
        R.string.zod_leo,
        DateRangeUiModel.parse("23.7", "31.7") to DateRangeUiModel.parse("1.8", "23.8")
    ),
    Virgo(
        R.string.zod_virgo,
        DateRangeUiModel.parse("24.8", "31.8") to DateRangeUiModel.parse("1.9", "22.9")
    ),
    Libra(
        R.string.zod_libra,
        DateRangeUiModel.parse("23.9", "30.9") to DateRangeUiModel.parse("1.10", "23.10")
    ),
    Scorpio(
        R.string.zod_scorpio,
        DateRangeUiModel.parse("24.10", "31.10") to DateRangeUiModel.parse("1.11", "22.11")
    ),
    Sagittarius(
        R.string.zod_sagittarius,
        DateRangeUiModel.parse("23.11", "30.11") to DateRangeUiModel.parse("1.12", "21.12")
    ),
    Capricorn(
        R.string.zod_capricorn,
        DateRangeUiModel.parse("22.12", "31.12") to DateRangeUiModel.parse("1.1", "20.1")
    ),
    Aquarius(
        R.string.zod_aquarius,
        DateRangeUiModel.parse("21.1", "30.1") to DateRangeUiModel.parse("1.2", "18.2")
    ),
    Pisces(
        R.string.zod_pisces,//Мучаться с обработкой весокостного года мне уже совсем не хочется
        DateRangeUiModel.parse("19.2", "28.2") to DateRangeUiModel.parse("1.3", "20.3")
    );

    fun isDateAssociatedToZodiac(date: DateWithoutYearUiModel): Boolean {
        return this.range.first.isDateBetweenRange(date) ||
                this.range.second.isDateBetweenRange(date)
    }

    companion object {
        fun findZodiacByDate(date: DateWithoutYearUiModel): Zodiac {
            values().forEach { sign ->
                if (sign.isDateAssociatedToZodiac(date)) {
                    return sign
                }
            }
            error("Unexpected $date")
        }
    }
}