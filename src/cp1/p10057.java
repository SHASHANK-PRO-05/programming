package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class p10057 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		while ((input = bufferedReader.readLine()) != null && !input.isEmpty()) {
			int n = Integer.parseInt(input);
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(bufferedReader.readLine());
			}
			Arrays.sort(arr);
			int median = -1;
			if (n % 2 != 0) {
				median = n / 2;
				builder.append(arr[median] + " "
						+ (upperBound(arr, 0, n - 1, arr[median]) - lowerBound(arr, 0, n - 1, arr[median]) + 1)
						+ " 1\n");
			} else {
				if (arr[n / 2] == arr[(n - 1) / 2]) {
					median = (n - 1) / 2;
					builder.append(arr[median] + " "
							+ (upperBound(arr, 0, n - 1, arr[median]) - lowerBound(arr, 0, n - 1, arr[median]) + 1)
							+ " 1\n");
				} else {
					median = (n - 1) / 2;
					int temp = upperBound(arr, 0, n - 1, arr[median]) - lowerBound(arr, 0, n - 1, arr[median]) + 1;
					temp += upperBound(arr, 0, n - 1, arr[median + 1]) - lowerBound(arr, 0, n - 1, arr[median + 1]) + 1;
					builder.append(arr[median] + " " + temp + " " + (arr[median + 1] - arr[median] + 1) + "\n");
				}

			}
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int lowerBound(int[] arr, int start, int end, int num) {
		int ans = -1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] >= num) {
				ans = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return ans;
	}

	public static int upperBound(int[] arr, int start, int end, int num) {
		int ans = -1;
		while (start <= end) {
			int mid = (start + end) >> 1;
			if (arr[mid] <= num) {
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
