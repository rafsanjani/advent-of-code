package day06

import Solution
import println
import readInput

fun main() {
    data class Race(val targetDistance: Long, val time: Long)

    val solution = object : Solution {
        private fun parse(
            input: List<String>,
        ): List<Race> {
            val timeList =
                input.first().substringAfter(": ").trim().split(" ").filter { it.isNotEmpty() }.map(String::toLong)

            val distanceList =
                input.last().substringAfter(": ").trim().split(" ").filter { it.isNotEmpty() }.map(String::toLong)

            return distanceList.zip(timeList) { distance, time ->
                Race(targetDistance = distance, time = time)
            }
        }

        private fun parse(input: String): Race {
            val time = input.splitToSequence("\n").first().substringAfter(":").trim().filter(Char::isDigit).toLong()
            val distance = input.splitToSequence("\n").last().substringAfter(":").trim().filter(Char::isDigit).toLong()
            return Race(distance, time)
        }

        private fun calculateWinningWays(race: Race): Int {
            return (1..race.time).count { buttonTime ->
                (race.time - buttonTime) * buttonTime > race.targetDistance
            }
        }

        override fun part1(input: List<String>): Int {
            return parse(input).map(::calculateWinningWays).reduce(Int::times)
        }

        override fun part2(input: List<String>): Int {
            val race = parse(input.joinToString(separator = "\n"))
            return calculateWinningWays(race)
        }
    }

    val input = readInput("day06/Day06")
    solution.part2(input).println()
}
