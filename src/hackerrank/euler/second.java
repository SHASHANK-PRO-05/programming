package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Vector;

public class second {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static Vector<Long> vector = new Vector<>();
	static long ans = 2;
	static HashMap<Long, Long> map = new HashMap<>();

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int t = readInt();
		vector.add(1L);
		vector.add(2L);
		map.put(1L, 0L);
		map.put(2L, 2L);
		while (t-- != 0) {
			long n = readLong();
			add(n);
			builder.append(find(n) + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static long find(long n) {
		long res = 0;
		for (int i = vector.size() - 1; i >= 0; i--) {
			if (vector.get(i) <= n) {
				res = map.get(vector.get(i));
				break;
			}
		}
		return res;
	}

	public static void add(long n) {
		Long temp = vector.lastElement();
		while (temp < n) {
			temp = temp + vector.get(vector.size() - 2);
			if ((temp & 1) == 0) {
				ans += temp;
			}
			vector.add(temp);
			map.put(temp, ans);
		}
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
