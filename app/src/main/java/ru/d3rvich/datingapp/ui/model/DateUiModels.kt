package ru.d3rvich.datingapp.ui.model

data class DateWithoutYearUiModel(val day: Int, val month: Int) {
    companion object {
        fun parse(date: String): DateWithoutYearUiModel {
            val parsedString = date.split('.')
            require(parsedString.size == 2)
            val dayOfMonth = parsedString[0]
            val month = parsedString[1]
            require(dayOfMonth.length <= 2 && month.length <= 2)
            return DateWithoutYearUiModel(day = dayOfMonth.toInt(), month = month.toInt())
        }
    }
}

data class DateRangeUiModel(val from: DateWithoutYearUiModel, val to: DateWithoutYearUiModel) {
    fun isDateBetweenRange(date: DateWithoutYearUiModel): Boolean {
        return checkMonth(date.month) && checkDay(date.day)
    }

    private fun checkMonth(month: Int): Boolean = month in from.month..to.month

    private fun checkDay(day: Int): Boolean = day in from.day..to.day

    companion object {
        fun parse(from: String, to: String): DateRangeUiModel {
            val dateFrom = DateWithoutYearUiModel.parse(from)
            val dateTo = DateWithoutYearUiModel.parse(to)
            require(dateFrom.day < dateTo.day) {
                "День ДО должен быть раньше дня ОТ"
            }
            require(dateFrom.month == dateTo.month) {
                "Месяцы должны совпадать"
            }
            return DateRangeUiModel(
                from = dateFrom,
                to = dateTo
            )
        }
    }
}