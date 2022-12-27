package Day24

import java.io.File

object Day24 {
    private val allBlizzards = mutableListOf<Blizard>()
    val walls = mutableListOf<Position>()
    val minuteBlizards = mutableMapOf<Int,List<Position>>()



    var minX = 0
    var minY = 0
    var maxX = 0
    var maxY = 0

    var opening = Position(1, 0)
    var ending =  Position(Day24.maxX-1,Day24.maxY)


    fun moveBlizards(){
        Day24.allBlizzards.forEach { it.move() }


    }

   /* fun moveBackBlizards(){
        Day24.allBlizzards.forEach { it.moveBack() }
    }*/

    fun fillBlizardMap(){
        minuteBlizards.put(1, allBlizzards.map{it.pos})
        for (i in 1 .. 1500){
            allBlizzards.forEach { it.move() }
            minuteBlizards.put(i, allBlizzards.map{it.pos})

        }


    }

    fun readInput(inputType: String) {
        var y = 0
        File("src/main/resources/Day24/${inputType}Input.txt").forEachLine { line ->
            var x =0
            line.forEach {
                val pos = Position(x,y)
                val str = it.toString()

                if (str in listOf("v","^",">","<")) allBlizzards.add(Blizard(pos,Direction(str)))
                else if (str=="#") walls.add(pos)

                x++

            }
            y++

        }

         minX = walls.minOf { it.x }
         minY = walls.minOf { it.y }
         maxX = walls.maxOf { it.x }
         maxY = walls.maxOf { it.y }

        opening = Position(1, 0)
        ending =  Position(Day24.maxX-1,Day24.maxY)


        fillBlizardMap()

    }

    fun display(){
        println()

        for (y in minY..maxY) {
            for (x in minX..maxX) {
                val pos = Position(x,y)
                if ( pos in walls) print("#") else {
                    val blizards = allBlizzards.filter { it.pos == pos }
                    if (blizards.size>1) print(blizards.size) else

                        if (blizards.size==1) print(blizards[0].dir.value)
                        else print(".")


                }
            }
            println()
        }


    }

    fun part1(inputType: String){

        val endPos = Position(Day24.maxX-1,Day24.maxY)
        readInput(inputType)
        val wp = PathFinder(1,opening,ending).findShortestPath()
        if (wp!=null) {
            println("Winner: " + (wp.size() - 1))
            println(wp.points)
        }

    }


    fun part2(inputType: String){
        readInput(inputType)


        //Start to End
        var wp = PathFinder(1, opening,ending).findShortestPath()
        val firstTrip = wp!!.size()-1
        println("First: " + firstTrip)
        wp = PathFinder(firstTrip+1, ending,opening).findShortestPath()
        val secondTrip = wp!!.size()-1
        println("second: " + secondTrip)
        wp = PathFinder(firstTrip+secondTrip+1, opening, ending).findShortestPath()
        val thirdTrip = wp!!.size()-1
        println("Third: " + thirdTrip)

        println("Total: " + (firstTrip+secondTrip+thirdTrip))



    }
}



