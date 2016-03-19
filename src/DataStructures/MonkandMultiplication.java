package DataStructures;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class MonkandMultiplication {
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

		PriorityQueue<Long> longs = new PriorityQueue<>();
		long max1 = -1, max2 = -1, max3 = -1;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				max1 = readLong();
				builder.append("-1\n");
			} else if (i == 1) {
				long temp2 = readLong();
				if (max1 > temp2) {
					max2 = temp2;
				} else {
					max2 = max1;
					max1 = temp2;
				}
				builder.append("-1\n");
			} else {
				long temp2 = readLong();
				if (temp2 > max1) {
					max3 = max2;
					max2 = max1;
					max1 = temp2;
				} else if (temp2 > max2) {
					max3 = max2;
					max2 = temp2;
				} else if (temp2 > max3) {
					max3 = temp2;
				}
				builder.append(max1 * max2 * max3 + "\n");
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
