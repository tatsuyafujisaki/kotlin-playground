package data

object TreeUtil {
    data class Vertex(val ancestors: List<Int>, val children: List<Int>, val subtreeSum: Long) {
        val isRoot get() = ancestors.isEmpty()
        val depth get() = ancestors.size
        val isLeaf get() = children.isEmpty()
    }

    fun convertGraphToTree(graph: List<Set<Int>>, data: List<Int>, root: Int): List<Vertex> {
        val ancestors = Array<List<Int>>(graph.size) { emptyList() }
        val vertices = Array<Vertex?>(graph.size) { null }

        fun visitVertex(id: Int, parent: Int) {
            ancestors[id] = if (parent == -1) emptyList() else listOf(parent) + ancestors[parent]
            val children = graph[id].filter { parent != it }
            for (child in children) visitVertex(child, id)
            vertices[id] = Vertex(
                    ancestors[id],
                    children,
                    data[id] + children.map { vertices[it]!!.subtreeSum }.sum()
            )
        }

        visitVertex(root, -1)
        return vertices.filterNotNull()
    }

    /** Faster but has more code */
    fun fastConvertGraphToTree(graph: List<List<Int>>, data: List<Int>, root: Int): List<Vertex> {
        val ancestors = Array<List<Int>>(graph.size) { emptyList() }
        val vertices = Array<Vertex?>(graph.size) { null }

        fun visitVertex(id: Int, parent: Int) {
            ancestors[id] = listOf(parent) + ancestors[parent]
            val children = graph[id].filter { parent != it }
            for (child in children) visitVertex(child, id)
            vertices[id] = Vertex(
                    ancestors[id],
                    children,
                    data[id] + children.map { vertices[it]!!.subtreeSum }.sum()
            )
        }

        for (child in graph[root]) visitVertex(child, root)
        vertices[root] = Vertex(
                ancestors[root],
                graph[root],
                data[root] + graph[root].map { vertices[it]!!.subtreeSum }.sum()
        )

        return vertices.filterNotNull()
    }

    /** finds the lowest common ancestor (LCA). */
    fun findLCA(ancestors: List<List<Int>>, vertex1: Int, vertex2: Int): Int {
        var v1 = vertex1
        var v2 = vertex2
        while (ancestors[v1].size != ancestors[v2].size) {
            if (ancestors[v1].size < ancestors[v2].size) {
                v2 = ancestors[v2].first()
            } else {
                v1 = ancestors[v1].first()
            }
        }
        while (v1 != v2) {
            v1 = ancestors[v1].first()
            v2 = ancestors[v2].first()
        }
        return v1
    }

    fun isParentChildRelationship(ancestors: List<List<Int>>, vertex1: Int, vertex2: Int) =
            findLCA(ancestors, vertex1, vertex2).let {
                it == vertex1 || it == vertex2
            }

    /**
     * returns one or two roots of minimum height tree(s).
     * https://www.geeksforgeeks.org/roots-tree-gives-minimum-height/
     */
    fun findRootOfMinHeightTree(graph: List<Set<Int>>): Set<Int> {
        // Early return if the graph has only one vertex because the rest of findRootOfMinHeightTree() mistakenly returns an empty set in the scenario.
        if (graph.size == 1) return setOf(0)
        var remainingVertices = graph.size
        val degrees = graph.map { it.size }.toMutableList()
        val leaves = graph.indices.filter { graph[it].size == 1 }.toMutableList()
        while (remainingVertices > 2) {
            val leafCount = leaves.size
            remainingVertices -= leaves.size
            repeat(leafCount) {
                val leaf = leaves.removeFirst()
                for (semiLeaf in graph[leaf]) {
                    degrees[semiLeaf]--
                    if (degrees[semiLeaf] == 1) leaves.add(semiLeaf)
                }
            }
        }
        return leaves.toSet()
    }

    fun List<Vertex>.getDescendants(i: Int): List<Int> {
        fun getDescendants(vertex: Vertex): List<Int> {
            if (vertex.children.isEmpty()) return emptyList()
            return vertex.children + vertex.children.map { getDescendants(it) }.flatten()
        }
        return getDescendants(this[i])
    }
}
