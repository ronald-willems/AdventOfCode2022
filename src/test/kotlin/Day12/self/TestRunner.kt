package Day12.self

import org.junit.jupiter.api.Test

internal class TestRunner {

    @Test
    fun readFullInput() {
        Day12Self.readinput("Test")
        println(Day12Self.map.get(Pair(126,34)))


    }

    @Test
    fun part1Sample() {
        Day12Self.part1("Sample")

    }

    @Test
    fun part1() {
        Day12Self.part1("Test",1000000)
//        Day12.openPaths[0].display()
//        println()
//        Day12.openPaths[15].display()

        //Day12.finishedPaths.sortBy { it.points.size }
        //Day12.finishedPaths[0].display()
    }
}