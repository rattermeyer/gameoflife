import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Test

class GameOfLifeTest {
    @Test
    fun `can construct an empty board`() {
        val board = Board(setOf())
        board.cellsAlive.shouldBeEmpty()
    }

    @Test
    fun `a cell with no neighbors dies`() {
        val cells = setOf(Cell(1,1));
        val nextGen = Board(cells).step()
        nextGen.shouldBeEmpty()
    }

    @Test
    fun `a live cell with two neighbors survives` () {
        val board = Board(setOf(Cell(0,0), Cell(2,0), Cell(1,1)))
        board.step().shouldContain(Cell(1,1));
    }

    class Cell(posX: Int, posY: Int)
    class Board(val cellsAlive: Set<Cell>) {
        fun step(): Set<Cell> {
            cellsAlive.stream()
            return setOf()
        }
    }
}