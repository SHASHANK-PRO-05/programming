package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Chanduandchandnissecretchat {
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
			char[] input = readString().toCharArray();
			int n = readInt() - 1;
			swift[] sw = new swift[input.length];
			char[] arr = new char[input.length];
			for (int i = 0; i < input.length; i++) {
				sw[i] = new swift();
				sw[i].ch = input[i];
				sw[i].index = i;
			}
			Arrays.sort(sw);
			arr[0] = input[n];
			if (input.length > 1) {
				arr[1] = sw[n].ch;
				int i = 2;
				int j = sw[n].index;
				while (i < input.length) {
					arr[i++] = sw[j].ch;
					j = sw[j].index;
				}
			}
			for (int k = 0; k < arr.length; k++) {
				builder.append(arr[k]);
			}
			builder.append("\n");
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

class swift implements Comparable<swift> {
	char ch;
	int index;

	@Override
	public int compareTo(swift o) {
		if (this.ch > o.ch) {
			return -1;
		} else if (this.ch < o.ch) {
			return 1;
		} else {
			if (o.index > this.index)
				return -1;
			else
				return 1;
		}
	}
}