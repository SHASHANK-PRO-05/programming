package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Vector;

public class twelve {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static Vector<Long> longs = new Vector<>();
	static Map<Long, Long> map = new HashMap<Long, Long>();

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		find();
		int t = readInt();
		while (t-- != 0) {
			long n = readLong();
			long ans = 0;
			for (int i = 0; i < longs.size(); i++) {
				if (longs.get(i) > n) {
					ans = map.get(longs.get(i));
					break;
				}
			}
			builder.append(ans + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void find() {
		long temp = 0;
		long dummy = 0;
		for (int i = 1; temp <= 1000; i++) {
			dummy += i;
			long fi = findFactor(dummy);
			if (fi > temp) {
				temp = fi;
				longs.add(temp);
				map.put(temp, dummy);
			}
		}
	}

	public static int findFactor(long number) {
		if (number == 1)
			return 1;
		int temp = 1;
		int iter = 2;
		int dummy = 0;
		while (number % 2 == 0) {
			number /= 2;
			dummy++;
		}
		temp *= (dummy + 1);
		iter = 3;
		while (iter <= number) {
			dummy = 0;
			while (number % iter == 0) {
				number /= iter;
				dummy++;
			}
			temp *= (dummy + 1);
			iter = iter + 2;
		}
		return temp;
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
