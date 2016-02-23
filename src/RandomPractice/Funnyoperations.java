package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Funnyoperations {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int len;
	static long max;
	static long[] arr;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		while (t-- != 0) {
			int n = readInt();
			arr = new long[n];
			len = 0;
			max = Long.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				arr[i] = readLong();
			}
			for (int i = 0; i < n; i++) {
				if (arr[i] > max) {
					max = arr[i];
					len = 1;
				}
				find(arr[i], arr[i], i + 1, 1, n);
			}
			find(0, 0, 0, 0, n);
			builder.append(max + " " + len + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find(long xor, long and, int i, int k, int n) {
		if (i == n)
			return;
		long ans = (xor ^ arr[i]) | (and & arr[i]);
		ans = (ans & 1073741823);
		if (ans > max) {
			max = ans;
			len = k + 1;
		} else if (ans == max && len < k) {
			len = k;
		}
		find(xor, and, i + 1, k, n);
		find(xor ^ arr[i], and & arr[i], i + 1, k + 1, n);
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
