package codechef;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class STRSUB {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		while (t-- != 0) {
			// will work according to the solution in editorial
			int n = readInt();
			int k = readInt();
			int q = readInt();
			String string = readString();
			int[] far = someOptmizationArray(string, k, n);
			// for (int i = 0; i < n; i++) {
			// System.out.println(i + " " + far[i]);
			// }
			while (q-- != 0) {
				int l = readInt() - 1, r = readInt() - 1;
				builder.append(someOptmization(string, l, r + 1, k, n, far) + "\n");
			}

		}
		out.print(builder);
		out.flush();
		out.close();
	}

	/*
	 * This is just small optimization
	 */
	public static int notSoOptimized(String s, int l, int r, int k, int n) {
		int ans = 0;
		for (int i = l; i <= r; i++) {
			for (int j = i; j <= Math.min(2 * k + i - 1, r); j++) {
				int count0 = 0, count1 = 0;
				for (int k1 = i; k1 <= j; k1++) {
					if (s.charAt(k1) == '0')
						count0++;
					else
						count1++;
					if (count0 > k || count1 > k)
						break;
				}
				if (count0 <= k && count1 <= k) {
					ans++;
				}
			}

		}
		return ans;
	}

	/*
	 * With some optimization(dp and precompute)
	 */
	public static int someOptmization(String s, int l, int r, int k, int n, int[] far) {
		int ans = 0;
		for (int i = l; i < r; i++) {
			int j = Math.min(far[i], r);
			ans += (j - i);
		}
		return ans;
	}

	public static int[] someOptmizationArray(String s, int k, int n) {
		int[] arr = new int[n];
		int j = 0;
		int count0 = 0;
		int count1 = 0;
		if (s.charAt(j) == '0') {
			count0++;
		} else {
			count1++;
		}
		for (int i = 0; i < n; i++) {
			while (j < n && count0 <= k && count1 <= k) {
				j++;
				if (j >= n)
					break;
				if (s.charAt(j) == '0') {
					count0++;
				} else {
					count1++;
				}

			}
			arr[i] = j;
			if (s.charAt(i) == '0')
				count0--;
			else {
				count1--;
			}
		}
		return arr;
	}

	public static int read() throws IOException {
		if (numChar <= curChar) {
			curChar = 0;
			numChar = stream.read(buffer);
			if (numChar <= 0) {
				return -1;
			}
		}
		return buffer[curChar++];
	}

	public static long readLong() throws IOException, InputMismatchException {
		int c = read();
		if (c == -1)
			throw new IOException();
		while (isSpaceChar(c)) {
			c = read();
		}
		boolean negative = false;
		if (c == '-') {
			negative = true;
			c = read();
		}
		long res = 0;
		while (!isSpaceChar(c)) {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += (c - '0');
			c = read();
		}
		if (negative)
			return -res;
		return res;
	}

	public static int readInt() throws IOException, InputMismatchException {
		return (int) readLong();
	}

	public static String readString() throws IOException {
		int c = read();
		if (c == -1)
			throw new IOException();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder builder = new StringBuilder();
		while (!isSpaceChar(c)) {
			builder.append((char) c);
			c = read();
		}
		return builder.toString();
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
	}
}
