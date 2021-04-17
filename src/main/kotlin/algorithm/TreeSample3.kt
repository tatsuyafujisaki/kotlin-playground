package algorithm

object TreeSample3 {
    fun convertUndirectedGraphToTree(
        undirectedGraph: List<Set<Int>>,
        data: List<Int>,
        root: Int
    ): Triple<IntArray, List<Set<Int>>, LongArray> {
        val n = undirectedGraph.size
        val parents = IntArray(n) { Int.MIN_VALUE }
        val children = Array<Set<Int>>(n) { emptySet() }
        val subtreeData = LongArray(n)

        fun visitVertex(parent: Int, id: Int) {
            parents[id] = parent
            children[id] = undirectedGraph[id].filter { parents[it] == Int.MIN_VALUE }.toSet()
            for (child in children[id]) visitVertex(id, child)
            subtreeData[id] = data[id] + children[id].map { subtreeData[it] }.sum()
        }

        visitVertex(-1, root)

        return Triple(parents, children.toList(), subtreeData)
    }

    /** finds the lowest common ancestor. */
    fun findLowestCommonAncestor(parents: IntArray, vertex1: Int, vertex2: Int): Int {
        val visited = mutableListOf<Int>()
        var current = vertex1
        while (true) {
            visited.add(current)
            if (current == -1) break
            current = parents[current]
        }
        current = vertex2
        while (!visited.contains(current)) current = parents[current]
        return current
    }
}
