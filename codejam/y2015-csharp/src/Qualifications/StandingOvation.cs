using System;
using System.IO;
using System.Linq;

// https://code.google.com/codejam/contest/6224486/dashboard
namespace CodeJam._2015.Qualifications {
	class StandingOvation {

		private static readonly string Year = "2015";
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

				//		TextReader reader = System.Console.In;
				//		TextWriter writer = System.Console.Out ;

				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					string line = reader.ReadLine();
					writer.WriteLine("Case #{0}: {1}", testCaseId, solve(line));
					writer.Flush();
				}
			}
		}

		private static int solve(string line) {
			string[] tokens = line.Split(' ');
			int maxShynessLevel = Convert.ToInt32(tokens[0]);

			char[] digits = tokens[1].ToCharArray();
			int[] minPeopleToStandUp = toIntArray(digits);

			int minNumOfFriends = 0;
			int numPeopleStanding = 0;

			for (int i = 1; i < minPeopleToStandUp.Length; i++) {
				numPeopleStanding += minPeopleToStandUp[i - 1];
				if (numPeopleStanding < i) {
					minNumOfFriends += i - numPeopleStanding;
					numPeopleStanding += i - numPeopleStanding;
				}
			}

			return minNumOfFriends;
		}

		private static int[] toIntArray(char[] charArray) {
			return charArray.Select(c => c - '0').ToArray();
		}
	}
}
