package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

import javax.swing.plaf.synth.SynthStyle;

public class MehtaAndSubarrays {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		int n = readInt();
		Point[] points = new Point[n + 1];
		points[0] = new Point();
		points[0].sum = 0;
		points[0].index = 1;
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			long x = readLong();
			sum += x;
			points[i] = new Point();
			points[i].sum = sum;
			points[i].index = i + 1;
		}
		Arrays.sort(points);
		long ans = 0, ansNum = 0;
		long mn = points[0].index;
		for (int i = 1; i <= n; i++) {
			long val = Math.max(0, points[i].index - mn);
			if (val > ans) {
				ans = val;
				ansNum = 1;
			} else if (val == ans) {
				ansNum++;
			}
			mn = Math.min(mn, points[i].index);
		}
		if (ans == 0)
			out.print("-1");
		else
			out.print(ans + " " + ansNum);
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

class Point implements Comparable<Point> {
	long sum;
	long index;

	@Override
	public int compareTo(Point o) {
		if (this.sum > o.sum) {
			return 1;
		} else if (this.sum < o.sum) {
			return -1;
		} else {
			if (this.index > o.index)
				return 1;
			else if (this.index < o.index) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
