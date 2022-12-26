package Day22

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day22Test {

    @Test
    fun part1() {
        assertEquals(75254,Day22.findPath("Test"))
        Day22.display()
        //91472 to high
        //63300 to low
        //75252 to low
    }

    @Test
    fun cubeInit(){

        Day22.initCube()
        assertEquals(Situation(Position(103,51),Direction("U")),Day22.connections.get(Situation(Position(101,53),Direction("R"))))
        assertEquals(Situation(Position(50,98),Direction("R")),Day22.connections.get(Situation(Position(48,100),Direction("U"))))

    }
    @Test
    fun connect(){
        val f = CubeSite(101..101,51..100)
        val n = CubeSite(101..150,51..51)
        val cons = Day22.createConnections(f, Direction("R"), n, Direction("U"), false)
        cons.forEach{
            println(" " +it.key + " " + it.value)
        }
    }

    //

    @Test
    fun part2() {
        Day22.initCube()
        Day22.findPath("Test")
        Day22.display()

    }


    @Test
    fun readInput() {
        Day22.readInput("Test")
        println(Day22.map[0] + "!")
        println(Day22.map[1] + "!")
        println(Day22.map[110] + "!")
        println(Day22.map[200] + "!")
        println(Day22.map.size)
        println(Day22.map[1].length)



    }

    @Test
    fun part1Test() {
        Day22.readInput("Sample")


        Day22.pos.x = Day22.map[1].indexOf(".") //Go to start
        assertEquals(Position(9,1),(Day22.pos))
        Day22.doInstruction("10")
        assertEquals(Position(11,1),(Day22.pos))
        Day22.doInstruction("R")
        assertEquals(Direction("D"),Day22.dir)
        Day22.doInstruction("5")
        assertEquals(Position(11,6),(Day22.pos))
        Day22.doInstruction("L")
        assertEquals(Direction("R"),Day22.dir)
        Day22.doInstruction("5")
        assertEquals(Position(4,6),(Day22.pos))




    }
}