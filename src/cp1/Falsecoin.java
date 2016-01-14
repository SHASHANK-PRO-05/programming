package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Falsecoin {
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
			int n = readInt(), k = readInt();
			boolean[] ans = new boolean[n + 1];
			while (k-- != 0) {
				int temp = readInt();
				int[] temparr = new int[temp * 2];
				for (int i = 0; i < temp * 2; i++) {
					temparr[i] = readInt();
				}
				if (readString().compareTo("=") == 0)
					for (int i = 0; i < temp * 2; i++) {
						ans[temparr[i]] = true;
					}
			}
			boolean found = false;
			int finalAns = 0;
			for (int i = 1; i <= n; i++) {
				if (ans[i] == false) {
					if (!found) {
						found = true;
						finalAns = i;
					} else {
						finalAns = 0;
						found = false;
						break;
					}
				}
			}

			builder.append(finalAns + "\n");
			if (t != 0)
				builder.append("\n");

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
