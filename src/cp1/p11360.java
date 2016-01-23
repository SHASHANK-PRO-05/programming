package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p11360 {
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
		for (int k = 1; k <= t; k++) {
			int n = readInt();
			int[][] arr = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				String s = readString();
				for (int j = 1; j <= n; j++) {
					arr[i][j] = s.charAt(j - 1) - '0';
				}
			}
			int m = readInt();
			int inc = 0;
			while (m-- != 0) {
				switch (readString()) {
				case "row":
					int a = readInt();
					int b = readInt();
					for (int i = 1; i <= n; i++) {
						int temp = arr[a][i];
						arr[a][i] = arr[b][i];
						arr[b][i] = temp;
					}
					break;
				case "col":
					a = readInt();
					b = readInt();
					for (int i = 1; i <= n; i++) {
						int temp = arr[i][a];
						arr[i][a] = arr[i][b];
						arr[i][b] = temp;
					}
					break;
				case "transpose":
					for (int i = 1; i <= n; i++) {
						for (int j = i; j <= n; j++) {
							int temp = arr[i][j];
							arr[i][j] = arr[j][i];
							arr[j][i] = temp;
						}
					}
					break;
				case "inc":
					inc++;
					break;
				case "dec":
					inc--;
					break;
				}
			}
			builder.append("Case #" + k + "\n");
			if (inc != 0) {
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= n; j++) {
						arr[i][j] += inc;
						while (arr[i][j] < 0)
							arr[i][j] += 10;
						arr[i][j] %= 10;
					}
				}
			}
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (j != n)
						builder.append(arr[i][j]);
					else {
						builder.append(arr[i][j] + "\n");
					}
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
