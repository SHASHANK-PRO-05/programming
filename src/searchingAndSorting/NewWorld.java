package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class NewWorld {
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
			int n = readInt();
			int k = readInt();
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = readLong();
			}
			int start = 1, end = 1000000000, mid = 0, ans = 0;
			while (start <= end) {
				mid = (start + end) / 2;
				if (check(mid, arr, n, k)) {
					ans = mid;
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
			builder.append(ans + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static boolean check(int mid, long arr[], int n, int k) {
		int req = 0, prev = 0, curr = 0;
		for (int i = 0; i < n; i++) {
			while ((curr != n) && (arr[curr] - arr[prev] <= mid)) {
				curr++;
			}
			req++;
			if (curr == n)
				break;
			prev = curr - 1;
		}
		if (curr != n)
			return false;
		if (req <= k) {
			return true;
		}
		return false;
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
