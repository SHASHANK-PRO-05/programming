package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class p400 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		String hyphen = "------------------------------------------------------------\n";
		while (num != null && !num.isEmpty()) {
			int n = Integer.parseInt(num);
			int max = Integer.MIN_VALUE;
			String[] arr = new String[n];
			for (int i = 0; i < n; i++) {
				arr[i] = br.readLine();
				if (arr[i].length() > max) {
					max = arr[i].length();
				}
			}
			Arrays.sort(arr);
			int col = 1;
			int size = 60 - max;
			while (size - max - 2 >= 0) {
				size = size - max - 2;
				col++;
			}
			int row = n / col + (n % col != 0 ? 1 : 0);
			builder.append(hyphen);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (j * row + i < n) {
						builder.append(arr[j * row + i]);
						for (int k = arr[j * row + i].length(); k < max; k++) {
							builder.append(" ");
						}
						if (j < col - 1 && (j * row + i) != n - 1) {
							builder.append("  ");
						}
					}
				}
				builder.append("\n");
			}
			num = br.readLine();
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
