import {GoL} from './gol'

test('we can initialize the board correctly', () => {
    let startingBoard = [
        [0, 0, 1, 0],
        [1, 0, 0, 1],
        [0, 1, 0, 0],
        [0, 0, 1, 1]
    ];
    expect(new GoL(startingBoard).board).toEqual(startingBoard)
})

test('when there is only one life cell in the middle, it dies by underpopulation', () => {
    // Arrange
    const startingBoard = [
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0],
    ]
    const nextStepBoard = [
        [0, 0, 0],
        [0, 0, 0],
        [0, 0, 0],
    ];
    let game = new GoL(startingBoard);
    // Act
    game.step()
    // Assure
    expect(game.board).toEqual(nextStepBoard)
})

test('counts the neighbors of middle cell correctly', () => {
    // Arrange
    const startingBoard = [
        [1, 0, 1],
        [0, 1, 0],
        [0, 0, 0],
    ]
    let goL = new GoL(startingBoard);
    // ACT + Assure
    expect(goL.countNeighbors(1,1)).toEqual(2)
})

test('counts the neighbors of an edge cell correctly', () => {
    // Arrange
    const startingBoard = [
        [1, 1, 1],
        [0, 1, 0]
    ]
    let goL = new GoL(startingBoard);
    // ACT + Assure
    expect(goL.countNeighbors(0,0)).toEqual(2)
    expect(goL.countNeighbors(1,0)).toEqual(3)
    expect(goL.countNeighbors(2,0)).toEqual(2)
    expect(goL.countNeighbors(0,1)).toEqual(3)
    expect(goL.countNeighbors(1,1)).toEqual(3)
    expect(goL.countNeighbors(2,1)).toEqual(3)
})

test('when there is one live cell with two live neighbors, it survives', () => {
    // Arrange
    const startingBoard = [
        [1, 0, 1],
        [0, 1, 0]
    ]
    const nextStepBoard = [
        [0, 1, 0],
        [0, 1, 0]
    ]
    const game = new GoL(startingBoard)
    // ACT
    game.step()
    // Assure
    expect(game.board).toEqual(nextStepBoard)

})

test('when there is one live cell with three live neighbors, it survives', () => {
    // Arrange
    const startingBoard = [
        [1, 1, 1],
        [0, 1, 0]
    ]
    const nextStepBoard = [
        [1, 1, 1],
        [1, 1, 1]
    ]
    const game = new GoL(startingBoard)
    // ACT
    game.step()
    // Assure
    expect(game.board).toEqual(nextStepBoard)

})

test('when there is one live cell with four live neighbors, it dies', () => {
    // Arrange
    const startingBoard = [
        [1, 1, 1],
        [0, 1, 0],
        [1, 1, 0]
    ]
    const nextStepBoard = [
        [1, 1, 1],
        [0, 0, 0],
        [1, 1, 0],
    ]
    const game = new GoL(startingBoard)
    // ACT
    game.step()
    // Assure
    expect(game.board).toEqual(nextStepBoard)
})

test('when there is one dead cell with exactly three live neighbors, it becomes alive', () => {
    // Arrange
    const startingBoard = [
        [1, 0, 1],
        [0, 0, 0],
        [0, 1, 0]
    ]
    const nextStepBoard = [
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0],
    ]
    const game = new GoL(startingBoard)
    // ACT
    game.step()
    // Assure
    expect(game.board).toEqual(nextStepBoard)
})