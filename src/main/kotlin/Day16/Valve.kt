package Day16

data class Valve(val name:String, var rate:Int=0,   ){
    val connections = mutableListOf<Valve>()
}
