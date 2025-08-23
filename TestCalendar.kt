#!/usr/bin/env kotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val targetText = "HELLO WORLD"
    val startDate = LocalDate.of(2025, 8, 24) // Точка старта: 24 августа 2025
    val testDate = LocalDate.of(2025, 8, 24) // Завтра
    
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
    
    // Определяем количество пушей
    val pushCount = if (currentChar == ' ') 1 else 30
    println("Количество пушей: $pushCount")
    
    println("\nРасписание на первые 20 дней:")
    for (day in 0..19) {
        val dayDate = startDate.plusDays(day.toLong())
        val dayPosition = day % targetText.length
        val dayChar = targetText[dayPosition]
        val dayPushCount = if (dayChar == ' ') 1 else 30
        
        println("День $day (${dayDate.format(DateTimeFormatter.ofPattern("dd.MM"))}): '$dayChar' - $dayPushCount пушей")
    }
}
