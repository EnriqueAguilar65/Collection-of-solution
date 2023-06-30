const fs = require('fs')

console.log('Binary Diagnostic');

const result = fs
  .readFileSync('day03input.txt', { encoding: "utf-8" })
  .split("\n")
  .filter(line => line.length > 0)
  .reduce((diagnostic, binary) => {
    for (var i = 0; i < binary.length; i++) {
      diagnostic.count1[i] += parseInt(binary[i]);
    }
    diagnostic.numbersCount++;
    return diagnostic;
  }, { numbersCount: 0, count1: Array(12).fill(0) });

let gammaRate = '';
const half = result.numbersCount / 2;
for (var i = 0; i < result.count1.length; i++) {
  gammaRate += result.count1[i] > half ? '1' : '0';
}

let epsilonRate = '';
for (var i = 0; i < result.count1.length; i++) {
  epsilonRate += gammaRate[i] === '0' ? '1' : '0';
}

console.log(`Part 1 = ${parseInt(gammaRate, 2) * parseInt(epsilonRate, 2)}`);
