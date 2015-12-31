package decemberEasy2015;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.Vector;

public class MissingSoldiers {
	/*
	 * These variables will be used to get the input from the dom judge.
	 * 
	 * br=is a static buffered reader st=is a string tokenizer. it uses spaces
	 * to split
	 * 
	 */
	static BufferedReader br;
	static StringTokenizer st;

	/*
	 * Driver program
	 */
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		int t = nextInt();
		Pair[] pairs = new Pair[t];

		for (int i = 0; i < t; i++) {
			long x = nextLong(), y = nextLong(), d = nextLong();
			pairs[i] = new Pair();
			pairs[i].first = x;
			pairs[i].second = x + d;
		}
		Arrays.sort(pairs);
		out.println(findResult(pairs));
		out.flush();
		out.close();
	}

	/*
	 * build your functions below this
	 */
	public static long findResult(Pair[] pairs) {
		long res;
		res = pairs[0].second - pairs[0].first + 1;
		long begin = pairs[0].first, end = pairs[0].second;
		for (int i = 1; i < pairs.length; i++) {
			if (pairs[i].first <= end) {
				if (end < pairs[i].second) {
					res += (pairs[i].second - end);
					end = pairs[i].second;
				}
			} else {
				begin = pairs[i].first;
				end = pairs[i].second;
				res += (end - begin + 1);
			}
		}
		return res;
	}

	/*
	 * Functions below this comment are all used in reading inputs in a fast
	 * manner.
	 * 
	 */
	static String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static long nextLong() {
		return Long.parseLong(next());
	}

	static double nextDouble() {
		return Double.parseDouble(next());
	}

	static String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

}

/*
 * Build your class here
 */
class Pair implements Comparable<Pair> {
	public long first;
	public long second;

	@Override
	public int compareTo(Pair o) {
		if (this.first > o.first)
			return 1;
		else if (this.first < o.first) {
			return -1;
		}
		if (this.second > o.second) {
			return 1;
		} else if (this.second < o.second) {
			return -1;
		}
		return 0;
	}

}
