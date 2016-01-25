package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p12187 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int n = readInt(), r = readInt(), c = readInt(), k = readInt();
		while (n != 0) {
			int[][] area = new int[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					area[i][j] = readInt();
				}
			}
			while (k-- != 0) {
				int[][] newArea = new int[r][c];
				boolean[][] visited = new boolean[r][c];
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (i < r - 1 && (area[i][j] + 1) % n == area[i + 1][j]) {
							newArea[i + 1][j] = area[i][j];
							visited[i + 1][j] = true;
						}
						if (j < c - 1 && (area[i][j] + 1) % n == area[i][j + 1]) {
							newArea[i][j + 1] = area[i][j];
							visited[i][j + 1] = true;
						}
						if (i > 0 && (area[i][j] + 1) % n == area[i - 1][j]) {
							newArea[i - 1][j] = area[i][j];
							visited[i - 1][j] = true;
						}
						if (j > 0 && (area[i][j] + 1) % n == area[i][j - 1]) {
							newArea[i][j - 1] = area[i][j];
							visited[i][j - 1] = true;
						}
						if (!visited[i][j]) {
							newArea[i][j] = area[i][j];
						}
					}
				}
				area = newArea;
			}
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (j != c - 1) {
						builder.append(area[i][j] + " ");
					} else {
						builder.append(area[i][j] + "\n");
					}
				}
			}
			n = readInt();
			r = readInt();
			c = readInt();
			k = readInt();
		}
		out.print(builder);
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
