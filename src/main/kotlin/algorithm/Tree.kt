package algorithm

class Vertex(val parent: Vertex?, val id: Int, var children: Set<Vertex>)

fun convertUndirectedGraphToTree(undirectedGraph: List<Set<Int>>, root: Int): Vertex {
    val visited = BooleanArray(undirectedGraph.size)

    fun createVertex(parent: Vertex?, value: Int): Vertex {
        visited[value] = true
        return Vertex(parent, value, emptySet()).apply {
            children = undirectedGraph[value]
                .filterNot {
                    visited[it]
                }.map {
                    createVertex(this, it)
                }.toSet()
        }
    }

    return createVertex(null, root)
}
