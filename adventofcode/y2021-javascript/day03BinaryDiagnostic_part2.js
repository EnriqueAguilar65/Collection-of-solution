const fs = require('fs')

console.log('Binary Diagnostic');

const input = fs
  .readFileSync('day03input.txt', { encoding: "utf-8" })
  .split("\n")
  .filter(line => line.length > 0);

const countOnes = (i, numbers) => numbers.reduce((count, binary) => count + parseInt(binary[i]), 0);

const filter = (i, digit, numbers) => numbers.filter(binary => binary[i] === digit);

let numbers = [...input];
for (let i = 0; i < 12 && numbers.length > 1; i++) {
  if (countOnes(i, numbers) >= numbers.length / 2) {
    numbers = filter(i, '1', numbers);
  } else {
    numbers = filter(i, '0', numbers);
  }
}

const oxigen = numbers[0];

numbers = input;
for (let i = 0; i < 12 && numbers.length > 1; i++) {
  if (countOnes(i, numbers) < numbers.length / 2) {
    numbers = filter(i, '1', numbers);
  } else {
    numbers = filter(i, '0', numbers);
  }
}

const co2 = numbers[0];

console.log(`Part 2 = ${parseInt(oxigen, 2) * parseInt(co2, 2)}`);
