package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class twenty {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static BigInteger[] arr = new BigInteger[1001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		//arr[0] = new BigInteger("0");
		//find();
		int t = readInt();
		while (t-- != 0) {
			builder.append(findFactorial(readInt()).toString() + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find() {
		BigInteger bigInteger = new BigInteger("1");
		for (int i = 1; i < 1001; i++) {
			bigInteger = bigInteger.multiply(new BigInteger(i + ""));
			arr[i] = new BigInteger("0");
			String s = bigInteger.toString();
			for (int j = 0; j < s.length(); j++) {
				arr[i] = arr[i].add(new BigInteger((s.charAt(j) - '0') + ""));
			}
		}
	}

	public static BigInteger findFactorial(int n) {
		BigInteger bigInteger = new BigInteger("1");
		for (int i = 1; i <= n; i++) {
			bigInteger = bigInteger.multiply(new BigInteger(i + ""));
		}
		String s = bigInteger.toString();
		bigInteger = new BigInteger("0");
		for (int i = 0; i < s.length(); i++) {
			bigInteger = bigInteger.add(new BigInteger((s.charAt(i) - '0') + ""));
		}
		return bigInteger;
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
