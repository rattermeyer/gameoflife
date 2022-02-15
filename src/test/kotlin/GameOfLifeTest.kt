import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameOfLifeTest {
    @Test
    fun `can construct an empty board`() {
        val board = Board(setOf())
        board.cellsAlive.shouldBeEmpty()
    }

    @Test
    fun `a cell with no neighbors dies`() {
        val cells = setOf(Cell(1, 1));
        val nextGen = Board(cells).step()
        nextGen.cellsAlive.shouldBeEmpty()
    }

    @Test
    fun `a live cell with two neighbors survives`() {
        val board = Board(setOf(Cell(0, 0), Cell(2, 0), Cell(1, 1)))
        board.step().cellsAlive.shouldContain(Cell(1, 1));
    }
    @Test
    fun `a live cell with three neighbors survives`() {
        val board = Board(setOf(Cell(0, 0), Cell(1,0), Cell(2, 0), Cell(1, 1)))
        board.step().cellsAlive.shouldContain(Cell(1, 1));
    }
    @Test
    fun `a live cell with more than three neighbors dies`() {
        val board = Board(setOf(Cell(0, 0), Cell(1,0), Cell(2, 0), Cell(1, 1), Cell(0,2)))
        board.step().cellsAlive.shouldNotContain(Cell(1, 1));
    }
    @Test
    fun `a dead cell with exactly three neighbors becomes alive`() {
        val board = Board(setOf(Cell(0, 0), Cell(2, 0), Cell(0,2)))
        board.step().cellsAlive.shouldContain(Cell(1, 1));
    }

    @Test
    fun `a cell with no neighbors has a count of neighbors of 0`() {
        val board = Board(setOf(Cell(1, 1)))
        board.countNeighbors(Cell(1, 1)).shouldBe(0)
    }

    @Test
    fun `a cell with one neighbor has a count of neighbors of 1`() {
        val board = Board(setOf(Cell(0, 0), Cell(1, 1)))
        board.countNeighbors(Cell(1, 1)).shouldBe(1)
    }


    data class Cell(val posX: Int, val posY: Int) {
        override fun toString(): String {
            return "Cell(posX=$posX, posY=$posY)"
        }

    }

    class Board(val cellsAlive: Set<Cell>) {
        fun step(): Board {
            val nextGen = mutableSetOf<Cell>()
            cellsAlive.forEach { it ->
                val countNeighbors = countNeighbors(it)
                if (countNeighbors == 2 || countNeighbors == 3) {
                    nextGen.add(it)
                }
                testDeadNeighboringCells(it, nextGen)
            }
            return Board(nextGen)
        }

        private fun testDeadNeighboringCells(
            it: Cell,
            nextGen: MutableSet<Cell>
        ) {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    val testingCell = Cell(it.posX + dx, it.posY + dy)
                    if (countNeighbors(testingCell) == 3) {
                        nextGen.add(testingCell)
                    }
                }
            }
        }

        fun countNeighbors(cell: Cell): Int {
            var count = 0
            for (dx in -1..1) {
                for (dy in -1..1) {
                    val element = Cell(cell.posX + dx, cell.posY + dy)
                    if (cellsAlive.contains(element) && element != cell) {
                        count++
                    }
                }
            }
            return count
        }
    }
}