package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p11926 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), m = readInt();
		while (true) {
			if (n == 0 && m == 0) {
				break;
			}
			boolean ans = true;
			boolean[] bitset = new boolean[1000001];
			while (n-- != 0) {
				int a = readInt(), b = readInt();
				for (int i = a; i < b; i++) {
					if (bitset[i]) {
						ans = false;
					}
					bitset[i] = true;
				}
			}
			while (m-- != 0) {
				int a = readInt(), b = readInt(), r = readInt();
				int count = 1000000 / r;
				for (int k = 0; k <= count; k++) {
					int tempA = a + (k * r), tempB = b + (k * r);
					for (int i = tempA; i < tempB && i < 1000001; i++) {
						if (bitset[i]) {
							ans = false;
						}
						bitset[i] = true;
					}
				}
			}
			if (ans) {
				builder.append("NO CONFLICT\n");
			} else {
				builder.append("CONFLICT\n");
			}
			n = readInt();
			m = readInt();
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
