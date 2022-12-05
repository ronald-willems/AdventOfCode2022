package Day2

import org.junit.jupiter.api.Test

internal class Day2Test {

    @Test
    fun testPair() {
        var a = Pair("a", "b")
        var b = Pair("a", "b")
        if (a == b) println("Yes")
    }

    @Test
    fun testPart1() {
        var result = Day2.part1()
        println(result)
    }

    @Test
    fun testPart2() {
        var result = Day2.part2()
        println(result)
    }
}