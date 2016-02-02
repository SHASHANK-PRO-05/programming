package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class XennyandPartiallySortedStrings {
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
			int n = readInt(), k = readInt(), l = readInt();
			sortStrings[] ss = new sortStrings[n];
			for (int i = 0; i < n; i++) {
				ss[i] = new sortStrings();
				ss[i].string = readString();
				ss[i].k = l;
				ss[i].pos = i;
			}
			Arrays.sort(ss);
			builder.append(ss[k - 1].string + "\n");
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

class sortStrings implements Comparable<sortStrings> {
	String string;
	int pos;
	int k;

	@Override
	public int compareTo(sortStrings o) {
		if (this.string.substring(0, k).compareTo(o.string.substring(0, k)) > 0) {
			return 1;
		} else if (this.string.substring(0, k).compareTo(o.string.substring(0, k)) < 0)
			return -1;
		else {
			return this.k - o.k;
		}
	}

}