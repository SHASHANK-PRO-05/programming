package part7;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class KingdomOfMonkeys {
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
			int n = readInt(), m = readInt();
			int[] x = new int[m];
			int[] y = new int[m];
			int[] parent = new int[n];
			long[] rank = new long[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
			for (int i = 0; i < m; i++) {
				x[i] = readInt() - 1;
				y[i] = readInt() - 1;
			}
			for (int i = 0; i < n; i++)
				rank[i] = readLong();
			for (int i = 0; i < m; i++) {
				union(x[i], y[i], parent, rank);
			}
			long res = 0;
			for (int i = 0; i < n; i++) {
				if(res<rank[i])
				res = rank[i];
			}
			builder.append(res + "\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	public static void union(int a, int b, int[] parent, long[] rank) {
		int para = getParent(a, parent);
		int parb = getParent(b, parent);
		if (para == parb)
			return;
		if (rank[para] > rank[parb]) {
			parent[parb] = para;
			rank[para] += rank[parb];
		} else {
			parent[para] = parb;
			rank[parb] += rank[para];
		}
	}

	public static int getParent(int a, int[] parent) {
		if (parent[a] == a)
			return a;
		return getParent(parent[a], parent);
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
