#!/usr/bin/env kotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val targetText = "HELLO WORLD"
    val startDate = LocalDate.of(2025, 8, 24) // Точка старта: 24 августа 2025
    val testDate = LocalDate.of(2025, 8, 24) // Завтра
    
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
    
    // Вычисляем количество дней с момента старта
    val daysSinceStart = java.time.temporal.ChronoUnit.DAYS.between(startDate, testDate)
    
    println("Тестовая дата: ${testDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
    println("Стартовая дата: ${startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
    println("Дней с момента старта: $daysSinceStart")
    
    // Определяем позицию в тексте (циклично)
    val textPosition = daysSinceStart.toInt() % targetText.length
    val currentChar = targetText[textPosition]
    
    println("Позиция в тексте: $textPosition")
    println("Текущая буква: '$currentChar'")
    
    // Показываем пиксельную версию буквы
    val charMap = charMaps[currentChar] ?: charMaps[' ']!!
    println("Пиксельная версия:")
    for (row in charMap) {
        println(row)
    }
    
    // Определяем количество пушей
    val pushCount = if (currentChar == ' ') 1 else 30
    println("Количество пушей: $pushCount")
    
    println("\nПример содержимого файла newPixel.txt:")
    println("Дата: ${testDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
    println("Время: ${java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}")
    println("Буква: '$currentChar'")
    println("Позиция в тексте: $textPosition")
    println("Дней с момента старта: $daysSinceStart")
    println("---")
    println("Пиксельная версия:")
    for (row in charMap) {
        println(row)
    }
}
