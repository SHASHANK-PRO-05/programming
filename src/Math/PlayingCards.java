package Math;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class PlayingCards {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long mod = 1000000007;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		while (t-- != 0) {
			int n = readInt();
			int m = readInt();
			if (m % 2 != 0) {
				builder.append("0\n");
			} else {
				int blackFaces = 6 * n;
				int redFaces = 6 * n;
				int blackRanks = 18 * n;
				int redRanks = 18 * n;
				long[][][][][] dp = new long[blackFaces + 1][redFaces + 1][blackRanks + 1][redRanks + 1][m / 2 + 1];
				builder.append(find(blackFaces, redFaces, blackRanks, redRanks, m / 2, dp) + "\n");
			}
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static long find(int blackFaces, int redFaces, int blackRanks, int redRanks, int m, long[][][][][] dp) {
		if (m == 0) {
			return 1;
		}
		if (dp[blackFaces][redFaces][blackRanks][redRanks][m] != 0) {
			return dp[blackFaces][redFaces][blackRanks][redRanks][m];
		}
		long res = 0;
		res += (((blackFaces * redRanks) % mod) * find(blackFaces - 1, redFaces, blackRanks, redRanks - 1, m - 1, dp))
				% mod;
		res += (((redFaces * blackRanks) % mod) * find(blackFaces, redFaces - 1, blackRanks - 1, redRanks, m - 1, dp))
				% mod;
		dp[blackFaces][redFaces][blackRanks][redRanks][m] = res;
		System.out.println(res);
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
