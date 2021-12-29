package ru.d3rvich.datingapp.domain.utils

import ru.d3rvich.datingapp.domain.entity.DateEntity

fun DateEntity.calculateFateNumber(): Int {
    val first = day.sumOfNumbers()
    val second = month.sumOfNumbers()
    val third = year.sumOfNumbers()
    var sum = first + second + third
    while (sum >= 10) {
        sum = sum.sumOfNumbers()
    }
    return sum
}

fun Int.sumOfNumbers(): Int =
    this.toString().split("").filter { it.isNotEmpty() }.map { it.toInt() }.sum()