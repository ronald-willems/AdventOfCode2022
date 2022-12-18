package Day17

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RockTest{
    @BeforeEach
    internal fun setUp() {
        Day17.readInput("Sample")
    }

    @Test
    fun restRock1() {
        var r1 = Rock1()
        assertEquals(1,r1.points[0][3])
        assertEquals(0,r1.points[0][1])
        assertEquals(0,r1.points[1][3])
    }

    @Test
    fun restRock5() {
        var r5 = Rock5()
        assertEquals(1,r5.points[0][2])
        assertEquals(1,r5.points[1][3])
        assertEquals(0,r5.points[0][4])
        r5.print()
    }

    @Test
    fun moveLeft(){

        Day17.cave[6][1]=2
        var r1 = Rock2();
        println(r1.moveH(-1))
        r1.print()
        println(r1.moveH(-1))
        r1.print()
        println(r1.moveH(-1))
        r1.print()

    }

    @Test
    fun testPrint(){
        var r :Rock = Rock2();
        r.moveH(-1)
        r.moveDown()
        r.moveDown()
        r.moveDown()
        r.moveDown()
        r.addToBottom()
        r = Rock3()
        r.moveH(1)
        r.moveDown()
        r.moveDown()
        r.moveDown()
        r.moveH(-1)
        r.moveDown()
        println(r.moveDown())
        r.addToBottom()
        r = Rock5()

        r.printInBottom()


    }
}