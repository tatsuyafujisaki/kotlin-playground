package algorithm

object TreeSample {
    class Vertex(val parent: Vertex?, val id: Int, val data: Int, var children: Set<Vertex>)

    fun convertUndirectedGraphToTree(undirectedGraph: List<Set<Int>>, data: List<Int>, root: Int): Vertex {
        val visited = BooleanArray(undirectedGraph.size)

        fun createVertex(parent: Vertex?, id: Int): Vertex {
            visited[id] = true
            return Vertex(parent, id, data[id], emptySet()).apply {
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
}
