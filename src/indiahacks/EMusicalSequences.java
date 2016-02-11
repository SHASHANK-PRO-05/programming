package indiahacks;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class EMusicalSequences {
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
			long m = readLong();
			long z = readLong();
			int arrSize = 2 * (n + 1);
			long[] arr = new long[arrSize];
			long an = 0;
			for (int i = 0; i < n; i++) {
				long temp = readLong();
				if ((n - i) % 2 == 0) {
					an = (an - temp + m) % m;
				} else {
					an = (an + temp) % m;
				}
				arr[i] = temp;
				arr[n + 1 + i] = (-1 * temp + m) % m;
			}
			arr[n] = an;
			arr[arrSize - 1] = (-1 * an + m) % m;
			long ans = (z % arrSize);
			builder.append(arr[(int) ans] + "\n");
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

class Subtractor {
	long prev;
	long preprev;
	long prepreprev;
	int temp = 0;

	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Subtractor o = (Subtractor) object;
			return o.preprev == this.preprev && o.prev == this.prev && this.prepreprev == o.prepreprev;
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 7 * hash + temp + (int) this.preprev;
		hash = 7 * hash + temp + (int) this.prepreprev;
		hash = 7 * hash + temp + (int) this.prev;
		return hash;
	}

}