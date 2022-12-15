package Day12.self

import kotlin.math.absoluteValue

data class Path(val points:MutableList<Pair<Int,Int>>){

    var distance = 0
    var height = 1

    init{
        val curr = points.last()
        distance+= (curr.first-Day12Self.end.first).absoluteValue
        distance+= (curr.second-Day12Self.end.second).absoluteValue
        height = Day12Self.map.getOrDefault(curr,1)

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
        return isMax1Higher(curr,new) && !alreadyVisited(new) &&!alreadyVisitedFaster(new)
    }

    fun isMax1Higher(curr:Pair<Int,Int>, new: Pair<Int,Int>):Boolean{
        val currHeight = Day12Self.map.get(curr)
        val newHeight = Day12Self.map.get(new)
        //check if on map.
        if (newHeight!=null){
            if ((newHeight -  currHeight!!).absoluteValue<=1) return true
            //if ((newHeight - 1) == currHeight!!) return true
            //if (newHeight  == currHeight!!) return true

            //if ((newHeight - 1) <= currHeight!!) return true
        }
        return false
    }

    fun alreadyVisited(pos:Pair<Int,Int>):Boolean{
        return points.contains(pos)

    }

    fun alreadyVisitedFaster(pos:Pair<Int,Int>):Boolean{
        val allPaths = Day12Self.openPaths.toMutableList()
        allPaths.addAll(Day12Self.finishedPaths)
        allPaths.forEach {
            if (it != this && it.alreadyVisited(pos)){
                if (this.points.size>it.points.indexOf(pos)) return true

            }
        }

        return false
    }

    fun display(){
        for (y in 0..Day12Self.sizeY-1){
            for (x in 0.. Day12Self.sizeX-1){
                if (alreadyVisited(Pair(x,y))) print("#")
                else print(".")
                // print height
                // print((Day12.map[Pair(x,y)]!!+96).toChar().toString())
            }
            println()
        }

    }
}