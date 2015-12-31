package part3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

public class SherlockandNumbers {
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
			long n = nextLong();
			int k = nextInt();
			long p = nextLong();
			long[] integers = new long[k];
			for (int i = 0; i < k; i++) {
				integers[i] = nextLong();
			}
			Arrays.sort(integers);
			builder.append(diffSolution(integers, k, p, n)+"\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	/*
	 * build your functions below this
	 */
	public static long findP(long[] integers, int k, long p, long n) {
		long[] tempArr = new long[k + 2];
		tempArr[0] = 0;
		for (int i = 0; i < k; i++) {
			tempArr[i + 1] = integers[i];
		}
		tempArr[k + 1] = n + 1;
		for (int i = 1; i < k + 2; i++) {
			long range = tempArr[i] - tempArr[i - 1] - 1;
			if (p <= range) {
				return tempArr[i - 1] + p;
			} else {
				p = p - (range);
			}
		}
		return -1;
	}

	public static long diffSolution(long[] integers, int k, long p, long n) {
		long lo = 1, hi = 2 * n;
		long res = -1;
		while (lo <= hi) {
			long middleElement = (lo + hi) >> 1;
			int tobedeleted = findAllTheElementsDeletedBeforeThis(middleElement, integers);
			long temp = middleElement - tobedeleted;
			if (temp >= p) {
				res = middleElement;
				hi = middleElement - 1;
			} else {
				lo = middleElement + 1;
			}
		}
		if (res > n)
			return -1;
		else {
			return res;
		}
	}

	public static int findAllTheElementsDeletedBeforeThis(long x, long[] integers) {
		int res = -1;
		int lo = 0, hi = integers.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) >> 1;
			if (x >= integers[mid]) {
				res = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return res + 1;
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
