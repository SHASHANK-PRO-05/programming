package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p11093 {
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
			int[][] arr = new int[n][2];
			int suma = 0;
			int sumb = 0;
			boolean[] temp = new boolean[n];
			for (int i = 0; i < n; i++) {
				arr[i][0] = readInt();
				suma += arr[i][0];

			}
			for (int i = 0; i < n; i++) {
				arr[i][1] = readInt();
				sumb += arr[i][1];
				if (arr[i][0] - arr[i][1] >= 0)
					temp[i] = true;
			}
			int ans = -1;
			if (suma >= sumb)
				for (int i = 0; i < n; i++) {
					if (temp[i]) {
						if (ans != -1)
							break;
						int tempAns = arr[i][0] - arr[i][1];
						int j = (i + 1) % n;
						// System.out.println(tempAns);
						if (tempAns < 0)
							continue;
						for (; j != i; j = (j + 1) % n) {
							tempAns = tempAns + arr[j][0] - arr[j][1];
							if (tempAns < 0)
								break;
						}
						if (j == i)
							ans = i;
					}
				}
			if (ans != -1) {
				builder.append("Case " + k + ": Possible from station " + (ans + 1) + "\n");
			} else {
				builder.append("Case " + k + ": Not possible\n");
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
