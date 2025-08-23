#!/usr/bin/env kotlin

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Использование: kotlin PixelPrinter.kt \"текст для вывода\"")
        return
    }
    
    val text = args[0]
    val pixelPrinter = PixelPrinter()
    pixelPrinter.printText(text)
}

class PixelPrinter {
    private val charMaps = mapOf(
        'A' to arrayOf(
            "  OOO  ",
            " O   O ",
            "O     O",
            "O     O",
            "OOOOOOO",
            "O     O",
            "O     O"
        ),
        'B' to arrayOf(
            "OOOOO  ",
            "O     O",
            "O     O",
            "OOOOO  ",
            "O     O",
            "O     O",
            "OOOOO  "
        ),
        'C' to arrayOf(
            " OOOOO ",
            "O     O",
            "O      ",
            "O      ",
            "O      ",
            "O     O",
            " OOOOO "
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
        'E' to arrayOf(
            "OOOOOOO",
            "O      ",
            "O      ",
            "OOOOO  ",
            "O      ",
            "O      ",
            "OOOOOOO"
        ),
        'F' to arrayOf(
            "OOOOOOO",
            "O      ",
            "O      ",
            "OOOOO  ",
            "O      ",
            "O      ",
            "O      "
        ),
        'G' to arrayOf(
            " OOOOO ",
            "O     O",
            "O      ",
            "O  OOO ",
            "O     O",
            "O     O",
            " OOOOO "
        ),
        'H' to arrayOf(
            "O     O",
            "O     O",
            "O     O",
            "OOOOOOO",
            "O     O",
            "O     O",
            "O     O"
        ),
        'I' to arrayOf(
            "OOOOOOO",
            "   O   ",
            "   O   ",
            "   O   ",
            "   O   ",
            "   O   ",
            "OOOOOOO"
        ),
        'J' to arrayOf(
            "OOOOOOO",
            "     O ",
            "     O ",
            "     O ",
            "O    O ",
            "O    O ",
            " OOOO  "
        ),
        'K' to arrayOf(
            "O     O",
            "O    O ",
            "O   O  ",
            "OOOO   ",
            "O   O  ",
            "O    O ",
            "O     O"
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
        'M' to arrayOf(
            "O     O",
            "OO   OO",
            "O O O O",
            "O  O  O",
            "O     O",
            "O     O",
            "O     O"
        ),
        'N' to arrayOf(
            "O     O",
            "OO    O",
            "O O   O",
            "O  O  O",
            "O   O O",
            "O    OO",
            "O     O"
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
        'P' to arrayOf(
            "OOOOO  ",
            "O     O",
            "O     O",
            "OOOOO  ",
            "O      ",
            "O      ",
            "O      "
        ),
        'Q' to arrayOf(
            " OOOOO ",
            "O     O",
            "O     O",
            "O     O",
            "O   O O",
            "O    O ",
            " OOOO O"
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
        'S' to arrayOf(
            " OOOOO ",
            "O     O",
            "O      ",
            " OOOOO ",
            "      O",
            "O     O",
            " OOOOO "
        ),
        'T' to arrayOf(
            "OOOOOOO",
            "   O   ",
            "   O   ",
            "   O   ",
            "   O   ",
            "   O   ",
            "   O   "
        ),
        'U' to arrayOf(
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            " OOOOO "
        ),
        'V' to arrayOf(
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            "O     O",
            " O   O ",
            "  OOO  "
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
        'X' to arrayOf(
            "O     O",
            " O   O ",
            "  O O  ",
            "   O   ",
            "  O O  ",
            " O   O ",
            "O     O"
        ),
        'Y' to arrayOf(
            "O     O",
            " O   O ",
            "  O O  ",
            "   O   ",
            "   O   ",
            "   O   ",
            "   O   "
        ),
        'Z' to arrayOf(
            "OOOOOOO",
            "      O",
            "     O ",
            "    O  ",
            "   O   ",
            "  O    ",
            "OOOOOOO"
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
    
    fun printText(text: String) {
        val upperText = text.uppercase()
        val maxWidth = 52
        val charWidth = 7
        val maxChars = maxWidth / charWidth
        
        // Разбиваем текст на строки по maxChars символов
        val lines = upperText.chunked(maxChars)
        
        for (line in lines) {
            // Выводим каждую строку пикселей
            for (row in 0 until 7) {
                for (char in line) {
                    val charMap = charMaps[char] ?: charMaps[' ']!!
                    print(charMap[row])
                    print(" ") // Добавляем пробел между буквами
                }
                println()
            }
            println() // Пустая строка между строками текста
        }
    }
}
