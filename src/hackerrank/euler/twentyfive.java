package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class twentyfive {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int[] till1000 = new int[5001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		find();
		int t = readInt();
		while (t-- != 0) {
			int n = readInt();
			// if (n > 1000)
			// builder.append(term(n) + "\n");
			// else
				builder.append(till1000[n] + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static long term(long n) {
		double d = Math.log10(10) * (n - 1);
		double d1 = Math.log10(5) / 2;
		d = d + d1;
		d1 = 0.20898764025;
		d /= d1;
		return (long) Math.ceil(d);
	}

	public static void find() {
		BigInteger bigInteger1 = new BigInteger("1");
		BigInteger bigInteger2 = new BigInteger("1");
		int count = 2;
		int temp = 1;
		till1000[1] = 1;
		while (bigInteger2.toString().length() < 5000) {
			BigInteger bigInteger = bigInteger2.add(bigInteger1);
			bigInteger1 = bigInteger2;
			bigInteger2 = bigInteger;
			count++;
			if (temp < bigInteger2.toString().length()) {
				temp = bigInteger2.toString().length();
				till1000[temp] = count;
			}
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
