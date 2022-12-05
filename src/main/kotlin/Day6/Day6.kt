package Day6

import Day5.Day5
import java.io.File
import java.util.regex.Pattern

object Day6 {


    fun readinput() {
        var endOfStacks = false
        var stackLines = mutableListOf<String>();
        File("src/main/resources/Day5/TestInput.txt").forEachLine { line ->

            val elements = line.split(" ")
            val elements2 = line.split( Pattern.compile("[,\\-]"))


        }
    }

    fun part1(){

    }
}