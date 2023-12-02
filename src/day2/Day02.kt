package day2

import AdventOfCode
import println
import readInput
import kotlin.math.max

fun main() {
    data class GameRecord(val id: Int, val sets: Map<String, Int>)

    val dayTwo = object : AdventOfCode {
        fun List<String>.toGameRecords(): List<GameRecord> = mapIndexed { gameIndex, input ->
            val processed = input.substringAfter(":").split(";").map {
                it.split(",")
            }

            val sets = mutableMapOf<String, Int>()

            processed.mapIndexed { index, strings ->
                strings.map {
                    val (count, color) = it.trim().split(" ")
                    sets.put("$index$color", count.toInt())
                }
            }

            GameRecord(id = gameIndex + 1, sets = sets)
        }

        override fun part1(input: List<String>): Int {
            val totalGames = input.toGameRecords()

            return totalGames.filter { game ->
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

        override fun part2(input: List<String>): Int {
            val totalGames = input.toGameRecords()

            return totalGames.sumOf { game ->
                var maxRed = 0
                var maxGreen = 0
                var maxBlue = 0

                game.sets.forEach { (t, count) ->
                    val color = t.substring(1)

                    when (color) {
                        "red" -> maxRed = max(maxRed, count)
                        "green" -> maxGreen = max(maxGreen, count)
                        "blue" -> maxBlue = max(maxBlue, count)
                    }
                }

                maxRed * maxGreen * maxBlue
            }
        }
    }

    val input = readInput("day2/Day02_test")
    dayTwo.part1(input).println()
}
