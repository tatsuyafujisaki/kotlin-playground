package algorithm

object TreeSample {
    class Vertex(val id: Int, val data: Int, var children: Set<Vertex>)

    fun convertUndirectedGraphToTree(
        undirectedGraph: List<Set<Int>>,
        data: List<Int>,
        root: Int
    ): Pair<IntArray, Vertex> {
        val parents = IntArray(undirectedGraph.size) { Int.MIN_VALUE }

        fun createVertex(parent: Int, id: Int): Vertex {
            parents[id] = parent
            return Vertex(id, data[id], undirectedGraph[id]
                .filter {
                    parents[it] == Int.MIN_VALUE
                }.map {
                    createVertex(id, it)
                }.toSet()
            )
        }

        return parents to createVertex(-1, root)
    }
}
