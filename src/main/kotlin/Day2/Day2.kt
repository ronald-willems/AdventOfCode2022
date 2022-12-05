package Day2


import java.io.File

object Day2 {




    fun winScore( opp:String,  me:String): Int{
        var draws = listOf(Pair("A", "X"),Pair("B", "Y"),Pair("C", "Z"))
        var wins = listOf(Pair("A", "Y"),Pair("B", "Z"),Pair("C", "X"))
        if (Pair(opp,me) in draws) return 3
        else if (Pair(opp,me) in wins) return 6
        else  return 0;
    }

    fun choiceScore(   me:String): Int{
        if (me =="X") return 1
        if (me == "Y") return 2
        return 3;
    }


    fun part1():Int {
        var totalScore = 0

        File("src/main/resources/Day2/TestInput.txt").forEachLine { line ->
            var opp = line[0].toString()
            var me = line[2].toString()
            totalScore = totalScore + winScore(opp,me)
            totalScore = totalScore + choiceScore(me)


        }
        return totalScore;

      //  println(allElves.maxOf { it })

    }

    fun winScore2( goal:String): Int{
        if (goal =="X") return 0
        if (goal == "Y") return 3
        return 6;
    }

    fun choiceScore2(   me:String): Int{
        if (me =="A") return 1
        if (me == "B") return 2
        return 3;
    }

    fun determineMove(opp:String, goal:String): String{
        if (goal =="Y") return opp;
        if (goal=="Z") return when (opp){
            "A" -> "B"
            "B" -> "C"
            else -> "A"
        }

        else return when (opp){
            "A" -> "C"
            "B" -> "A"
            else -> "B"
        }


    }


    fun part2():Int {
        var totalScore = 0

        File("src/main/resources/Day2/TestInput.txt").forEachLine { line ->
            var opp = line[0].toString()
            var goal = line[2].toString()
            var me = determineMove(opp, goal)
            totalScore = totalScore + winScore2(goal)
            totalScore = totalScore + choiceScore2(me)


        }
        return totalScore;

        //  println(allElves.maxOf { it })

    }

}
