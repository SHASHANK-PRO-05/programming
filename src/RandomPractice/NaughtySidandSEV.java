package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NaughtySidandSEV {
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
			double a = nextDouble(), H = nextDouble(), theta = nextDouble();
			if ((a * Math.tan(theta * (3.141593) / 180)) < H) {
				double h = (0.5) * ((2 * H) - (a * Math.tan(theta * (3.141593) / 180)));
				builder.append(((long) Math.ceil(h)) + "\n");
			} else {
				double h = (H * H) / (2 * (a * Math.tan(theta * 3.141593 / 180)));
				builder.append(((long) Math.ceil(h)) + "\n");
			}

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
