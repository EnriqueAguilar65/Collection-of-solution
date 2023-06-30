const fs = require('fs')

console.log('Sonar Sweep');

const result = fs
  .readFileSync('day01input.txt', { encoding: "utf-8" })
  .split("\n")
  .map(line => parseInt(line))
  .reduce((increases, currentDepth) => ({
    count1: currentDepth > increases.previous1Depth ? increases.count1 + 1 : increases.count1,
    count2: currentDepth + increases.previous1Depth + increases.previous2Depth > increases.previous1Depth + increases.previous2Depth + increases.previous3Depth ? increases.count2 + 1 : increases.count2,
    previous1Depth: currentDepth,
    previous2Depth: increases.previous1Depth,
    previous3Depth: increases.previous2Depth,
  }), { count1: 0, count2: 0, previous1Depth: -1, previous2Depth: -1 });

console.log(`Part 1 = ${result.count1 - 1}\nPart 2 = ${result.count2 - 2}`);
