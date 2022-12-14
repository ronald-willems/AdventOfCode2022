package Day12

import org.junit.jupiter.api.Test

internal class TestRunner {

    @Test
    fun readFullInput() {
        Day12.readinput("Test")
        println(Day12.map.get(Pair(126,34)))


    }

    @Test
    fun part1Sample() {
        Day12.part1("Sample")

    }

    @Test
    fun part1() {
        Day12.part1("Test",1000000)
//        Day12.openPaths[0].display()
//        println()
//        Day12.openPaths[15].display()

        //Day12.finishedPaths.sortBy { it.points.size }
        //Day12.finishedPaths[0].display()
    }
}