package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class thirtytwo {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int[] arr = new int[] { 12, 52, 162, 0, 13458, 45228 };
	static int finalAns = 0;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		// while (t-- != 0) {
		//
		// }
		find(t);
		out.print(finalAns);
		out.flush();
		out.close();
	}

	public static void find(int n) {

		boolean[] tempVis = new boolean[10001];
		for (int i = 1; i <= 2000l; i++) {
			if (tempVis[i])
				continue;
			tempVis[i] = true;
			for (int j = 1; j <= 2000l; j++) {
				boolean visited[] = new boolean[n + 1];
				boolean found = true;
				int temp1 = i;
				int temp2 = j;
				long temp3 = i * j;
				while (temp1 != 0 && found) {
					int rem = temp1 % 10;
					if (rem > n || visited[rem] || rem == 0) {
						found = false;
						break;
					}
					visited[rem] = true;
					temp1 = temp1 / 10;
				}
				while (temp2 != 0 && found) {
					int rem = temp2 % 10;
					if (rem > n || visited[rem] || rem == 0) {
						found = false;
						break;
					}
					visited[rem] = true;
					temp2 = temp2 / 10;
				}
				while (temp3 != 0 && found) {
					int rem = (int) (temp3 % 10);
					if (rem > n || visited[rem] || rem == 0) {
						found = false;
						break;
					}
					visited[rem] = true;
					temp3 = temp3 / 10;
				}
				for (int k = 1; k <= n; k++) {
					if (!visited[k]) {
						found = false;
					}
				}
				if (found) {
					if (tempVis[i * j])
						continue;
					tempVis[j] = true;
					tempVis[i * j] = true;
					finalAns += (i * j);
				}

			}

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
