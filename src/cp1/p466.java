package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class p466 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		Scanner scanner = new Scanner(stream);
		StringBuilder builder = new StringBuilder();
		int temp = 0;
		while (scanner.hasNext()) {
			temp++;
			int n = scanner.nextInt();
			char[][] orig = new char[n][n];
			char[][] trans = new char[n][n];
			char[][] mirror = new char[n][n];
			for (int i = 0; i < n; i++) {
				String s = scanner.next();
				for (int j = 0; j < n; j++) {
					orig[i][j] = s.charAt(j);
					mirror[n - 1 - i][j] = orig[i][j];
				}
				s = scanner.next();
				for (int j = 0; j < n; j++) {
					trans[i][j] = s.charAt(j);
				}
			}
			if (checkEqual(orig, trans, n)) {
				System.out.println("Pattern " + temp + " was preserved.");
			} else if (check90(orig, trans, n)) {
				System.out.println("Pattern " + temp + " was rotated 90 degrees.");
			} else if (check180(orig, trans, n)) {
				System.out.println("Pattern " + temp + " was rotated 180 degrees.");
			} else if (check270(orig, trans, n)) {
				System.out.println("Pattern " + temp + " was rotated 270 degrees.");
			} else if (checkEqual(mirror, trans, n)) {
				System.out.println("Pattern " + temp + " was reflected vertically.");
			} else if (check90(mirror, trans, n)) {
				System.out.println("Pattern " + temp + " was reflected vertically and rotated 90 degrees.");
			} else if (check180(mirror, trans, n)) {
				System.out.println("Pattern " + temp + " was reflected vertically and rotated 180 degrees.");
			} else if (check270(mirror, trans, n)) {
				System.out.println("Pattern " + temp + " was reflected vertically and rotated 270 degrees.");
			} else {
				System.out.println("Pattern " + temp + " was improperly transformed.");
			}
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static boolean checkEqual(char[][] orig, char[][] trans, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (orig[i][j] == trans[i][j])
					continue;
				return false;
			}
		}
		return true;
	}

	public static boolean check90(char[][] orig, char[][] trans, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (orig[n - 1 - j][i] == trans[i][j])
					continue;
				return false;
			}
		}
		return true;
	}

	public static boolean check180(char[][] orig, char[][] trans, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (orig[n - 1 - i][n - 1 - j] == trans[i][j])
					continue;
				return false;
			}
		}
		return true;
	}

	public static boolean check270(char[][] orig, char[][] trans, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (orig[j][n - 1 - i] == trans[i][j])
					continue;
				return false;
			}
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
