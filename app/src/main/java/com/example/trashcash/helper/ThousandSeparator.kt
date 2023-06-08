package com.example.trashcash.helper

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun addThousandSeparator(number: Long): String {
    val decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault())
    decimalFormatSymbols.groupingSeparator = '.'
    decimalFormatSymbols.decimalSeparator = ','

    val decimalFormat = DecimalFormat("#,###", decimalFormatSymbols)
    return decimalFormat.format(number)
}