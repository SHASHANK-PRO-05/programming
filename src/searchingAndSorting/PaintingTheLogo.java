package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class PaintingTheLogo {
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
			long n = readLong();
			long start = 1, end = (long)Math.sqrt(n);
			long evenAns = 0, oddAns = 0;
			long sumAns = 0;
			while (start <= end) {
				long mid = (start + end) / 2;
				if (mid % 2 == 0) {
					long even = evenSum(mid / 2);
					long odd1 = oddSum((mid + 2) / 2);
					long odd2 = oddSum(mid / 2);
					if (even + odd1 <= n && (even + odd1) > sumAns) {
						evenAns = mid;
						oddAns = mid + 1;
						sumAns = even + odd1;
					} else if (even + odd2 <= n && (even + odd2) > sumAns) {
						evenAns = mid;
						oddAns = mid - 1;
						sumAns = even + odd2;
					}
					if (even + odd2 <= n || even + odd1 <= n) {
						start = mid + 1;
					} else {
						end = mid - 1;
					}
				} else {
					long odd = oddSum((mid + 1) / 2);
					long even1 = evenSum((mid + 1) / 2);
					long even2 = evenSum((mid - 1) / 2);
					if (even1 + odd <= n && (even1 + odd) > sumAns) {
						evenAns = mid + 1;
						oddAns = mid;
						sumAns = even1 + odd;
					} else if (even2 + odd <= n && (even2 + odd) > sumAns) {
						evenAns = mid - 1;
						oddAns = mid;
						sumAns = even2 + odd;
					}
					if (even1 + odd <= n || even2 + odd <= n) {
						start = mid + 1;
					} else {
						end = mid - 1;
					}
				}
			}
			if (evenAns == 0 || oddAns == 0) {
				builder.append("0 0\n");
			} else {
				builder.append(oddAns + " " + evenAns + "\n");
			}
		}
		out.print(builder);
		out.flush();
		out.close();

	}

	public static long evenSum(long term) {
		long ans = ((term) * (4 + (term - 1) * 2)) / 2;
		if (ans <= 0)
			return Long.MAX_VALUE;
		return 4 * ans;
	}

	public static long oddSum(long term) {
		long ans = ((term) * (2 + (term - 1) * 2)) / 2;
		if (ans <= 0)
			return Long.MAX_VALUE;
		return 4 * ans;
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
