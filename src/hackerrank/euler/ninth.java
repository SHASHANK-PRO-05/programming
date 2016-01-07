package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

public class ninth {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static HashMap<Long, Long> map = new HashMap<>();

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		for (int i = 3000; i >= 1; i--) {
			for (int j = 3000; j >= 1; j--) {
				long temp = (i * i) + (j * j);
				long c = (long) Math.sqrt(temp);
				if (c * c == temp) {
					long ans = i * j * c;
					long check = i + j + c;
					if (map.containsKey(check)) {
						ans = Math.max(ans, map.get(check));
					}
					map.put(check, ans);
				}
			}
		}
		int t = readInt();
		while (t-- != 0) {
			long temp = readInt();
			if (map.containsKey(temp))
				builder.append(map.get(temp) + "\n");
			else
				builder.append("-1\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void findTriplet(long sum) {
		long range = (long) Math.sqrt(sum);
		for (long i = 2; i < range; i++) {
			if ((sum / 2) % i == 0) {
				long k;
				k = i + 1;
				while (k <= 2 * i && k <= sum / (2 * i)) {
					if (sum / (2 * i) % k == 0 && gcd(i, k) == 1) {
						long d = sum / 2 / (k * i);
						long n = k - i;
						long a = d * (i * i - n * n);
						long b = d * 2 * i * n;
						long c = (i * i + n * n) * d;
						long ans = a * b * c;
						if (map.containsKey(a + b + c))
							ans = Math.max(ans, map.get(a + b + c));
						map.put(a + b + c, ans);
					}
					k = k + 2;
				}
			}
		}
	}

	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
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
