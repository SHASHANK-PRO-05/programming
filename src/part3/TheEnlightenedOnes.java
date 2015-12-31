package part3;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TheEnlightenedOnes {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), k = readInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = readLong();
		}
		Arrays.sort(arr);
		long low = 0, hi = 10000000, mi=0;
		while (low <= hi) {
			mi = (low + hi) >> 1;
			boolean temp = check(arr, mi, k);
			if (temp && !check(arr, mi - 1, k))
				break;
			if (temp) {
				hi = mi - 1;
			} else {
				low = mi + 1;
			}
		}
		out.println(mi);
		out.flush();
		out.close();
	}

	public static boolean check(long[] arr, long mi, int k) {
		long prev = arr[0] + mi;
		k--;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] <= prev + mi) {
				continue;
			}
			if (k == 0)
				return false;
			prev = arr[i] + mi;
			k--;
		}
		return true;
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
