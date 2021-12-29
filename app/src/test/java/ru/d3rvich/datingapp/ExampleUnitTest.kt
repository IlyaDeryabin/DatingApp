package ru.d3rvich.datingapp

import org.junit.Test

import org.junit.Assert.*
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.utils.calculateFateNumber
import ru.d3rvich.datingapp.domain.utils.sumOfNumbers

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

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
}