using System;
using System.IO;
using System.Linq;

// https://code.google.com/codejam/contest/6224486/dashboard#s=p1
namespace CodeJam._2015.Qualifications {
	class OminousOmino {

		private static readonly string Richard = "RICHARD";
		private static readonly string Gabriel = "GABRIEL";

		private static readonly string Year = "2015";
		private static readonly string Round = "Qualifications";
		private static readonly string FileName = "D-large";

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
					string line = reader.ReadLine();
					writer.WriteLine("Case #{0}: {1}", testCaseId, solve(line));
					writer.Flush();
				}
			}
		}

		private static string solve(string line) {
			int[] values = Array.ConvertAll(line.Split(' '), int.Parse);

			if (values[0] > 6 || values[1] * values[2] % values[0] > 0 || values[0] - Math.Min(values[1], values[2]) >= 2)
				return Richard;

			return Gabriel;
		}
	}
}
