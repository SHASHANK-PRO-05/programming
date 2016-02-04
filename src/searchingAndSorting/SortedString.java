package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;

public class SortedString {
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
			String s = readString();
			int[] ch = new int[26];
			int[] index = new int[26];
			for (int i = 0; i < 26; i++) {
				index[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < s.length(); i++) {
				index[s.charAt(i) - 'a'] = Math.min(index[s.charAt(i) - 'a'], i);
				ch[s.charAt(i) - 'a']++;
			}
			Vector<StringSorted> vec = new Vector<>();
			for (int i = 0; i < 26; i++) {
				if (ch[i] != 0) {
					StringSorted sorted = new StringSorted();
					sorted.ch = (char) i;
					sorted.id = index[i];
					sorted.count = ch[i];
					vec.add(sorted);
				}
			}
			Collections.sort(vec);
			for (int i = 0; i < vec.size(); i++) {
				for (int j = 0; j < vec.get(i).count; j++) {
					builder.append((char) (vec.get(i).ch + 'a'));
				}
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

class StringSorted implements Comparable<StringSorted> {
	char ch;
	int count;
	int id;

	@Override
	public int compareTo(StringSorted o) {
		if (this.count > o.count)
			return 1;
		else if (this.count < o.count)
			return -1;
		else {
			if (this.ch > o.ch)
				return 1;
			else if (this.ch < o.ch)
				return -1;
			else
				return 0;
		}
	}

}
