package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Vector;

public class twentythree {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int[] arr = new int[100001];
	static boolean[] abundant = new boolean[100001];
	static Vector<Integer> integers = new Vector<>();

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		find();
		int t = readInt();
		while (t-- != 0) {
			int n = readInt();
			boolean itis = false;
			for (int i = 0; i < integers.size(); i++) {
				if (integers.get(i) > n)
					break;
				int temp = n - integers.get(i);
				if (abundant[temp]) {
					itis = true;
					break;
				}
			}
			if (itis)
				builder.append("YES\n");
			else
				builder.append("NO\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find() {
		for (int i = 1; i <= 100000; i++) {
			for (int j = i * 2; j <= 100000; j += i) {
				arr[j] += i;
			}
		}
		for (int i = 1; i <= 100000; i++) {
			if (arr[i] > i) {
				abundant[i] = true;
				integers.add(i);
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
