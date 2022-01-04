package ru.d3rvich.datingapp

import org.junit.Test

import org.junit.Assert.*
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.utils.calculateFateNumber
import ru.d3rvich.datingapp.domain.utils.sumOfNumbers
import ru.d3rvich.datingapp.ui.constants.Zodiac
import ru.d3rvich.datingapp.ui.model.DateRangeUiModel
import ru.d3rvich.datingapp.ui.model.DateWithoutYearUiModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTests {

    @Test
    fun checkDateEntityParser() {
        val date = DateEntity.parse("24.12.1999")
        assertEquals(24, date.day)
        assertEquals(12, date.month)
        assertEquals(1999, date.year)
    }

    @Test
    fun checkSumOfNumbers() {
        assertEquals(6, (24).sumOfNumbers())
        assertEquals(3, (12).sumOfNumbers())
        assertEquals(28, (1999).sumOfNumbers())
    }

    @Test
    fun checkFateNumberCalculation() {
        val date = DateEntity.parse("24.12.1999")
        assertEquals(1, date.calculateFateNumber())
    }

    @Test
    fun isDateBetweenDateRange() {
        val target = DateWithoutYearUiModel.parse("24.12")
        val range = DateRangeUiModel.parse("1.12", "31.12")
        assertEquals(true, range.isDateBetweenRange(target))
    }

    @Test
    fun isDateInLeftBorderOfDateRange() {
        val target = DateWithoutYearUiModel.parse("1.1")
        val range = DateRangeUiModel.parse("1.1", "31.1")
        assertEquals(true, range.isDateBetweenRange(target))
    }

    @Test
    fun isDateInRightBorderOfDateRange() {
        val target = DateWithoutYearUiModel.parse("30.4")
        val range = DateRangeUiModel.parse("1.4", "30.4")
        assertEquals(true, range.isDateBetweenRange(target))
    }

    @Test
    fun isDateNotInDateRange() {
        val target = DateWithoutYearUiModel.parse("30.4")
        val range = DateRangeUiModel.parse("1.1", "30.1")
        assertEquals(false, range.isDateBetweenRange(target))
    }

    @Test
    fun isDateAssociateToZodiacSign() {
        val target = DateWithoutYearUiModel.parse("24.12")
        assertEquals(Zodiac.Capricorn, Zodiac.findZodiacByDate(target))
    }
}