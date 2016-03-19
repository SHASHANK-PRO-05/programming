package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Colorfulballs {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long[][][][] dp = new long[11][11][11][11];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int a = readInt(), b = readInt(), c = readInt(), d = readInt();

		out.print(findAns(a, b, c, d, -1));
		out.flush();
		out.close();
	}

	public static long findAns(int a, int b, int c, int d, int thisOne) {
		int res = 0;
		System.out.println(a + " " + b + " " + c + " " + d);
		if (a == 0 && b == 0 && c == 0 && d == 0)
			return 1;
		if (dp[a][b][c][d] != 0) {
			return dp[a][b][c][d];
		}
		if (a != 0 && thisOne != 0) {
			res += findAns(a - 1, b, c, d, 0);
		}
		if (b != 0 && thisOne != 1) {
			res += findAns(a, b - 1, c, d, 1);
		}
		if (c != 0 && thisOne != 2) {
			res += findAns(a, b, c - 1, d, 2);
		}
		if (d != 0 && thisOne != 3) {
			res += findAns(a, b, c, d - 1, 3);
		}
		dp[a][b][c][d] = res;
		return res;
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
