#!/usr/bin/env kotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val targetText = "HELLO WORLD"
    val startDate = LocalDate.of(2025, 8, 24) // Точка старта: 24 августа 2025
    
    // Пиксельные карты символов
    val charMaps = mapOf(
        'H' to arrayOf(
            "O     O",
            "O     O",
            "O     O",
            "OOOOOOO",
            "O     O",
            "O     O",
            "O     O"
        ),
        'E' to arrayOf(
            "OOOOOOO",
            "O      ",
            "O      ",
            "OOOOO  ",
            "O      ",
            "O      ",
            "OOOOOOO"
        ),
        'L' to arrayOf(
            "O      ",
            "O      ",
            "O      ",
            "O      ",
            "O      ",
            "O      ",
            "OOOOOOO"
        ),
        'O' to arrayOf(
            " OOOOO ",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            " OOOOO "
        ),
        'W' to arrayOf(
            "O     O",
            "O     O",
            "O     O",
            "O  O  O",
            "O O O O",
            "OO   OO",
            "O     O"
        ),
        'R' to arrayOf(
            "OOOOO  ",
            "O     O",
            "O     O",
            "OOOOO  ",
            "O   O  ",
            "O    O ",
            "O     O"
        ),
        'D' to arrayOf(
            "OOOOO  ",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            "OOOOO  "
        ),
        ' ' to arrayOf(
            "       ",
            "       ",
            "       ",
            "       ",
            "       ",
            "       ",
            "       "
        )
    )
    
    println("Новая логика заполнения:")
    println("1. Полностью заполняем одну букву (49 дней)")
    println("2. 6 дней отдыха (по 1 пушу)")
    println("3. Переходим к следующей букве")
    println()
    
    val lettersPerCycle = targetText.length
    val daysPerLetter = 49 // 7x7 пикселей
    val restDays = 6 // дней отдыха между буквами
    val totalDaysPerLetter = daysPerLetter + restDays
    
    // Показываем первые 100 дней
    for (day in 0..99) {
        val dayDate = startDate.plusDays(day.toLong())
        val letterIndex = (day / totalDaysPerLetter) % lettersPerCycle
        val dayInLetter = day % totalDaysPerLetter
        val currentChar = targetText[letterIndex]
        val isInLetterPhase = dayInLetter < daysPerLetter
        
        if (isInLetterPhase) {
            // Заполняем букву
            val pixelPosition = dayInLetter
            val pixelRow = (pixelPosition / 7).toInt()
            val pixelCol = (pixelPosition % 7).toInt()
            
            val charMap = charMaps[currentChar] ?: charMaps[' ']!!
            val isPixelFilled = if (pixelRow < charMap.size && pixelCol < charMap[pixelRow].length) {
                charMap[pixelRow][pixelCol] == 'O'
            } else {
                false
            }
            
            val pushCount = if (isPixelFilled) 30 else 1
            
            println("День $day (${dayDate.format(DateTimeFormatter.ofPattern("dd.MM"))}): Буква '$currentChar' - [$pixelRow, $pixelCol] = ${if (isPixelFilled) "O" else " "} -> $pushCount пушей")
        } else {
            // Фаза отдыха
            val restDay = dayInLetter - daysPerLetter
            println("День $day (${dayDate.format(DateTimeFormatter.ofPattern("dd.MM"))}): ОТДЫХ (день $restDay из $restDays) -> 1 пуш")
        }
        
        // Показываем разделители между буквами
        if (dayInLetter == daysPerLetter - 1) {
            println("--- Буква '$currentChar' завершена ---")
        }
        if (dayInLetter == totalDaysPerLetter - 1) {
            println("=== Переход к следующей букве ===")
        }
    }
    
    println("\nСводка по буквам:")
    for (i in 0 until targetText.length) {
        val char = targetText[i]
        val startDay = i * totalDaysPerLetter
        val endDay = startDay + totalDaysPerLetter - 1
        val letterStartDate = startDate.plusDays(startDay.toLong())
        val letterEndDate = startDate.plusDays(endDay.toLong())
        
        println("Буква '$char': дни $startDay-$endDay (${letterStartDate.format(DateTimeFormatter.ofPattern("dd.MM"))} - ${letterEndDate.format(DateTimeFormatter.ofPattern("dd.MM"))})")
    }
}
