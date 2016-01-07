package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class eleven {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long[][] arr = new long[20][20];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				arr[i][j] = readLong();
			}
		}
		out.print(find());
		out.flush();
		out.close();
	}

	public static long find() {
		long max = Long.MIN_VALUE;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				long maxRight = 1;
				long maxDown = 1;
				long maxDiag = 1;
				long temp = 1;
				if (i <= 16) {
					max = Math.max(max, arr[i][j] * arr[i + 1][j] * arr[i + 2][j] * arr[i + 3][j]);
				}
				if (j <= 16) {
					max = Math.max(max, arr[i][j] * arr[i][j + 1] * arr[i][j + 2] * arr[i][j + 3]);
				}
				if (i <= 16 && j <= 16) {
					max = Math.max(max, arr[i][j] * arr[i + 1][j + 1] * arr[i + 2][j + 2] * arr[i + 3][j + 3]);
				}
				if (i <= 16 && j >= 3) {
					max = Math.max(max, arr[i][j] * arr[i + 1][j - 1] * arr[i + 2][j - 2] * arr[i + 3][j - 3]);
				}
			}
		}
		return max;
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
