const fs = require('fs')

console.log('Dive');

const result = fs
  .readFileSync('day02input.txt', { encoding: "utf-8" })
  .split("\n")
  .reduce((movements, command) => {
    command = command.split(' ');
    const amount = parseInt(command[1]);
    if (command[0] === 'forward') {
      movements.position += amount;
      movements.depth += movements.aim * amount;
    } else if (command[0] === 'up') {
      movements.aim -= amount;
    } else if (command[0] === 'down') {
      movements.aim += amount;
    }
    return movements;
  }, { position: 0, depth: 0, aim: 0 });

console.log(`Part 2 = ${result.position * result.depth}`);
