package util

object MatrixUtil {
    fun rowCount(matrix: Array<DoubleArray>) = matrix.size
    fun columnCount(matrix: Array<DoubleArray>) = matrix[0].size
    fun isSquareMatrix(matrix: Array<DoubleArray>) = rowCount(matrix) == columnCount(matrix)
    fun inverse(matrix: Array<DoubleArray>): Array<DoubleArray> = multiply(1.0 / determinant(matrix), transpose(cofactor(matrix)))

    fun matrix(rowCount: Int, columnCount: Int) = Array(rowCount) {
        DoubleArray(columnCount)
    }

    // Helper method to create a 2x2 matrix
    fun matrix2(a00: Double, a01: Double, a10: Double, a11: Double) =
        matrix(2, 2).apply {
            this[0][0] = a00
            this[0][1] = a01
            this[1][0] = a10
            this[1][1] = a11
        }

    // Helper method to create a 3x3 matrix
    fun matrix3(a00: Double, a01: Double, a02: Double, a10: Double, a11: Double, a12: Double, a20: Double, a21: Double, a22: Double) =
        matrix(3, 3).apply {
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

    fun submatrix(matrix: Array<DoubleArray>, excludingRow: Int, excludingColumn: Int): Array<DoubleArray> {
        val rowCount = rowCount(matrix)
        val columnCount = columnCount(matrix)
        val submatrix = matrix(rowCount - 1, columnCount - 1)

        var submatrixRow = -1
        for (matrixRow in 0 until rowCount) {
            if (matrixRow == excludingRow) {
                continue
            }
            submatrixRow++
            var submatrixColumn = -1
            for (matrixColumn in 0 until columnCount) {
                if (matrixColumn == excludingColumn) {
                    continue
                }
                submatrixColumn++
                submatrix[submatrixRow][submatrixColumn] = matrix[matrixRow][matrixColumn]
            }
        }
        return submatrix
    }

    fun transpose(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val rowCount = rowCount(matrix)
        val columnCount = columnCount(matrix)
        val transposed = matrix(columnCount, rowCount)

        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                transposed[j][i] = matrix[i][j]
            }
        }

        return transposed
    }

    fun multiply(x: Double, matrix: Array<DoubleArray>): Array<DoubleArray> {
        val rowCount = rowCount(matrix)
        val columnCount = columnCount(matrix)
        for (row in 0 until rowCount) {
            for (column in 0 until columnCount) {
                matrix[row][column] = x * matrix[row][column]
            }
        }
        return matrix
    }

    fun multiply(matrix1: Array<DoubleArray>, matrix2: Array<DoubleArray>): Array<DoubleArray> {
        val columnCount1 = columnCount(matrix1)
        val rowCount2 = rowCount(matrix2)

        require(columnCount1 == rowCount2)

        val rowCount1 = rowCount(matrix1)
        val columnCount2 = columnCount(matrix2)
        val product = matrix(rowCount1, columnCount2)

        for (i in 0 until rowCount1) {
            for (j in 0 until columnCount2) {
                for (k in 0 until columnCount1) {
                    product[i][j] += matrix1[i][k] * matrix2[k][j]
                }
            }
        }
        return product
    }

    fun determinant(matrix: Array<DoubleArray>): Double {
        require(isSquareMatrix(matrix))
        val n = rowCount(matrix)
        return when (n) {
            1 -> matrix[0][0]
            2 -> matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
            else -> {
                var sum = 0.0
                for (column in 0 until n) {
                    sum += (if (column % 2 == 0) 1 else -1) * matrix[0][column] * determinant(submatrix(matrix, 0, column))
                }
                sum
            }
        }
    }

    fun cofactor(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val rowCount = rowCount(matrix)
        val columnCount = columnCount(matrix)
        val cofactorMatrix = matrix(rowCount, columnCount)
        for (row in 0 until rowCount) {
            for (column in 0 until columnCount) {
                cofactorMatrix[row][column] = (if ((row + column) % 2 == 0) 1 else -1) * determinant(submatrix(matrix, row, column))
            }
        }
        return cofactorMatrix
    }

    fun printMatrix(matrix: Array<DoubleArray>) {
        for (row in matrix) {
            for (column in row) {
                print("%10.2f ".format(column))
            }
            println()
        }
    }
}