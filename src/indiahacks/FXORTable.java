package indiahacks;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;

public class FXORTable {
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
			int n = readInt(), m = readInt(), p = readInt(), x = readInt();
			if (n < 10) {
				builder.append(nLessThan10(n, m, p, x) + "\n");
			} else {
				n = n - 1;
				String s = readString();
				StringBuilder builder2 = new StringBuilder(s);
				StringBuilder builder3 = new StringBuilder();
				HashMap<String, Integer> set = new HashMap<>();
				ArrayList<String> arrayList = new ArrayList<>();
				int index = 0;
				while (!set.containsKey(builder2.toString())) {
					arrayList.add(builder2.toString());
					set.put(builder2.toString(), index++);
					builder3 = new StringBuilder();
					int j = m - p;
					int temp = 0;
					while (temp < m) {
						int appender = (builder2.charAt(j) - '0') ^ x ^ (builder2.charAt((j + p) % m) - '0');
						builder3.append(appender);
						j = (j + 1) % m;
						temp++;
					}
					builder2 = builder3;
				}
				int temp = set.get(builder2.toString());
				if (n < temp) {
					builder.append(arrayList.get(n) + "\n");
				} else {
					int cycle = index - temp;
					n = n - temp;
					n = n % cycle;
					builder.append(arrayList.get(temp + n) + "\n");
				}
			}
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static String nLessThan10(int n, int m, int p, int x) throws IOException {
		StringBuilder builder2 = new StringBuilder(readString());
		StringBuilder builder3 = new StringBuilder();
		for (int i = 1; i < n; i++) {
			int j = m - p;
			int temp = 0;
			while (temp < m) {
				int appender = (builder2.charAt(j) - '0') ^ x ^ (builder2.charAt((j + p) % m) - '0');
				builder3.append(appender);
				j = (j + 1) % m;
				temp++;
			}
			builder2 = builder3;
			builder3 = new StringBuilder("");
		}
		return builder2.toString();
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
