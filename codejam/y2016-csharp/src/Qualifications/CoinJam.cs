using System.IO;
using System.Linq;
using System.Collections.Generic;
using System;
using System.Text;

/// https://code.google.com/codejam/contest/6254486/dashboard#s=p1
/// <author>Marco Lackovic</author>
/// Solved in 35 minutes
namespace CodeJam._2016.Qualifications {
	public class CoinJam {

		private static Dictionary<string, int[]> CoinJams = new Dictionary<string, int[]>();
		private static HashSet<int> ExtractedNumbers = new HashSet<int>();
		private static int maxNumber;
		private static int numberOfInternalBits;

		private static Random random = new Random();

		private static readonly string Year = "2016";
		private static readonly string Round = "Qualifications";
		private static readonly string FileName = "C-small-attempt2";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		public static readonly string OutputFile = FilePath + ".out";

		public static void Main2() {

			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					writer.WriteLine("Case #{0}: {1}", testCaseId, solve(reader.ReadLine()));
					writer.Flush();
				}
			}
		}

		private static string solve(string line) {
			int[] numbers = line.Split(' ').Select(int.Parse).ToArray();
			int jamcoinLength = numbers[0];
			int numberOfJamcoins = numbers[1];

			numberOfInternalBits = jamcoinLength - 2;
			maxNumber = (1 << numberOfInternalBits);

			while (CoinJams.Count < numberOfJamcoins) {
				int nonExtractedNumber = GetNonExtractedNumber();
				string jamcoinBinary = GetJamcoin(nonExtractedNumber);
				char[] bits = jamcoinBinary.ToCharArray();
				int[] divisors = new int[9];
				int @base = 2;
				for (; @base <= 10; @base++) {
					int currentBaseNumber = FromBinaryToBase(bits, @base);
					int divisor = GetDivisor(currentBaseNumber);
					if (divisor == -1) {
						@base = -1;
						break;
					}
					divisors[@base - 2] = divisor;
				}
				if (@base == 11) {
					CoinJams[jamcoinBinary] = divisors;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.AppendLine();
			foreach (KeyValuePair<string, int[]> entry in CoinJams) {
				sb.AppendLine(entry.Key + " " + string.Join(" ", entry.Value));
			}
			return sb.ToString();
		}

		public static int FromBinaryToBase(char[] bits, int @base) {
			int n = 1;
			int factor = @base;
			for (int i = bits.Length - 2; i > 0; i--) {
				n = n + factor * (int)char.GetNumericValue(bits[i]);
				factor = factor * @base;
			}
			return n + factor;
		}

		private static string GetJamcoin(int number) {
			string s = Convert.ToString(number, 2);
			while (s.Length < numberOfInternalBits) {
				s = "0" + s;
			}
			return "1" + s + "1";
		}

		private static int GetNonExtractedNumber() {
			int n = 0;
			while (ExtractedNumbers.Contains(n)) {
				n = random.Next(0, maxNumber);
			}
			ExtractedNumbers.Add(n);
			return n;
		}

		private static int GetDivisor(int n) {
			if (n % 2 == 0) {
				return 2;
			}
			int limit = (int)Math.Sqrt(n);
			for (int divisor = 3; divisor <= limit; divisor += 2) {
				if (n % divisor == 0) {
					return divisor;
				}
			}
			return -1;
		}
	}
}
