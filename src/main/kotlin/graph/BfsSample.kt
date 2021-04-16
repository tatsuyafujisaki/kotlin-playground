package graph

fun bfs(undirectedGraph: List<Set<Int>>, startVertex: Int): IntArray {
    val unknown = -1
    val distancesFromStartVertex = IntArray(undirectedGraph.size) {
        if (it == startVertex) 0 else unknown
    }
    val verticesToVisit = mutableListOf(startVertex)
    while (verticesToVisit.isNotEmpty()) {
        val vertex = verticesToVisit.removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
        undirectedGraph[vertex]
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
    val (vertexCount, edgeCount) = readLine().orEmpty().split(' ').map(String::toInt)
    val undirectedGraph = Array(vertexCount) { mutableSetOf<Int>() }
    repeat(edgeCount) {
        val (v1, v2) = readLine()
            .orEmpty()
            .split(' ')
            .map(String::toInt)
            .map { it - 1 } // converts to zero-based numbering.
        undirectedGraph[v1].add(v2)
        undirectedGraph[v2].add(v1)
    }
    val startVertex = readLine().orEmpty().toInt() - 1 // converts to zero-based numbering.
    bfs(undirectedGraph.map { it.toSet() }, startVertex)
        .filterIndexed { i, _ ->
            i != startVertex
        }
        .joinToString(" ")
        .let(::println)
}
