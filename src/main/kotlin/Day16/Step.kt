package Day16



data class Step(val dest:Valve, val turn:Boolean = false, val elephant:Boolean= false) {
    var options : MutableList<Step>? = null
}