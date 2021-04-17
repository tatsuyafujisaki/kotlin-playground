package algorithm

object TreeSample2 {
    class Vertex(val id: Int, val data: Int, val children: Set<Vertex>)

    fun convertUndirectedGraphToTree(undirectedGraph: List<Set<Int>>, data: List<Int>, root: Int): Vertex {
        val visited = BooleanArray(undirectedGraph.size)

        fun createVertex(id: Int): Vertex {
            visited[id] = true
            val children = undirectedGraph[id]
                .filterNot {
                    visited[it]
                }.map {
                    createVertex(it)
                }.toSet()
            return Vertex(id, data[id], children)
        }

        return createVertex(root)
    }
}
