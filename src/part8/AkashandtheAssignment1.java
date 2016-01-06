package part8;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AkashandtheAssignment1 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), q = readInt();
		StringBuilder builder2 = new StringBuilder(readString());
		Map<String, Set<Character>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			StringBuilder builder3 = new StringBuilder("");
			for (int j = i; j < n; j++) {
				builder3.append(builder2.charAt(j) + "");
				if (!map.containsKey(builder3.toString())) {
					Set<Character> characters = new TreeSet<>();
					for (int k = 0; k < builder3.length(); k++) {
						characters.add(builder3.charAt(k));
					}
					map.put(builder3.toString(), characters);
				}
			}
		}
		while (q-- != 0) {
			int l = readInt() - 1, r = readInt() - 1, k = readInt();
			if (k > (r - l + 1))
				builder.append("Out of range\n");
			else {
				Set<Character> characters = map.get(builder2.toString().substring(l, r + 1));
				 ArrayList<Character> arrayList = new ArrayList<>();
				 arrayList.addAll(characters);
				 builder.append(arrayList.get(k - 1) + "\n");
			}
		}
		out.println(builder);
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
