package Day17

abstract class Rock() {
    var bottomNr = Day17.highestPoint()+4
    val points = mutableListOf<IntArray>()

    init {
        for ( y in 1..4){
            points.add(intArrayOf(0,0,0,0,0,0,0))
        }
    }

    open fun height():Int{
        return 1
    }

    fun print(){ points.reversed().forEach { it.forEach { print(it) } ;println() }}

    fun printInBottom(){
        for (r in Day17.cave.size-1 downTo 0){
            for (p in 0..8){
                var isSolid = Day17.cave[r][p]==2
                var isRock = false
                var rockRow = r-bottomNr
                var rockPoint = p-1
                try{  isRock = points[rockRow][rockPoint]==1} catch (x:Exception){}
                if (isSolid) print("#")
                else if (isRock) print("o")
                else print(".")
            }
            println()
        }

    }

    fun moveH(dir:Int):Boolean{
        // checkIfPossible
        for (r in 0.. 3) {
            var rockRow = points[r]
            var bottonRow = Day17.cave[r+bottomNr]
            for (i in 0..6){
                if (rockRow[i]==1){
                    if (bottonRow[i+1+dir]==2) return false
                }
            }
        }

        // do Move
        for (r in 0.. 3) {
            var rockRow = points[r]
            if (dir == -1 ){
                for (i in 0..6){
                    if (rockRow[i]==1){
                        rockRow[i]=0
                        rockRow[i+dir]=1
                    }
                }
            } else {
                for (i in 6 downTo 0){
                    if (rockRow[i]==1){
                        rockRow[i]=0
                        rockRow[i+dir]=1
                    }
                }
            }


        }
        return true
    }

    fun moveDown():Boolean{
        //CheckMove
        for (r in 0.. 3) {
            var rockRow = points[r]
            var bottonRow = Day17.cave[r+bottomNr]
            for (i in 0..6){
                if (rockRow[i]==1){
                    if (Day17.cave[r+bottomNr-1][i+1]==2) return false
                }
            }
        }
        //Do move
        bottomNr--


        return true

    }

    fun addToBottom(){
        for (r in 0.. 3) {
            var rockRow = points[r]
            for (i in 0..6){
                if (rockRow[i]==1){
                    Day17.cave[r+bottomNr][i+1]=2
                    //println("R" + r + "bottom" + bottomNr)
                    if (r+bottomNr>Day17.highPoints[i])  Day17.highPoints[i]=r+bottomNr
                }
            }
        }





    }



}

class Rock1: Rock() {
    init {
        points[0] = intArrayOf(0,0,1,1,1,1,0)
    }

}

class Rock2: Rock() {
    init {
        points[2] = intArrayOf(0,0,0,1,0,0,0)
        points[1] = intArrayOf(0,0,1,1,1,0,0)
        points[0] = intArrayOf(0,0,0,1,0,0,0)

    }
    override fun height():Int{
        return 3
    }

}

class Rock3: Rock() {
    init {
        points[2] = intArrayOf(0,0,0,0,1,0,0)
        points[1] = intArrayOf(0,0,0,0,1,0,0)
        points[0] = intArrayOf(0,0,1,1,1,0,0)

    }

    override fun height():Int{
        return 3
    }

}



class Rock4: Rock() {
    init {
        points[3] = intArrayOf(0,0,1,0,0,0,0)
        points[2] = intArrayOf(0,0,1,0,0,0,0)
        points[1] = intArrayOf(0,0,1,0,0,0,0)
        points[0] = intArrayOf(0,0,1,0,0,0,0)
//        or (i in 0..3){
//            points[i][2] = 1
//        }f
    }

    override fun height():Int{
        return 4
    }
}

class Rock5: Rock() {
    init {
        points[1] = intArrayOf(0,0,1,1,0,0,0)
        points[0] = intArrayOf(0,0,1,1,0,0,0)
    }
    override fun height():Int{
        return 2
    }
}