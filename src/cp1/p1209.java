package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class p1209 {
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
		String string = null;
		while ((string = bufferedReader.readLine()) != null) {
			String[] arr = new String[21];
			arr[10] = string;
			for (int i = 11; i < 21; i++)
				arr[i] = nextPermutation(arr[i - 1].toCharArray());
			for (int i = 9; i >= 0; i--) {
				arr[i] = firstPermutaion(arr[i + 1].toCharArray());
			}
			Arrays.sort(arr);
			int ansMaxMin = Integer.MIN_VALUE;
			String ans = null;
			for (int i = 0; i < 21; i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 1; j < arr[i].length(); j++) {
					min = Math.min(Math.abs(arr[i].charAt(j) - arr[i].charAt(j - 1)), min);
				}
				if (ansMaxMin < min) {
					ansMaxMin = min;
					ans = arr[i];
				}
			}
			System.out.println(ans + ansMaxMin);
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static String firstPermutaion(char[] string) {
		int first = findPrev(string);
		if (first == -1) {
			return "fuck you bitch, you gave wrong inputs";
		}
		int last = string.length - 1;
		while (string[first] <= string[last])
			last--;
		swap(string, first++, last);
		last = string.length - 1;
		while (first < last) {
			swap(string, first++, last--);
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < string.length; i++) {
			builder.append((char) string[i]);
		}
		return builder.toString();
	}

	public static int findPrev(char[] string) {
		for (int i = string.length - 2; i >= 0; i--) {
			if (string[i] > string[i + 1])
				return i;
		}
		return -1;
	}

	public static String nextPermutation(char[] string) {
		int first = findNext(string);
		if (first == -1) {
			return "fuck off bitch, you gave wrong inputs";
		}
		int last = string.length - 1;
		while (string[first] >= string[last]) {
			last--;
		}
		swap(string, first++, last);
		last = string.length - 1;
		while (first < last) {
			swap(string, first++, last--);
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < string.length; i++) {
			builder.append((char) string[i]);
		}
		return builder.toString();
	}

	public static void swap(char[] string, int i, int j) {
		char temp = string[i];
		string[i] = string[j];
		string[j] = temp;
	}

	public static int findNext(char[] string) {
		for (int i = string.length - 2; i >= 0; i--) {
			if (string[i] < string[i + 1])
				return i;
		}
		return -1;
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
