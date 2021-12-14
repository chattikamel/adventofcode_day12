import java.util.*

interface Cave {
    val name: String
}

data class BigCave(override val name: String) : Cave {
    override fun toString(): String {
        return "$name"
    }
}

data class SmallCave(override val name: String) : Cave {
    override fun toString(): String {
        return "$name"
    }
}

val Start = BigCave("start")


val End = BigCave("end")

private fun isUpperCase(a: String) = a.toCharArray().all { it.isUpperCase() }

fun ofCave(name: String) =
    when (name) {
        "start" -> Start
        "end" -> End
        else -> {
            if (isUpperCase(name)) BigCave(name) else SmallCave(name)
        }
    }


data class Link(var cave1: Cave = Start, var cave2: Cave = End) {

    constructor(str: String) : this() {
        val (c1, c2) = Regex("(\\w+)-(\\w+)")
            .find(str)!!
            .destructured

        this.cave1 = ofCave(c1)

        this.cave2 = ofCave(c2)
    }


}

class Puzzule(private val links: List<Link>) {

    private var solution = mutableListOf<List<Cave>>()


    fun nextCaves(from: Cave): Set<Cave> {
        val r = mutableSetOf<Cave>()

        if (from == End) return r

        links.forEach {
            if (it.cave1 == from && it.cave2 != Start)
                r.add(it.cave2)
            else if (it.cave2 == from && it.cave1 != Start)
                r.add(it.cave1)
        }
        return r

    }

    fun findPaths(): MutableList<List<Cave>> {
        solution.clear()
        _findPaths(Start, Stack())
        return solution
    }

    private fun _findPaths(cc: Cave, stack: Stack<Cave>) {

        if (cc is BigCave || (cc is SmallCave && cc !in stack)) {
            stack.push(cc)
            nextCaves(cc)
                .forEach { c ->
                    _findPaths(c, stack)
                }

            if (cc == End)
                solution.add(stack.toList())

            stack.pop()
        }
    }
}