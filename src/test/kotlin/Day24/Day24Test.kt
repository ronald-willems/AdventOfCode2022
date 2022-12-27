package Day24

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day24Test {

    @Test
    fun readInput() {
        Day24.readInput("Test")
        Day24.display()
    }

    @Test
    fun testBlizards(){
        Day24.readInput("Test")

   /*     Day24.allBlizzards.forEach { it.move() }
        Day24.allBlizzards.forEach { it.move() }
        Day24.allBlizzards.forEach { it.move() }
        Day24.allBlizzards.forEach { it.move() }
        Day24.allBlizzards.forEach { it.move() }
        Day24.allBlizzards.forEach { it.moveBack() }
        Day24.allBlizzards.forEach { it.moveBack() }
        Day24.allBlizzards.forEach { it.moveBack() }
        Day24.allBlizzards.forEach { it.moveBack() }
        Day24.allBlizzards.forEach { it.moveBack() }*/
        Day24.display()

    }
    @Test
    fun testPaths() {
        Day24.readInput("sample")
        val pf = PathFinder(1,Day24.opening,Day24.ending)

        var currPath = Path(mutableListOf(pf.startStep),Day24.ending)
        val moves = currPath.possibleMoves(1)
        moves.forEach { println(it) }
        println(currPath.distance)

        println()
        val newPath= pf.createNewPath(currPath, moves[0], 2)!!

        val moves2 = newPath.possibleMoves(2)
        moves2.forEach { println(it) }
        println(currPath.distance)

        println()
        val newPath2= pf.createNewPath(currPath, moves2[0], 3)!!

        val moves3 = newPath2.possibleMoves(3)
        moves3.forEach { println(it) }
        println(currPath.distance)

        println()
        val newPath3= pf.createNewPath(currPath, moves3[0], 4)!!

        val moves4 = newPath3.possibleMoves(4)
        assertEquals(1,moves4.size)
        moves4.forEach { assertEquals(Position(1,1),it) }


    }

    @Test
    fun distance(){
        Day24.readInput("sample")
        val pf = PathFinder(1,Day24.opening,Day24.ending)

        var currPath = Path(mutableListOf(pf.startStep),Day24.ending)
        println(currPath.distance)


    }

    @Test
    fun part1(){
        val result = Day24.part1("Sample")
        println(result)

        //154  & 155 is too low
    }

    @Test
    fun part2(){
        val result = Day24.part2("Test")
        println(result)

        //154  & 155 is too low
    }
}