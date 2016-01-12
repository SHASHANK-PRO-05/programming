package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class twentynine {
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
		boolean[] visited = new boolean[n + 1];
		long finalAns = 0;
		for (long i = 2; i <= n; i++) {
			if (!visited[(int) i]) {
				long temp = i * i;
				visited[(int) i] = true;
				long pow = 2;
				finalAns += (n - 1);
				if (temp <= n) {
					boolean[] powervisited = new boolean[12000000];
					for (int j = 2; j <= n; j++) {
						powervisited[j] = true;
					}
					while (temp <= n) {
						visited[(int) temp] = true;
						for (int j = 2; j <= n; j++) {
							if (!powervisited[(int) pow * j]) {
								finalAns++;
								powervisited[(int) pow * j] = true;
							}
						}
						temp = temp * i;
						pow++;
					}
				}
			}
		}
		out.print(finalAns);
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
