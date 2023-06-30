using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

// https://code.google.com/codejam/contest/8224486/dashboard

namespace CodeJam._2015.Round1B {
	internal class CounterCulture {

		private static readonly string Year = "2015";
		private static readonly string Round = "Round1B";
		private static readonly string FileName = "A-small-attempt3";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		private static readonly string OutputFile = FilePath + ".out";

		public static void MainNotWorking() {

			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					string line = reader.ReadLine();
					writer.WriteLine("Case #{0}: {1}", testCaseId, Solve(int.Parse(line)));
					writer.Flush();
				}
			}
		}

		private static int Solve(int n) {
			var numSteps = 0;
			while (n > 20) {
				int lastDigit = n % 10;
				if (lastDigit == 0) {
					lastDigit = 10;
				}
				int endingWithOne = n - lastDigit + 1;
				int reversed = Reverse(endingWithOne);
				if (reversed < endingWithOne) {
					n = reversed - 1;
					numSteps += lastDigit + 1;
				} else {
					n = n - 1;
					numSteps++;
				}
			}
			return numSteps + n;
		}

		private static int Reverse(int n) {
			string s = n.ToString();
			char[] charArray = s.ToCharArray();
			Array.Reverse(charArray);
			return int.Parse(new string(charArray));
		}
	}
}
