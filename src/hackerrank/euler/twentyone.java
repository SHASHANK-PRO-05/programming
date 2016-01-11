package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class twentyone {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long[] sum = new long[304921];
	static boolean[] amicable = new boolean[304921];
	static long[] arr = new long[100001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		find();
		int t = readInt();
		while (t-- != 0) {
			builder.append(arr[readInt() - 1] + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find() {
		for (int i = 1; i <= 304920; i++) {
			for (int j = 2 * i; j <= 304920; j += i) {
				sum[j] += i;
			}
		}
		for (int i = 1; i <= 100000; i++) {
			if (i == sum[(int) sum[i]] && i != sum[i]) {
				amicable[i] = true;
				amicable[(int) sum[i]] = true;
			}
		}
		for (int i = 1; i < 100001; i++) {
			if (amicable[i]) {
				arr[i] += i;
			}
			arr[i] += arr[i - 1];
		}
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
