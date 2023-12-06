package day04

import Solution
import println
import readInput
import kotlin.math.pow

fun main() {
    data class Card(val id: Int, val scratchCards: List<Int>, val picks: List<Int>) {
        val winnings: Set<Int> get() = scratchCards.intersect(picks.toSet())
    }

    val solution = object : Solution {
        fun parseInput(input: List<String>): List<Card> = input.mapIndexed { index, line ->
            val (picks, scratchCards) = line.substringAfter(": ")
                .split("|")
                .map {
                    it.split(" ")
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }
                }

            Card(index, scratchCards, picks)
        }

        override fun part1(input: List<String>): Int {
            return parseInput(input).sumOf {
                2.0.pow(it.winnings.count() - 1).toInt()
            }
        }

        override fun part2(input: List<String>): Int {
            val data = parseInput(input)
            val cardCount = MutableList(data.size) { 1 }

            data.forEachIndexed { index, card ->
                val winningsCount = card.winnings.size
                val currentCardCount = cardCount[index]
                for (i in index + 1 until index + winningsCount + 1) {
                    cardCount[i] += currentCardCount
                }
            }
            return cardCount.sum()
        }
    }

    val input = readInput("day04/Day04")
    solution.part2(input).println()
}
