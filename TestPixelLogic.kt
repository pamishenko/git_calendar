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
    
    println("Логика заполнения пикселей:")
    println("Каждый день заполняется один пиксель из буквы")
    println("Если пиксель заполнен (O) - 30 пушей")
    println("Если пиксель пустой (пробел) - 1 пуш")
    println()
    
    // Показываем первые 20 дней
    for (day in 0..19) {
        val dayDate = startDate.plusDays(day.toLong())
        val textPosition = day % targetText.length
        val currentChar = targetText[textPosition]
        
        // Определяем позицию пикселя в букве (7x7 = 49 пикселей)
        val pixelPosition = (day / targetText.length) % 49
        val pixelRow = (pixelPosition / 7).toInt()
        val pixelCol = (pixelPosition % 7).toInt()
        
        // Получаем пиксельную карту буквы
        val charMap = charMaps[currentChar] ?: charMaps[' ']!!
        
        // Определяем, заполнен ли пиксель
        val isPixelFilled = if (pixelRow < charMap.size && pixelCol < charMap[pixelRow].length) {
            charMap[pixelRow][pixelCol] == 'O'
        } else {
            false
        }
        
        val pushCount = if (isPixelFilled) 30 else 1
        
        println("День $day (${dayDate.format(DateTimeFormatter.ofPattern("dd.MM"))}):")
        println("  Буква: '$currentChar'")
        println("  Пиксель: [$pixelRow, $pixelCol] = ${if (isPixelFilled) "O" else " "}")
        println("  Пушей: $pushCount")
        println()
    }
    
    // Показываем пример для буквы H
    println("Пример для буквы H (первые 7 дней):")
    val charMapH = charMaps['H']!!
    for (day in 0..6) {
        val pixelRow = day
        val pixelCol = 0
        val isPixelFilled = charMapH[pixelRow][pixelCol] == 'O'
        val pushCount = if (isPixelFilled) 30 else 1
        
        println("День $day: [$pixelRow, 0] = ${charMapH[pixelRow][0]} -> $pushCount пушей")
    }
}
