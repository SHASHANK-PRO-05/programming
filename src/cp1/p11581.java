package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p11581 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int index;
	static boolean found;
	static int[][] arr = new int[3][3];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		while (t-- != 0) {
			index = -1;
			found = true;
			for (int i = 0; i < 3; i++) {
				String s = readString();
				for (int j = 0; j < 3; j++) {
					arr[i][j] = s.charAt(j) - '0';
					if (arr[i][j] == 1) {
						found = false;
					}
				}
			}
			while (!found) {
				index++;
				found = true;
				arr = func();
			}
			builder.append(index + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int[][] func() {
		int[][] tempArr = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i < 2) {
					tempArr[i][j] += arr[i + 1][j];
				}
				if (j < 2) {
					tempArr[i][j] += arr[i][j + 1];
				}
				if (j > 0) {
					tempArr[i][j] += arr[i][j - 1];
				}
				if (i > 0) {
					tempArr[i][j] += arr[i - 1][j];
				}
				tempArr[i][j] %= 2;
				if (tempArr[i][j] == 1)
					found = false;
			}
		}
		return tempArr;
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
