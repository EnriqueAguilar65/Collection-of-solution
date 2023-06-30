/*
 * Marco Lackovic, Apr 2019
 * Google Code Jam, Qualification Round
 * Foregone Solution
 */

declare var require: any;
declare var process: any;
const readline = require("readline");
const rl = readline.createInterface({ input: process.stdin });

let i = 0;
rl.on("line", (num: string) => {
  if (i > 0) {
    const [a, b] = solve(num);
    console.log(`Case #${i}: ${a} ${b}`);
  }
  i++;
});

const solve = (num: string): string[] => {
  const checks = new Array<string>(2);
  const numAsArrayOfDigits = num.split("").map(Number);
  checks[0] = numAsArrayOfDigits
    .map((d: Number) => (d == 4 ? 2 : d))
    .map(String)
    .join("");
  checks[1] = numAsArrayOfDigits
    .map((d: Number) => (d == 4 ? 2 : 0))
    .map(String)
    .join("");
  return checks;
};
