using System;
using System.Text;
using System.Collections.Generic;
using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CodeJamTest {

	[TestClass]
	public class Program {
		static string fileName = "A-small-practice.in";
		//static string fileName = "test.txt";

		static Encoding Enc = Encoding.GetEncoding("us-ascii");
		static StreamReader reader = new StreamReader(@"C:\Google_Code_Jam_submit\" + fileName, Enc);
		static StreamWriter writer = new StreamWriter(@"C:\Google_Code_Jam_submit\Result.txt", false, Enc);

		static char[] cs = new char[] { ' ', ',' };

		[TestMethod]
		public void Ciccio() {
			int ncase = int.Parse(reader.ReadLine());

			for (int g = 0; g < ncase; g++) {
				int val = int.Parse(reader.ReadLine());
				//int[] vals = parseAry(reader.ReadLine().Split(cs));
				//int val = int.Parse(reader.ReadLine());
				//string str = reader.ReadLine();
				//string[] strs =reader.ReadLine().Split(cs);

				int[] vals = parseAry(reader.ReadLine().Split(cs));

				int n = val;

				PriorityQueue pq = new PriorityQueue();
				int[] let = new int[vals.Length];
				for (int i = 0; i < vals.Length; i++) {
					char temp = (char)('A' + i);
					pq.Enqueue(new PriQ() { rank = vals[i], str = temp.ToString() });
				}
				string ans = "";
				while (true) {
					int c = pq.Count;
					if (pq.Get(c - 1).rank == 0) {
						break;
					}
					else if (pq.Get(c - 1).rank == pq.Get(c - 2).rank + 2) {
						PriQ pri = pq.DequeueLast();
						pri.rank -= 2;
						pq.Enqueue(pri);
						ans += " " + pri.str + pri.str;
					}
					else if (pq.Get(c - 1).rank == pq.Get(c - 2).rank + 1) {
						PriQ pri = pq.DequeueLast();
						pri.rank -= 1;
						pq.Enqueue(pri);
						ans += " " + pri.str;
					}
					else if (pq.Get(c - 1).rank == pq.Get(c - 2).rank) {
						if (vals.Length > 2) {
							if (pq.Get(c - 1).rank == pq.Get(c - 3).rank) {
								PriQ pri = pq.DequeueLast();
								pri.rank -= 1;
								pq.Enqueue(pri);
								ans += " " + pri.str;
							}
							else {
								PriQ pri = pq.DequeueLast();
								PriQ pri2 = pq.DequeueLast();
								pri.rank -= 1;
								pri2.rank -= 1;
								pq.Enqueue(pri);
								pq.Enqueue(pri2);
								ans += " " + pri.str + pri2.str;
							}
						}
						else {
							PriQ pri = pq.DequeueLast();
							PriQ pri2 = pq.DequeueLast();
							pri.rank -= 1;
							pri2.rank -= 1;
							pq.Enqueue(pri);
							pq.Enqueue(pri2);
							ans += " " + pri.str + pri2.str;
						}
					}




				} //while
				ans = ans.Substring(1);
				AnswerGCJ(g, "" + ans);

			}

			writer.Close();
			return;
		}


		static void AnswerGCJ(int i, string ans) {
			writer.WriteLine("Case #" + (i + 1) + ": " + ans);
			return;
		}

		//　String[]をint[]に変換
		static int[] parseAry(String[] str) {
			int[] nums = new int[str.Length];
			for (int i = 0; i < str.Length; i++) {
				nums[i] = int.Parse(str[i]);
			}
			return nums;
		}


		public class PriQ {
			public int rank { get; set; }
			public string rankstr { get; set; }
			public string str { get; set; }
			public int num { get; set; }
			public int[] nums { get; set; }
			public Object obj { get; set; }
			public PriQ() { }
		}

		public class PriorityQueue {
			List<PriQ> list = new List<PriQ>();
			public PriorityQueue() { }
			public PriorityQueue(PriQ p) { list.Add(p); }
			public void Enqueue(PriQ item) { list.Insert(Binary_Search(item), item); }
			public PriQ Dequeue() { PriQ r = list[0]; list.RemoveAt(0); return r; }
			public PriQ DequeueLast() { PriQ r = list[list.Count - 1]; list.RemoveAt(list.Count - 1); return r; }
			public PriQ Peek() { return list[0]; }
			public PriQ Get(int n) { return list[n]; }
			public PriQ Last() { return list[list.Count - 1]; }
			public void Remove(int n) { list.RemoveAt(n); }
			public int Count { get { return list.Count; } }

			private int Binary_Search(PriQ pq) {
				int Lower = 0;
				int Upper = list.Count - 1;
				while (Lower <= Upper) {
					int Center = (Lower + Upper) >> 1;
					if (pq.rank > list[Center].rank) {
						Lower = Center + 1;
					}
					else if (pq.rank < list[Center].rank) {
						Upper = Center - 1;
					}
					else {
						return Center;
					}
				}
				return Lower;
			}
		}

	}
}
