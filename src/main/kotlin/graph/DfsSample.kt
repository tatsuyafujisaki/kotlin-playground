package graph

fun dfs(undirectedGraph: List<Set<Int>>, startVertex: Int) {
    val visited = BooleanArray(undirectedGraph.size)

    fun dfs(vertex: Int) {
        visited[vertex] = true
        undirectedGraph[vertex]
                .filterNot {
                    visited[it]
                }
                .forEach {
                    dfs(it)
                }
    }

    dfs(startVertex)
}
