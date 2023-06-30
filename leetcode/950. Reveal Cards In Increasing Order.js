/**
 * https://leetcode.com/problems/reveal-cards-in-increasing-order/
 */
var deckRevealedIncreasing = function(deck) {
  return deck
    .sort((a, b) => b - a)
    .reduce(function(accumulator, currentValue) {
      if (accumulator.length > 1) {
        accumulator.unshift(accumulator.pop());
      }
      accumulator.unshift(currentValue);
      return accumulator;
    }, []);
};
