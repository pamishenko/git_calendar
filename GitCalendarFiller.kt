#!/usr/bin/env kotlin

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.io.File

fun main(args: Array<String>) {
    val calendarFiller = GitCalendarFiller()
    calendarFiller.fillCalendar()
}

class GitCalendarFiller {
    private val targetText = "HELLO WORLD"
    private val startDate = LocalDate.of(2025, 8, 24) // Точка старта: 24 августа 2025
    
    // Пиксельные карты символов из PixelPrinter
    private val charMaps = mapOf(
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
    
    fun fillCalendar() {
        val today = LocalDate.now()
        val startDate = LocalDate.of(2025, 8, 24) // Точка старта: 24 августа 2025
        
        // Вычисляем количество дней с момента старта
        val daysSinceStart = java.time.temporal.ChronoUnit.DAYS.between(startDate, today)
        
        // Проверяем, что дата не раньше стартовой
        if (daysSinceStart < 0) {
            println("Сегодня: ${today.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
            println("Стартовая дата: ${startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
            println("Календарь еще не начался!")
            return
        }
        
        // Определяем, какая буква сейчас заполняется
        val lettersPerCycle = targetText.length
        val daysPerLetter = 49 // 7x7 пикселей
        val restDays = 6 // дней отдыха между буквами
        val totalDaysPerLetter = daysPerLetter + restDays
        
        val letterIndex = (daysSinceStart / totalDaysPerLetter).toInt() % lettersPerCycle
        val dayInLetter = daysSinceStart % totalDaysPerLetter
        val currentChar = targetText[letterIndex]
        
        // Определяем, находимся ли мы в фазе заполнения буквы или отдыха
        val isInLetterPhase = dayInLetter < daysPerLetter
        
        if (isInLetterPhase) {
            // Заполняем букву
            val pixelPosition = dayInLetter
            val pixelRow = (pixelPosition / 7).toInt()
            val pixelCol = (pixelPosition % 7).toInt()
        } else {
            // Фаза отдыха
            val restDay = dayInLetter - daysPerLetter
        }
        
        println("Сегодня: ${today.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
        println("Дней с момента старта: $daysSinceStart")
        println("Индекс буквы: $letterIndex")
        println("Текущая буква: '$currentChar'")
        println("День в цикле буквы: $dayInLetter")
        
        if (isInLetterPhase) {
            // Заполняем букву
            val pixelPosition = dayInLetter
            val pixelRow = (pixelPosition / 7).toInt()
            val pixelCol = (pixelPosition % 7).toInt()
            
            println("Фаза: Заполнение буквы")
            println("Позиция пикселя: [$pixelRow, $pixelCol]")
            
            // Получаем пиксельную карту буквы
            val charMap = charMaps[currentChar] ?: charMaps[' ']!!
            
            // Определяем, заполнен ли пиксель
            val isPixelFilled = if (pixelRow < charMap.size && pixelCol < charMap[pixelRow].length) {
                charMap[pixelRow][pixelCol] == 'O'
            } else {
                false
            }
            
            println("Пиксель заполнен: $isPixelFilled")
            
            // Показываем пиксельную версию буквы с выделенным пикселем
            println("Пиксельная версия (выделен текущий пиксель):")
            for (row in 0 until charMap.size) {
                val rowStr = charMap[row]
                if (row == pixelRow) {
                    val highlightedRow = rowStr.substring(0, pixelCol) + "[" + rowStr[pixelCol] + "]" + rowStr.substring(pixelCol + 1)
                    println(highlightedRow)
                } else {
                    println(rowStr)
                }
            }
            
            // Определяем количество пушей
            val pushCount = if (isPixelFilled) 30 else 1
            println("Количество пушей: $pushCount")
        } else {
            // Фаза отдыха
            val restDay = dayInLetter - daysPerLetter
            println("Фаза: Отдых (день $restDay из $restDays)")
            println("Количество пушей: 1")
        }
        
        // Создаем или обновляем файл с информацией о текущем пикселе
        val pixelContent = buildString {
            appendLine("Дата: ${today.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
            appendLine("Время: ${java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}")
            appendLine("Буква: '$currentChar'")
            appendLine("Индекс буквы: $letterIndex")
            appendLine("Дней с момента старта: $daysSinceStart")
            appendLine("День в цикле буквы: $dayInLetter")
            
            if (isInLetterPhase) {
                val pixelPosition = dayInLetter
                val pixelRow = (pixelPosition / 7).toInt()
                val pixelCol = (pixelPosition % 7).toInt()
                val charMap = charMaps[currentChar] ?: charMaps[' ']!!
                val isPixelFilled = if (pixelRow < charMap.size && pixelCol < charMap[pixelRow].length) {
                    charMap[pixelRow][pixelCol] == 'O'
                } else {
                    false
                }
                
                appendLine("Фаза: Заполнение буквы")
                appendLine("Позиция пикселя: [$pixelRow, $pixelCol]")
                appendLine("Пиксель заполнен: $isPixelFilled")
                appendLine("---")
                appendLine("Пиксельная версия буквы:")
                for (row in charMap) {
                    appendLine(row)
                }
                appendLine("---")
                appendLine("Текущий пиксель: [$pixelRow, $pixelCol] = ${if (isPixelFilled) "O" else " "}")
            } else {
                val restDay = dayInLetter - daysPerLetter
                appendLine("Фаза: Отдых (день $restDay из $restDays)")
                appendLine("Количество пушей: 1")
            }
        }
        
        // Записываем в файл
        File("newPixel.txt").writeText(pixelContent)
        
        // Делаем пуш
        runCommand("git add newPixel.txt")
        runCommand("git commit -m \"День $daysSinceStart: буква '$currentChar'\"")
        
        val pushCount = if (isInLetterPhase) {
            val pixelPosition = dayInLetter
            val pixelRow = (pixelPosition / 7).toInt()
            val pixelCol = (pixelPosition % 7).toInt()
            val charMap = charMaps[currentChar] ?: charMaps[' ']!!
            val isPixelFilled = if (pixelRow < charMap.size && pixelCol < charMap[pixelRow].length) {
                charMap[pixelRow][pixelCol] == 'O'
            } else {
                false
            }
            if (isPixelFilled) 30 else 1
        } else {
            1
        }
        
        if (pushCount > 1) {
            println("Делаем $pushCount пушей с интервалом 10 секунд...")
            for (i in 1..pushCount) {
                println("Пуш $i из $pushCount")
                runCommand("git push")
                
                if (i < pushCount) {
                    println("Ожидание 10 секунд...")
                    Thread.sleep(10000) // 10 секунд
                }
            }
        } else {
            println("Делаем один пуш...")
            runCommand("git push")
        }
        
        println("Готово!")
    }
    
    private fun runCommand(command: String) {
        try {
            val process = ProcessBuilder(*command.split(" ").toTypedArray())
                .directory(File("."))
                .start()
            
            val exitCode = process.waitFor()
            
            if (exitCode != 0) {
                println("Ошибка выполнения команды: $command")
                println("Код выхода: $exitCode")
            } else {
                println("Команда выполнена успешно: $command")
            }
        } catch (e: Exception) {
            println("Ошибка при выполнении команды '$command': ${e.message}")
        }
    }
}
