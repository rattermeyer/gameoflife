export class GoL {
    board: number[][];

    constructor(startingBoard: number[][]) {
        this.board = startingBoard
    }


    countNeighbors(posX: number, posY: number): number {
        let count = 0;
        for (let x = posX - 1; x <= posX + 1; x++) {
            for (let y = posY - 1; y <= posY + 1; y++) {
                if ((x === posX) && (y === posY)) continue
                count += (this.board[y]?.[x] || 0)
            }
        }
        return count
    }

    step() {
        this.board = this.board.map((row, posY) => {
            return row.map((cell, posX) => {
                const countNeighbors = this.countNeighbors(posX, posY);
                if (cell === 1 && [2,3].includes(countNeighbors)) return 1
                if (cell === 0 && countNeighbors === 3) return 1
                return 0
            })
        })

    }
}
