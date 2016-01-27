package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class CountingTriangles {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt();
		Comp compi = new Comp();
		int sub = 0;
		Set<Comp> se = new TreeSet<>(compi);
		Set<Comp> map = new TreeSet<>(compi);
		for (int i = 0; i < n; i++) {
			long[] arr = new long[3];
			arr[0] = readLong();
			arr[1] = readLong();
			arr[2] = readLong();
			Arrays.sort(arr);
			Comp c1 = new Comp();
			c1.a = arr[0];
			c1.b = arr[1];
			c1.c = arr[2];
			if (se.contains(c1) && !map.contains(c1)) {
				sub++;
				map.add(c1);
			} else if (!se.contains(c1)) {
				se.add(c1);
			}
		}

		out.print(se.size() - sub);
		out.flush();
		out.close();
	}

	public static boolean findKey(Vector<Integer> temp, int find) {
		int start = 0;
		int end = temp.size() - 1;
		boolean result = false;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (temp.get(mid) == find) {
				result = true;
				break;
			} else if (temp.get(mid) < find) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return result;
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

class Comp implements Comparator<Comp> {
	long a;
	long b;
	long c;

	@Override
	public int compare(Comp o1, Comp o2) {
		if (o1.a < o2.a) {
			return -1;
		} else if (o1.a > o2.a) {
			return 1;
		} else {
			if (o1.b < o2.b) {
				return -1;
			} else if (o1.b > o2.b) {
				return 1;
			} else {
				if (o1.c < o2.c) {
					return -1;
				} else if (o1.c > o2.c) {
					return 1;
				}
			}
		}
		return 0;

	}

}