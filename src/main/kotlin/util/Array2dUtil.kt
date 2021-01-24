package util

object Array2dUtil {
    fun Array<DoubleArray>.rowCount() = size
    fun Array<DoubleArray>.columnCount() = first().size
    fun Array<DoubleArray>.isSquareMatrix() = rowCount() == columnCount()
    fun Array<DoubleArray>.inverse(): Array<DoubleArray> = cofactor().transpose().multiply(1.0 / determinant())

    fun create2dIntArray(rowCount: Int, columnCount: Int): Array<IntArray> = Array(rowCount) {
        IntArray(columnCount)
    }

    fun create2dDoubleArray(rowCount: Int, columnCount: Int): Array<DoubleArray> = Array(rowCount) {
        DoubleArray(columnCount)
    }

    fun create2dArray(vararg xs: IntArray): Array<IntArray> = arrayOf(*xs)
    fun create2dArray(vararg xs: DoubleArray): Array<DoubleArray> = arrayOf(*xs)
    inline fun <reified T> create2dArray(vararg xs: Array<T>): Array<Array<T>> = arrayOf(*xs)

    /**
     * Creates a 2x2 matrix.
     */
    fun createMatrix(
        a00: Int, a01: Int,
        a10: Int, a11: Int
    ): Array<IntArray> = create2dIntArray(2, 2).apply {
        this[0][0] = a00
        this[0][1] = a01
        this[1][0] = a10
        this[1][1] = a11
    }

    /**
     * Creates a 2x2 matrix.
     */
    fun createMatrix(
        a00: Double, a01: Double,
        a10: Double, a11: Double
    ): Array<DoubleArray> = create2dDoubleArray(2, 2).apply {
        this[0][0] = a00
        this[0][1] = a01
        this[1][0] = a10
        this[1][1] = a11
    }

    /**
     * Creates a 3x3 matrix.
     */
    fun createMatrix(
        a00: Int, a01: Int, a02: Int,
        a10: Int, a11: Int, a12: Int,
        a20: Int, a21: Int, a22: Int
    ): Array<IntArray> = create2dIntArray(3, 3).apply {
        this[0][0] = a00
        this[0][1] = a01
        this[0][2] = a02
        this[1][0] = a10
        this[1][1] = a11
        this[1][2] = a12
        this[2][0] = a20
        this[2][1] = a21
        this[2][2] = a22
    }

    /**
     * Creates a 3x3 matrix.
     */
    fun createMatrix(
        a00: Double, a01: Double, a02: Double,
        a10: Double, a11: Double, a12: Double,
        a20: Double, a21: Double, a22: Double
    ): Array<DoubleArray> = create2dDoubleArray(3, 3).apply {
        this[0][0] = a00
        this[0][1] = a01
        this[0][2] = a02
        this[1][0] = a10
        this[1][1] = a11
        this[1][2] = a12
        this[2][0] = a20
        this[2][1] = a21
        this[2][2] = a22
    }

    fun Array<DoubleArray>.subMatrix(rowToExclude: Int, columnToExclude: Int): Array<DoubleArray> {
        val subMatrix = create2dDoubleArray(rowCount() - 1, columnCount() - 1)
        for ((subMatrixRow, matrixRow) in indices.filterNot { it == rowToExclude }.withIndex()) {
            for ((subMatrixColumn, matrixColumn) in first().indices.filterNot { it == columnToExclude }.withIndex()) {
                subMatrix[subMatrixRow][subMatrixColumn] = this[matrixRow][matrixColumn]
            }
        }
        return subMatrix
    }

    fun Array<DoubleArray>.transpose(): Array<DoubleArray> {
        val transposed = create2dDoubleArray(columnCount(), rowCount())
        for (i in indices) {
            for (j in first().indices) {
                transposed[j][i] = this[i][j]
            }
        }
        return transposed
    }

    fun Array<DoubleArray>.multiply(x: Double): Array<DoubleArray> {
        for (row in indices) {
            for (column in first().indices) {
                this[row][column] = x * this[row][column]
            }
        }
        return this
    }

    fun multiply(matrix1: Array<DoubleArray>, matrix2: Array<DoubleArray>): Array<DoubleArray> {
        val columnCount1 = matrix1.columnCount()
        val rowCount2 = matrix2.rowCount()

        require(columnCount1 == rowCount2)

        val rowCount1 = matrix1.rowCount()
        val columnCount2 = matrix2.columnCount()
        val product = create2dDoubleArray(rowCount1, columnCount2)

        for (i in 0 until rowCount1) {
            for (j in 0 until columnCount2) {
                for (k in 0 until columnCount1) {
                    product[i][j] += matrix1[i][k] * matrix2[k][j]
                }
            }
        }
        return product
    }

    fun Array<DoubleArray>.determinant(): Double {
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

    fun Array<DoubleArray>.printMatrix() {
        for (row in this) {
            for (column in row) {
                print("%10.2f ".format(column))
            }
            println()
        }
    }
}
