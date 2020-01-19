package graph

fun bfs(vertices: List<Set<Int>>, startVertex: Int): IntArray {
    val unknown = -1
    val distancesFromStartVertex = IntArray(vertices.size) { unknown }.also {
        it[startVertex] = 0
    }
    val verticesToVisit = mutableListOf(startVertex)
    while (verticesToVisit.isNotEmpty()) {
        val vertex = verticesToVisit.removeFirst()
        vertices[vertex]
            .filter {
                distancesFromStartVertex[it] == unknown
            }
            .forEach {
                distancesFromStartVertex[it] = distancesFromStartVertex[vertex] + 1
                verticesToVisit.add(it)
            }
    }
    return distancesFromStartVertex
}

fun main() {
    val (vertexCount, edgeCount) = readln().split(' ').map(String::toInt)
    val undirectedGraph = List(vertexCount) { mutableSetOf<Int>() }
    repeat(edgeCount) {
        val (v1, v2) = readLine()
            .orEmpty()
            .split(' ')
            .map(String::toInt)
            .map { it - 1 } // converts to zero-based numbering.
        undirectedGraph[v1].add(v2)
        undirectedGraph[v2].add(v1)
    }
    val startVertex = readln().toInt() - 1 // converts to zero-based numbering.
    bfs(undirectedGraph.map { it.toSet() }, startVertex)
        .filterIndexed { i, _ ->
            i != startVertex
        }
        .joinToString(" ")
        .let(::println)
}
