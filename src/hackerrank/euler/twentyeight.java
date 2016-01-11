package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class twentyeight {
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
		System.out.println(669171001 % mod);
		while (t-- != 0) {
			long a = readLong();
			builder.append(find(a / 2 + 1) + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static long find(long a) {
		long res1 = a;
		long res2 = (a + 1);
		long res3 = (2 * a + 1);
		if (res1 % 3 == 0) {
			res1 /= 3;
		} else {
			if (res2 % 3 == 0)
				res2 /= 3;
			else
				res3 /= 3;
		}
		long firstRes = (8 * ((((res1 % mod) * (res2 % mod)) % mod * (res3 % mod)) % mod)) % mod;
		long secondRes = (((14 * (a % mod)) % mod) * ((a + 1) % mod)) % mod;
		long thirdRes = (16 * (a % mod)) % mod;
		long finalres = (((((firstRes - secondRes) % mod + mod) % mod + thirdRes) % mod - 3) + mod) % mod;
		return finalres;
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
