package Day1


import java.io.File

object Day1 {
    var allElves = mutableListOf<Int>()

    fun part2() {
        part1();



        allElves.sortByDescending { it }

             //Waarom zitten ze er dubbel in?
        println(allElves[0]+ allElves[1]+ allElves[2])

    }

    fun part1() {
        var currentCalories = 0

        File("src/main/resources/Day1/TestInput.txt").forEachLine { line ->
            if (line == "") {
                allElves.add(currentCalories)
               // println(currentCalories)
                currentCalories = 0

            } else {
                currentCalories = currentCalories + line.toInt()

            }


        }

      //  println(allElves.maxOf { it })

    }


}
