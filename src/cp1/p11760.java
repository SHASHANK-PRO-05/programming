package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p11760 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int R = readInt(), C = readInt(), N = readInt();
		int test = 0;
		while (R != 0) {
			test++;
			builder.append("Case " + test + ": ");
			boolean[] rows = new boolean[R];
			boolean[] cols = new boolean[C];
			while (N-- != 0) {
				int r = readInt(), c = readInt();
				rows[r] = true;
				cols[c] = true;
			}
			int arifr = readInt(), arifc = readInt();
			if (!rows[arifr] && !cols[arifc]) {
				// System.out.println("here");
				builder.append("Escaped again! More 2D grid problems!\n");
			} else if (arifr > 0 && !rows[arifr - 1] && !cols[arifc]) {
				// System.out.println("here1");
				builder.append("Escaped again! More 2D grid problems!\n");
			} else if (arifc > 0 && !rows[arifr] && !cols[arifc - 1]) {
				// System.out.println("here2");
				builder.append("Escaped again! More 2D grid problems!\n");
			} else if (arifc < C - 1 && !rows[arifr] && !cols[arifc + 1]) {
				// System.out.println("here3");
				builder.append("Escaped again! More 2D grid problems!\n");
			} else if (arifr < R - 1 && !rows[arifr + 1] && !cols[arifc]) {
				// System.out.println("here4");
				builder.append("Escaped again! More 2D grid problems!\n");
			} else {
				builder.append("Party time! Let's find a restaurant!\n");
			}
			R = readInt();
			C = readInt();
			N = readInt();
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
