using System;
using System.IO;
using System.Linq;

// https://code.google.com/codejam/contest/6224486/dashboard#s=p1
namespace CodeJam._2015.Qualifications {
	class InfiniteHouseOfPancakes {

		private static readonly string Year = "2015";
		private static readonly string Round = "Qualifications";
		private static readonly string FileName = "B-Sample";

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
					string numDiners = reader.ReadLine();
					string line = reader.ReadLine();
					writer.WriteLine("Case #{0}: {1}", testCaseId, solve(line));
					writer.Flush();
				}
			}
		}

		private static int solve(string line) {
			int[] numPancakes = Array.ConvertAll(line.Split(' '), int.Parse);
			Array.Sort(numPancakes);

			int minNumOfMinutes = 0;

			return minNumOfMinutes;
		}

	}
}
