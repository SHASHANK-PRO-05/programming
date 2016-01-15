package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class p10260 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			System.out.println(findAns(sc.nextLine()));
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static String findAns(String s) {
		StringBuilder builder = new StringBuilder("");
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'B':
			case 'F':
			case 'P':
			case 'V':
				if (builder.length() == 0)
					builder.append("1");
				else if (builder.charAt(builder.length() - 1) != '1') {
					builder.append("1");
				}
				break;
			case 'C':
			case 'G':
			case 'J':
			case 'K':
			case 'Q':
			case 'S':
			case 'Z':
			case 'X':
				if (builder.length() == 0)
					builder.append("2");
				else if (builder.charAt(builder.length() - 1) != '2') {
					builder.append("2");
				}
				break;
			case 'D':
			case 'T':
				if (builder.length() == 0)
					builder.append("3");
				else if (builder.charAt(builder.length() - 1) != '3') {
					builder.append("3");
				}
				break;
			case 'M':
			case 'N':
				if (builder.length() == 0)
					builder.append("5");
				else if (builder.charAt(builder.length() - 1) != '5') {
					builder.append("5");
				}
				break;
			case 'R':
				if (builder.length() == 0)
					builder.append("6");
				else if (builder.charAt(builder.length() - 1) != '6') {
					builder.append("6");
				}
				break;
			case 'L':
				if (builder.length() == 0)
					builder.append("4");
				else if (builder.charAt(builder.length() - 1) != '4') {
					builder.append("4");
				}
				break;
			default:
				builder.append(" ");
			}
		}
		return builder.toString().replace(" ", "");
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
