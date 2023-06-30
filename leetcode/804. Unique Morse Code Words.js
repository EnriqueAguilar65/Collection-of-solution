/**
 * https://leetcode.com/problems/unique-morse-code-words/
 */

const table = [
  ".-",
  "-...",
  "-.-.",
  "-..",
  ".",
  "..-.",
  "--.",
  "....",
  "..",
  ".---",
  "-.-",
  ".-..",
  "--",
  "-.",
  "---",
  ".--.",
  "--.-",
  ".-.",
  "...",
  "-",
  "..-",
  "...-",
  ".--",
  "-..-",
  "-.--",
  "--.."
];

const base = "a".charCodeAt(0);

const getCode = function(word) {
  return word
    .split("")
    .map(c => table[c.charCodeAt(0) - base])
    .join("");
};

const removeDuplicates = function(codes) {
  return codes.reduce(function(accumulator, currentValue) {
    if (!accumulator.includes(currentValue)) {
      accumulator.push(currentValue);
    }
    return accumulator;
  }, []);
};

const uniqueMorseRepresentations = function(words) {
  return removeDuplicates(words.map(w => getCode(w))).length;
};
