package day02

import Solution
import println
import readInput

fun main() {
    data class GameRecord(val id: Int, val sets: Map<String, Int>)

    val dayTwo = object : Solution {
        fun List<String>.toGameRecords(): List<GameRecord> = mapIndexed { gameIndex, input ->
            val processed = input.substringAfter(":").split(";").map {
                it.split(",")
            }

            val sets = mutableMapOf<String, Int>()

            processed.mapIndexed { index, strings ->
                strings.map {
                    val (count, color) = it.trim().split(" ")

                    // Prepend bogus index to bypass unique key constraint of maps
                    sets.put("$index$color", count.toInt())
                }
            }

            GameRecord(id = gameIndex + 1, sets = sets)
        }

        override fun part1(input: List<String>): Int {
            val gameRecords = input.toGameRecords()

            return gameRecords.filter { game ->
                game.sets.all { (colorCode, score) ->
                    val color = colorCode.substring(1)
                    when (color) {
                        "red" -> score <= 12
                        "green" -> score <= 13
                        "blue" -> score <= 14
                        else -> true
                    }
                }
            }.sumOf { it.id }
        }

        private fun GameRecord.maxOf(color: String): Int = sets.maxOf { (c, count) ->
            // Substring from second character to drop the bogus index
            if (color == c.substring(1)) count else 0
        }

        override fun part2(input: List<String>): Int {
            return input.toGameRecords().sumOf { game ->
                game.maxOf("red") * game.maxOf("green") * game.maxOf("blue")
            }
        }
    }

    val input = readInput("day02/Day02")
    dayTwo.part2(input).println()
}
