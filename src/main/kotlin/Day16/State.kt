package Day16

data class State (val minute:Int, val currentValve: Valve, val curValveElephant:Valve,  val currentPressure:Int, val totalPressure:Int, val closedValves:Set<Valve>){
}