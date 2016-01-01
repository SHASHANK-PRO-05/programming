package part8;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class MonkandMatchMaking {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder(readString());
		long[] arr = new long[builder2.length()];
		arr[0] = builder2.charAt(0);
		long mod = (long) Math.pow(10, 9) + 7;
		;
		for (int i = 1; i < builder2.length(); i++) {
			arr[i] = (arr[i - 1] * 10 + builder2.charAt(i)) % mod;
		}
		int q = readInt();
		while (q-- != 0) {
			int l1 = readInt() - 1, r1 = readInt() - 1, l2 = readInt() - 1, r2 = readInt() - 1;
			long hash1 = ((arr[r1] - (l1 > 0 ? arr[l1 - 1] : 0) * pow(10, r1 - l1 + 1, mod)) % mod + mod) % mod;
			long hash2 = ((arr[r2] - (l2 > 0 ? arr[l2 - 1] : 0) * pow(10, r2 - l2 + 1, mod)) % mod + mod) % mod;
			if (hash1 == hash2) {
				builder.append("Yes\n");
			} else
				builder.append("No\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	public static long pow(long a, long b, long mod) {
		long res = 1;
		while (b != 0) {
			if (b % 2 != 0) {
				res = (res * a) % mod;
			}
			a = (a * a) % mod;
			b = b >> 1;
		}
		return res;
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
