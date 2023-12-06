import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.createFile
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.writeText

fun main() {
    fun pad(day: Int): String = day.toString().padStart(2, '0')

    val sourceDirectory = Path("src")
    val previousDay = sourceDirectory.listDirectoryEntries()
        .maxOf { it.fileName.toString() }
        .filter { it.isDigit() }
        .toInt()

    val currentDay = pad(previousDay + 1)
    val todayPackage = "day$currentDay"

    val fileContents = """
        package day$currentDay
    
        import Solution
        import println
        import readInput
    
        fun main() {
            val solution = object : Solution {
                override fun part1(input: List<String>): Int {
                    TODO("Not yet implemented")
                }
    
                override fun part2(input: List<String>): Int {
                    TODO("Not yet implemented")
                }
            }
    
            val input = readInput("day$currentDay/Day$currentDay")
            solution.part1(input).println()
        }

    """.trimIndent()

    sourceDirectory.resolve(todayPackage).createDirectory().apply {
        resolve("Day$currentDay.kt").createFile().apply {
            writeText(fileContents)
        }
        resolve("Day${currentDay}_test.txt").createFile()
        resolve("Day$currentDay.txt").createFile()
        resolve("problem.txt").createFile()
    }
}
