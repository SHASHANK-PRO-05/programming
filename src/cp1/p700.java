package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p700 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int n = readInt();
		int cases = 0;
		while (n != 0) {
			cases++;
			builder.append("Case #" + cases + ":\n");
			int diff[] = new int[n];
			int y[] = new int[n];
			int a[] = new int[n];
			int b[] = new int[n];
			int yi = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				y[i] = readInt();
				a[i] = readInt();
				b[i] = readInt();
				diff[i] = b[i] - a[i];
				if (yi < a[i]) {
					yi = a[i];
				}
			}
			for (; yi < 10000; yi++) {
				int i;
				for (i = 0; i < n; i++) {
					if ((yi - y[i]) % diff[i] != 0) {
						break;
					}
				}
				if (i == n) {
					break;
				}
			}
			n = readInt();
			if (yi == 10000) {
				builder.append("Unknown bugs detected.\n\n");
			} else {
				builder.append("The actual year is " + yi + ".\n\n");
			}
		}
		out.print(builder);
		out.flush();
		out.close();
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
