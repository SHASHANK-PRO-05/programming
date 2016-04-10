
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class CoinJam {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int ans;
	static StringBuilder builder = new StringBuilder();

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter("Coin.txt");

		int t = readInt();
		for (int input = 1; input <= t; input++) {
			int n = readInt();
			int j = readInt();
			ans = 0;
			StringBuilder in = new StringBuilder("");
			builder.append("Case #" + input + ":\n");
			recur(in, n, j);
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void recur(StringBuilder b, int n, int j) {
		if (b.length() == n && b.charAt(0) == '1' && b.charAt(b.length() - 1) == '1') {
			long[] arr = new long[11];
			for (int i = 2; i < 11; i++) {
				arr[i] = powBase(b, i);
				if (isprime(arr[i]))
					return;
			}
			builder.append(b + " ");
			for (int i = 2; i < 11; i++) {
				if (i != 10)
					builder.append(ret(arr[i]) + " ");
				else
					builder.append(ret(arr[i]) + "\n");

			}
			ans++;
			return;
		} else if (b.length() == n) {
			return;
		}
		if (j != ans)
			recur(new StringBuilder(b).append("0"), n, j);
		if (j != ans)
			recur(new StringBuilder(b).append("1"), n, j);
	}

	public static boolean isprime(long num) {
		for (long i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static long ret(long num) {
		for (long i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return i;
			}
		}
		return 0;
	}

	public static long powBase(StringBuilder in, int base) {
		long retu = 0;
		for (int i = 0; i < in.length(); i++) {
			retu = retu * base + (in.charAt(i) - '0');
		}
		return retu;
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
