package jan16;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Codecoloringoverdose {

	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		int n = readInt(), k = readInt(), m = readInt();
		int[] colors = new int[k + 1];
		long[] pow = new long[n + 1];
		long mod = (long) (1e9 + 7);
		pow[0] = 1;
		for (int i = 1; i <= n; i++) {
			int temp = readInt();
			colors[temp]++;
			pow[i] = (pow[i - 1] * 2) % mod;
		}
		long[] dynamic = new long[m + 1];
		dynamic[0] = 1;
		for (int i = 1; i <= k; i++) {
			for (int j = m; j >= 1; j--) {
				dynamic[j] = (dynamic[j] + (dynamic[j - 1] * (pow[colors[i]] - 1)) % mod) % mod;
			}
		}
		long ans = 1;
		for (int i = 1; i <= m; i++) {
			ans = (ans + dynamic[i]) % mod;
		}
		out.println(ans);
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
