package Day12.self

import kotlin.math.absoluteValue

data class Path(private val points:MutableList<Pair<Int,Int>>){

    var distance = 0
    var height = 1

    init{
        val curr = points.last()
        distance+= (curr.first-Day12Self.end.first).absoluteValue
        distance+= (curr.second-Day12Self.end.second).absoluteValue
        height = Day12Self.map.getOrDefault(curr,1)

    }

    fun size():Int {
        return points.size
    }

    fun last():Pair<Int,Int>{
        return points.last()
    }

    fun clonePlus(extra:Pair<Int,Int>):Path{
        var newpoints = points.toMutableList()
        newpoints.add(extra)
        return Path(newpoints)

    }

    fun cloneMin():Path{
        var newpoints = points.toMutableList()
        newpoints.removeLast()
        return Path(newpoints)

    }

    fun possibleMoves(): List<Pair<Int,Int>>{
        var result = mutableListOf<Pair<Int,Int>>()
        var currentPos = points.last()
        //Up
        val up = Pair(currentPos.first,currentPos.second-1)
        if (isPossible(currentPos, up)) result.add(up)
        var down = Pair(currentPos.first,currentPos.second+1)
        if (isPossible(currentPos, down)) result.add(down)
        var left = Pair(currentPos.first+1,currentPos.second)
        if (isPossible(currentPos, left)) result.add(left)
        var right = Pair(currentPos.first-1,currentPos.second)
        if (isPossible(currentPos, right)) result.add(right)

        return result

    }

    fun isPossible(curr: Pair<Int,Int>, new: Pair<Int,Int>):Boolean{
        return isMax1Higher(curr,new)  && faster(new)
    }

    fun isMax1Higher(curr:Pair<Int,Int>, new: Pair<Int,Int>):Boolean{
        val currHeight = Day12Self.map.get(curr)
        val newHeight = Day12Self.map.get(new)
        //check if on map.
        if (newHeight!=null){
            //if ((newHeight -  currHeight!!).absoluteValue<=1) return true
            //if ((newHeight - 1) == currHeight!!) return true
            //if (newHeight  == currHeight!!) return true

            if ((newHeight - 1) <= currHeight!!) return true
        }
        return false
    }



    fun faster(pos:Pair<Int,Int>):Boolean{
        val earlierDistance = Day12Self.pointsVisited.get(pos)
        if (earlierDistance!=null){
            return this.points.size+1<earlierDistance
        }

        return true
    }

    fun display(){
        for (y in 0..Day12Self.sizeY-1){
            for (x in 0.. Day12Self.sizeX-1){
                if (points.contains(Pair(x,y))) print("#")
                else print(".")
            }
            println()
        }

    }
}