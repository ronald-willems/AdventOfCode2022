package Day13

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day13Test {

    @Test
    fun findListEnd() {
        var input = "[sdfasdf]dsd"
        val endIndex = Day13.findListEnd(input)
        assertEquals("[sdfasdf]",input.substring(0,endIndex+1))
    }

    @Test
    fun parseList1() {
        var input = "[1,2,3]"
        val list = Day13.parseList(input)


        assertEquals(2,list[1] as Int)
    }

    @Test
    fun parseList2() {
        var input = "[1,[2,4],3]"
        val list = Day13.parseList(input)
        print(list)


        assertEquals(3,list[2] as Int)
        assertEquals(4,(list[1] as MutableList<Any>)[1] as Int)
    }

    @Test
    fun parseList3() {
        var input = "[[1],[2,3,4]]"
        val list = Day13.parseList(input)
        print(list)


       // assertEquals(1,list[2] as Int)
        assertEquals(2,(list[1] as MutableList<Any>)[0] as Int)
    }

    @Test
    fun checkList2() {
        var left = Day13.parseList("[[1],[2,3,4]]")
        var right = Day13.parseList("[[1],4]")
        println(Day13.rightOrder(left,right))
    }

    @Test
    fun checkList4() {
        var left = Day13.parseList("[[4,4],4,4]")
        var right = Day13.parseList("[[4,4],4,4,4]")
        println(Day13.rightOrder(left,right))
    }



    @Test
    fun part1() {
        Day13.part1("Test")
    }

    @Test
    fun part2() {
        Day13.part2("Test")
    }




}