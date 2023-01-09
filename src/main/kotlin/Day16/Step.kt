package Day16



data class Step(val dest:Valve, val turn:Boolean = false, val elephantDest:Valve? = null, val elephantTurn:Boolean = false) {
    var options : MutableList<Step>? = null
}