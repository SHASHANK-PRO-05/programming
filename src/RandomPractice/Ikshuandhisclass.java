package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Ikshuandhisclass {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long mod = 1000000007;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), k = readInt();
		int[] arr = new int[n];
		ArrayList<Integer>[] array = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
			array[i] = new ArrayList();
			array[i].add(i);
		}
		while (k-- != 0) {
			int n1 = readInt(), n2 = readInt();
			while (n1 != arr[n1]) {
				n1 = arr[n1];
			}
			while (n2 != arr[n2]) {
				n2 = arr[n2];
			}
			if (n1 < n2) {
				arr[n2] = n1;
				array[n1].addAll(array[n2]);
				array[n2] = new ArrayList<>();
			} else if (n1 > n2) {
				arr[n1] = n2;
				array[n2].addAll(array[n1]);
				array[n1] = new ArrayList<>();
			}
		}
		long ans = 1;
		for (int i = 0; i < n; i++) {
			if (array[i].size() > 1) {
				ans = (ans * factorial(array[i].size())) % mod;
			}
		}
		out.print(ans);
		out.flush();
		out.close();
	}

	public static long factorial(int n) {
		long ans = 1;
		while (n != 1) {
			ans = (ans * n) % mod;
			n--;
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
