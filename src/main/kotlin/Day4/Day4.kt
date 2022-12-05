package Day4

import java.io.File

object Day4 {

    fun rangeOverlap(range1: IntRange, range2: IntRange ):Boolean {
        if (range1.contains(range2.first) || range1.contains(range2.last)) return true;
        if (range2.contains(range1.first) || range2.contains(range1.last)) return true;
        return false
    }

    fun part2(){
        var totalOverlap = 0;
        File("src/main/resources/Day4/TestInput.txt").forEachLine { line ->
            var (elve1,elve2) = line.split(",")
            var (begin1,end1) = elve1.split("-")
            var (begin2,end2) = elve2.split("-")
            var range1 = begin1.toInt()..end1.toInt();
            var range2 = begin2.toInt()..end2.toInt()
            if (rangeOverlap(range1,range2)) totalOverlap++

        }

        println(totalOverlap)
    }
}