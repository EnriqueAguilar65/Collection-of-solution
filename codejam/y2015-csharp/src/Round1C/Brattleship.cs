using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

// https://code.google.com/codejam/contest/4244486/dashboard
namespace CodeJam._2015.Round1C {
	class Brattleship {
		private const string Year = "2015";
		private const string Round = "Round1C";
		private const string FileName = "A-large";

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
					writer.WriteLine("Case #{0}: {1}", testCaseId, Solve(reader.ReadLine().Split(' ').Select(int.Parse)));
					writer.Flush();
				}
			}
		}

		private static int Solve(IEnumerable<int> input) {
			var numRows = input.ElementAt(0);
			var numColumns = input.ElementAt(1);
			var shipLength = input.ElementAt(2);

			var numAttemptsWrongRows = (int)Math.Ceiling((double)numColumns / shipLength);
			var numAttemptsRightRow = numAttemptsWrongRows + shipLength - 1;

			var lowestScore = numAttemptsWrongRows * (numRows - 1) + numAttemptsRightRow;

			return lowestScore;
		}

	}
}
