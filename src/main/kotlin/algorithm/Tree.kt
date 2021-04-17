package algorithm

class Vertex(val parent: Vertex?, val id: Int, var children: Set<Vertex>)

fun convertUndirectedGraphToTree(undirectedGraph: List<Set<Int>>, root: Int): Vertex {
    val visited = BooleanArray(undirectedGraph.size)

    fun createVertex(parent: Vertex?, id: Int): Vertex {
        visited[id] = true
        return Vertex(parent, id, emptySet()).apply {
            children = undirectedGraph[id]
                .filterNot {
                    visited[it]
                }.map {
                    createVertex(this, it)
                }.toSet()
        }
    }

    return createVertex(null, root)
}
