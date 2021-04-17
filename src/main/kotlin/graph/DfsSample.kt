package graph

fun dfs(vertices: List<Set<Int>>, startVertex: Int) {
    val visited = BooleanArray(vertices.size) { false }

    fun dfs(vertex: Int) {
        visited[vertex] = true
        vertices[vertex]
            .filterNot {
                visited[it]
            }
            .forEach {
                dfs(it)
            }
    }

    dfs(startVertex)
}
