package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

class ModuloCheck implements Comparable<ModuloCheck> {
	long num;
	long mod;

	@Override
	public int compareTo(ModuloCheck o) {
		if (this.num % mod > o.num % mod) {
			return 1;
		} else if (this.num % mod < o.num % mod) {
			return -1;
		} else {
			if (this.num % 2 != 0 && o.num % 2 == 0) {
				return -1;
			} else if (this.num % 2 == 0 && o.num % 2 != 0) {
				return 1;
			} else if (this.num % 2 == 0 && o.num % 2 == 0) {
				if (this.num < o.num) {
					return -1;
				} else if (this.num > o.num) {
					return 1;
				}
			} else if (this.num % 2 != 0 && o.num % 2 != 0) {
				if (this.num < o.num) {
					return 1;
				} else if (this.num > o.num) {
					return -1;
				}
			}
		}
		return 0;
	}
}

public class p11321 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), m = readInt();
		while (n != 0) {
			ModuloCheck[] moduloChecks = new ModuloCheck[n];
			for (int i = 0; i < n; i++) {
				moduloChecks[i] = new ModuloCheck();
				moduloChecks[i].num = readLong();
				moduloChecks[i].mod = m;
			}
			Arrays.sort(moduloChecks);
			builder.append(n + " " + m + "\n");
			for (int i = 0; i < n; i++) {
				builder.append(moduloChecks[i].num + "\n");
			}
			n = readInt();
			m = readInt();
		}
		builder.append("0 0\n");
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
