package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class InterestingCoins {
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
			int n = readInt();
			boolean foundIn = false;
			long[] arr = new long[n];
			arr[0] = readLong();
			for (int i = 1; i < n; i++) {
				arr[i] = readLong();
				if (arr[i] <= arr[i - 1])
					foundIn = true;
			}
			if (!foundIn) {
				builder.append("0\n");
			} else {
				int temp = 1;
				while (!checkIt(arr, temp, n)) {
					temp *= 2;
				}
				builder.append(binarySearch(temp / 2 + 1, temp, arr, n) + "\n");
			}
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int binarySearch(int lo, int high, long[] arr, int n) {
		int start = lo, end = high, mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (checkIt(arr, mid, n) && (mid == lo || !checkIt(arr, mid - 1, n))) {
				break;
			}
			if (checkIt(arr, mid, n))
				end = mid - 1;
			else if (!checkIt(arr, mid, n))
				start = mid + 1;
		}
		return mid;
	}

	public static boolean checkIt(long[] arr, long num, int n) {
		long[] A = new long[n];
		for (int i = 0; i < n; i++) {
			A[i] = arr[i];
		}
		A[0] = Math.max(1, A[0] - num);
		for (int i = 1; i < n; i++) {
			if (A[i] > A[i - 1]) {
				A[i] = Math.max(A[i - 1] + 1, A[i] - num);
			} else {
				A[i] = Math.min(A[i - 1] + 1, A[i] + num);
			}
			if (A[i] <= A[i - 1]) {
				return false;
			}
		}
		return true;
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
