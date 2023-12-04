package day01

import AdventOfCode

fun main() {
    val adventOfCodeOne = object : AdventOfCode {
        override fun part1(input: List<String>): Int {
            return input.map { text ->
                "${text.first(Char::isDigit)}${text.reversed().first(Char::isDigit)}"
            }.sumOf { it.toInt() }
        }

        override fun part2(input: List<String>): Int {
            // Mapper
            val lookup = arrayOf(
                "one" to "1",
                "two" to "2",
                "three" to "3",
                "four" to "4",
                "five" to "5",
                "six" to "6",
                "seven" to "7",
                "eight" to "8",
                "nine" to "9",
            )

            // Replace all words with their digit equivalents and solve using part1 algorithm
            val adjustedInput = input.map { oldCalibrationLine ->
                var newCalibrationLine = oldCalibrationLine

                for (index in oldCalibrationLine.indices) {
                    lookup.forEach { (word, digit) ->
                        val substring = oldCalibrationLine.substring(index)
                        if (substring.startsWith(word)) {
                            newCalibrationLine = newCalibrationLine.replace(
                                oldValue = substring,
                                newValue = substring.replaceFirst(word, digit + word.substring(1)),
                            )
                        }
                    }
                }
                newCalibrationLine
            }
            return part1(adjustedInput)
        }
    }
}
