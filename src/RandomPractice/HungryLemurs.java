package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class HungryLemurs {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), k = readInt();
		bandinc(n, k, 0);
		bandiscard(n, k, 0);
		perdec(n, k, 0);
		perinc(n, k, 0);
		out.println(min);
		out.flush();
		out.close();
	}

	public static void bandiscard(int n, int k, int mini) {
		k = k - 1;
		mini++;
		if (mini >= min)
			return;
		if (n % k == 0) {
			min = mini;
			return;
		}
		bandiscard(n, k, mini);
		perinc(n, k, mini);
		perdec(n, k, mini);
	}

	public static void bandinc(int n, int k, int mini) {
		k = k + 1;
		mini++;
		if (mini >= min)
			return;
		if (n % k == 0) {
			min = mini;
			return;
		}
		bandinc(n, k, mini);
		perinc(n, k, mini);
		perdec(n, k, mini);
	}

	public static void perinc(int n, int k, int mini) {
		n = n + 1;
		mini++;
		if (mini >= min)
			return;
		if (n % k == 0) {
			min = mini;
			return;
		}
		bandinc(n, k, mini);
		perinc(n, k, mini);
		bandiscard(n, k, mini);
	}

	public static void perdec(int n, int k, int mini) {
		n = n - 1;
		mini++;
		if (mini >= min)
			return;
		if (n % k == 0) {
			min = mini;
			return;
		}
		bandinc(n, k, mini);
		perdec(n, k, mini);
		bandiscard(n, k, mini);
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
