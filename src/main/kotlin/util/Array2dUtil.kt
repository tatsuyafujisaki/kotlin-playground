package util

object Array2dUtil {
    fun create2dIntArray(rowCount: Int, columnCount: Int) = Array(rowCount) { IntArray(columnCount) }
    fun create2dDoubleArray(rowCount: Int, columnCount: Int) = Array(rowCount) { DoubleArray(columnCount) }
    fun create2dArray(vararg xs: IntArray) = arrayOf(*xs)
    fun create2dArray(vararg xs: DoubleArray) = arrayOf(*xs)
    inline fun <reified T> create2dArray(vararg xs: Array<T>) = arrayOf(*xs)

    fun Array<DoubleArray>.rowCount() = size
    fun Array<DoubleArray>.columnCount() = first().size

    fun Array<DoubleArray>.transpose(): Array<DoubleArray> {
        val transposed = create2dDoubleArray(columnCount(), rowCount())
        for (i in indices) {
            for (j in first().indices) {
                transposed[j][i] = this[i][j]
            }
        }
        return transposed
    }

    fun Array<DoubleArray>.subMatrix(rowToExclude: Int, columnToExclude: Int): Array<DoubleArray> {
        val subMatrix = create2dDoubleArray(rowCount() - 1, columnCount() - 1)
        var subMatrixRow = 0
        for (matrixRow in indices) {
            if (matrixRow == rowToExclude) continue
            var subMatrixColumn = 0
            for (matrixColumn in first().indices) {
                if (matrixColumn == columnToExclude) continue
                subMatrix[subMatrixRow][subMatrixColumn] = this[matrixRow][matrixColumn]
                subMatrixColumn++
            }
            subMatrixRow++
        }
        return subMatrix
    }

    fun Array<DoubleArray>.multiply(x: Double): Array<DoubleArray> {
        for (row in indices) {
            for (column in first().indices) {
                this[row][column] = x * this[row][column]
            }
        }
        return this
    }

    fun Array<DoubleArray>.multiply(matrix: Array<DoubleArray>): Array<DoubleArray> {
        require(columnCount() == matrix.rowCount())
        val result = create2dDoubleArray(size, matrix.columnCount())
        for (i in indices) {
            for (j in matrix.first().indices) {
                for (k in first().indices) {
                    result[i][j] += this[i][k] * matrix[k][j]
                }
            }
        }
        return result
    }

    fun Array<DoubleArray>.inverse(): Array<DoubleArray> {
        fun Array<DoubleArray>.determinant(): Double {
            fun Array<DoubleArray>.isSquareMatrix() = rowCount() == columnCount()
            require(isSquareMatrix())
            return when (val n = rowCount()) {
                1 -> this[0][0]
                2 -> this[0][0] * this[1][1] - this[0][1] * this[1][0]
                else -> {
                    var sum = 0.0
                    for (column in 0 until n) {
                        sum += (if (column % 2 == 0) 1 else -1) * this[0][column] * subMatrix(0, column).determinant()
                    }
                    sum
                }
            }
        }

        fun Array<DoubleArray>.cofactor(): Array<DoubleArray> {
            val cofactorMatrix = create2dDoubleArray(rowCount(), columnCount())
            for (row in indices) {
                for (column in first().indices) {
                    cofactorMatrix[row][column] = (if ((row + column) % 2 == 0) 1 else -1) * subMatrix(row, column).determinant()
                }
            }
            return cofactorMatrix
        }
        return cofactor().transpose().multiply(1 / determinant())
    }
}
