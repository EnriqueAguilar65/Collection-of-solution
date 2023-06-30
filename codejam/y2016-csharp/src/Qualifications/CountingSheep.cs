using System;
using System.IO;

/// https://code.google.com/codejam/contest/6254486/dashboard#s=p0
/// <author>Marco Lackovic</author>
/// Solved in 50 minutes
namespace CodeJam._2016.Qualifications {
	class CountingSheep {

		private static readonly string Insomnia = "INSOMNIA";

		private static readonly string Year = "2016";
		private static readonly string Round = "Qualifications";
		private static readonly string FileName = "A-large";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		private static readonly string OutputFile = FilePath + ".out";

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

		private static string solve(string chosenNumberAsString) {
			int chosenNumber = Int32.Parse(chosenNumberAsString);
			if (chosenNumber == 0) {
				return Insomnia;
			}

			bool[] digits = new bool[10];
			int iteration = 0;
			int number = 0;
			while (!SeenAllDigits(digits)) {
				iteration++;
				number = iteration * chosenNumber;
				char[] currentDigits = number.ToString().ToCharArray();
				for (int i = 0; i < currentDigits.Length; i++) {
					digits[(int)Char.GetNumericValue(currentDigits[i])] = true;
				}
			}
			return number.ToString();
		}

		private static bool SeenAllDigits(bool[] digits) {
			for (int i = 0; i < digits.Length; i++) {
				if (!digits[i]) {
					return false;
				}
			}
			return true;
		}
	}
}
