package Day25

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day25Test {

    @Test
    fun convertSNAFU() {
        assertEquals(1,Day25.convertSNAFU("1"))
        assertEquals(2,Day25.convertSNAFU("2"))
        assertEquals(1,Day25.getSNAFUBase(0))
        assertEquals(5,Day25.getSNAFUBase(1))
        assertEquals(25,Day25.getSNAFUBase(2))
        assertEquals(125,Day25.getSNAFUBase(3))
        assertEquals(8,Day25.convertSNAFU("2="))
    }

    @Test
    fun base(){
        println(Long.MAX_VALUE)
        println(Day25.getSNAFUBase(21))
    }

    @Test
    fun part1(){
        val result = Day25.part1("Test")
        println(result)
        println(Day25.toSnafu(result))

    }

    @Test
    fun testTops(){
        Day25.readInput("Sample")
        for (i in 0..21) println(Day25.snafuTops[i])
    }

    @Test
    fun LongtoSnafu(){
        Day25.readInput("Sample")
        assertEquals(1,Day25.snafuLength(2))
        assertEquals(2,Day25.snafuLength(3))
        assertEquals(3,Day25.snafuLength(13))

        assertEquals(1, Day25.getSNAFUBase(0))
        assertEquals(5, Day25.getSNAFUBase(1))
        assertEquals(25, Day25.getSNAFUBase(2))


        assertEquals("2",Day25.toSnafu(2))
        assertEquals("1=",Day25.toSnafu(3))
        assertEquals("1-",Day25.toSnafu(4))
        assertEquals("10",Day25.toSnafu(5))
        assertEquals("11",Day25.toSnafu(6))
        assertEquals("12",Day25.toSnafu(7))
        assertEquals("2=",Day25.toSnafu(8))

      //  println(Day25.toSnafu(2022))

    }

    @Test
    fun LongtoSnafu2() {
        Day25.readInput("Sample")
        assertEquals("1=11-2",Day25.toSnafu(2022))

    }

}