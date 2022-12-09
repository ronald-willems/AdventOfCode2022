package Day9

import java.io.File
import java.util.regex.Pattern
import kotlin.math.absoluteValue

object Day9 {
    var instructions = mutableListOf<Pair<String,Int>>()
    var visitedByTail = mutableSetOf<Pair<Int,Int>>()
    var head = Position(0,0)
    var tail = Position(0,0)

    fun readinput() {

        File("src/main/resources/Day9/TestInput.txt").forEachLine { line ->
            val elements = line.split(" ")
            instructions.add(Pair(elements[0],elements[1].toInt()))
            //val elements2 = line.split( Pattern.compile("[,\\-]"))

        }
    }

    fun moveHead(direction:String){

        when (direction) {
            "U" -> head.y+=1
            "D" -> head.y-=1
            "L" -> head.x-=1
            "R" -> head.x+=1


        }

    }

    fun moveTailInDirA(ta:Int,tb:Int,ha:Int, hb:Int):Boolean{
        if (ha - ta>1) {

            return true
        }
        if (ha-ta>0 && (hb-tb).absoluteValue>1) {

            return true
        }

        return false


    }

    fun moveTail(){
        var newX = tail.x
        var newY = tail.y
        if (moveTailInDirA(tail.x, tail.y, head.x, head.y)) newX= tail.x+1
        if (moveTailInDirA(tail.y, tail.x, head.y, head.x)) newY = tail.y+1
        if (moveTailInDirA(tail.x*-1, tail.y*-1, head.x*-1, head.y*-1)) newX = tail.x-1
        if (moveTailInDirA(tail.y*-1, tail.x*-1, head.y*-1, head.x*-1)) newY = tail.y-1

        tail.x = newX
        tail.y = newY

    }


    fun part1(){
        readinput()
        instructions.forEach {
            for (i in 1.. it.second){
                moveHead(it.first)
                moveTail()
                visitedByTail.add(Pair(tail.x, tail.y))
            }
        /*    print("head" + head.x + " " + head.y)
            println("-- tail" + tail.x + " " + tail.y)
            println(visitedByTail.size)*/
        }
        println(visitedByTail.size)
     /*   for (pair in visitedByTail) {
            println(pair)

        }*/


    }


    fun part2(){
        readinput()

    }
}

data class Position(var x:Int, var y: Int)
