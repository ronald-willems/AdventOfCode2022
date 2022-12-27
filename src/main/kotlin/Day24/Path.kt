package Day24

import kotlin.math.absoluteValue


data class Path( val points:MutableList<Step>, val end:Position){

    var distance = 0


    init{
        val curr = points.last().curr
        distance+= (curr.x - end.x).absoluteValue
        distance+= (curr.y - end.y).absoluteValue


    }

    fun size():Int {
        return points.size
    }

    fun last():Step{
        return points.last()
    }

    fun clonePlus(extra:Step):Path{
        var newpoints = points.toMutableList()
        newpoints.add(extra)
        return Path(newpoints,end)

    }

    fun cloneMin():Path{
        var newpoints = points.toMutableList()
        newpoints.removeLast()
        return Path(newpoints,end)

    }

    fun possibleMoves(minute:Int): List<Position>{
        var result = mutableListOf<Position>()
        var currentPos = points.last().curr
        //Up.down, left, right, say
        val up = currentPos.addDirection(Direction("^"))
        if (isPossible(up,minute)) result.add(up)
        val down = currentPos.addDirection(Direction("v"))
        if (isPossible(down,minute)) result.add(down)
        val left = currentPos.addDirection(Direction("<"))
        if (isPossible(left,minute)) result.add(left)
        val right = currentPos.addDirection(Direction(">"))
        if (isPossible(right,minute)) result.add(right)

        if (isPossible(currentPos,minute)) result.add(currentPos)


        return result

    }

    fun isPossible(pos:Position,minute:Int):Boolean{
        return (pos !in Day24.minuteBlizards.get(minute)!! && pos !in Day24.walls && pos.y>=0 && pos.y<=Day24.maxY)
    }



   /* fun display(){
        for (y in 0..Day12Self.sizeY-1){
            for (x in 0.. Day12Self.sizeX-1){
                if (points.contains(Pair(x,y))) print("#")
                else print(".")
            }
            println()
        }

    }*/
}