package Day6


import java.io.File
import java.util.regex.Pattern

object Day6 {


    fun readinput() {


        //val elements = line.split(" ")
        //val elements2 = line.split( Pattern.compile("[,\\-]"))


    }


    fun hasOverlap(window: List<Char>): Boolean {
        return window.toSet().size != window.size
    }

    fun part2() {
        var result = 0
        File("src/main/resources/Day6/TestInput.txt").forEachLine { line ->
            var window = mutableListOf<Char>()

            for (i in 0..line.length) {
                var char = line[i]
                window.add(char)
                if (window.size > 14) window.removeFirst()
                if (window.size == 14) {
                    if (!hasOverlap(window)) {
                        result = i + 1
                        println(result)
                        break;


                    }
                }
            }

            println(result)


        }

    }
}
