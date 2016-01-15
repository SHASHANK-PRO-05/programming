package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class p755 {
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
			int n = readInt();
			Map<String, Integer> map = new TreeMap<>();
			boolean foundExtra = false;
			while (n-- != 0) {
				String s = mapIt(readString());
				// System.out.println(s);
				if (map.containsKey(s)) {
					foundExtra = true;
					map.put(s, map.get(s) + 1);
				} else {
					map.put(s, 1);
				}
			}
			// System.out.println(map.size());
			if (foundExtra) {
				for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
					String s = it.next();
					if (map.get(s) > 1) {
						builder.append(s + " " + map.get(s) + "\n");
					}
				}
			} else {
				builder.append("No duplicates.\n");
			}
			if (t != 0)
				builder.append("\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static String mapIt(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'A':
			case 'B':
			case 'C':
				builder.append("2");
				break;
			case 'D':
			case 'E':
			case 'F':
				builder.append("3");
				break;
			case 'G':
			case 'H':
			case 'I':
				builder.append("4");
				break;
			case 'J':
			case 'K':
			case 'L':
				builder.append("5");
				break;
			case 'M':
			case 'N':
			case 'O':
				builder.append("6");
				break;
			case 'P':
			case 'R':
			case 'S':
				builder.append("7");
				break;
			case 'T':
			case 'U':
			case 'V':
				builder.append("8");
				break;
			case 'W':
			case 'X':
			case 'Y':
				builder.append("9");
				break;
			case 'Q':
			case 'Z':
			case '-':
				break;
			default:
				builder.append(s.charAt(i));
				break;
			}
			if (builder.length() == 3)
				builder.append("-");
		}
		return builder.toString();
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
