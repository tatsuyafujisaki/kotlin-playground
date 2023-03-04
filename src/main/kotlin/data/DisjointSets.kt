package data

/**
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
class DisjointSets(n: Int) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    /** Using the technique "path compression" */
    private fun find(x: Int): Int {
        if (parents[x] == x) return x
        parents[x] = find(parents[x])
        return parents[x]
    }

    /** Using the technique "union by rank" */
    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)
        when {
            ranks[xRoot] < ranks[yRoot] -> parents[xRoot] = yRoot
            ranks[xRoot] > ranks[yRoot] -> parents[yRoot] = xRoot
            xRoot != yRoot -> {
                parents[yRoot] = xRoot
                ranks[xRoot]++
            }
        }
    }

    val components: Collection<List<Int>>
        get() {
            // Make each parent the direct child of its root ancestor.
            for (i in parents.indices) parents[i] = find(parents[i])
            return parents
                .mapIndexed { i, x -> x to i }
                .groupBy({ it.first }, { it.second })
                .values
        }
}
