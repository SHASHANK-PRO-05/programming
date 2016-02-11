package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;

public class Promotion {
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
		Vector<Long> truck = new Vector<>();
		Vector<Long> weight = new Vector<>();
		for (int i = 0; i < n; i++) {
			weight.add(readLong());
		}
		for (int i = 0; i < m; i++) {
			truck.add(readLong());
		}
		Collections.sort(truck, Collections.reverseOrder());
		Collections.sort(weight, Collections.reverseOrder());
		int iter = 0;
		while (weight.size() > 0) {
			Vector<Long> tempVetor = new Vector<>();
			iter++;
			int i = 0;
			int j = 0;
			while (i < truck.size() && j < weight.size()) {
				if (weight.get(j) <= truck.get(i)) {
					i++;
				} else {
					tempVetor.add(weight.get(j));
				}
				j++;
			}
			while (j < weight.size()) {
				tempVetor.add(weight.get(j));
				j++;
			}
			weight = tempVetor;
		}
		builder.append((2 * iter - 1));
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

class Truck implements Comparable<Truck> {
	long size;
	int iter = 0;

	@Override
	public int compareTo(Truck o) {
		if (this.size > o.size) {
			return 1;
		} else if (this.size < o.size) {
			return -1;
		}
		return 0;
	}

}