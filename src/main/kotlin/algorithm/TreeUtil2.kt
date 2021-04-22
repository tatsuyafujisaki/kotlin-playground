package algorithm

import sortedByValue

object TreeUtil2 {
    data class Vertex(
        val ancestors: List<Int>,
        val children: Map<Int, Long>, // id and subtreeSum
        val subtreeSum: Long
    ) {
        val isRoot get() = ancestors.isEmpty()
        val depth get() = ancestors.size
    }

    fun List<Vertex>.getDescendants(i: Int): Map<Int, Long> {
        fun getDescendants(vertex: Vertex): Map<Int, Long> {
            if (vertex.children.isEmpty()) return emptyMap()
            return vertex.children + vertex.children.keys.map {
                getDescendants(this[it])
            }.reduce { acc, x -> acc + x }
        }
        return getDescendants(this[i]).sortedByValue()
    }

    fun convertGraphToTree(graph: List<Set<Int>>, data: List<Int>, root: Int): List<Vertex> {
        val n = graph.size
        val ancestors = Array<List<Int>>(n) { emptyList() }
        val vertices = Array<Vertex?>(n) { null }

        fun visitVertex(id: Int, parent: Int) {
            ancestors[id] = if (parent == -1) emptyList() else listOf(parent) + ancestors[parent]
            val children = graph[id].filter { it != root && ancestors[it].isEmpty() }
            for (child in children) visitVertex(child, id)

            var subtreeSum = 0L
            val childrenMap = mutableMapOf<Int, Long>()
            for (child in children) {
                subtreeSum += vertices[child]!!.subtreeSum
                childrenMap[child] = vertices[child]!!.subtreeSum
            }

            val subtreeData = data[id] + subtreeSum
            vertices[id] = Vertex(ancestors[id], childrenMap.sortedByValue(), subtreeData)
        }

        visitVertex(root, -1)
        return vertices.filterNotNull()
    }
}
