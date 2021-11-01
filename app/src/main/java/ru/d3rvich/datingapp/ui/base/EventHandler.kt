package ru.d3rvich.datingapp.ui.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}