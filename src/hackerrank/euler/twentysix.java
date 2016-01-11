package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;

public class twentysix {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int[] ans = new int[10001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		findRecur();
		int t = readInt();
		while (t-- != 0) {
			builder.append(ans[readInt() - 1] + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void findRecur() {
		int temp = 0;
		int dummy = 0;
		for (int i = 1; i <= 10000; i++) {
			int tempans = findChain(i);
			if (tempans > temp) {
				temp = tempans;
				dummy = i;
			}
			ans[i] = dummy;
		}
	}

	public static int findChain(int n) {
		boolean[] visited = new boolean[10001];
		int[] position = new int[10001];
		int rem = 1;
		int temp = 0;
		while (!visited[rem] && rem != 0) {
			visited[rem] = true;
			temp++;
			position[rem] = temp;
			rem *= 10;
			rem = rem % n;
		}
		if (rem == 0)
			return 0;
		else
			return temp + 1 - position[rem];
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
