using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

// https://code.google.com/codejam/contest/4244486/dashboard
namespace CodeJam._2015.Round1C {
	class TypewriterMonkey {
		private const string Year = "2015";
		private const string Round = "Round1C";
		private const string FileName = "B-sample";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		private static readonly string OutputFile = FilePath + ".out";

		public static void Main() {

			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					int typedStringLength = reader.ReadLine().Split(' ').Select(int.Parse).Last();
					writer.WriteLine("Case #{0}: {1:F}", testCaseId, Solve(typedStringLength, reader.ReadLine(), reader.ReadLine()));
					writer.Flush();
				}
			}
		}

		private static double Solve(int typedStringLength, string keyboard, string target) {
			Dictionary<char, int> keyboardDictionary = BuildDictionary(keyboard);
			if (!Possible(typedStringLength, keyboardDictionary, target))
				return 0;
			int numBananasToBring = typedStringLength / target.Length;
			double expectedNumBananas = 1;
			return expectedNumBananas;
		}

		private static Dictionary<char, int> BuildDictionary(string keyboard) {
			Dictionary<char, int> dic = new Dictionary<char, int>();
			foreach (var c in keyboard.ToCharArray()) {
				if (dic.ContainsKey(c)) {
					dic[c]++;
				} else {
					dic.Add(c, 1);
				}
			}
			return dic;
		}

		private static bool Possible(int typedStringLength, IReadOnlyDictionary<char, int> keyboard, string target) {
			return typedStringLength >= target.Length && target.ToCharArray().All(keyboard.ContainsKey);
		}
	}
}
