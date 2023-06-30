using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CodeJamTest {
	[TestClass]
	public class Qualifications {

		[TestMethod]
		public void CoinJam() {
			CodeJam._2016.Qualifications.CoinJam.Main2();
			string InputFile = CodeJam._2016.Qualifications.CoinJam.OutputFile;
			using (TextReader reader = new StreamReader(InputFile)) {
				string line = reader.ReadLine();
				while (!string.IsNullOrWhiteSpace(line)) {
					line = reader.ReadLine();
					Check(line);
				}
			}
		}

		private void Check(string line) {
			string[] divisors = line.Split(' ');
			char[] coinjam = divisors[0].ToCharArray();
			for (int i = 1; i < divisors.Length; i++) {
				int number = CodeJam._2016.Qualifications.CoinJam.FromBinaryToBase(coinjam, i + 1);
				Assert.AreEqual(number % int.Parse(divisors[i]), 0);
			}
		}
	}
}