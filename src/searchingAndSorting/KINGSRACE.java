package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class KINGSRACE {
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
			long n, k;
			n = readLong();
			k = readLong();
			long stren[] = new long[(int) n];
			long[] hurdle = new long[(int) k];
			for (int i = 0; i < n; i++) {
				stren[i] = readLong();
			}
			for (int i = 0; i < k; i++) {
				hurdle[i] = readLong();
			}
			Arrays.sort(hurdle);
			int ans = 0;
			long max = Long.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				int temp = findBsearch(hurdle, stren[i], (int) k) + 1;
				if (max < temp) {
					System.out.println(max + " " + temp);
					ans = i;
					max = temp;
				}
			}
			builder.append(ans + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int findBsearch(long[] hurdle, long strn, int k) {
		int ans = 0;
		int start = 0, end = k - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (hurdle[mid] <= strn) {
				ans = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return ans;
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
