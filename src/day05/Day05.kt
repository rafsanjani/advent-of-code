package day05

import Solution
import println
import readInput

fun main() {
    val solution = object : Solution {
        override fun part1(input: List<String>): Int {
            return 1
        }

        override fun part2(input: List<String>): Int {
            return 100
        }
    }

    val input = readInput("day05/Day05")
    solution.part1(input).println()
}
