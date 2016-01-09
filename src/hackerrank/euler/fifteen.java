package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class fifteen {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long[][] arr = new long[501][501];
	static long mod = 1000000007;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		find();
		int t = readInt();
		while (t-- != 0) {
			long n = readLong(), m = readLong();
			builder.append(arr[(int) n][(int) m] + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find() {
		for (int i = 1; i < 501; i++) {
			for (int j = 1; j < 501; j++) {
				arr[i][j] = path(i, j);
			}
		}
	}

	public static long path(int i, int j) {
		if (i == 0 && j == 0)
			return 1L;
		if (arr[i][j] != 0)
			return arr[i][j];
		long ans = 0;
		ans += i > 0 ? path(i - 1, j) : 0;
		ans %= mod;
		ans += j > 0 ? path(i, j - 1) : 0;
		ans %= mod;
		arr[i][j] = ans;
		return ans;
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
