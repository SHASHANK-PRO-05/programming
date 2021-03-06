package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GameofNumbers {
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
		StringBuilder builder = new StringBuilder("");

		while (t-- != 0) {
			int[] res = new int[1000001];
			long x = nextLong(), y = nextLong();
			for (long i = 2; i * i <= y; i++) {
				long sq = i * i;
				long j = (sq - (x % sq));
				if (j == sq) {
					j = 0;
				}
				for (j = j + x; j <= y; j += sq) {
					res[(int) (j - x)] = 1;
				}
			}
			int count = 0;
			for (int i = 0; i < y - x + 1; i++) {
				if (res[i] == 0) {
					count++;
				}
			}
			builder.append(count+"\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}
	/*
	 * build your functions below this
	 */

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
