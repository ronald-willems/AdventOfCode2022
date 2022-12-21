package Day21

abstract class Monkey(val name: String) {
    abstract fun humanInfluencer(): Monkey?
    abstract fun yell(): Long?
    open fun toMatchPredecessor(toMatch: Long): Long {
        println("fout")
        return 0.toLong()
    }

}

class NrMonkey(name: String, val nr: Int) : Monkey(name) {
    override fun yell(): Long? {
        return nr.toLong()
    }

    override fun humanInfluencer(): Monkey? {
        if (name == "humn") return this
        else return null
    }

}

class MathMonkey(name: String, val dep1: String, val operator: String, val dep2: String) : Monkey(name) {
    var calcValue: Long? = null
    private var humanInfluence: Monkey? = null
    override fun yell(): Long? {

        if (calcValue != null) return calcValue

        val dep1M = Day21.allMonkeys.get(dep1)
        val dep2M = Day21.allMonkeys.get(dep2)


        if (dep1M != null && dep2M != null) {
            if (dep1M.humanInfluencer() != null) humanInfluence = dep1M
            if (dep2M.humanInfluencer() != null) humanInfluence = dep2M


            when (operator) {
                "+" -> calcValue = dep1M.yell()!! + dep2M.yell()!!;
                "-" -> calcValue = dep1M.yell()!! - dep2M.yell()!!;
                "*" -> calcValue = dep1M.yell()!! * dep2M.yell()!!;
                "/" -> calcValue = dep1M.yell()!! / dep2M.yell()!!;

            }
        }

        return calcValue
    }

    override fun humanInfluencer(): Monkey? {
        return humanInfluence
    }

    fun getDep1(): Monkey {
        return Day21.allMonkeys.get(dep1)!!
    }

    fun getDep2(): Monkey {
        return Day21.allMonkeys.get(dep2)!!
    }


    override fun toMatchPredecessor(toMatch: Long): Long {
        var result: Long = 0

        if (humanInfluence == getDep1()) {
            when (operator) {
                "+" -> result = toMatch - getDep2().yell()!!
                "-" -> result = getDep2().yell()!! + toMatch
                "*" -> result = toMatch / getDep2().yell()!!
                "/" -> result = getDep2().yell()!! * toMatch
            }
        } else { //Dep2
            when (operator) {
                "+" -> result = toMatch - getDep1().yell()!!
                "-" -> result = getDep1().yell()!! - toMatch
                "*" -> result = toMatch / getDep1().yell()!!
                "/" -> result = getDep1().yell()!! / toMatch
            }
        }

        return result;
    }

}