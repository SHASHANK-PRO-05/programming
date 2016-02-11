package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class MissingAlphabets {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		while (t-- != 0) {
			char[] alpha = readString().toCharArray();
			int[] alphabetsScore = new int[26];
			for (int i = 0; i < alpha.length; i++) {
				alphabetsScore[alpha[i] - 'a'] = i;
			}
			int m = readInt();
			Sorted[] arr = new Sorted[m];
			for (int i = 0; i < m; i++) {
				arr[i] = new Sorted();
				arr[i].alphabets = alphabetsScore;
				arr[i].string = readString().toCharArray();
			}
			Arrays.sort(arr);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < arr[i].string.length; j++) {
					builder.append(arr[i].string[j]);
				}
				builder.append("\n");
			}
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

class Sorted implements Comparable<Sorted> {
	int[] alphabets;
	char[] string;

	@Override
	public int compareTo(Sorted o) {
		int len = Math.min(this.string.length, o.string.length);
		int i = 0;
		for (i = 0; i < len; i++) {
			if (this.alphabets[this.string[i] - 'a'] > o.alphabets[o.string[i] - 'a']) {
				return 1;
			} else if (this.alphabets[this.string[i] - 'a'] < o.alphabets[o.string[i] - 'a']) {
				return -1;
			}
		}
		if (this.string.length > o.string.length) {
			return 1;
		} else if (this.string.length < o.string.length) {
			return -1;
		}
		return 0;
	}

}