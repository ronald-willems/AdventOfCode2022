package Day16

data class Valve(val name:String, var rate:Int=0,   ):Node{
    val connections = mutableListOf<Valve>()
    val destinations = mutableListOf<Pair<Valve,Int>>()
}
