package algorithm

/**
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
class DisjointSets(n: Int) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    val disjointSets get() = parents
        .mapIndexed { i, x -> x to i }
        .groupBy({ it.first }, { it.second })
        .values.map { it.toSet() }
        .toSet()

    /** Using the technique "path compression" */
    private fun findRoot(x: Int): Int {
        if (parents[x] == x) return x
        parents[x] = findRoot(parents[x])
        return parents[x]
    }

    /** Using the technique "union by rank" */
    fun union(x: Int, y: Int) {
        val xRoot = findRoot(x)
        val yRoot = findRoot(y)
        when {
            ranks[xRoot] < ranks[yRoot] -> parents[xRoot] = yRoot
            ranks[xRoot] > ranks[yRoot] -> parents[yRoot] = xRoot
            xRoot != yRoot -> {
                parents[yRoot] = xRoot
                ranks[xRoot]++
            }
        }
    }
}
