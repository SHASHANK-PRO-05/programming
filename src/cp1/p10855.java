package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p10855 {
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
		int m = readInt();
		while (n != 0) {
			int[][] mainArr = new int[n][n];
			int[][] tempArr = new int[n][n];
			for (int i = 0; i < n; i++) {
				String temp = readString();
				for (int j = 0; j < n; j++) {
					mainArr[i][j] = temp.charAt(j);
				}
			}
			for (int i = 0; i < m; i++) {
				String temp = readString();
				for (int j = 0; j < m; j++) {
					tempArr[i][j] = temp.charAt(j);
				}
			}
			builder.append((find(mainArr, tempArr, n, m)) + " ");
			tempArr = rotate90(tempArr, m);
			builder.append((find(mainArr, tempArr, n, m)) + " ");
			tempArr = rotate90(tempArr, m);
			builder.append((find(mainArr, tempArr, n, m)) + " ");
			tempArr = rotate90(tempArr, m);
			builder.append((find(mainArr, tempArr, n, m)) + "\n");
			n = readInt();
			m = readInt();
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int find(int[][] mainArr, int[][] tempArr, int n, int m) {
		int res = 0;
		for (int i = 0; i <= n - m; i++) {
			for (int j = 0; j <= n - m; j++) {
				boolean found = true;
				for (int k = i; k < i + m; k++) {
					if (found)
						for (int l = j; l < j + m; l++) {
							if (mainArr[k][l] != tempArr[k - i][l - j]) {
								found = false;
								break;
							}
						}
				}
				if (found == true)
					res++;
			}
		}
		return res;
	}

	public static int[][] rotate90(int[][] tempArr, int m) {
		int[][] re = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				re[j][m - i - 1] = tempArr[i][j];
			}
		}
		return re;
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
