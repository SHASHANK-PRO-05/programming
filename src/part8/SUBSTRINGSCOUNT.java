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

public class SUBSTRINGSCOUNT {
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
		Map<String, Set<Integer>> map = new HashMap<>();
		for (int k = 0; k < n; k++) {
			StringBuilder builder2 = new StringBuilder(readString());
			for (int i = 0; i < builder2.length(); i++) {
				StringBuilder builder3 = new StringBuilder("");
				for (int j = i; j < builder2.length(); j++) {
					builder3.append(builder2.charAt(j) + "");
					if (map.containsKey(builder3.toString())) {
						map.get(builder3.toString()).add(k);

					} else {
						Set<Integer> integers = new TreeSet<>();
						integers.add(k);
						map.put(builder3.toString(), integers);

					}
				}
			}
		}

		int q = readInt();
		while (q-- != 0) {
			int l = readInt() - 1, r = readInt() - 1;
			if (l > r) {
				int temp = l;
				l = r;
				r = temp;
			}
			StringBuilder builder2 = new StringBuilder(readString());
			ArrayList<Integer> arrayList = new ArrayList<>();
			if (!map.containsKey(builder2.toString()))
				builder.append("0\n");
			else {
				arrayList.addAll(map.get(builder2.toString()));
				builder.append(lowerBound(arrayList, r)-upperBound(arrayList, l)+1+"\n");
			}

		}
		out.println(builder);
		out.flush();
		out.close();
	}

	public static int lowerBound(ArrayList<Integer> arrayList, int k) {
		int res = -1;
		int l = 0, r = arrayList.size() - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (arrayList.get(mid) <= k) {
				res = mid;
				l = mid + 1;
			} else {
				r = r - 1;
			}
		}
		return res;
	}

	public static int upperBound(ArrayList<Integer> arrayList, int k) {
		int res = arrayList.size();
		int l = 0, r = arrayList.size() - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (arrayList.get(mid) < k) {
				l = mid + 1;
			} else {
				res = mid;
				r = r - 1;
			}
		}
		return res;
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
