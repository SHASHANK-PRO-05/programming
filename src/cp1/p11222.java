package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;

public class p11222 {
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
			int[] arr = new int[10001];
			int[] temp = new int[3];
			Vector<Integer> finalArr[] = new Vector[3];
			for (int i = 0; i < 3; i++) {
				int n = readInt();
				finalArr[i] = new Vector<>();
				for (int j = 0; j < n; j++) {
					int input = readInt();
					finalArr[i].addElement(input);
					if (arr[input] != 0) {
						if (arr[input] == 1) {
							temp[0]--;
							arr[input] = 1 * 10 + (i + 1);
						} else if (arr[input] == 2) {
							temp[1]--;
							arr[input] = 23;
						} else {
							arr[input] = 123;
						}
					} else {
						arr[input] = i + 1;
						temp[i]++;
					}
				}
			}
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				max = Math.max(max, temp[i]);
			}
			builder.append("Case #" + k + ":\n");
			for (int i = 0; i < 3; i++) {
				if (temp[i] == max) {
					Collections.sort(finalArr[i]);
					builder.append(i + 1 + " " + max + " ");
					for (int j = 0; j < finalArr[i].size(); j++) {
						if (arr[finalArr[i].get(j)] == i + 1)
							builder.append(finalArr[i].get(j) + " ");
					}
					builder.replace(builder.length() - 1, builder.length(), "");
					builder.append("\n");
				}
			}
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
