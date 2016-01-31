package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class RoyandTiles {
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
			long[][] arr = new long[n][n];
			long[][] ansArr = new long[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = readLong();
				}
				Arrays.sort(arr[i]);
			}
			for (int i = 0; i < n; i++) {
				ansArr[n - 1][i] = 1;
			}
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					if (j > 0 && arr[i][j - 1] == arr[i][j]) {
						ansArr[i][j] = ansArr[i][j - 1];
					} else
						ansArr[i][j] = countNum(arr[i + 1], arr[i][j] + 1, n, ansArr[i + 1], i);
				}
			}
			int q = readInt();
			while (q-- != 0) {
				long a = readLong(), b = readLong();
				if (b - a != n + 1)
					builder.append("0\n");
				else
					builder.append(countNum(arr[0], a + 1, n, ansArr[0], 0) + "\n");
			}
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static long countNum(long[] arr, long num, int n, long[] ansArr, int temp) {
		long count = 0;
		long mod = 1000000007;
		int start = 0, end = n - 1, mid = 0, ans = 0;
		while (start <= end) {
			mid = (start + end) >> 1;
			if (arr[mid] >= num) {
				end = mid - 1;
				ans = mid;
			} else {
				start = mid + 1;
			}
		}
		for (int i = ans; i < n; i++) {
			if (arr[i] == num) {
				count = (count + ansArr[i]) % mod;
				continue;
			}
			break;
		}
		return count;
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
