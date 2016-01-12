package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class twentyseven {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static boolean[] isPrime = new boolean[8002001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		find();
		int n = readInt();
		int nMax = 0;
		int a = 0;
		int b = 0;
		for (int i = -n; i <= n; i++) {
			for (int j = -n; j <= n; j++) {
				int k = 0;
				while (!isPrime[Math.abs(k * k + k * i + j)]) {
					k++;
				}
				if (k > nMax) {
					nMax = k;
					a = i;
					b = j;
				}
			}
		}

		out.print(a + " " + b);
		out.flush();
		out.close();
	}

	public static void find() {
		for (long i = 2; i <= 8002000; i++) {
			if (isPrime[(int) i] == false) {
				long j = i * i;
				for (; j <= 8002000; j += i) {
					isPrime[(int) j] = true;
				}
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
