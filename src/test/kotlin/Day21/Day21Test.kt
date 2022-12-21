package Day21

import org.junit.jupiter.api.Test

internal class Day21Test {

    @Test
    fun part1() {
        Day21.part1("Test")
        val root = Day21.allMonkeys["root"] as MathMonkey
        val dep1 = Day21.allMonkeys[root.dep1] as MathMonkey
        val dep2 = Day21.allMonkeys[root.dep2] as MathMonkey
//        println(root.humanInfluence())
//        println(dep1.humanInfluence())
//        println(dep2.humanInfluence())
    }

    @Test
    fun readInput() {
        Day21.readInput("Sample")
        println(Day21.allMonkeys.size)
        println(Day21.allMonkeys.get("ptdq")!!.yell())
    }

    @Test
    fun part2() {
        Day21.part2("Test")
    }
}