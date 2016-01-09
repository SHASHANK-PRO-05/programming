package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class sixteen {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	public static long[] longs = new long[10001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		// find();
		int t = readInt();
		while (t-- != 0) {
			builder.append(findSum(readLong()) + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find() {
		for (int i = 1; i <= 10000; i++) {
			longs[i] = findSum(i);
		}
	}

	public static long findSum(long n) {
		BigInteger bigInteger = new BigInteger("1");
		BigInteger bigInteger2 = new BigInteger("2");
		while (n != 0) {
			if (n % 2 != 0) {
				bigInteger = bigInteger.multiply(bigInteger2);
			}
			bigInteger2 = bigInteger2.multiply(bigInteger2);
			n = n / 2;
		}
		long ans = 0;
		String s = bigInteger.toString();
		for (int i = 0; i < s.length(); i++) {
			ans += (s.charAt(i) - '0');
		}
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
