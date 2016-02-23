package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class TomandJerry {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int[][] arr;
	static boolean[][] visited;
	static long count = 0;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt();
		arr = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = readInt();
			}
		}
		find(0, 0, n);
		out.print(count);
		out.flush();
		out.close();
	}

	public static void find(int i, int j, int n) {
		if (arr[i][j] == 1) {
			return;
		}
		if (i == n - 1 && j == n - 1) {
			count++;
			return;
		}
		if (i < n - 1 && !visited[i + 1][j]) {
			visited[i][j] = true;
			find(i + 1, j, n);
			visited[i][j] = false;
		}
		if (i > 0 && !visited[i - 1][j]) {
			visited[i][j] = true;
			find(i - 1, j, n);
			visited[i][j] = false;
		}
		if (j > 0 && !visited[i][j - 1]) {
			visited[i][j] = true;
			find(i, j - 1, n);
			visited[i][j] = false;
		}
		if (j < n - 1 && !visited[i][j + 1]) {
			visited[i][j] = true;
			find(i, j + 1, n);
			visited[i][j] = false;
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
