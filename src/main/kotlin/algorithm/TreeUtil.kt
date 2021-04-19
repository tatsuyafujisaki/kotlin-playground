package algorithm

data class Vertex(val ancestors: List<Int>, val children: Set<Int>, val subtreeData: Long) {
    val depth get() = ancestors.size
}

fun convertGraphToTree(graph: List<Set<Int>>, data: List<Int>, root: Int): List<Vertex> {
    val n = graph.size
    val ancestors = Array<List<Int>>(n) { emptyList() }
    val vertices = Array<Vertex?>(n) { null }

    fun visitVertex(id: Int, parent: Int) {
        ancestors[id] = if (parent == -1) emptyList() else listOf(parent) + ancestors[parent]
        val children = graph[id].filter { it != root && ancestors[it].isEmpty() }.toSet()
        for (child in children) visitVertex(child, id)
        val subtreeData = data[id] + children.map { vertices[it]!!.subtreeData }.sum()
        vertices[id] = Vertex(ancestors[id], children, subtreeData)
    }

    visitVertex(root, -1)
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

/**
 * returns one or two roots of minimum height tree(s).
 * https://www.geeksforgeeks.org/roots-tree-gives-minimum-height/
 */
fun findRootOfMinHeightTree(graph: List<Set<Int>>): Set<Int> {
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
