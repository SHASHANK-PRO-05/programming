package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.Collections;
import java.util.InputMismatchException;

public class p146 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		String s = readString();
		while (s.compareTo("#") != 0) {
			char[] ch = s.toCharArray();
			builder.append(next_permutation(ch) + "\n");
			s = readString();
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static String next_permutation(char[] ch) {
		StringBuilder string = new StringBuilder("");
		int first = find(ch);
		if (first == -1) {
			return "No Successor";
		}
		int last = ch.length - 1;
		while (ch[first] >= ch[last])
			last--;
		swap(ch, first++, last);
		last = ch.length - 1;
		while (first < last) {
			swap(ch, first++, last--);
		}
		for (int i = 0; i < ch.length; i++) {
			string.append(ch[i]);
		}
		return string.toString();
	}

	private static void swap(char[] ch, int first, int last) {
		char temp = ch[first];
		ch[first] = ch[last];
		ch[last] = temp;
	}

	public static int find(char[] ch) {
		int i = ch.length - 2;
		for (; i >= 0; i--) {
			if (ch[i] < ch[i + 1])
				return i;
		}
		return -1;
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
