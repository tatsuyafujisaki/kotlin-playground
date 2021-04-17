package algorithm

data class Tree(val ancestors: List<List<Int>>, val children: List<Set<Int>>, val subtreeData: List<Long>)

fun convertUndirectedAcyclicGraphToTree(
    undirectedGraph: List<Set<Int>>,
    data: List<Int>,
    root: Int
): Tree {
    val n = undirectedGraph.size
    val ancestors = Array<List<Int>>(n) { emptyList() }
    val children = Array<Set<Int>>(n) { emptySet() }
    val subtreeData = LongArray(n)

    fun visitVertex(parent: Int, id: Int) {
        if (parent != -1) ancestors[id] = listOf(parent) + ancestors[parent]
        children[id] = undirectedGraph[id].filter { it != root && ancestors[it].isEmpty() }.toSet()
        for (child in children[id]) visitVertex(id, child)
        subtreeData[id] = data[id] + children[id].map { subtreeData[it] }.sum()
    }

    visitVertex(-1, root)
    return Tree(ancestors.toList(), children.toList(), subtreeData.toList())
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
