package part5;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class TheConfusedMonk {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long modulo = 1000000007;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		int n = readInt();
		long gcd1 = 0;
		long fx = 1;
		for (int i = 0; i < n; i++) {
			long temp = readLong();
			gcd1 = gcd(temp, gcd1);
			fx = mulMod(fx, temp);
		}
		out.println(powModulo(fx, gcd1));
		out.flush();
		out.close();
	}

	public static long powModulo(long a, long b) {
		long res = 1;
		while (b > 0) {
			if (b % 2 == 1) {
				res = (res * a) % modulo;
			}
			a = (a * a) % modulo;
			b = b / 2;
		}
		return res;
	}

	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		else {
			
			return gcd(b, a % b);
		}
	}

	public static long mulMod(long ans, long mul) {
		return (ans * mul) % modulo;
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
