package day03

import AdventOfCode
import println
import readInput

fun main() {
    val dayThree = object : AdventOfCode {
        fun getNumber(lines: String, index: Int): Int? {
            // If the index is out of bounds or does not contain a digit, return null.
            if (index !in lines.indices || !lines[index].isDigit()) return null

            // keep subtracting from leftIndex while there are digits to the left. The getOrNull version of get ensures
            // that the leftIndex does not go below 0.
            var leftIndex = index
            while (lines.getOrNull(leftIndex - 1)?.isDigit() == true) leftIndex--

            // keep adding to rightIndex while there are digits to the right
            var rightIndex = index
            while (lines[rightIndex + 1].isDigit()) rightIndex++

            // get the substring and return it as an Int
            return lines.substring(leftIndex..rightIndex).toInt()
        }

        fun validNumbers(lines: String, validSymbol: (Char) -> Boolean): List<List<Int>> = lines
            .withIndex()
            .filter { validSymbol(it.value) } // tests whether a 'symbol' in pt1, and a 'gear' in pt2
            .map { (index, _) -> // turn each valid symbol into a list of the adjacent Ints
                (-1..1).flatMap { x -> // 3x3 grid
                    (-1..1).mapNotNull { y ->
                        getNumber(
                            lines,
                            index + y * (lines.indexOf("\n") + 1) + x,
                        ) // get any number with a digit in that grid
                    }
                }.distinct() // the above grid will have duplicate numbers; this de-dupes the list
            }

        override fun part1(input: List<String>): Int {
            val lines = input.joinToString("\n")

            return validNumbers(lines) { it !in ".\n" && !it.isDigit() }.sumOf { it.sum() }
        }

        override fun part2(input: List<String>): Int {
            val lines = input.joinToString("\n")

            return validNumbers(lines) { it == '*' }
                .filter { it.size == 2 }
                .sumOf { it.reduce(Int::times) }
        }
    }

    val input = readInput("day03/Day03")
    dayThree.part2(input).println()
}
