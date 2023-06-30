const fs = require('fs')

console.log('Giant Squid');

const input = fs
  .readFileSync('day04input.txt', { encoding: "utf-8" })
  .split("\n")
  .filter(line => line.length > 0);

const drawnNumbers = input[0].split(",").map(s => parseInt(s));

const getBoard = (start, input) => {
  const board = [];
  for (let i = start; i < start + 5; i++) {
    board.push(input[i].split(" ").map(s => parseInt(s)).filter(n => !isNaN(n)));
  }
  return board;
};

const boards = [];
for (let i = 1; i < input.length; i += 5) {
  boards.push(getBoard(i, input));
}

const markDrawn = (drawnNumber, board) => {
  let bingo = false;
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[i].length; j++) {
      if (board[i][j] === drawnNumber) {
        board[i][j] = -1;
      }
    }
    if (board[i].reduce((sum, n) => sum + n) === -5) {
      bingo = true;
    }
  }
  for (let i = 0; i < board.length; i++) {
    const column = [];
    for (let j = 0; j < board[i].length; j++) {
      column.push(board[j][i]);
    }
    if (column.reduce((sum, n) => sum + n) === -5) {
      bingo = true;
    }
  }
  return bingo ? board : null;
};

const sumOfUnmarkedNumbers = (board) => {
  let sum = 0;
  for (let i = 0; i < board.length; i++) {
    sum += board[i].filter(n => n > 0).reduce((sum, n) => sum + n, 0);
  }
  return sum;
};

let lastWinningBoardSum = null;
let lastWinningDrawnNumber = null;
let alreadyWon = Array(boards.length).fill(false);
let i = null;
for (i = 0; i < drawnNumbers.length; i++) {
  for (let j = 0; j < boards.length; j++) {
    if (!alreadyWon[j]) {
      const winningBoard = markDrawn(drawnNumbers[i], boards[j]);
      if (winningBoard) {
        alreadyWon[j] = true;
        lastWinningDrawnNumber = drawnNumbers[i];
        lastWinningBoardSum = sumOfUnmarkedNumbers(winningBoard);
      }
    }
  }
}

console.log(`Part 2 = ${lastWinningBoardSum * lastWinningDrawnNumber}`);
