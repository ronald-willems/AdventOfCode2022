package Day24

data class Direction(var value: String) {
    fun getDirArray(): IntArray {
        return when (value) {
            "v" -> intArrayOf(0, 1)
            "<" -> intArrayOf(-1, 0)
            "^" -> intArrayOf(0, -1)
            else -> intArrayOf(1, 0) //R

        }

    }

    fun getYDir(): Int {
        return when (value) {
            "v" -> 1
            "<" -> 0
            "^" -> -1
            else -> 0

        }
    }

    fun getXDir(): Int {
        return when (value) {
            "v" -> 0
            "<" -> -1
            "^" -> 0
            else -> 1

        }

    }

    override fun toString():String{
        return value
    }




}
