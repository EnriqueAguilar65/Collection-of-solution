const fs = require('fs')

console.log('Dive');

const result = fs
  .readFileSync('day02input.txt', { encoding: "utf-8" })
  .split("\n")
  .reduce((movements, command) => {
    command = command.split(' ');
    if (command[0] === 'forward') {
      movements.position += parseInt(command[1]);
    } else if (command[0] === 'up') {
      movements.depth -= parseInt(command[1]);
    } else if (command[0] === 'down') {
      movements.depth += parseInt(command[1]);
    }
    return movements;
  }, { position: 0, depth: 0 });

console.log(`Part 1 = ${result.position * result.depth}`);
