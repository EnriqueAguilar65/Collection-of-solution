#  Marco Lackovic, Apr 2020
#  Google Code Jam, Qualification Round
#  Vestigium

def current_line
  STDIN.each_line.next
end

def contains_dups(array)
  array.length != array.uniq.length
end

def repeated_count(matrix)
  count = 0
  matrix.each do |row|
    count += 1 unless !contains_dups(row)
  end
  count
end

number_of_test_cases = current_line.to_i

for x in 1..number_of_test_cases do
  matrix_size = current_line.to_i
  matrix = []
  matrix_size.times{ matrix << current_line.split.map(&:to_i) }
  k =  (0..matrix.length-1).collect { |i| matrix[i][i] }.reduce(0, :+)
  r = repeated_count(matrix)
  c = repeated_count(matrix.transpose)
  puts "Case ##{x}: #{k} #{r} #{c}"
end
