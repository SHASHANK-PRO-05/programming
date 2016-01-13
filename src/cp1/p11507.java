package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class p11507 {
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
		StringTokenizer st;
		while (n != 0) {
			int initial = 1;
			for (int i = 0; i < n - 1; i++)
				initial = findState(initial, findInt(readString()));
			builder.append(findString(initial) + "\n");
			n = readInt();
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int findInt(String s) {
		switch (s) {
		case "+z":
			return 3;
		case "-z":
			return -3;
		case "-y":
			return -2;
		case "+y":
			return 2;
		default:
			return 0;
		}
	}

	public static String findString(int n) {
		switch (n) {
		case -1:
			return "-x";
		case -2:
			return "-y";
		case -3:
			return "-z";
		case 1:
			return "+x";
		case 2:
			return "+y";
		default:
			return "+z";
		}
	}

	public static int findState(int initial, int rotate) {
		switch (initial) {
		case 1:
			if (rotate == 2)
				initial = 2;
			else if (rotate == -2)
				initial = -2;
			else if (rotate == 3)
				initial = 3;
			else if (rotate == -3)
				initial = -3;
			break;
		case -1:
			if (rotate == 2)
				initial = -2;
			else if (rotate == -2)
				initial = 2;
			else if (rotate == 3)
				initial = -3;
			else if (rotate == -3)
				initial = 3;
			break;
		case 2:
			if (rotate == 2)
				initial = -1;
			else if (rotate == -2)
				initial = 1;
			else if (rotate == 3)
				initial = 2;
			else if (rotate == -3)
				initial = 2;
			break;
		case -2:
			if (rotate == 2)
				initial = 1;
			else if (rotate == -2)
				initial = -1;
			else if (rotate == 3)
				initial = -2;
			else if (rotate == -3)
				initial = -2;
			break;
		case -3:
			if (rotate == 2)
				initial = -3;
			else if (rotate == -2)
				initial = -3;
			else if (rotate == 3)
				initial = 1;
			else if (rotate == -3)
				initial = -1;
			break;
		case 3:
			if (rotate == 2)
				initial = 3;
			else if (rotate == -2)
				initial = 3;
			else if (rotate == 3)
				initial = -1;
			else if (rotate == -3)
				initial = 1;
			break;
		}
		return initial;
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
