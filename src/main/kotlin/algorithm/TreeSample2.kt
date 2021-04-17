package algorithm

object TreeSample2 {
    class Vertex(val id: Int, val subtreeData: Int /* includes data of this vertex */, val children: Set<Vertex>)

    fun convertUndirectedGraphToTree(
        undirectedGraph: List<Set<Int>>,
        data: List<Int>,
        root: Int
    ): Pair<IntArray, Vertex> {
        val parents = IntArray(undirectedGraph.size) { Int.MIN_VALUE }

        fun createVertex(parent: Int, id: Int): Vertex {
            parents[id] = parent
            val children = undirectedGraph[id]
                .filterNot {
                    parents[it] == Int.MIN_VALUE
                }.map {
                    createVertex(id, it)
                }.toSet()
            return Vertex(id, data[id] + children.map { it.subtreeData }.sum(), children)
        }

        return parents to createVertex(-1, root)
    }
}
