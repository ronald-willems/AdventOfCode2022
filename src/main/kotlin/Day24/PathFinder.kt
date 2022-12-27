package Day24

class PathFinder(val startMinute:Int, val start:Position, val end:Position) {
    val alreadyVisited = mutableSetOf<Step>()
    val finishedPaths = mutableListOf<Path>()
    var minPathLength = Int.MAX_VALUE
    var winnerPath: Path? = null
    val startStep = Step(start, 0)
    var minute = startMinute


    init{
        alreadyVisited.add(startStep)
    }

    fun createNewPath(currPath: Path, pos: Position, minute: Int): Path? {
        if ((minute-startMinute)>499) return null
        val newStep = Step(pos, minute)
        if (newStep !in alreadyVisited) {

            val newPath = currPath.clonePlus(newStep)
            if (pos == end) {
                finishedPaths.add(newPath)
                if (newPath.size() < minPathLength) {
                    minPathLength = newPath.size()
                    winnerPath = newPath
                    println("Winner: " + minPathLength)

                }

            } else {
                if (newPath.size() + newPath.distance <= minPathLength)
                    return newPath
            }
        }
        return null

    }

    fun findShortestPath():Path? {
        println("Start")

        var newMovePossible = true
        var currPath = Path(mutableListOf(startStep),end)
        //Day24.allBlizzards.forEach { it.move() }
        var itNr = 0
        while (newMovePossible) {
            val moves = currPath.possibleMoves(minute)
            val newPaths = moves.mapNotNull { createNewPath(currPath, it, minute) }.toMutableList()

            if (newPaths.size > 0) {
                newPaths.sortBy { it.distance }
                currPath = newPaths[0]
                alreadyVisited.add(currPath.last())
                //Day24.moveBlizards()
                minute++


            } else {

                currPath = currPath.cloneMin()
                //Day24.moveBackBlizards()
                minute--

                if (currPath.size() <= 1) newMovePossible = false
            }

            itNr++
            //if (itNr % 100 ==0) println("Status ${currPath.size()}. ${currPath.distance}. ${minute}.")
        }
        println("winner: " + winnerPath?.size())
        //currPath.display()
        return winnerPath
    }

}