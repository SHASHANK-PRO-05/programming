package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p10703 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int w = readInt(), h = readInt(), sub = readInt();
		while (w != 0) {
			boolean[][] arr = new boolean[w + 1][h + 1];
			long ans = w * h;
			while (sub-- != 0) {
				int x1 = readInt();
				int y1 = readInt();
				int x2 = readInt();
				int y2 = readInt();
				if (x1 > x2) {
					int temp = x1;
					x1 = x2;
					x2 = temp;
					temp = y1;
					y1 = y2;
					y2 = temp;
				}
				for (int i = x1; i <= x2; i++) {
					if (y1 <= y2) {
						for (int j = y1; j <= y2; j++) {
							if (arr[i][j] == false) {
								arr[i][j] = true;
								ans--;
							}
						}
					} else {
						for (int j = y1; j >= y2; j--) {
							if (arr[i][j] == false) {
								arr[i][j] = true;
								ans--;
							}
						}
					}
				}
			}
			if (ans == 0) {
				builder.append("There is no empty spots.\n");
			} else if (ans == 1) {
				builder.append("There is one empty spot.\n");
			} else {
				builder.append("There are " + ans + " empty spots.\n");
			}
			w = readInt();
			h = readInt();
			sub = readInt();
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
