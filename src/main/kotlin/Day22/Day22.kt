package Day22


import java.io.File

object Day22 {
    val map = mutableListOf<String>()

    var instructions = mutableListOf<String>()
    var pos = Position(0, 1)
    var dir = Direction("R")
    val path = mutableMapOf<Position,Direction>()
    val transformations = mutableMapOf<Situation,Situation>()

    var cube = false;

    fun mapItem(x: Int, y: Int): String {
        return map[y][x].toString()
    }

    fun mapItem(pos: Position): String {
        return map[pos.y][pos.x].toString()
    }

    fun readInput(inputType: String) {
        var mapInput = true
        File("src/main/resources/Day22/${inputType}Input.txt").forEachLine { line ->
            if (line == "") mapInput = false
            else {
                if (mapInput) {
                    map.add(" " + line + " ")  //padding
                } else {
                    instructions = line.replace("R", " R ").replace("L", " L ").split(" ").toMutableList()

                }
            }
        }

        val spacesLine = " ".repeat(map[0].length)
        map.add(0, spacesLine)
        map.add(spacesLine)

        for (i in 0..map.size-1){
            val diff = map[0].length - map[i].length
            if (diff>0) map[i] = map[i] + " ".repeat(diff) // " ".repeat(diff)
        }



    }

    fun initCube(inputType: String){
        cube = true
        val cubesize = if (inputType=="Sample") 4 else 50

        //TODO Make map Situation => Situation
        //14


    }


    fun turn(turn: String) {
        dir = Direction(
            if (turn == "R") {
                when (dir.dir) {
                    "R" -> "D"
                    "D" -> "L"
                    "L" -> "U"
                    else -> "R"
                }
            } else {
                when (dir.dir) {
                    "R" -> "U"
                    "D" -> "R"
                    "L" -> "D"
                    else -> "L"
                }

            }
        )
        path.put(pos,dir)

    }





    fun move(nr: Int) {
        //println("Start pos" + pos)
        for (i in 1..nr) {

            val nextPos = pos.copy()
            var nextDir = dir.copy()
            nextPos.x += 1 * dir.getXDir()
            nextPos.y += 1 * dir.getYDir()
            var mapItem = mapItem(nextPos)
            if (mapItem == " ") {
               nextDir = nextPos.fly(nextDir)

            }
            mapItem = mapItem(nextPos)
            if (mapItem == "#") {
                break;
            } else {

                dir = nextDir
                pos = nextPos
                path.put(pos,dir)
            }


        }


    }

    fun doInstruction(instr:String){
        if (instr[0].isDigit())
            move(instr.toInt())
        else
            turn(instr)

        //display()
    }


    fun findPath(inputType: String): Int {
        readInput(inputType)

        pos.x = map[1].indexOf(".") //Go to start


        instructions.forEach {

           doInstruction(it)


        }


        println("Final: " + (pos.x) + "," + (pos.y))
        val result = (pos.y)*1000 + (pos.x)*4 + dir.facingCount()
        println("Result= "+ result)
        return result
    }

    fun display() {
        for (y in 0..map.size-1){
            for (x in 0..map[0].length-1){
                val pathpos = path.get(Position(x,y))
                if (pathpos!=null)
                    print(pathpos.asArrow())

                else
                    print(mapItem(x,y))

            }
            println()
        }

        println()
        println()


    }


}

data class Direction(var dir: String) {
    fun getDirArray(): IntArray {
        return when (dir) {
            "D" -> intArrayOf(0, 1)
            "L" -> intArrayOf(-1, 0)
            "U" -> intArrayOf(0, -1)
            else -> intArrayOf(1, 0) //R

        }

    }

    fun getYDir(): Int {
        return when (dir) {
            "D" -> 1
            "L" -> 0
            "U" -> -1
            else -> 0

        }
    }

    fun getXDir(): Int {
        return when (dir) {
            "D" -> 0
            "L" -> -1
            "U" -> 0
            else -> 1

        }

    }

    fun asArrow(): String {
        return when (dir) {
            "D" -> "V"
            "L" -> "<"
            "U" -> "A"
            else -> ">"

        }
    }

    fun facingCount():Int{
        return when (dir){
            "D" -> 1
            "L" -> 2
            "U" -> 3
            else -> 0

        }

    }
}

data class Position(var x: Int, var y: Int){


    fun fly(dir:Direction):Direction{
        return if (Day22.cube) cubeFly(dir) else simpleFly(dir)
    }

    fun cubeFly(dir:Direction):Direction{
        //TODO
        //if this in map then pos = new pos and to turn

        return Day22.dir
    }

    fun simpleFly(dir:Direction):Direction {
        //ga terug tot begin en dan weer 1 vooruit.
        var goBackItem = ""
        while (goBackItem != " ") {
            x -= dir.getXDir()
            y -= dir.getYDir()
            goBackItem = Day22.mapItem(this)
        }
        x += dir.getXDir()
        y += dir.getYDir()

        return dir

    }
}

data class Situation(val pos:Position, val dir:Direction)
