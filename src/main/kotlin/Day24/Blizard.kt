package Day24

class Blizard(var pos: Position, var dir: Direction) {
    fun move() {
        pos = pos.addDirection(dir)
        if (pos in Day24.walls) {
            pos = startPos()
        }
    }

    fun moveBack() {
        pos = pos.minDirection(dir)
        if (pos in Day24.walls) {
            pos = endPos()
        }


    }

    fun startPos(): Position {
        return when (dir.value) {
            "^" -> Position(pos.x, Day24.maxY - 1)
            "v" -> Position(pos.x, 1)
            ">" -> Position(1, pos.y)
            else -> Position(Day24.maxX - 1, pos.y)

        }

    }

    fun endPos(): Position {
        return when (dir.value) {
            "^" -> Position(pos.x, 1)
            "v" -> Position(pos.x, Day24.maxY - 1)
            ">" -> Position(Day24.maxX - 1, pos.y)
            else -> Position(1, pos.y)

        }

    }
}