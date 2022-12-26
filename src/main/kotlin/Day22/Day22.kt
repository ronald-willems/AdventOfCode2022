package Day22


import java.io.File

object Day22 {
    val map = mutableListOf<String>()

    var instructions = mutableListOf<String>()
    var pos = Position(0, 1)
    var dir = Direction("R")
    val path = mutableMapOf<Position,Direction>()

    val connections = mutableMapOf<Situation,Situation>()
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

    fun createConnections(a: CubeSite, da: Direction, b: CubeSite,db: Direction, flip:Boolean):
        Map<Situation,Situation>

    {
        val result = mutableMapOf<Situation,Situation>()
        for (x in a.xrange){
            for (y in a.yrange){
                val step = (x - a.xrange.first) + (y - a.yrange.first)
                //print("Step: " + step + " " + x + "," + y)

                val currSituation = Situation(Position(x,y),da)
                var newPos:Position?=null
                if (flip){
                    if (b.xrange.first==b.xrange.last){
                        newPos = Position(b.xrange.first,b.yrange.last-step)
                    }
                    else {
                        newPos = Position(b.xrange.last-step,b.yrange.first)
                    }
                }
                else{
                    if (b.xrange.first==b.xrange.last){
                        newPos = Position(b.xrange.first,b.yrange.first+step)
                    }
                    else {
                        newPos = Position(b.xrange.first+step,b.yrange.first)
                    }

                }

                result.put(currSituation, Situation(newPos!!,db))
            }
        }
        return result

    }

    fun initCube(){
        cube = true


        //All CubeSites to map
        val a = CubeSite(51..100,0..0)
        val b = CubeSite(101..150,0..0)
        val c = CubeSite(50..50,1..50)
        val d = CubeSite(151..151,1..50)
        val n = CubeSite(101..150,51..51)
        val e = CubeSite(50..50,51..100)
        val f = CubeSite(101..101,51..100)
        val g = CubeSite(1..50,100..100)
        val h = CubeSite(0..0,101..150)
        val i = CubeSite(101..101,101..150)
        val j = CubeSite(51..100,151..151)
        val k = CubeSite(0..0,151..200)
        val l = CubeSite(51..51,151..200)
        val m = CubeSite(1..50,201..201)

        connections.putAll(createConnections(a,Direction("U"),k,Direction("R"),false))
        connections.putAll(createConnections(k,Direction("L"),a,Direction("D"),false))

        connections.putAll(createConnections(b,Direction("U"),m,Direction("U"),false))
        connections.putAll(createConnections(m,Direction("D"),b,Direction("D"),false))

        connections.putAll(createConnections(c,Direction("L"),h,Direction("R"),true))
        connections.putAll(createConnections(h,Direction("L"),c,Direction("R"),true))

        connections.putAll(createConnections(d,Direction("R"),i,Direction("L"),true))
        connections.putAll(createConnections(i,Direction("R"),d,Direction("L"),true))

        connections.putAll(createConnections(e,Direction("L"),g,Direction("D"),false))
        connections.putAll(createConnections(g,Direction("U"),e,Direction("R"),false))

        connections.putAll(createConnections(n,Direction("D"),f,Direction("L"),false))
        connections.putAll(createConnections(f,Direction("R"),n,Direction("U"),false))

        connections.putAll(createConnections(j,Direction("D"),l,Direction("L"),false))
        connections.putAll(createConnections(l,Direction("R"),j,Direction("U"),false))




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

        //if this in map then pos = new pos and to turn
        val newSit = Day22.connections.get(Situation(this,dir))!!
        x = newSit.pos.x + newSit.dir.getXDir() //1 step extra
        y = newSit.pos.y + newSit.dir.getYDir() //1 step extra


        return newSit.dir
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

data class CubeSite(val xrange:IntRange, val yrange:IntRange)
