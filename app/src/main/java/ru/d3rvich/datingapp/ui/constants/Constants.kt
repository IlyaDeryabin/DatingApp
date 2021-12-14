package ru.d3rvich.datingapp.ui.constants

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.R

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

enum class Zodiac(@StringRes val stringRes: Int) {
    Aries(R.string.zod_aries),
    Taurus(R.string.zod_taurus),
    Gemini(R.string.zod_gemini),
    Cancer(R.string.zod_cancer),
    Leo(R.string.zod_leo),
    Virgo(R.string.zod_virgo),
    Libra(R.string.zod_libra),
    Scorpio(R.string.zod_scorpio),
    Sagittarius(R.string.zod_sagittarius),
    Capricorn(R.string.zod_capricorn),
    Aquarius(R.string.zod_aquarius),
    Pisces(R.string.zod_pisces)
}