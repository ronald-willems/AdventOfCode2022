package Day24


data class Position(var x:Int,var  y: Int){

    fun addDirection(dir: Direction): Position {
        return Position(x+dir.getXDir(),y+dir.getYDir())

    }

    fun minDirection(dir: Direction): Position {
        return Position(x-dir.getXDir(),y-dir.getYDir())

    }
}